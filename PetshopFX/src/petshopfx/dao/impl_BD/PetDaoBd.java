package dao.impl_BD;

import dao.PetDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;

/**
 *
 * @author Diovane
 */
public class PetDaoBd extends DaoBd<Pet> implements PetDao {
    @Override
    public void salvar(Pet pet) {
        int idPet = 0;
        try {
            String sql = "INSERT INTO pet (nome, tipo_animal, cliente) "
                    + "VALUES (?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, pet.getNomePet());
            comando.setString(2, pet.getTipoAnimal());
            comando.setInt(3, pet.getFkDono());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                idPet = resultado.getInt(1);
                pet.setIdPet(idPet);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar pet no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void deletar(Pet pet) {
        try {
            String sql = "DELETE FROM pet WHERE id = ?";

            conectar(sql);
            comando.setInt(1, pet.getIdPet());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar pet no Banco de Dados!");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void atualizar(Pet pet) {
        try {
            String sql = "UPDATE pet SET nome=?, tipo_animal=?, cliente=?"
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, pet.getNomePet());
            comando.setString(2, pet.getTipoAnimal());
            comando.setInt(3, pet.getFkDono());
            comando.setInt(4, pet.getIdPet());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar pet no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public List<Pet> listar() {
        List<Pet> listaPets = new ArrayList<>();

        String sql = "SELECT * FROM pet";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nomePet = resultado.getString("nome");
                String tipoAnimal = resultado.getString("tipo_animal");
                int fkDono = resultado.getInt("cliente");
                
                Pet pet = new Pet(id, nomePet, tipoAnimal, fkDono);
                listaPets.add(pet);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pet do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaPets);
    }
    
    @Override
    public Pet procurarPorId(int idPet) {
        String sql = "SELECT"
                + "p.nome, p.tipo_animal, c.nome"
                + "FROM pet p"
                + "JOIN cliente c ON(c.id = p.cliente)"
                + "WHERE p.idPet=?";

        try {
            conectar(sql);
            comando.setInt(1, idPet);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nomePet = resultado.getString("p.nome");
                String tipoAnimal = resultado.getString("p.tipo_animal");
                String nomeDono = resultado.getString("c.nome");
                
                Pet pet = new Pet(nomePet, tipoAnimal, nomeDono);

                return pet;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o pet pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
    @Override
    public List<Pet> listarPorNome(String nome) {
        List<Pet> listaPets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE nome LIKE ?";
        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String tipoAnimal = resultado.getString("tipo_animal");
                int fkDono = resultado.getInt("cliente");

                Pet pet = new Pet(id, nome, tipoAnimal, fkDono);

                listaPets.add(pet);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pets pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaPets);
    }
    
    @Override
    public Pet procurarPorNome(String nome) {
        String sql = "SELECT * FROM pet WHERE nome = ?";
        try {
            conectar(sql);
            comando.setString(1, nome);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String tipoAnimal = resultado.getString("tipo_animal");
                int NomeDono = resultado.getInt("cliente");

                Pet pet = new Pet(id, nome, tipoAnimal, NomeDono);
                return pet;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o pet pelo o nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
}
