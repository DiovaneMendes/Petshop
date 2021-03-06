
package Negocio;

import Repositorio.RepositorioVenda;
import java.util.List;
import model.VendaServico;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto cliente, é feito aqui
public class VendaNegocio{
    
    //Validando campos e adicionando a lista
    public void salvar(VendaServico vs) throws NegocioException{
        this.validarCamposObrigatorios(vs);
        RepositorioVenda.getInstance().add(vs);
    }
    
    //Listar a lista de vendas
    public List<VendaServico> listar() {
        return(RepositorioVenda.getInstance().getVendas());
    }
    
    //Retorn se não há vendas na lista
    public boolean naoHaVendas(){
        return RepositorioVenda.getInstance().estaVazio();
    }
    
    //Validando campos
    private void validarCamposObrigatorios(VendaServico vs) throws NegocioException{
        if(vs.getCliente() == null){
            throw new NegocioException("Campo Cliente nao informado!");
        }
    }
}
