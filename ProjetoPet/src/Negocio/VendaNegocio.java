
package Negocio;

import Repositorio.RepositorioVenda;
import dao.VendaDao;
import dao.impl_BD.VendaDaoBd;
import java.util.List;
import model.Venda;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto cliente, é feito aqui
public class VendaNegocio{
    private VendaDao vendaDao;
    
    public VendaNegocio(){
        vendaDao = new VendaDaoBd();
    }
    
    //Validando campos e adicionando a lista
    public void salvar(Venda v) throws NegocioException{
        this.validarCamposObrigatorios(v);
        vendaDao.salvar(v);
    }
    
    //Listar a lista de vendas
    public List<Venda> listar() {
        return(vendaDao.listar());
    }
    
    public void deletar(Venda venda) throws NegocioException {
        if (venda == null) {
            throw new NegocioException("Venda nao existe!");
        }
        vendaDao.deletar(venda);
    }
    
    public void atualizar(Venda venda) throws NegocioException {
        if (venda == null) {
            throw new NegocioException("Venda nao existe!");
        }
        this.validarCamposObrigatorios(venda);
        vendaDao.atualizar(venda);
    }
    
    public Venda procurarPorId(int id) throws NegocioException {
        if (id < 0) {
            throw new NegocioException("Campo id nao informado");
        }
        Venda venda = vendaDao.procurarPorId(id);
        if (venda == null) {
            throw new NegocioException("Venda nao encontrado");
        }
        return (venda);
    }
    
    public boolean naoHaVendas(){
        return RepositorioVenda.getInstance().estaVazio();
    }
    
    //Validando campos
    private void validarCamposObrigatorios(Venda vs) throws NegocioException{
        if(vs.getDataEHora() == null){
            throw new NegocioException("Campo data com hora nao informado!");
        }
    }
}
