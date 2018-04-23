
package Negocio;

import Repositorio.RepositorioTipoServico;
import java.util.List;
import model.TipoServico;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto tipo serviço é feito aqui
public class TipoServicoNegocio {
    
    //Validando campos e adicionando a lista
    public void salvar(TipoServico ts) throws negocio.NegocioException{
        this.validarCamposObrigatorios(ts);
        this.validarServicosExistente(ts);
        RepositorioTipoServico.getInstance().add(ts);
    }
    
    //Listar a lista de clientes
    public List<TipoServico> listar() {
        return(RepositorioTipoServico.getInstance().getTipoServico());
    }
    
    //Retorn se não há clientes na lista
    public boolean naoHaServicos(){
        return RepositorioTipoServico.getInstance().estaVazio();
    }
    
    //Validando campos
    private void validarCamposObrigatorios(TipoServico ts) throws negocio.NegocioException{
        if(ts.getNumeroServico() < 0){
            throw new negocio.NegocioException("Campo Numero invalido!");
        }
        
        if(ts.getNomeServico() == null || ts.getNomeServico().isEmpty()){
            throw new negocio.NegocioException("Campo Nome nao informado!");
        } 
        
        if(!ts.getTipoDeAtendimento().equals("estetico") && !ts.getTipoDeAtendimento().equals("clinico")){
            throw new negocio.NegocioException("Informacao nao corresponde as opcoes!");
        }          
        
        if(ts.getTipoDeAtendimento() == null || ts.getTipoDeAtendimento().isEmpty()){
            throw new negocio.NegocioException("Campo Tipo Atendimento nao informado!");
        }
        
        if(ts.getPrecoServico() <= 0){
            throw new negocio.NegocioException("Campo Preco invalido!");
        }
    }
    
    //Validando com o rg se o servico é existente
    private void validarServicosExistente(TipoServico ts) throws negocio.NegocioException {
        if(RepositorioTipoServico.getInstance().servicoExiste(ts.getNomeServico())){
            throw new negocio.NegocioException("Servico ja existente!");
        }
    }
}
