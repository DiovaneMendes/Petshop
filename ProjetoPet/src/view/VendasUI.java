
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
import model.VendaServico;
import Negocio.NegocioException;
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
        int opcao = 0;
        do {
            System.out.println(VendaMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case VendaMenu.OP_ADICIONAR:
                    adicionarVendas();
                    break;
                case VendaMenu.OP_LISTAR:
                    listarVendas();
                    break;
                case VendaMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != VendaMenu.OP_VOLTAR);
    }
    
    //REALIZANDO UMA VENDA
    private void adicionarVendas(){        
        //INSERINDO DATA ATUAL
        String data = Console.scanString("Data e hora: ");

        //MOSTRA A LISTA DE CLIENTES
        System.out.println(String.format("%-20s","\n|LISTA DE CLIENTES:"));
        for(Cliente c: RepositorioClientes.getInstance().getClientes()){
            System.out.println(String.format("%-20s",c.getNome()));
        }

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
            vendaNegocio.salvar(new VendaServico (DateTimeUtil.stringToDateTime(data), cliente, listaServicoVenda, valorTotal));
            System.out.println("Venda concluida!");
        }catch (DateTimeParseException ex) {
            System.out.println("Formato de Data inválido!");
        } catch (NegocioException ex) {
            Logger.getLogger(VendasUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //LISTANDO VENDAS REALIZADAS
    private void listarVendas(){
        if (vendaNegocio.naoHaVendas()) {
            System.out.println("=============================");
            System.out.println("Nao ha vendas realizadas");
            System.out.println("=============================\n");
        }else{
            System.out.println("=============================\n");
            System.out.println(String.format("%-20s","|DATA E HORA") + "\t"
                + String.format("%-15s","|NOME CLIENTE") + "\t"
                + String.format("%-25s","|SERVICOS REALIZADOS") + "\t"
                + String.format("%-15s","|VALOR TOTAL"));
            for(VendaServico vs: RepositorioVenda.getInstance().getVendas()){
                System.out.print(String.format("%-20s",vs.getDataEHora().format(DateTimeUtil.formatadorData)));
                System.out.print(String.format("%-15s",vs.getCliente().getNome()));
                System.out.print(String.format("%-25s",vs.getListaServico()));
                System.out.println(String.format("%-15s",vs.getValorTotal()));
            }
        }        
    } 
}
