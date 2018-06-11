package dao;

import java.time.LocalDateTime;
import java.util.List;
import model.Venda;

/**
 *
 * @author Diovane
 */

public interface VendaDao extends Dao<Venda>{
    public List<Venda> listarPorData(LocalDateTime data); 
    @Override
    public Venda procurarPorId(int id); 
}