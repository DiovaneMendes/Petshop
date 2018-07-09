/*
Cadastro de pets: o pet possui como informação o nome, 
o tipo de animal (String) e o cliente (dono do animal).
*/

package model;

public class Pet {
    private int idPet, fkDono, fkServicoRealizado;
    private String nomePet, tipoAnimal, nomeDono, nomeServicoRealizado;
    
    public Pet(String nomePet, String tipoAnimal, int fkDono){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.fkDono = fkDono;
    }
    
    public Pet(String nomePet, String tipoAnimal, String nomeDono){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.nomeDono = nomeDono;
    }
    
    public Pet(int idPet, String nomePet, String tipoAnimal, int fkDono){
        this.idPet = idPet;
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.fkDono = fkDono;
    }
    
    public Pet(int idPet, String nomePet, String tipoAnimal, String nomeDono){
        this.idPet = idPet;
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.nomeDono = nomeDono;
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
    
    public int getFkDono() {
        return fkDono;
    }
    
    public void setFkDono(int fkDono) {
        this.fkDono = fkDono;
    }
    
    public int getFkServicoRealizado() {
        return fkServicoRealizado;
    }
    
    public void setFkServicoRealizado(int fkServicoRealizado) {
        this.fkServicoRealizado = fkServicoRealizado;
    }
    
    public String getTipoAnimal(){
        return tipoAnimal;
    }
    
    public void setTipoAnimal(String tipoAnimal){
        this.tipoAnimal = tipoAnimal;
    }

    /**
     * @return the nomeDono
     */
    public String getNomeDono() {
        return nomeDono;
    }

    /**
     * @param nomeDono the nomeDono to set
     */
    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    /**
     * @return the nomeServicoRealizado
     */
    public String getNomeServicoRealizado() {
        return nomeServicoRealizado;
    }

    /**
     * @param nomeServicoRealizado the nomeServicoRealizado to set
     */
    public void setNomeServicoRealizado(String nomeServicoRealizado) {
        this.nomeServicoRealizado = nomeServicoRealizado;
    }
}
