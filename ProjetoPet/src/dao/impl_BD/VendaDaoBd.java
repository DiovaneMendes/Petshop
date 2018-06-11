/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl_BD;

import dao.VendaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Venda;

/**
 *
 * @author Diovane
 */
public class VendaDaoBd extends DaoBd<Venda> implements VendaDao{
    @Override
    public void salvar(Venda venda) {
        int id = 0;
        try {
            String sql = "INSERT INTO venda (data_hora, cliente, tipo_servico, valor_total) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            Timestamp timestamp = Timestamp.valueOf(venda.getDataEHora());
            comando.setTimestamp(1, timestamp);
            comando.setInt(2, venda.getFkDono());
            comando.setInt(3, venda.getFkServico());
            comando.setDouble(4, venda.getValorTotal());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()){
                id = resultado.getInt(1);
                venda.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar venda no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(Venda venda) {
        try {
            String sql = "DELETE FROM venda WHERE id = ?";

            conectar(sql);
            comando.setInt(1, venda.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar venda no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void atualizar(Venda venda) {
        try {
            String sql = "UPDATE venda SET data_hora=?, cliente=?, tipo_servico=?"
                    + "WHERE id=?";

            conectar(sql);
            Timestamp timestamp = Timestamp.valueOf(venda.getDataEHora());
            comando.setTimestamp(1, timestamp);
            comando.setLong(2, venda.getFkDono());
            comando.setLong(3, venda.getFkServico());
            comando.setInt(4, venda.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar venda no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public List<Venda> listar() {
        List<Venda> listaVendas = new ArrayList<>();

        String sql = "SELECT venda.id, venda.data_hora"
                + "cliente.nome, tipo_servico.nome, venda.valor_total"
                + " FROM venda "
                + "JOIN cliente ON(cliente.id = venda.cliente)"
                + "JOIN tipo_servico ON(tipo_servico.id = venda.tipo_servico)";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("venda.id");
                Timestamp dataT = resultado.getTimestamp("venda.data_hora");
                LocalDateTime dataL = dataT.toLocalDateTime();
                String dono = resultado.getString("cliente.nome");
                String servico = resultado.getString("tipo_servico.nome");
                double valorTotal = resultado.getDouble("venda.valor_total");

                Venda venda = new Venda(id, dataL, dono, servico, valorTotal);
                listaVendas.add(venda);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os vendas do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaVendas);
    }

    @Override
    public Venda procurarPorId(int id) {
        String sql = "SELECT * FROM venda WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                Timestamp dataT = resultado.getTimestamp("venda.data_hora");
                LocalDateTime dataL = dataT.toLocalDateTime();
                String dono = resultado.getString("cliente.nome");
                String servico = resultado.getString("tipo_servico.nome");
                double valorTotal = resultado.getDouble("venda.valor_total");

                Venda venda = new Venda(id, dataL, dono, servico, valorTotal);

                return venda;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o venda pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
}
