
package projetopet;

import Util.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Util.DateTimeUtil;
import java.time.LocalDateTime;

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
                        adicionarCliente();
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
    private static void adicionarPet() {
        if (listaClientes.isEmpty() || listaServico.isEmpty()) {
            System.out.println("Adicione cliente e servico antes dessa acao!");
        } else{
            System.out.println("\nAdicionando Pet...");
            String nomePet = Console.scanString("Nome: ");
            String tipoAnimal = Console.scanString("Tipo pet: ");
            Cliente dono = null;
            boolean validarCliente = true;
            do{
                //Lista de clientes antes de escolher um
                listarClientes();
                String donoString = Console.scanString("Dono: ");
                //DANDO ERRO!
                for(Cliente c: listaClientes){
                    if(donoString.equals(c.getNome())){
                        dono = c;
                        validarCliente = false;
                    } else{
                        System.out.println("Erro: Nome não confere com a lista de clientes acima!");
                        int adicionarCliente = Console.scanInt("\nDeseja cadastrar cliente novo? \n1- Adicionar\n2- Escolher cliente existente\nOpcao: ");
                        if(adicionarCliente == 1){
                            adicionarCliente();
                            listarClientes();
                            donoString = Console.scanString("Dono: ");
                            if(donoString.equals(c.getNome())){
                                dono = c;
                            }
                        }                        
                    }
                }
            } while(validarCliente);
            
            TipoServico servicoRealizado = null;
            boolean validarServico = true;
            do{   
                //Lista de servicos antes de escolher um
                listarServico();
                String servicoReal = Console.scanString("Servico Realizado: ");                
                for(TipoServico t: listaServico){
                    if(servicoReal.equals(t.getNomeServico())){
                        servicoRealizado = t;
                        validarServico = false;
                    } else{
                        System.out.println("Erro: Nome não confere com a lista de servicos acima!");
                        int adicionarServico = Console.scanInt("\nDeseja cadastrar servico novo? \n1- Adicionar\n2- Escolher servico existente\nOpcao: ");
                        if(adicionarServico == 1){
                            adicionarServico();
                            listarServico();
                            servicoReal = Console.scanString("Servico Realizado: ");
                        }
                    }
                }
            }while(validarServico);

            Pet pet = new Pet(nomePet, tipoAnimal, dono, servicoRealizado);
            listaPets.add(pet);
            System.out.println("Cadastro realizado com sucesso!");
        }
    }
        
    //==============================CASE 4======================================
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
    private static void vendido(){
        if (listaClientes.isEmpty() || listaServico.isEmpty() || listaPets.isEmpty()) {
	    System.out.println("Adicione cliente e servico antes dessa acao!");
        } else{
            System.out.println("\nRealizando venda...");
            try{  
                System.out.println("Data: "); 
                String data = DateTimeUtil.dateTimeToString(LocalDateTime.now());
                //Lista de clientes antes de escolher um
	        listarClientes();
                String nomeCliente = Console.scanString("Nome cliente: ");
                Cliente cliente = null;
                for(Cliente c: listaClientes){
                    if(nomeCliente.equals(c.getNome())){
                        cliente = c;
                    }
                }

                //String tipoAtendimento = Console.scanString("Servico realizado: ");
                TipoServico tipoServico = null;
                double valorTotal = 0;
                for(Pet p: listaPets){
                    if(nomeCliente.equals(p.getDono().getNome())){
                        tipoServico = p.getServicoRealizado();
                        valorTotal = valorTotal + tipoServico.getPrecoServico();
                    }                
                }
                
                VendaServico vendaServico = new VendaServico(data, cliente, tipoServico, valorTotal);
                listaVendaServico.add(vendaServico);
                System.out.println("Venda concluida!");
            } catch(Exception e){
                System.out.println("ERROOOOOOOOOOOO!");
            }
        }
    }
    
    //==============================CASE 8======================================
    private static void relatorio(){
        
    }
}