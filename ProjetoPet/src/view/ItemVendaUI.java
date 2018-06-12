package view;

import Negocio.ItemVendaNegocio;
import Negocio.NegocioException;
import Util.Console;
import dao.impl_BD.PetDaoBd;
import dao.impl_BD.TipoServicoDaoBd;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import model.ItemVenda;
import model.Pet;
import model.TipoServico;
import view_menu.ItemVendaMenu;

/**
 *
 * @author Diovane
 */
public class ItemVendaUI {    
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
                        deletarItem();
                        break;
                    case ItemVendaMenu.OP_ATUALIZAR:
                        atualizarItem();
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
    
    private void cadastrarItem(){
        listaPets();
        String pet = Console.scanString("Nome Pet: ");
        listaServicos();
        String servico = Console.scanString("Nome Servico: ");
        try {
            ivNegocio.salvar(new ItemVenda(nome, retornaIdPet(pet), retornaIdServico(servico)));
            System.out.println("Item cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    
    
    
    
    //=========================METODOS DE APOIO=================================
    private List<Pet> retornaListaPets(){
        List<Pet> listaPets = new ArrayList<>();
        PetDaoBd pet = new PetDaoBd();    
        return listaPets = pet.listar();    
    }
    
    private void listaPets(){
        System.out.println("PETS");
        for(Pet p: retornaListaPets()){
            System.out.println(p.getNomePet());
        }
    }
    
    private int retornaIdPet(String pet){
        int fkPet = -1;
        for(Pet p: retornaListaPets()){
            if(pet.equals(p.getNomePet())){
                fkPet = p.getIdPet();                
            }
        }
        return fkPet;
    }    
    
    private List<TipoServico> retornaListaServicos(){
        List<TipoServico> listaServicos = new ArrayList<>();
        TipoServicoDaoBd servico = new TipoServicoDaoBd();    
        return listaServicos = servico.listar();    
    }
    
    private void listaServicos(){
        System.out.println("SERVICOS");
        for(TipoServico ts: retornaListaServicos()){
            System.out.println(ts.getNomeServico());
        }
    }
    
    private int retornaIdServico(String servico){
        int fkServico = -1;
        for(TipoServico ts: retornaListaServicos()){
            if(servico.equals(ts.getNomeServico())){
                fkServico = ts.getId();                
            }
        }
        return fkServico;
    }
    //==========================================================================
}
