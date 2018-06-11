package dao.impl_BD;

import dao.TipoServicoDao;
import model.TipoServico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Diovane
 */
public class TipoServicoDaoBd extends DaoBd<TipoServico> implements TipoServicoDao{
    @Override
    public void salvar(TipoServico tipoServico) {
        int id = 0;
        try {
            String sql = "INSERT INTO tipo_servico (numero, nome, tipo_atendimento, preco) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, tipoServico.getNumeroServico());
            comando.setString(2, tipoServico.getNomeServico());
            comando.setString(3, tipoServico.getTipoDeAtendimento());
            comando.setDouble(4, tipoServico.getPrecoServico());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                tipoServico.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar tipo de servico no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(TipoServico tipoServico) {
        try {
            String sql = "DELETE FROM tipo_servico WHERE id = ?";

            conectar(sql);
            comando.setInt(1, tipoServico.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar tipo de servico no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void atualizar(TipoServico tipoServico) {
        try {
            String sql = "UPDATE tipo_servico SET numero_servico=?, nome=?, tipo_atendimento=?, preco=?"
                    + "WHERE id=?";

            conectarObtendoId(sql);
            comando.setInt(1, tipoServico.getNumeroServico());
            comando.setString(2, tipoServico.getNomeServico());
            comando.setString(3, tipoServico.getTipoDeAtendimento());
            comando.setDouble(4, tipoServico.getPrecoServico());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar tipo de servico no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public List<TipoServico> listar() {
        List<TipoServico> listaServicos = new ArrayList<>();

        String sql = "SELECT * FROM tipo_servico";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int numeroServico = resultado.getInt("numero_servico");
                String nome = resultado.getString("nome");
                String tipoAtendimento = resultado.getString("tipo_atendimento");
                double preco = resultado.getLong("preco");

                TipoServico tipoServico = new TipoServico(id, numeroServico, nome, tipoAtendimento, preco);
                listaServicos.add(tipoServico);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os tipo de servicos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaServicos);
    }
    
    @Override
    public TipoServico procurarPorId(int id) {
        String sql = "SELECT * FROM tipo_servico WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idServico = resultado.getInt("id");
                int numeroServico = resultado.getInt("numero_servico");
                String nome = resultado.getString("nome");
                String tipoAtendimento = resultado.getString("tipo_atendimento");
                double preco = resultado.getLong("preco");

                TipoServico tipoServico = new TipoServico(idServico, numeroServico, nome, tipoAtendimento, preco);

                return tipoServico;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
    @Override
    public List<TipoServico> listarPorNome(String nome) {
        List<TipoServico> listaTipoServicos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_servico WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int numeroServico = resultado.getInt("numero_servico");
                String nomeServico = resultado.getString("nome");
                String tipoAtendimento = resultado.getString("tipo_atendimento");
                double preco = resultado.getLong("preco");

                TipoServico tipoServico = new TipoServico(id, numeroServico, nomeServico, tipoAtendimento, preco);

                listaTipoServicos.add(tipoServico);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os tipo de servicos pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaTipoServicos);
    }

    @Override
    public TipoServico procurarPorNome(String nomeX) {
        String sql = "SELECT * FROM tipo_servico WHERE nome = ?";
        try {
            conectar(sql);
            comando.setString(1, nomeX);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int numero = resultado.getInt("numero_servico");
                String nome = resultado.getString("nome");
                String tipoAtendimento = resultado.getString("tipo_atendimento");
                double valor = resultado.getInt("preco");

                TipoServico pet = new TipoServico(numero, nome, tipoAtendimento, valor);
                return pet;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o servico pelo o nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
