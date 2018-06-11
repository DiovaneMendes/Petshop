package Negocio;

import dao.ItemVendaDao;
import dao.impl_BD.ItemVendaDaoBd;
import java.util.List;
import model.ItemVenda;

/**
 *
 * @author Diovane
 */
public class ItemVendaNegocio {
    private ItemVendaDao ivDao;
    
    public ItemVendaNegocio(){
        ivDao = new ItemVendaDaoBd();
    }
    
    public void salvar(ItemVenda iv) throws NegocioException{
        this.validarCamposObrigatorios(iv);
        ivDao.salvar(iv);
    }
    
    public List<ItemVenda> listar() {
        return(ivDao.listar());
    }
    
    public void deletar(ItemVenda item) throws NegocioException {
        if (item == null) {
            throw new NegocioException("Item nao existe!");
        }
        ivDao.deletar(item);
    }
    
    public void atualizar(ItemVenda item) throws NegocioException {
        if (item == null) {
            throw new NegocioException("Item nao existe!");
        }
        this.validarCamposObrigatorios(item);
        ivDao.atualizar(item);
    }
    
    public ItemVenda procurarPorId(int id) throws NegocioException {
        if (id == 0) {
            throw new NegocioException("Campo id nao informado");
        }
        ItemVenda item = ivDao.procurarPorId(id);
        if (item == null) {
            throw new NegocioException("Item de venda nao encontrado");
        }
        return (item);
    }
    
    private void validarCamposObrigatorios(ItemVenda iv) throws NegocioException{
        if(iv.getPet() == null || iv.getPet().isEmpty()){
            throw new NegocioException("Campo nome do pet nao informado!");
        }
        if(iv.getServico() == null || iv.getServico() .isEmpty()){
            throw new NegocioException("Campo nome do servico nao informado!");
        } 
    }
}
