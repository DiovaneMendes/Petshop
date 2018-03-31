
package projetopet;

import Util.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Util.DateTimeUtil;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProjetoPet {

    private static ArrayList<Cliente> listaClientes;
    private static ArrayList<Pet> listaPets;
    private static ArrayList<TipoServico> listaServico;
    private static ArrayList<VendaServico> listaVendaServico;
    
    public static void main(String[] args) {
        listaClientes = new ArrayList<>();
        listaPets = new ArrayList<>();
        listaServico = new ArrayList<>();
        listaVendaServico = new ArrayList<>();
        
        //============TESTE CLIENTE========
        Cliente c1 = new Cliente("diovane", 609606722, 457896321);
        Cliente c2 = new Cliente("bianca", 604826722, 457987891);
        Cliente c3 = new Cliente("juliana", 604894722, 457364721);
            listaClientes.add(c1);
            listaClientes.add(c2);
            listaClientes.add(c3);
            
        //===========TESTE SERVICO==========
        TipoServico t1 = new TipoServico(10, "tosa1", "a", 28);
        TipoServico t2 = new TipoServico(20, "tosa2", "b", 38);
        TipoServico t3 = new TipoServico(30, "tosa3", "c", 48);
            listaServico.add(t1);
            listaServico.add(t2);
            listaServico.add(t3);
            
        //============TESTE PET============
        Pet pet1 = new Pet("TED1", "gato", c1, t1);
        Pet pet2 = new Pet("TED2", "dog", c1, t2);
        Pet pet3 = new Pet("TED3", "gato", c3, t3);
            listaPets.add(pet1);
            listaPets.add(pet2);
            listaPets.add(pet3);
        
        

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
                        clienteMaisFrequentes();
                        break;
                    case 2:
                        listarClientes();
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
                String data = DateTimeUtil.dateTimeToString(LocalDateTime.now());
                System.out.println("Data: " + data); 
                
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
                VendaServico vendaServico = new VendaServico(data, cliente, listaServicoVenda, valorTotal);
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
                System.out.print(String.format("%-20s",vs.getDataEHora()));
                System.out.print(String.format("%-15s",vs.getCliente().getNome()));
                System.out.print(String.format("%-25s",vs.getListaServico()));
                System.out.println(String.format("%-15s",vs.getValorTotal()));
            }
        }        
    } 
    
    //==============================CASE 9======================================
    // TA DANDO RUIM AQUIIIIIIIIIIIII!
    private static void clienteMaisFrequentes(){
        int tamanhoVetor = 0;
        for(int i=0; i<listaVendaServico.size(); i++){
            tamanhoVetor++;
        }
        String[] maisFrequente = new String [tamanhoVetor];
        int[] quantidade = new int[tamanhoVetor];
        
        int acumulador = 0;
        for(Cliente c: listaClientes){
            int indice = 0;
            String nome = null;
            for(VendaServico vs:listaVendaServico){
                acumulador++;
                if(c.getNome().equals(vs.getCliente().getNome())){
                    if(indice==0){
                        nome = c.getNome();
                    }
                    indice++;
                }
            }
            maisFrequente[acumulador] = nome;
        }
    }
}