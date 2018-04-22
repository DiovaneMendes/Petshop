
package view;

import Util.Console;
import model.Cliente;
import negocio.ClienteNegocio;
import negocio.NegocioException;
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
        int opcao = 0;
        do {
            System.out.println(ClienteMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case ClienteMenu.OP_ADICIONAR:
                    adicionarCliente();
                    break;
                case ClienteMenu.OP_LISTAR:
                    listarClientes();
                    break;
                case ClienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != ClienteMenu.OP_VOLTAR);
    }
    
    //Adicionando um novo cliente e dando uma ultima validação
    private void adicionarCliente() {     
        String nome = Console.scanString("Nome: ");
        String rg = Console.scanString("RG: ");
        String telefone = Console.scanString("Telefone: ");
        
        try{
            clienteNegocio.salvar(new Cliente (nome, rg, telefone));
            System.out.println("Cadastro realizado com sucesso!");
        } catch(NegocioException ne){
            System.err.println(ne.getMessage());
        }
    }
    
    //Listando clientes cadastrados
    private void listarClientes() {
        if (clienteNegocio.naoHaClientes()) {
            System.out.println("-----------------------------");
            System.out.println("Nao ha clientes cadastrados");
            System.out.println("-----------------------------\n");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-20s", "NOME") + "\t"
                    + String.format("%-10s", "|RG") + "\t"
                    + String.format("%-10s", "|TELEFONE"));
            for (Cliente cliente : clienteNegocio.listar()) {
                System.out.println(String.format("%-20s", cliente.getNome()) + "\t"
                        + String.format("%-10s", "|" + cliente.getRg()) + "\t"
                        + String.format("%-10s", "|" + cliente.getTelefone()));
            }
        }
    }
}
