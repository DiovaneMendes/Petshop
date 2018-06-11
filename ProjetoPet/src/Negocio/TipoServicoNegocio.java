
package Negocio;

import Repositorio.RepositorioTipoServico;
import dao.TipoServicoDao;
import dao.impl_BD.TipoServicoDaoBd;
import java.util.List;
import model.TipoServico;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto tipo serviço é feito aqui
public class TipoServicoNegocio {
    private TipoServicoDao tipoServicoDao;
    
    public TipoServicoNegocio(){
        tipoServicoDao = new TipoServicoDaoBd();
    }
    
    //Validando campos e adicionando a lista
    public void salvar(TipoServico ts) throws NegocioException{
        this.validarCamposObrigatorios(ts);
        tipoServicoDao.salvar(ts);
    }
    
    //Listar a lista de clientes
    public List<TipoServico> listar() {
        return(tipoServicoDao.listar());
    }
    
    //Retorn se não há clientes na lista
    public boolean naoHaServicos(){
        return RepositorioTipoServico.getInstance().estaVazio();
    }
    
    public void deletar(TipoServico tipoServico) throws NegocioException {
        if (tipoServico == null || tipoServico.getNomeServico() == null) {
            throw new NegocioException("Servico nao existe!");
        }
        tipoServicoDao.deletar(tipoServico);
    }
    
    public void atualizar(TipoServico tipoServico) throws NegocioException {
        if (tipoServico == null || tipoServico.getNomeServico() == null) {
            throw new NegocioException("Servico nao existe!");
        }
        this.validarCamposObrigatorios(tipoServico);
        tipoServicoDao.atualizar(tipoServico);
    }
    
    public TipoServico procurarPorNome(String nome) throws NegocioException {
        if (nome == null) {
            throw new NegocioException("Servico nao encontrado");
        }
        TipoServico tipoServico = tipoServicoDao.procurarPorNome(nome);
        if (tipoServico == null) {
            throw new NegocioException("Servico nao encontrado");
        }
        return (tipoServico);
    }
    
    public List<TipoServico> listarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(tipoServicoDao.listarPorNome(nome));
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
