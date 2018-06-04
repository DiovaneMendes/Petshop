
package view;

import Util.Console;
import model.Cliente;
import Negocio.ClienteNegocio;
import Negocio.NegocioException;
import java.util.InputMismatchException;
import java.util.List;
import view_menu.ClienteMenu;

/**
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar cliente
public class ClienteUI {
    private ClienteNegocio clienteNegocio;
    
    public ClienteUI() {
        clienteNegocio = new ClienteNegocio();
    }
    
    //Executnado menu de cliente
    public void executar() {
        int opcao = -1;
        do {
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        deletarCliente();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        mostrarClientes();
                        break;
                    case ClienteMenu.OP_CONSULTAR:
                        consultarClientesPorNome();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ClienteMenu.OP_SAIR);
    }
    
    private void cadastrarCliente() {
        String nome = Console.scanString("Nome: ");
        long rg = Console.scanLong("RG: ");
        long telefone = Console.scanLong("Telefone: ");
        try {
            clienteNegocio.salvar(new Cliente(nome, rg, telefone));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    public void mostrarClientes() {
        List<Cliente> listaClientes = clienteNegocio.listar();
        this.listarClientes(listaClientes);
    }
    
    private void deletarCliente() {
        long rg = Console.scanLong("RG do cliente a ser deletado: ");
        try {
            Cliente cliente = clienteNegocio.procurarPorRg(rg);
            this.mostraCliente(cliente);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                clienteNegocio.deletar(cliente);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void atualizarCliente() {
        long rg = Console.scanLong("RG do paciente a ser alterado: ");
        try {
            Cliente cliente = clienteNegocio.procurarPorRg(rg);
            this.mostraCliente(cliente);

            System.out.println("Digite os dados do cliente que quer alterar");
            String nome = Console.scanString("Nome[Deixe vazio caso não queira]: ");
            long telefone = Console.scanLong("Telefone[Digite zero caso não queira]: ");
            if (!nome.isEmpty()) {
                cliente.setNome(nome);
            }
            if (telefone != 0) {
                cliente.setTelefone(telefone);
            }

            clienteNegocio.atualizar(cliente);
            System.out.println("Cliente " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void consultarClientesPorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Cliente> listaCliente = clienteNegocio.listarPorNome(nome);
            this.listarClientes(listaCliente);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void mostraCliente(Cliente c) {
        System.out.println("Cliente");
        System.out.println("Nome: " + c.getNome());
        System.out.println("RG: " + c.getRg());
        System.out.println("Telefone: " + c.getTelefone());
    }
    
    //Listando clientes cadastrados
    private void listarClientes(List<Cliente> listaClientes) {
        if (clienteNegocio.naoHaClientes()) {
            System.out.println("=============================");
            System.out.println("Nao ha clientes cadastrados");
            System.out.println("=============================\n");
        } else {
            System.out.println("=============================\n");
            System.out.println(String.format("%-20s", "NOME") + "\t"
                    + String.format("%-10s", "|RG") + "\t"
                    + String.format("%-10s", "|TELEFONE"));
            for (Cliente cliente : listaClientes) {
                System.out.println(String.format("%-20s", cliente.getNome()) + "\t"
                        + String.format("%-10s", "|" + cliente.getRg()) + "\t"
                        + String.format("%-10s", "|" + cliente.getTelefone()));
            }
        }
    }
}
