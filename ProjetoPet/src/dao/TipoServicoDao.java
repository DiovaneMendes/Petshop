package dao;

import java.util.List;
import model.TipoServico;

/**
 *
 * @author Diovane
 */
public interface TipoServicoDao extends Dao<TipoServico>{
    @Override
    public TipoServico procurarPorId(int numeroServico);
    public List<TipoServico> listarPorNome(String nome);    
}