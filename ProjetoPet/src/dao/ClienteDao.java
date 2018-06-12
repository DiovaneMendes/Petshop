package dao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author Diovane
 */
public interface ClienteDao extends Dao<Cliente>{
    public List<Cliente> listarPorNome(String nome);
    public Cliente procurarPorRg(long rg);    
}