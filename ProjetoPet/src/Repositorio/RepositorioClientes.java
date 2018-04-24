
package Repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Diovane
 */

//Cria o repositório/lista de clientes
public class RepositorioClientes {
    private List<Cliente> clientes;
    private static RepositorioClientes instance = null;
    
    //Nova lista de clientes
    private RepositorioClientes() {
        clientes = new ArrayList<Cliente>();
    }
    
    //Retorna um novo repositorio caso esteja vazio
    public static RepositorioClientes getInstance() {
        if(instance == null) instance = new RepositorioClientes();
        return instance;
    }
    
    //Adiciona cliente
    public boolean add(Cliente cliente) {
        return (clientes.add(cliente));
    }
    
    //Retorna se a lista está vazia ou não
    public boolean estaVazio(){
        return clientes.isEmpty();
    }
    
    //Busca a lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    //Verifica se o cliente existe ou não
    public boolean clienteExiste(long rg) {
        for(Cliente cliente : clientes) {
            if (cliente.getRg() == rg) {
                return true;
            }
        }
        return false;
    }
    
    //Teste para validar pet
    public Cliente testeParaPet(String nomeDono) {
        for(Cliente cliente : clientes) {
            if (cliente.getNome().equals(nomeDono)) {
                return cliente;
           }
        }
        return null;
    }
}
