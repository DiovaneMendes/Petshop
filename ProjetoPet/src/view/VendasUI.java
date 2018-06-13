
package view;

import Negocio.VendaNegocio;
import Repositorio.RepositorioVenda;
import Util.Console;
import Util.DateTimeUtil;
import java.time.LocalDateTime;
import model.TipoServico;
import model.Venda;
import Negocio.NegocioException;
import Util.MostraCoisas;
import dao.impl_BD.VendaDaoBd;
import java.util.InputMismatchException;
import view_menu.VendaMenu;

/**
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar venda
public class VendasUI {
    private MostraCoisas mc = new MostraCoisas();
    private VendaNegocio vendaNegocio;
    private ItemVendaUI ivUI = new ItemVendaUI();
    private VendaDaoBd vDaoDB = new VendaDaoBd();
    
    public VendasUI() {
        vendaNegocio = new VendaNegocio();
    }
    
    //Executnado menu de vendas
    public void executar() {
        int opcao = -1;
        do {
            try {
                System.out.println(VendaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case VendaMenu.OP_CADASTRAR:
                        efetuarVenda();
                        break;
                    case VendaMenu.OP_DELETAR:
                        deletarVenda();
                        break;
                    case VendaMenu.OP_ATUALIZAR:
                        //atualizarVenda();
                        break;
                    case VendaMenu.OP_LISTAR:
                        //mostrarVendas();
                        break;
                    case VendaMenu.OP_CONSULTAR:
                        //consultarVendasPorNome();
                        break;
                    case VendaMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != VendaMenu.OP_SAIR);
    }
    
    private void efetuarVenda(){
        String dataString = Console.scanString("Data e hora: (ex: 01/01/1999 23:59) ");
        LocalDateTime data = DateTimeUtil.stringToDateTime(dataString);
               
        double valorTotal = ivUI.cadastrarItem();
        
        try {
            vendaNegocio.salvar(new Venda(data,  valorTotal));
            System.out.println("Venda efetuada com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void deletarVenda(){
        mc.retornaListaItens();
        int id = Console.scanInt("Id da venda a ser deletado: ");
        try {            
            Venda venda = vendaNegocio.procurarPorId(id);
            
            if(UIUtil.getConfirmacao("Realmente deseja excluir essa venda?")){
                ivUI.deletar(id);
                vendaNegocio.deletar(venda);
                System.out.println("Venda deletada com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    //LISTANDO VENDAS REALIZADAS
    private void listarVendas(){
        if (vendaNegocio.naoHaVendas()) {
            System.out.println("=============================");
            System.out.println("  Nao ha vendas realizadas");
            System.out.println("=============================\n");
        }else{
            System.out.println("=============================\n");
            System.out.println(String.format("%-20s","|DATA E HORA") + "\t"
                + String.format("%-15s","|NOME CLIENTE") + "\t"
                + String.format("%-20s","|SERVICOS REALIZADOS") + "\t"
                + String.format("%-15s","|VALOR TOTAL"));
            for(Venda vs: RepositorioVenda.getInstance().getVendas()){
                System.out.println(String.format("%-20s", "|" + vs.getDataEHora().format(DateTimeUtil.formatadorDataHora)) + "\t"
                    + String.format("%-15s", "|" + vs.getCliente().getNome()) + "\t"
                    + String.format("%-20s", "|" + vs.getListaServico()) + "\t"
                    + String.format("%-15s", "|" + vs.getValorTotal()));
            }
        }        
    } 
}
