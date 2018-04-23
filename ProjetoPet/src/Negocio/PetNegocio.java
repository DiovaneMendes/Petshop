
package Negocio;
import Repositorio.RepositorioPet;
import java.util.List;
import model.Pet;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto pet, é feito aqui
public class PetNegocio {
    
    //Validando campos e adicionando a lista
    public void salvar(Pet p) throws NegocioException{
        this.validarCamposObrigatorios(p);
        RepositorioPet.getInstance().add(p);
    }
    
    //Listar a lista de pets
    public List<Pet> listarPets() {
        return(RepositorioPet.getInstance().getPets());
    }
    
    //Retorn se não há pets na lista
    public boolean naoHaPets(){
        return RepositorioPet.getInstance().estaVazio();
    }
    
    //Validando campos para não ficar em branco
    private void validarCamposObrigatorios(Pet p) throws NegocioException{
        if(p.getNomePet() == null || p.getNomePet().isEmpty()){
            throw new NegocioException("Campo Nome nao informado!");
        } 
        
        if(p.getTipoAnimal() == null || p.getTipoAnimal().isEmpty()){
            throw new NegocioException("Campo Tipo Animal nao informado!");
        }          
        
        if(p.getDono() == null){
            throw new NegocioException("Campo Dono nao informado!");
        } 
    }
}
