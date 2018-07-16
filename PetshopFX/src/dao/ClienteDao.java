package dao;



import java.util.List;
import Model.Cliente;

/**
 *
 * @author Diovane
 */
public interface ClienteDao extends Dao<Cliente>{
    @Override
    public Cliente procurarPorId(int id); 
    public Cliente procurarPorRg(long rg);     
    public List<Cliente> listarPorNome(String nome);
}
