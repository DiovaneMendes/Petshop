package dao;

import java.util.List;
import model.TipoServico;

/**
 *
 * @author Diovane
 */
public interface TipoServicoDao extends Dao<TipoServico>{
    public TipoServico procurarPorNumeroServico(String numeroServico);
    public List<TipoServico> listarPorNome(String nome);    
}