
package Validacao;

import Util.Console;
import java.util.ArrayList;
import projetopet.Cliente;

public class TratandoValidacao {
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    
    public TratandoValidacao(){
        
    }
    
    //==============================CASE 1======================================
    private static void adicionarCliente() {
        System.out.println("\nAdicionando Cliente...");
        try{            
            String nome = Console.scanString("Nome: ");
            long rg = Console.scanInt("RG: ");
            long telefone = Console.scanInt("Telefone: ");
        
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
                    System.out.print(String.format("%-20s",c.getNome()));
                    System.out.print(String.format("%-10s",c.getRg()));
                    System.out.println(String.format("%-10s",c.getTelefone()));

                }
            }        
        }
    }
    
    public Cliente validarCliente(){
	Cliente dono = null;
        boolean validarCliente = true;
        String donoString = Console.scanString("Dono: ");
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
        return dono;
    }
}
