/*
Cadastro de pets: o pet possui como informação o nome, 
o tipo de animal (String) e o cliente (dono do animal).
*/

package Model;

public class Pet {
    private int idPet, fkDono;
    private String nomePet, tipoAnimal, nomeDono, nomeServicoRealizado;
    private Cliente dono;
    
    public Pet(String nomePet, String tipoAnimal, Cliente dono){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.dono = dono;
    }
    
    public Pet(int idPet, String nomePet, String tipoAnimal, Cliente dono){
        this.idPet = idPet;
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.dono = dono;
    }
    
    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
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

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }    
}
