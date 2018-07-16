/*
Cadastro de clientes: para cada cliente, anota-se o RG, nome e telefone.
*/
package Model;

//Criando o objeto cliente

public class Cliente{
    private int id;
    private String nome;
    private long rg, telefone;

    public Cliente(String nome, long rg, long telefone) {
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
    }
    
    public Cliente(int id, String nome, long rg, long telefone) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getRg() {
        return rg;
    }

    public long getTelefone() {
        return telefone;
    }
    
    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString(){
        return " " + getNome();
    }
}