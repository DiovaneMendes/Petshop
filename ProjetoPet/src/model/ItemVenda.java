package model;

/**
 *
 * @author Diovane
 */
public class ItemVenda {
    private int id, fkVenda, fkPet, fkServico;
    private String pet, servico;
    
    public ItemVenda(String pet, String servico){
        this.pet = pet;
        this.servico = servico;
    }
    
    public ItemVenda(int id, String pet, String servico){
        this.id = id;
        this.pet = pet;
        this.servico = servico;
    }

    public ItemVenda(int fkVenda, int fkPet, int fkServico){
        this.fkVenda = fkVenda;
        this.fkPet = fkPet;
        this.fkServico = fkServico;
    } 
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getFkVenda() {
        return fkVenda;
    }

    public void setFkVenda(int fkVenda) {
        this.fkVenda = fkVenda;
    }

    public int getFkPet() {
        return fkPet;
    }

    public void setFkPet(int fkPet) {
        this.fkPet = fkPet;
    }

    public int getFkServico() {
        return fkServico;
    }

    public void setFkServico(int fkServico) {
        this.fkServico = fkServico;
    }

    public String getPet() {
        return pet;
    }
    
    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getServico() {
        return servico;
    }
    
    public void setServico(String servico) {
        this.servico = servico;
    }
}
