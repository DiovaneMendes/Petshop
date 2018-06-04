package dao;

import java.util.List;

/**
 *
 * @author Diovane
 */
public interface Dao<T> {
    public void salvar(T dominio);
    public void deletar(T paciente);
    public void atualizar(T paciente);
    public List<T> listar();
    public T procurarPorId(int id);
}
