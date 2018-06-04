/*
Cadastro de pets: o pet possui como informação o nome, 
o tipo de animal (String) e o cliente (dono do animal).
*/

package model;

public class Pet {
    private int id_pet;
    private String nomePet, tipoAnimal;
    private Cliente dono;
    private TipoServico servicoRealizado;
    
    public Pet(String nomePet, String tipoAnimal, Cliente dono, TipoServico servicoRealizado){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.dono = dono;
        this.servicoRealizado = servicoRealizado;
    }
    
    public Pet(int id_pet, String nomePet, String tipoAnimal, Cliente dono, TipoServico servicoRealizado){
        this.id_pet = id_pet;
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.dono = dono;
        this.servicoRealizado = servicoRealizado;
    }
    
    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }
    
    public String getNomePet(){
        return nomePet;
    }
    
    public void setNomePet(String nomePet){
        this.nomePet = nomePet;
    }
    
    public String getTipoAnimal(){
        return tipoAnimal;
    }
    
    public void setTipoAnimal(String tipoAnimal){
        this.tipoAnimal = tipoAnimal;
    }
    
    public Cliente getDono(){
        return dono;
    }
    
    public void setDono(Cliente dono){
        this.dono = dono;
    }
    
    public TipoServico getServicoRealizado(){
        return servicoRealizado;
    }
    
    public void setTipoServico(TipoServico servicoRealizado){
        this.servicoRealizado = servicoRealizado;
    }
}
