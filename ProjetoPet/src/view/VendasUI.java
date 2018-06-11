
package view;

import Negocio.VendaNegocio;
import Repositorio.RepositorioClientes;
import Repositorio.RepositorioPet;
import Repositorio.RepositorioVenda;
import Util.Console;
import Util.DateTimeUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import model.Cliente;
import model.Pet;
import model.TipoServico;
import model.Venda;
import Negocio.NegocioException;
import dao.impl_BD.ClienteDaoBd;
import dao.impl_BD.PetDaoBd;
import dao.impl_BD.TipoServicoDaoBd;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view_menu.VendaMenu;

/**
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar venda
public class VendasUI {
    private VendaNegocio vendaNegocio;
    
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
                        atualizarVenda();
                        break;
                    case VendaMenu.OP_LISTAR:
                        mostrarVendas();
                        break;
                    case VendaMenu.OP_CONSULTAR:
                        consultarVendasPorNome();
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
        String dataString = Console.scanString("Data e hora: ex: 19/05/2017 21:51 ");
        LocalDateTime data = DateTimeUtil.stringToDateTime(dataString);
        int opcao = 0;
        
        do(
                
            System.out.println("");
            listaPets();
            String pet = Console.scanString("Nome pet: ");

            listaServicos();
            String servico = Console.scanString("Nome servico: ");
                
            try {
                vendaNegocio.salvar(new Venda(data, retornaIdDono(cliente), retornaIdServico(servico), valorTotal));
                System.out.println("Venda efetuada com sucesso!");
            } catch (NegocioException ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }
        )while(opcao!=0);
        
        double valorTotal = 0;
        for(TipoServico ts: retornaListaServicos()){
            valorTotal += ts.getPrecoServico();
        }
        
        try {
            vendaNegocio.salvar(new Venda(data, retornaIdDono(cliente), retornaIdServico(servico), valorTotal));
            System.out.println("Venda efetuada com sucesso!");
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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //REALIZANDO UMA VENDA
    private void adicionarVendas(){        
        //INSERINDO DATA ATUAL
        String dataString = Console.scanString("Data e hora: ");
        LocalDateTime data = DateTimeUtil.stringToDateTime(dataString);
        
        //MOSTRA A LISTA DE CLIENTES
        System.out.println(String.format("%-20s","\n|LISTA DE CLIENTES:"));
        for(Cliente c: RepositorioClientes.getInstance().getClientes()){
            System.out.println(String.format("%-20s",c.getNome()));
        } 
        System.out.println("------------------------");

        //INCLUINDO NOME DE CLIENTE E VERIFICANDO SE CONSTA NA LISTA DE CLIENTES
        String nomeCliente = Console.scanString("Nome cliente: ");
        Cliente cliente = null;
        for(Cliente c: RepositorioClientes.getInstance().getClientes()){
            if(nomeCliente.equals(c.getNome())){
                cliente = c;
            }
        }
        //LISTANDO SERVICOS REALIZADOS E INCLUINDO VALOR TOTAL
        TipoServico tipoServico;
        ArrayList<TipoServico> listaServicoVenda = new ArrayList<>();
        double valorTotal = 0;
        for(Pet p: RepositorioPet.getInstance().getPets()){
            if(nomeCliente.equals(p.getDono().getNome())){
                tipoServico = p.getServicoRealizado();
                listaServicoVenda.add(tipoServico);
                valorTotal = valorTotal + tipoServico.getPrecoServico();
            }                
        }

        //INSERINDO INFORMACOES COLETADAS ACIMA PARA CRIAR UM NOVO OBJETO E DEPOIS ADICIONANDO NO ARRAY
        try{
            vendaNegocio.salvar(new Venda (data, cliente, listaServicoVenda, valorTotal));
            System.out.println("Venda concluida!");
        }catch (DateTimeParseException ex) {
            System.out.println("Formato de Data inválido!");
        } catch(NegocioException ne){
            System.err.println(ne.getMessage());
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
