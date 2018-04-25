
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
    public void salvar(TipoServico ts) throws NegocioException{
        this.validarCamposObrigatorios(ts);
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
    private void validarCamposObrigatorios(TipoServico ts) throws Negocio.NegocioException{
        if(ts.getNumeroServico() < 0){
            throw new Negocio.NegocioException("Campo Numero invalido!");
        }
        
        if(RepositorioTipoServico.getInstance().buscarServicoNumero(ts.getNumeroServico())){
            throw new Negocio.NegocioException("Numero ja consiste no sistema!");
        }
        
        if(ts.getNomeServico() == null || ts.getNomeServico().isEmpty()){
            throw new Negocio.NegocioException("Campo Nome nao informado!");
        }
        
        if(RepositorioTipoServico.getInstance().buscarServicoNome(ts.getNomeServico())){
            throw new Negocio.NegocioException("Servico informado ja consiste no sistema!");
        }
        
        if(!ts.getTipoDeAtendimento().equals("estetico") && !ts.getTipoDeAtendimento().equals("clinico")){
            throw new Negocio.NegocioException("Informacao de servico nao corresponde as opcoes!");
        }          
        
        if(ts.getTipoDeAtendimento() == null || ts.getTipoDeAtendimento().isEmpty()){
            throw new Negocio.NegocioException("Campo Tipo Atendimento nao informado!");
        }
        
        if(ts.getPrecoServico() <= 0){
            throw new Negocio.NegocioException("Campo Preco invalido!");
        }
    }
}
