
package Repositorio;

import java.util.ArrayList;
import java.util.List;
import model.VendaServico;

/**
 *
 * @author Diovane
 */

//Cria o repositório/lista de vendas
public class RepositorioVenda {
    private List<VendaServico> vendas;
    private static RepositorioVenda instance = null;
    
    //Nova lista de vendas
    private RepositorioVenda() {
        vendas = new ArrayList<VendaServico>();
    }
    
    //Retorna um novo repositorio caso esteja vazio
    public static RepositorioVenda getInstance() {
        if(instance == null) instance = new RepositorioVenda();
        return instance;
    }
    
    //Adiciona venda
    public boolean add(VendaServico venda) {
        return (vendas.add(venda));
    }
    
    //Retorna se a lista está vazia ou não
    public boolean estaVazio(){
        return vendas.isEmpty();
    }
    
    //Busca a lista de vendas
    public List<VendaServico> getVendas() {
        return vendas;
    }
}
