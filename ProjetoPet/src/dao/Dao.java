package dao;

import java.util.List;

/**
 *
 * @author Diovane
 */
public interface Dao<T> {
    public void salvar(T dominio);
    public void deletar(T tezinho);
    public void atualizar(T tezinho);
    public List<T> listar();
    public T procurarPorId(int id);
}
