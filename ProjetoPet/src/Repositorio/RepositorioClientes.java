
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
    public boolean clienteExiste(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg().equals(rg)) {
                return true;
            }
        }
        return false;
    }
    
    //Busca um cliente dentro da lista
    public Cliente buscarCliente(String rg) {
        for (Cliente cliente : clientes) {
            if (cliente.getRg().equals(rg)) {
                return cliente;
           }
        }
        return null;
    }
}
