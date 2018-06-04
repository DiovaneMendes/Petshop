package dao;

import java.util.List;
import model.Pet;

/**
 *
 * @author Diovane
 */
public interface PetDao extends Dao<Pet>{
    public Pet procurarPorId(String id_pet);
    public List<Pet> listarPorNome(String nome);    
}