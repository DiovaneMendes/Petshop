package dao;

import java.util.List;
import model.TipoServico;

/**
 *
 * @author Diovane
 */
public interface TipoServicoDao extends Dao<TipoServico>{
    public List<TipoServico> listarPorNome(String nome);  
    public TipoServico procurarPorNome(String nome);
    @Override
    public TipoServico procurarPorId(int numeroServico);  
}