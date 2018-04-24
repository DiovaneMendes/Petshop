
package Repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Pet;

/**
 *
 * @author Diovane
 */

public class RepositorioPet {
    private List<Pet> pets;
    private static RepositorioPet instance = null;
    
    //Criando lista de pets
    private RepositorioPet() {
        pets = new ArrayList<Pet>();
    }
    
    //Retorna um novo repositorio caso esteja vazio
    public static RepositorioPet getInstance() {
        if(instance == null) instance = new RepositorioPet();
        return instance;
    }
    
    //Adiciona pet
    public boolean add(Pet pet) {
        return (pets.add(pet));
    }
    
    //Retorna se a lista está vazia ou não
    public boolean estaVazio(){
        return pets.isEmpty();
    }
    
    //Busca a lista de pets
    public List<Pet> getPets() {
        return pets;
    }
    
    //Verifica se o pet existe ou não
    public boolean petExiste(String nome) {
        for (Pet pet : pets) {
            if (pet.getNomePet().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    
    //Busca um cliente dentro da lista
    public Pet buscarPet(String nome) {
        for (Pet pet : pets) {
            if (pet.getNomePet().equals(nome)) {
                return pet;
           }
        }
        return null;
    }
}
