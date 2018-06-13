package view;

import Negocio.ItemVendaNegocio;
import Negocio.NegocioException;
import Util.Console;
import Util.MostraCoisas;
import dao.impl_BD.VendaDaoBd;
import java.util.InputMismatchException;
import model.ItemVenda;
import view_menu.ItemVendaMenu;

/**
 *
 * @author Diovane
 */
public class ItemVendaUI {
    private MostraCoisas mc = new MostraCoisas();
    private VendaDaoBd vDaoDB;    
    private ItemVendaNegocio ivNegocio;
    public ItemVendaUI(){
        ivNegocio = new ItemVendaNegocio();
    }
    
    public void executar() {
        int opcao = -1;
        do {
            try {
                System.out.println(ItemVendaMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case ItemVendaMenu.OP_CADASTRAR:
                        cadastrarItem();
                        break;
                    case ItemVendaMenu.OP_DELETAR:
                        //deletarItem();
                        break;
                    case ItemVendaMenu.OP_ATUALIZAR:
                        //atualizarItem();
                        break;
                    case ItemVendaMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != ItemVendaMenu.OP_SAIR);
    }
    
    public double cadastrarItem(){
        double valorTotal;
        do{
            mc.listaPets();
            String pet = Console.scanString("Nome Pet: ");
            mc.listaServicos();
            String servico = Console.scanString("Nome Servico: ");
            int fkVenda = vDaoDB.id;
            try {
                ivNegocio.salvar(new ItemVenda(fkVenda, mc.retornaIdPet(pet), mc.retornaIdServico(servico)));
                System.out.println("Item cadastrado com sucesso!");
            } catch (NegocioException ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }
            valorTotal = mc.retornaValorItem(vDaoDB.id);
        }while(UIUtil.getConfirmacao("Deseja adicionar mais um item?"));
        return valorTotal;
    }
    
    public void deletar(int fk){
        try { 
            ItemVenda iv;
            for(ItemVenda ivList: mc.retornaListaItens()){
                if(ivList.getFkVenda() == fk){                    
                    ivNegocio.deletar(ivNegocio.procurarPorId(fk));
                }
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
}
