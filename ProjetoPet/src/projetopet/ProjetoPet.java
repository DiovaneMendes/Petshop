
package projetopet;

import Util.Console;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Util.DateTimeUtil;
import java.time.LocalDateTime;

public class ProjetoPet {

    private static ArrayList<Cliente> listaClientes;
    private static ArrayList<Pet> listaPets;
    private static ArrayList<TipoServico> listaServico;
    
    public static void main(String[] args) {
        listaClientes = new ArrayList<>();
        listaPets = new ArrayList<>();
        listaServico = new ArrayList<>();

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
            int rg = Console.scanInt("RG: ");
            int telefone = Console.scanInt("Telefone: ");
        
            //String dataString = Console.scanString("Data Nascimento:");        
            //LocalDate dataNascimento = DateTimeUtil.stringToDate(dataString);
        
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
                System.out.print(String.format("%-10s","|RG"));
                System.out.println(String.format("%-10s","|TELEFONE"));
                for(Cliente c: listaClientes){
                    System.out.print(String.format("%-10s",c.getRg()));
                    System.out.print(String.format("%-20s",c.getNome()));
                    //String dataUtil = DateTimeUtil.dateToString(c.getDataNascimento());
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
            try{            
                String nomePet = Console.scanString("Nome: ");
                String tipoAnimal = Console.scanString("Tipo pet: ");
                //Lista de clientes antes de escolher um
                listarClientes();
                String donoString = Console.scanString("Dono: ");
                Cliente dono = null;
                for(Cliente c: listaClientes){
                    if(donoString.equals(c.getNome())){
                        dono = c;
                    }
                }
                //Lista de servicos antes de escolher um
                listarServico();
                String servicoReal = Console.scanString("Servico Realizado: ");
                TipoServico servicoRealizado = null;
                for(TipoServico t: listaServico){
                    if(servicoReal.equals(t.getNomeServico())){
                        servicoRealizado = t;
                    }
                }        
                Pet pet = new Pet(nomePet, tipoAnimal, dono, servicoRealizado);
                listaPets.add(pet);
                System.out.println("Cadastro realizado com sucesso!");
            } catch(Exception e){
                System.out.println("ERROOOOOOOOOOOO!");
            }
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
                System.out.print(String.format("%-20s","|NOME"));
                System.out.print(String.format("%-10s","|TIPO ANIMAL"));
                System.out.println(String.format("%-10s","|CLIENTE"));
                for(Pet p: listaPets){
                    System.out.print(String.format("%-10s",p.getNomePet()));
                    System.out.print(String.format("%-20s",p.getTipoanimal()));
                    System.out.println(String.format("%-10s",p.getDono().getNome()));
                }
            }        
        }
    }
    
    //==============================CASE 5======================================
    private static void adicionarServico() {
        System.out.println("\nAdicionando Servico...");
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
                System.out.print(String.format("%-20s","|NUMERO"));
                System.out.print(String.format("%-10s","|NOME"));
                System.out.println(String.format("%-10s","|TIPO ATENDIMENTO"));
                System.out.println(String.format("%-10s","|PRECO"));
                for(TipoServico t: listaServico){
                    System.out.print(String.format("%-20s",t.getNumeroServico()));
                    System.out.print(String.format("%-10s",t.getNomeServico()));
                    System.out.println(String.format("%-10s",t.getTipoDeAtendimento()));
                    System.out.println(String.format("%-10s",t.getPrecoServico()));
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

                String tipoAtendimento = Console.scanString("Servico realizado: ");
                TipoServico tipoServico = null;
                double valorTotal = 0;
                
                for(Pet p: listaPets){
                    if(nomeCliente.equals(p.getDono().getNome())){
                        tipoServico = p.getServicoRealizado();
                        valorTotal = valorTotal + tipoServico.getPrecoServico();
                    }                
                }
                
                VendaServico vendaServico = new VendaServico(data, cliente, tipoServico, valorTotal);
                listaServico.add(tipoServico);
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