
package Repositorio;

import java.util.ArrayList;
import java.util.List;
import model.TipoServico;

/**
 *
 * @author Diovane
 */

//Cria o repositório/lista de tipo serviço
public class RepositorioTipoServico{
    private List<TipoServico> tipoServicos;
    private static RepositorioTipoServico instance = null;
    
    //Nova lista de tipo de serviço
    private RepositorioTipoServico(){        
        tipoServicos = new ArrayList<TipoServico>();
    }
    
    //Retorna um novo repositorio caso esteja vazio
    public static RepositorioTipoServico getInstance(){
        if(instance == null) instance = new RepositorioTipoServico();
        return instance;
    }
    
    //Adiciona serviço
    public boolean add(TipoServico tipoServico) {
        return (tipoServicos.add(tipoServico));
    }
    
    //Retorna se a lista está vazia ou não
    public boolean estaVazio(){
        return tipoServicos.isEmpty();
    }
    
    //Busca a lista de serviços
    public List<TipoServico> getTipoServico() {
        return tipoServicos;
    }
    
    //Verifica se o serviço existe ou não
    public boolean servicoExiste(String nomeServico) {
        for (TipoServico tipoServico : tipoServicos) {
            if (tipoServico.getNomeServico().equals(nomeServico)) {
                return true;
            }
        }
        return false;
    }
    
    //Busca um serviço dentro da lista
    public TipoServico buscarCliente(String nomeServico) {
        for (TipoServico tipoServico : tipoServicos) {
            if (tipoServico.getNomeServico().equals(nomeServico)) {
                return tipoServico;
           }
        }
        return null;
    }
}
