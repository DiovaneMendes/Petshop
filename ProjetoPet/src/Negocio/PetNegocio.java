
package Negocio;

import Repositorio.RepositorioPet;
import dao.PetDao;
import dao.impl_BD.PetDaoBd;
import java.util.List;
import model.Pet;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto pet, é feito aqui
public class PetNegocio{
    
    private PetDao petDao;
    
    public PetNegocio(){
        petDao = new PetDaoBd();
    }
    
    //Validando campos e adicionando a lista
    public void salvar(Pet p) throws NegocioException{
        this.validarCamposObrigatorios(p);
        //this.validarPetsExistente(p);
        petDao.salvar(p);
    }
    
    //Listar a lista de pets
    public List<Pet> listarPets() {
        return(petDao.listar());
    }
    
    public void deletar(Pet pet) throws NegocioException{
        if(pet == null){
            throw new NegocioException("Pet nao existe!");
        }
        petDao.deletar(pet);
    }
    
    public void atualizar(Pet pet) throws NegocioException {
        if (pet == null) {
            throw new NegocioException("Pet nao existe!");
        }
        this.validarCamposObrigatorios(pet);
        petDao.atualizar(pet);
    }
    
    public List<Pet> listarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(petDao.listarPorNome(nome));
    }
    
     public boolean petExiste(int id) {
        Pet pet = petDao.procurarPorId(id);
        return (pet != null);
    } 
    
    //Retorna se não há pets na lista
    public boolean naoHaPets(){
        return RepositorioPet.getInstance().estaVazio();
    }
    
    public Pet procurarPorNome(String nome) throws NegocioException {
        if (nome == null) {
            throw new NegocioException("Campo RG nao informado");
        }
        Pet pet = petDao.procurarPorNome(nome);
        if (pet == null) {
            throw new NegocioException("Pet nao encontrado");
        }
        return (pet);
    }
    
    //Validando campos para não ficar em branco
    private void validarCamposObrigatorios(Pet p) throws NegocioException{
        if(p.getNomePet() == null || p.getNomePet().isEmpty()){
            throw new NegocioException("Campo Nome nao informado!");
        } 
        
        if(p.getTipoAnimal() == null || p.getTipoAnimal().isEmpty()){
            throw new NegocioException("Campo Tipo de animal nao informado!");
        } 
        
        if(p.getFkDono() == 0){
            throw new NegocioException("Campo Dono nao informado!");
        }
    }
    
    //Validando se o pet é existente
//    private void validarPetsExistente(Pet p) throws NegocioException {
//        if(RepositorioPet.getInstance().petExiste(p.getNomePet()) && 
//              RepositorioPet.getInstance().conferirDono(p.getDono().getNome())){
//            throw new NegocioException("Pet ja existente!");
//        }
//    }
}
