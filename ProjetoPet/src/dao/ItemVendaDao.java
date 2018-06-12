package dao;

import model.ItemVenda;

/**
 *
 * @author Diovane
 */
public interface ItemVendaDao extends Dao<ItemVenda>{
    @Override
    public ItemVenda procurarPorId(int id);    
}
