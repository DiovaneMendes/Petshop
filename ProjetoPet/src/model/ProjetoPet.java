
package model;

import Relatorio.MaisFrequente;
import Relatorio.MaisGastam;
import Relatorio.ServicoMaisVendido;
import Relatorio.VendasPorMes;
import Util.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Util.DateTimeUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Random;

public class ProjetoPet {
    private static ArrayList<Cliente> listaClientes;
    private static ArrayList<Pet> listaPets;
    private static ArrayList<TipoServico> listaServico;
    private static ArrayList<VendaServico> listaVendaServico;
    private static ArrayList<MaisFrequente> listaMaisFrequente;
    private static ArrayList<MaisGastam> listaMaisGastam;
    private static ArrayList<VendasPorMes> listaVendasPorMes;
    private static ArrayList<ServicoMaisVendido> listaServicoMaisVendido;
    
    
    public static void main(String[] args) {
        listaClientes = new ArrayList<>();
        listaPets = new ArrayList<>();
        listaServico = new ArrayList<>();
        listaVendaServico = new ArrayList<>();
        listaMaisFrequente = new ArrayList<>();
        listaMaisGastam = new ArrayList<>();
        listaVendasPorMes = new ArrayList<>();
        listaServicoMaisVendido = new ArrayList<>();
        
        //============TESTE CLIENTE========
        Cliente c1 = new Cliente("diovane", 609606722, 457896321);
        Cliente c2 = new Cliente("bianca", 604826722, 457987891);
        Cliente c3 = new Cliente("juliana", 604894722, 457364721);
        Cliente c4 = new Cliente("yohana", 987543564, 457361423);
            listaClientes.add(c1);
            listaClientes.add(c2);
            listaClientes.add(c3);
            listaClientes.add(c4);
            
        //===========TESTE SERVICO==========
        TipoServico t1 = new TipoServico(10, "tosa1", "a", 28);
        TipoServico t2 = new TipoServico(20, "tosa2", "b", 38);
        TipoServico t3 = new TipoServico(30, "tosa3", "c", 48);
        TipoServico t4 = new TipoServico(40, "tosa4", "d", 58);
            listaServico.add(t1);
            listaServico.add(t2);
            listaServico.add(t3);
            listaServico.add(t4);
            
        //============TESTE PET============
        Pet pet1 = new Pet("TED1", "gato", c1, t1);
        Pet pet2 = new Pet("TED2", "dog", c1, t2);
        Pet pet3 = new Pet("TED3", "gato", c3, t3);
        Pet pet4 = new Pet("TED4", "dog", c4, t4);
            listaPets.add(pet1);
            listaPets.add(pet2);
            listaPets.add(pet3);
            listaPets.add(pet4);
            
        int opcao = 0;
        do {

            try {
                System.out.println("\nMenu: ");
                System.out.println("1- Adicionar Cliente\n2- Listar Clientes");
                System.out.println("3- Adicionar Pet\n4- Listar Pets");
                System.out.println("5- Adicionar Servico\n6- Listar Servico");
                System.out.println("7- Venda Servico\n8- Relatorio");
                System.out.println("0- Sair");
                opcao = Console.scanInt("Digite a opcao: ");

                switch (opcao) {
                    case 1:
                        //adicionarCliente();
                        listaMaisFrequente();
                        break;
                    case 2:
                        //listarClientes();
                        listaServicoMaisVendido();
                        break;
                    case 3:
                        adicionarPet();
                        break;
                    case 4:
                        listarPets();
                        break;
                    case 5:
                        adicionarServico();   
                        break;
                    case 6:
                        listarServico();
                        break;
                    case 7:
                        vendido();
                        break;
                    case 8:
                        listaVenda();
                        break;
                    case 9:
                        //relatorio();
                        break;
                    case 0:
                        System.out.println("Saindo do Sistema...");
                        break;
                    default:
                        System.err.println("Erro: Escolha a opcao correta!!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Escolha a opcao correta!");
            }
        } while (opcao != 0);
    }
    
    //==============================CASE 1======================================
    //CADASTRANDO CLIENTES
    private static void adicionarCliente() {
        System.out.println("\nAdicionando Cliente...");
        try{            
            String nome = Console.scanString("Nome: ");
            long rg = Console.scanLong("RG: ");
            long telefone = Console.scanLong("Telefone: ");
        
            Cliente cliente = new Cliente(nome, rg, telefone);
            listaClientes.add(cliente);
            System.out.println("Cadastro realizado com sucesso!");
        } catch(Exception e){
            System.out.println("ERROOOOOOOOOOOO!");
        }
    }
    
    //==============================CASE 2======================================
    //LISTANDO CLIENTES CADASTRADOS
    private static void listarClientes() {
        System.out.println("\nListando Clientes...");
        if (listaClientes.isEmpty()) {
            System.out.println("Nao ha clientes cadastrados!");
        } else {
            if(listaClientes.isEmpty()){
                System.out.println("Nenhum cliente cadastrado!");
            }
            else{
                System.out.print(String.format("%-20s","|NOME"));
                System.out.print(String.format("%-15s","|RG"));
                System.out.println(String.format("%-10s","|TELEFONE"));
                for(Cliente c: listaClientes){
                    System.out.print(String.format("%-20s",c.getNome()));
                    System.out.print(String.format("%-15s",c.getRg()));
                    System.out.println(String.format("%-10s",c.getTelefone()));

                }
            }        
        }
    }
    
    //==============================CASE 3======================================
    //CADASTRANDO PETS
    private static void adicionarPet() {        
        if (listaClientes.isEmpty() || listaServico.isEmpty()) {
            System.out.println("Adicione cliente e servico antes dessa acao!");
        } else{
            System.out.println("\nAdicionando Pet...");
            String nomePet = Console.scanString("Nome: ");
            String tipoAnimal = Console.scanString("Tipo pet: ");
            Cliente dono = null;
            
            //LISTANDO CLIENTES PARA ESCOLHA DE DONO
            System.out.println(String.format("%-20s","\n|LISTA DE CLIENTES:"));
            for(Cliente c: listaClientes){
                System.out.println(String.format("%-20s",c.getNome()));
            }
            //ESCOLHENDO DONO
            String donoString = Console.scanString("Dono: ");
            try{
                for(Cliente c: listaClientes){
                    if(donoString.equals(c.getNome())){
                        dono = c;
                    }
                }
            }catch(Exception e){
                System.out.println("ERROOOOOOOOOOOO!");
            }
            
            TipoServico servicoRealizado = null; 
            //LISTANDO SERVICOS PARA ESCOLHA
            System.out.println(String.format("%-20s","\n|LISTA DE SERVICOS:"));
            for(TipoServico ts: listaServico){
                System.out.println(String.format("%-20s",ts.getNomeServico()));
            }
            //ESCOLHENDO SERVICO DESEJADO
            String servicoReal = Console.scanString("Servico Realizado: ");
            try{
                for(TipoServico t: listaServico){
                    if(servicoReal.equals(t.getNomeServico())){
                        servicoRealizado = t;
                    }
                }
            }catch(Exception e){
                System.out.println("ERROOOOOOOOOOOO!");
            }

            Pet pet = new Pet(nomePet, tipoAnimal, dono, servicoRealizado);
            listaPets.add(pet);
            System.out.println("Cadastro realizado com sucesso!");
        }
    }
        
    //==============================CASE 4======================================
    //LISTANDO PETS CADASTRADOS
    private static void listarPets() {
        System.out.println("\nListando Pets...");
        if (listaPets.isEmpty()) {
            System.out.println("Nao ha pets cadastrados!");
        } else {
            if(listaPets.isEmpty()){
                System.out.println("Nenhum pet cadastrado!");
            }
            else{
                System.out.print(String.format("%-10s","|NOME"));
                System.out.print(String.format("%-15s","|TIPO ANIMAL"));
                System.out.print(String.format("%-20s","|CLIENTE"));
                System.out.println(String.format("%-10s","|TIPO SERVICO"));
                for(Pet p: listaPets){
                    System.out.print(String.format("%-10s",p.getNomePet()));
                    System.out.print(String.format("%-15s",p.getTipoanimal()));
                    System.out.print(String.format("%-20s",p.getDono().getNome()));
                    System.out.println(String.format("%-10s",p.getServicoRealizado().getNomeServico()));
                }
            }        
        }
    }
    
    //==============================CASE 5======================================
    //CADASTRANDO SERVICOS
    private static void adicionarServico() {        
        System.out.println("\nAdicionando Servico...");
        /*
        TRY funciona da seguinte forma: 
            *Adicionando Servico...
            *Numero servico: tosa
            *ERROOOOOOOOOOOO!
        como eu coloquei pra dar erro em todos, no momento que coloquei uma string 
        no lugar de um int, parou toda a adicao e deu erro, assim fazendo com que
        eu volte ao menu principal. A ideia mais tarde é fazer um TRY para cada 
        elemento da adicao e não para todos!
        */
        try{   
            int numeroServico = Console.scanInt("Numero servico: ");         
            String nomeServico = Console.scanString("Nome servico: ");
            String tipoAtendimento = Console.scanString("Tipo atendimento: (clínico ou estetico)");
            double preco = Console.scanDouble("Preco: ");
            TipoServico tipoServico = new TipoServico(numeroServico, nomeServico, tipoAtendimento, preco);
            listaServico.add(tipoServico);
            System.out.println("Cadastro realizado com sucesso!");
        } catch(Exception e){
            System.out.println("ERROOOOOOOOOOOO!");
        }
    }
    
    //==============================CASE 6======================================
    //LISTANDO SERVICOS CADASTRADOS
    private static void listarServico() {
        System.out.println("\nListando Servicos...");
        if (listaServico.isEmpty()) {
            System.out.println("Nao ha servicos cadastrados!");
        } else {
            if(listaServico.isEmpty()){
                System.out.println("Nenhum servico cadastrado!");
            }
            else{
                System.out.print(String.format("%-10s","|NUMERO"));
                System.out.print(String.format("%-15s","|NOME"));
                System.out.print(String.format("%-20s","|TIPO ATENDIMENTO"));
                System.out.println(String.format("%-15s","|PRECO"));
                for(TipoServico t: listaServico){
                    System.out.print(String.format("%-10s",t.getNumeroServico()));
                    System.out.print(String.format("%-15s",t.getNomeServico()));
                    System.out.print(String.format("%-20s",t.getTipoDeAtendimento()));
                    System.out.println(String.format("%-15s",t.getPrecoServico()));
                }
            }        
        }
    }
    
    //==============================CASE 7======================================
    //REALIZANDO UMA VENDA
    private static void vendido(){
        if (listaClientes.isEmpty() || listaServico.isEmpty() || listaPets.isEmpty()) {
	    System.out.println("Adicione cliente, servico e/ou pet antes dessa acao!");
        } else{
            System.out.println("\nRealizando venda...");
            try{
                //INSERINDO DATA ATUAL
                /*String data = DateTimeUtil.dateTimeToString(LocalDateTime.now());
                System.out.println("Data: " + data);*/
                
                //DATA PARA TESTE
                String[] sorteioData = new String [12];
                sorteioData[0] = "10/01/2017"; sorteioData[1] = "15/02/2017"; sorteioData[2] = "30/03/2017";
                sorteioData[3] = "18/04/2017"; sorteioData[4] = "21/05/2017"; sorteioData[5] = "31/06/2017";
                sorteioData[6] = "07/07/2017"; sorteioData[7] = "13/08/2017"; sorteioData[8] = "24/09/2017";
                sorteioData[9] = "22/10/2017"; sorteioData[10] = "02/11/2017"; sorteioData[11] = "11/12/2017";
                
                Random gerador = new Random();
                String dataTeste = sorteioData[gerador.nextInt(10)];
                LocalDate datta = DateTimeUtil.stringToDate(dataTeste);
                System.out.println("NÃO FORMATADA: "+datta);
                
                //MOSTRA A LISTA DE CLIENTES
                System.out.println(String.format("%-20s","\n|LISTA DE CLIENTES:"));
                for(Cliente c: listaClientes){
                    System.out.println(String.format("%-20s",c.getNome()));

                }
                //INCLUINDO NOME DE CLIENTE E VERIFICANDO SE COSTA NA LISTA DE CLIENTES
                String nomeCliente = Console.scanString("Nome cliente: ");
                Cliente cliente = null;
                for(Cliente c: listaClientes){
                    if(nomeCliente.equals(c.getNome())){
                        cliente = c;
                    }
                }
                
                //LISTANDO SERVICOS REALIZADOS E INCLUINDO VALOR TOTAL
                TipoServico tipoServico;
                ArrayList<TipoServico> listaServicoVenda = new ArrayList<>();
                double valorTotal = 0;
                for(Pet p: listaPets){
                    if(nomeCliente.equals(p.getDono().getNome())){
                        tipoServico = p.getServicoRealizado();
                        listaServicoVenda.add(tipoServico);
                        valorTotal = valorTotal + tipoServico.getPrecoServico();
                    }                
                }
                
                //INSERINDO INFORMACOES COLETADAS ACIMA PARA CRIAR UM NOVO OBJETO E DEPOIS ADICIONANDO NO ARRAY
                VendaServico vendaServico = new VendaServico(datta, cliente, listaServicoVenda, valorTotal);
                listaVendaServico.add(vendaServico);
                
                System.out.println("Venda concluida!");           
            } catch(Exception e){
                System.out.println("ERROOOOOOOOOOOO!");
            }
        }
    }
    
    //==============================CASE 8======================================
    //LISTANDO VENDAS REALIZADAS
    private static void listaVenda(){
        System.out.println("\nListando vendas...");
        if(listaVendaServico.isEmpty()){
            System.out.println("Nenhuma venda realizada!");
        }
        else{
            System.out.print(String.format("%-20s","|DATA E HORA"));
            System.out.print(String.format("%-15s","|NOME CLIENTE"));
            System.out.print(String.format("%-25s","|SERVICOS REALIZADOS"));
            System.out.println(String.format("%-15s","|VALOR TOTAL"));
            for(VendaServico vs: listaVendaServico){
                System.out.print(String.format("%-20s",vs.getDataEHora().format(DateTimeUtil.formatadorData)));
                System.out.print(String.format("%-15s",vs.getCliente().getNome()));
                System.out.print(String.format("%-25s",vs.getListaServico()));
                System.out.println(String.format("%-15s",vs.getValorTotal()));
            }
        }        
    } 
    
    //==============================CASE 9======================================
    //LISTANDO CLIENTE MAIS FREQUENTES
    private static void listaMaisFrequente(){
        if(listaVendaServico.isEmpty()){
            System.out.println("\nNão há nenhuma venda!");
        }else{
            listaMaisFrequente.clear();
            for(Cliente c: listaClientes){
                String nome = null;
                int acumulador = 0;
                for(VendaServico vs:listaVendaServico){
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
        if(listaVendaServico.isEmpty()){
            System.out.println("\nNão há nenhuma venda!");
        }else{
            listaMaisGastam.clear();
            for(Cliente c: listaClientes){
                String nome = null;
                double valorTotal = 0;
                for(VendaServico vs:listaVendaServico){
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
        if(listaVendaServico.isEmpty()){
            System.out.println("\nNão há nenhuma venda!");
        }
        else{
            ArrayList<Integer> listaApoio = new ArrayList<Integer>();
            listaApoio.add(0);
            listaVendasPorMes.clear();
            int mes;
            int ano;
            

            for(VendaServico vs1: listaVendaServico){
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

                for(VendaServico vs2: listaVendaServico){
                    if(mes == vs2.getDataEHora().getMonthValue() && ano == vs2.getDataEHora().getYear()){
                        quantidade++;
                        valorTotalMes = valorTotalMes + vs2.getValorTotal();
                    }
                }

                if(mes!=0){
                    VendasPorMes vendasPorMes = new VendasPorMes(mes, ano, quantidade, valorTotalMes);
                    listaVendasPorMes.add(vendasPorMes);
                }
                listaApoio.add(mes);
            }
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
    
    //LISTANDO SERVICOS MAIS VENDIDOS
    private static void listaServicoMaisVendido(){
        if(listaVendaServico.isEmpty()){
            System.out.println("\nNão há nenhuma venda!");
        }
        else{
            ArrayList<String> listaApoio = new ArrayList<String>();            
            listaApoio.add("a");
            
            //Uso de apoio
            ArrayList<TipoServico> listaSegundariaTipoServico = new ArrayList<TipoServico>();
            
            
            listaServicoMaisVendido.clear();
            
            int numeroServico;
            String nomeServico, tipoDeAtendimento;
            double precoServico;
            
            for(VendaServico vs: listaVendaServico){                
                for(TipoServico tsDaVenda: vs.getListaServico()){
                    numeroServico = tsDaVenda.getNumeroServico();
                    nomeServico = tsDaVenda.getNomeServico();
                    tipoDeAtendimento = tsDaVenda.getTipoDeAtendimento();
                    precoServico = tsDaVenda.getPrecoServico();
                    
                    
                    TipoServico segTipoServico = new TipoServico(numeroServico, nomeServico, tipoDeAtendimento, precoServico);
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
                    ServicoMaisVendido servicoMV = new ServicoMaisVendido(quantidade, nomeServicoRelatorio);
                    listaServicoMaisVendido.add(servicoMV);
                }
                listaApoio.add(nomeServicoRelatorio);
            }
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
    
    //- Relatório: tipos de pets que fornecem mais lucro, etc.
    //LISTANDO PETS QUE FORNECEM MAIS LUCRO
    private static void listaPetsMaisLucro(){
        
    }
}




