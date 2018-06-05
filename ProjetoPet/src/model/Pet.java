/*
Cadastro de pets: o pet possui como informação o nome, 
o tipo de animal (String) e o cliente (dono do animal).
*/

package model;

public class Pet {
    private int idPet, fkDono, fkServicoRealizado;
    private String nomePet, tipoAnimal, nomeDono, nomeServicoRealizado;
    private Cliente dono;
    private TipoServico servicoRealizado;
    
    public Pet(String nomePet, String tipoAnimal, Cliente dono, TipoServico servicoRealizado){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.dono = dono;
        this.servicoRealizado = servicoRealizado;
    }
    
    public Pet(int idPet, String nomePet, String tipoAnimal, int fkDono, int fkServicoRealizado){
        this.idPet = idPet;
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.fkDono = fkDono;
        this.fkServicoRealizado = fkServicoRealizado;
    }
    
    public Pet(String nomePet, String tipoAnimal, String nomeDono, String nomeServicoRealizado){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.nomeDono = nomeDono;
        this.nomeServicoRealizado = nomeServicoRealizado;
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
