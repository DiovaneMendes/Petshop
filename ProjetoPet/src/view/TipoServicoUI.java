
package view;

import Negocio.TipoServicoNegocio;
import Util.Console;
import model.TipoServico;
import Negocio.NegocioException;
import java.util.InputMismatchException;
import java.util.List;
import view_menu.TipoServicoMenu;

/**
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar servico
public class TipoServicoUI {
    private TipoServicoNegocio servicoNegocio;
    
    public TipoServicoUI(){
        servicoNegocio = new TipoServicoNegocio();
    }
    
    //Executnado menu de servicos
    public void executar() {
        int opcao = -1;
        do {
            try {
                System.out.println(TipoServicoMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case TipoServicoMenu.OP_CADASTRAR:
                        cadastrarServico();
                        break;
                    case TipoServicoMenu.OP_DELETAR:
                        deletarServico();
                        break;
                    case TipoServicoMenu.OP_ATUALIZAR:
                        atualizarServico();
                        break;
                    case TipoServicoMenu.OP_LISTAR:
                        mostrarServicos();
                        break;
                    case TipoServicoMenu.OP_CONSULTAR:
                        consultarServicosPorNome();
                        break;
                    case TipoServicoMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != TipoServicoMenu.OP_SAIR);
    }
    
    private void cadastrarServico() {
        int numero = Console.scanInt("Numero servico: ");
        String nome = Console.scanString("Nome: ");
        String tipoAtendimento = Console.scanString("Tipo de Atendimento[estetico ou clinico]: ");
        double valor = Console.scanDouble("Valor: ");
        
        try {
            servicoNegocio.salvar(new TipoServico(numero, nome, tipoAtendimento, valor));
            System.out.println("Servico " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void deletarServico() {
        String nome = Console.scanString("Nome servico a ser deletado: ");
        try {
            TipoServico tipoServico = servicoNegocio.procurarPorNome(nome);
            this.mostraServico(tipoServico);
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse servico?")) {
                servicoNegocio.deletar(tipoServico);
                System.out.println("Servico deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void atualizarServico() {
        String nomeX = Console.scanString("Nome servico a ser alterado: ");
        try {
            TipoServico tipoServico = servicoNegocio.procurarPorNome(nomeX);
            this.mostraServico(tipoServico);

            System.out.println("Digite os dados do cliente que quer alterar");
            int numero = Console.scanInt("Numero[Digite zero caso nao queira]: ");
            String nome = Console.scanString("Nome[Deixe vazio caso nao queira]: ");
            String tipoAtendimento = Console.scanString("Tipo Atendimento:(estetico ou clinico)\n[Deixe vazio caso nao queira]: ");
            double valor = Console.scanDouble("Valor[Digite zero caso nao queira]: ");
            if (numero == 0) {
                tipoServico.setNumeroServico(numero);
            }
            if (!nome.isEmpty()) {
                tipoServico.setNomeServico(nome);
            }
            if (tipoAtendimento.isEmpty()) {
                tipoServico.setTipoDeAtendimento(tipoAtendimento);
            }
            if (valor == 0) {
                tipoServico.setPrecoServico(valor);
            }

            servicoNegocio.atualizar(tipoServico);
            System.out.println("Servico " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void consultarServicosPorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<TipoServico> listaServico = servicoNegocio.listarPorNome(nome);
            this.listarServicos(listaServico);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void mostraServico(TipoServico ts){
        System.out.println("Servico");
        System.out.println("Numero: " + ts.getNumeroServico());
        System.out.println("Nome: " + ts.getNomeServico());
        System.out.println("Tipo Atendimento: " +ts.getTipoDeAtendimento());
        System.out.println("Valor: " + ts.getPrecoServico());
    }
    
    public void mostrarServicos() {
        List<TipoServico> listaServicos = servicoNegocio.listar();
        this.listarServicos(listaServicos);
    }
    
    private void listarServicos(List<TipoServico> listaServicos) {
        if (listaServicos.isEmpty()) {
            System.out.println("=============================");
            System.out.println("Nao ha servicos cadastrados");
            System.out.println("=============================\n");
        } else {
            System.out.println("=============================\n");
            System.out.println(String.format("%-10s","|NUMERO") + "\t"
                + String.format("%-15s","|NOME") + "\t"
                + String.format("%-20s","|TIPO ATENDIMENTO") + "\t"
                + String.format("%-15s","|PRECO"));
            for(TipoServico ts: listaServicos){
                System.out.println(String.format("%-10s",ts.getNumeroServico()) + "\t"
                    + String.format("%-15s", "|" +ts.getNomeServico()) + "\t"
                    + String.format("%-20s", "|" +ts.getTipoDeAtendimento()) + "\t"
                    + String.format("%-15s", "|" +ts.getPrecoServico()));
            } 
        }
    }
}
