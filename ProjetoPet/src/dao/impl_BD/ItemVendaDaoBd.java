package dao.impl_BD;

import dao.ItemVendaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ItemVenda;

/**
 *
 * @author Diovane
 */
public class ItemVendaDaoBd extends DaoBd<ItemVenda> implements ItemVendaDao{
    @Override
    public void salvar(ItemVenda item) {
        int id = 0;
        try {
            String sql = "INSERT INTO item_venda (venda, pet, tipo_servico) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, item.getFkVenda());
            comando.setInt(2, item.getFkPet());
            comando.setInt(3, item.getFkServico());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                item.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar item de venda no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(ItemVenda item) {
        try {
            String sql = "DELETE FROM item_venda WHERE id = ?";

            conectar(sql);
            comando.setInt(1, item.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar item de venda no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void atualizar(ItemVenda item) {
        try {
            String sql = "UPDATE item_venda SET pet=?, servico=?"
                    + "WHERE id=?";

            conectar(sql);
            comando.setInt(1, item.getFkPet());
            comando.setInt(2, item.getFkServico());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar item de venda no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public List<ItemVenda> listar() {
        List<ItemVenda> listaItemVendas = new ArrayList<>();

        String sql = "SELECT iv.id, p.nome, s.nome"
                + "FROM item_venda iv"
                + "JOIN pet p ON(pet.iv = p.id)"
                + "JOIN servico s ON(servico.iv = s.id);";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("iv.id");
                String pet = resultado.getString("p.nome");
                String servico = resultado.getString("s.nome");

                ItemVenda item = new ItemVenda(id, pet, servico);
                listaItemVendas.add(item);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os itens de venda do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaItemVendas);
    }
    
    @Override
    public ItemVenda procurarPorId(int id) {
        String sql = "SELECT iv.id, p.nome, s.nome"
                + "FROM item_venda iv"
                + "JOIN pet p ON(pet.iv = p.id)"
                + "JOIN servico s ON(servico.iv = s.id);";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String pet = resultado.getString("p.nome");
                String servico = resultado.getString("s.nome");

                ItemVenda item = new ItemVenda(id, pet, servico);

                return item;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o itens de venda pelo id no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }   
}
