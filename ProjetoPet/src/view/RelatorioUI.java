
package view;


import Util.Console;
import Relatorio.MaisGastam;
import Relatorio.VendasPorMes;
import Relatorio.MaisFrequente;
import Relatorio.PetGeraMaisLucro;
import Relatorio.ServicoMaisVendido;
import Repositorio.RepositorioPet;
import Repositorio.RepositorioVenda;
import Repositorio.RepositorioClientes;
import java.util.ArrayList;
import java.util.Collections;
import model.Pet;
import model.Cliente;
import model.TipoServico;
import model.VendaServico;
import view_menu.RelatorioMenu;

/**
 *
 * @author Diovane
 */

public class RelatorioUI {
    
    public RelatorioUI(){
        
    }
    
    public void executar() {
        int opcao = 0;
        do {
            System.out.println(RelatorioMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case RelatorioMenu.OP_CLIENTE_MAIS_FREQUENTE:
                    listaMaisFrequente();
                    break;
                case RelatorioMenu.OP_MAIS_GASTAM:
                    listaMaisGastam();
                    break;
                case RelatorioMenu.OP_PET_GERA_MAIS_LUCRO:
                    listaVendaPorMes();
                    break;
                case RelatorioMenu.OP_SERVICO_MAIS_VENDIDO:
                    //listarClientes();
                    break;
                case RelatorioMenu.OP_VENDAS_POR_MES:
                    //listarClientes();
                    break;
                case RelatorioMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != RelatorioMenu.OP_VOLTAR);
    }
    
    //LISTANDO CLIENTE MAIS FREQUENTES
    private static void listaMaisFrequente(){
        
        if (RepositorioVenda.getInstance().estaVazio()) {
            System.out.println("=============================");
            System.out.println("  Nao ha vendas realizadas!");
            System.out.println("=============================\n");
        }else{
            ArrayList<MaisFrequente> listaMaisFrequente = new ArrayList<>();
            
            for(Cliente c: RepositorioClientes.getInstance().getClientes()){
                String nome = null;
                int acumulador = 0;
                for(VendaServico vs: RepositorioVenda.getInstance().getVendas()){
                    if(c.getNome().equals(vs.getCliente().getNome())){
                        if(acumulador==0){
                            nome = c.getNome();
                        }
                        acumulador++;
                    }
                }
                if(nome!=null || acumulador!=0){
                    MaisFrequente maisFrequente = new MaisFrequente(acumulador, nome);
                    listaMaisFrequente.add(maisFrequente);
                    Collections.sort(listaMaisFrequente);
                }
            }
            //MOSTRANDO A LISTA
            System.out.println("\nCLIENTES MAIS FREQUENTES:");
            System.out.print(String.format("%-15s","|QUANTIDADE"));
            System.out.println(String.format("%-15s","|NOME"));
            int i =0;
            for(MaisFrequente mf: listaMaisFrequente){
                i++;
                if(i<4){
                    System.out.print(String.format("%-15s", mf.getQuantidade()));
                    System.out.println(String.format("%-15s", mf.getCliente()));
                }
            }
        }
    }
    
    //LISTANDO CLIENTE QUE MAIS GASTAM
    private static void listaMaisGastam(){
        if (RepositorioVenda.getInstance().estaVazio()) {
            System.out.println("=============================");
            System.out.println("  Nao ha vendas realizadas!");
            System.out.println("=============================\n");
        }else{
            ArrayList<MaisGastam> listaMaisGastam = new ArrayList<>();
            
            for(Cliente c: RepositorioClientes.getInstance().getClientes()){
                String nome = null;
                double valorTotal = 0;
                for(VendaServico vs:RepositorioVenda.getInstance().getVendas()){
                    if(c.getNome().equals(vs.getCliente().getNome())){
                        if(valorTotal==0){
                            nome = c.getNome();
                        }
                        valorTotal = valorTotal + vs.getValorTotal();
                    }
                }
                if(nome!=null || valorTotal!=0){
                    MaisGastam maisGastam = new MaisGastam(nome, valorTotal);
                    listaMaisGastam.add(maisGastam);
                    Collections.sort(listaMaisGastam);
                }
            }
            //MOSTRANDO A LISTA
            System.out.println("\nCLIENTES MAIS GASTAM:");
            System.out.print(String.format("%-15s","|NOME"));
            System.out.println(String.format("%-15s","|VALOR TOTAL"));
            int i =0;
            for(MaisGastam mg: listaMaisGastam){
                i++;
                if(i<4){
                    System.out.print(String.format("%-15s", mg.getCliente()));
                    System.out.println(String.format("%-15s", mg.getValorTotal()));
                }
            }
        }
    }
    
    //LISTANDO VENDAS POR MES
    private static void listaVendaPorMes(){
        if (RepositorioVenda.getInstance().estaVazio()) {
            System.out.println("=============================");
            System.out.println("  Nao ha vendas realizadas!");
            System.out.println("=============================\n");
        }else{
            ArrayList<VendasPorMes> listaVendasPorMes = new ArrayList<>();
            ArrayList<Integer> listaApoio = new ArrayList<Integer>();
            listaApoio.add(0);
            
            int mes;
            int ano;
            

            for(VendaServico vs1: RepositorioVenda.getInstance().getVendas()){
                mes = vs1.getDataEHora().getMonthValue();
                ano = vs1.getDataEHora().getYear();
                int quantidade = 0;
                double valorTotalMes = 0;

                for(Integer i: listaApoio){
                    if(mes == i){
                        mes = 0;
                        ano = 0;
                    }
                }

                for(VendaServico vs2: RepositorioVenda.getInstance().getVendas()){
                    if(mes == vs2.getDataEHora().getMonthValue() && ano == vs2.getDataEHora().getYear()){
                        quantidade++;
                        valorTotalMes = valorTotalMes + vs2.getValorTotal();
                    }
                }

                if(mes!=0){
                    VendasPorMes vendasPorMes 
                            = new VendasPorMes(mes, ano, quantidade, valorTotalMes);
                    listaVendasPorMes.add(vendasPorMes);
                }
                listaApoio.add(mes);
            }
            
            Collections.sort(listaVendasPorMes);


            //MOSTRANDO A LISTA
            System.out.println("\nVENDAS POR MES:");
            System.out.print(String.format("%-15s","|MES/ANO"));
            System.out.print(String.format("%-15s","|QTD VENDAS"));
            System.out.println(String.format("%-15s","|VALOR"));
            for(VendasPorMes vpm: listaVendasPorMes){
                System.out.print(String.format("%-15s", vpm.getMes()+"/"+vpm.getAno()));
                System.out.print(String.format("%-15s", vpm.getQuantidadeVenda()));
                System.out.println(String.format("%-15s", vpm.getValorPorMes()));
            }
        }
    }
    
    //LISTANDO SERVICOS MAIS VENDIDOS
    private static void listaServicoMaisVendido(){
        if (RepositorioVenda.getInstance().estaVazio()) {
            System.out.println("=============================");
            System.out.println("  Nao ha vendas realizadas!");
            System.out.println("=============================\n");
        }else{
            ArrayList<ServicoMaisVendido> listaServicoMaisVendido = new ArrayList<>();
            ArrayList<String> listaApoio = new ArrayList<String>();            
            listaApoio.add("a");
            
            //Uso de apoio
            ArrayList<TipoServico> listaSegundariaTipoServico = new ArrayList<TipoServico>();
            
            int numeroServico;
            String nomeServico, tipoDeAtendimento;
            double precoServico;
            
            for(VendaServico vs: RepositorioVenda.getInstance().getVendas()){                
                for(TipoServico tsDaVenda: vs.getListaServico()){
                    numeroServico = tsDaVenda.getNumeroServico();
                    nomeServico = tsDaVenda.getNomeServico();
                    tipoDeAtendimento = tsDaVenda.getTipoDeAtendimento();
                    precoServico = tsDaVenda.getPrecoServico();
                    
                    
                    TipoServico segTipoServico 
                            = new TipoServico(numeroServico, nomeServico, tipoDeAtendimento, precoServico);
                    listaSegundariaTipoServico.add(segTipoServico);  
                }
            }
            
            for(TipoServico ts1: listaSegundariaTipoServico){
                int quantidade = 0;
                String nomeServicoRelatorio = ts1.getNomeServico();
                
                for(String str: listaApoio){
                    if(nomeServicoRelatorio == str){
                        nomeServicoRelatorio = null;
                    }
                }
                
                for(TipoServico ts2: listaSegundariaTipoServico){
                    if(nomeServicoRelatorio == ts2.getNomeServico()){
                        quantidade++;
                    }
                }
                
                if(nomeServicoRelatorio != null){
                    ServicoMaisVendido servicoMV 
                            = new ServicoMaisVendido(quantidade, nomeServicoRelatorio);
                    listaServicoMaisVendido.add(servicoMV);
                }
                listaApoio.add(nomeServicoRelatorio);
            }
            
            Collections.sort(listaServicoMaisVendido);

            //MOSTRANDO A LISTA
            System.out.println("\nSERVICOS MAIS VENDIDOS:");
            System.out.print(String.format("%-15s","|QTD VENDAS"));
            System.out.println(String.format("%-15s","|TIPO SERVICO"));
            for(ServicoMaisVendido smv: listaServicoMaisVendido){
                System.out.print(String.format("%-15s", smv.getQuantidade()));
                System.out.println(String.format("%-15s", smv.getNomeServico()));
            }
        }
    }
    
    //LISTANDO PETS QUE FORNECEM MAIS LUCRO
    private static void listaPetsMaisLucro(){
        if (RepositorioVenda.getInstance().estaVazio()) {
            System.out.println("=============================");
            System.out.println("  Nao ha vendas realizadas!");
            System.out.println("=============================\n");
        }else{
            ArrayList<PetGeraMaisLucro> listaPetMaisLucro = new ArrayList<>();
            ArrayList<String> listaApoio = new ArrayList<String>();            
            listaApoio.add(null);        
            
            //Uso de apoio
            ArrayList<String> listaSegundariaTipoPet = new ArrayList<String>();
            
            for(Pet p: RepositorioPet.getInstance().getPets()){
                for(VendaServico vs: RepositorioVenda.getInstance().getVendas()){                
                    String tipoPet = null;
                    if(p.getDono() == vs.getCliente()){
                        tipoPet = p.getTipoAnimal();
                    }
                    
                    if(tipoPet != null){
                        listaSegundariaTipoPet.add(tipoPet);
                    }
                }
            }
            //==================================================================
            int quantidade; 
            String tipoPet;
            for(String stp1: listaSegundariaTipoPet){
                quantidade = 0;
                tipoPet = stp1;
                
                for(String str: listaApoio){
                    if(tipoPet == str){
                        tipoPet = null;
                    }
                }
                
                for(String stp2: listaSegundariaTipoPet){
                    if(tipoPet == stp2){
                        quantidade++;
                    }
                }  
                
                if(tipoPet != null){
                    PetGeraMaisLucro tipoGeraMaisLucro 
                            = new PetGeraMaisLucro(quantidade, tipoPet);
                    listaPetMaisLucro.add(tipoGeraMaisLucro);
                }
                listaApoio.add(tipoPet);
            }
            
            Collections.sort(listaPetMaisLucro);

            //MOSTRANDO A LISTA
            System.out.println("\nTIPO DE PET QUE GERA MAIS LUCROS:");
            System.out.print(String.format("%-15s","|QTD VENDAS"));
            System.out.println(String.format("%-15s","|TIPO PET"));
            for(PetGeraMaisLucro pgml: listaPetMaisLucro){
                System.out.print(String.format("%-15s", pgml.getQuantidade()));
                System.out.println(String.format("%-15s", pgml.getTipoPet()));
            }
        }
    }
}