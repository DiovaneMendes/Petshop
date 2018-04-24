
package view;

import Negocio.TipoServicoNegocio;
import Repositorio.RepositorioTipoServico;
import Util.Console;
import model.TipoServico;
import negocio.NegocioException;
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
        int opcao = 0;
        do {
            System.out.println(TipoServicoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case TipoServicoMenu.OP_ADICIONAR:
                    adicionarServicos();
                    break;
                case TipoServicoMenu.OP_LISTAR:
                    listarServicos();
                    break;
                case TipoServicoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != TipoServicoMenu.OP_VOLTAR);
    }
    
    //ADICIONANDO SERVICOS
    private void adicionarServicos() {  
        int numeroServico = Console.scanInt("Numero servico: ");
        String nomeServico = Console.scanString("Nome servico: ");
        String tipoAtendimento = Console.scanString("Tipo atendimento ->(clinico ou estetico): ");
        double preco = Console.scanDouble("Preco: ");
        
        try{
            servicoNegocio.salvar(new TipoServico(numeroServico, nomeServico, tipoAtendimento, preco));
            System.out.println("Cadastro realizado com sucesso!");
        }catch (NegocioException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //LISTANDO SERVICOS
    private void listarServicos() {
        if (servicoNegocio.naoHaServicos()) {            
                System.out.println("=============================");
                System.out.println("Nao ha servicos cadastrados");
                System.out.println("=============================\n");
        }else{
            System.out.println("=============================\n");
            System.out.println(String.format("%-10s","|NUMERO") + "\t"
                + String.format("%-15s","|NOME") + "\t"
                + String.format("%-20s","|TIPO ATENDIMENTO") + "\t"
                + String.format("%-15s","|PRECO"));
            for(TipoServico ts: servicoNegocio.listar()){
                System.out.println(String.format("%-10s",ts.getNumeroServico()) + "\t"
                    + String.format("%-15s",ts.getNomeServico()) + "\t"
                    + String.format("%-20s",ts.getTipoDeAtendimento()) + "\t"
                    + String.format("%-15s",ts.getPrecoServico()));
            }        
        }
    }
}
