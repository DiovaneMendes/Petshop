package dao;

import java.util.List;
import model.Pet;

/**
 *
 * @author Diovane
 */
public interface PetDao extends Dao<Pet>{
    @Override
    public Pet procurarPorId(int idPet);
    public Pet procurarPorNome(String nome);
    public List<Pet> listarPorNome(String nome);    
}