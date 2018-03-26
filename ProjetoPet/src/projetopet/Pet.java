/*
Cadastro de pets: o pet possui como informação o nome, 
o tipo de animal (String) e o cliente (dono do animal).
*/

package projetopet;

public class Pet {
    private String nomePet, tipoAnimal;
    private Cliente dono;
    private TipoServico servicoRealizado;
    
    public Pet(String nomePet, String tipoAnimal, Cliente dono, TipoServico servicoRealizado){
        this.nomePet = nomePet;
        this.tipoAnimal = tipoAnimal;
        this.dono = dono;
        this.servicoRealizado = servicoRealizado;
    }
    
    public String getNomePet(){
        return nomePet;
    }
    
    public String getTipoanimal(){
        return tipoAnimal;
    }
    
    public Cliente getDono(){
        return dono;
    }
    
    public TipoServico getServicoRealizado(){
        return servicoRealizado;
    }
}
