package dao.impl_BD;

import dao.ClienteDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Diovane
 */
public class ClienteDaoBd extends DaoBd<Cliente> implements ClienteDao{
    @Override
    public void salvar(Cliente cliente) {
        int id = 0;
        try {
            String sql = "INSERT INTO cliente (nome, rg, telefone) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, cliente.getNome());
            comando.setLong(2, cliente.getRg());
            comando.setLong(3, cliente.getTelefone());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                cliente.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar cliente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";

            conectar(sql);
            comando.setInt(1, cliente.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void atualizar(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET nome=?, rg=?, telefone=?"
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setLong(2, cliente.getRg());
            comando.setLong(3, cliente.getTelefone());
            comando.setInt(4, cliente.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar cliente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public List<Cliente> listar() {
        List<Cliente> listaPacientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                long telefone = resultado.getLong("telefone");

                Cliente cliente = new Cliente(id, nome, rg, telefone);
                listaPacientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaPacientes);
    }
    
    @Override
    public Cliente procurarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                long telefone = resultado.getLong("telefone");

                Cliente cliente = new Cliente(id, nome, rg, telefone);

                return cliente;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
    @Override
    public Cliente procurarPorRg(long rg) {
        String sql = "SELECT * FROM cliente WHERE rg = ?";

        try {
            conectar(sql);
            comando.setLong(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                long telefone = resultado.getLong("telefone");

                Cliente cliente = new Cliente(id, nome, rg, telefone);

                return cliente;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
    @Override
    public List<Cliente> listarPorNome(String nome) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nomeX = resultado.getString("nome");
                long rg = resultado.getLong("rg");
                long telefone = resultado.getLong("telefone");

                Cliente cliente = new Cliente(id, nome, rg, telefone);

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }
}
