/*
Cadastro de clientes: para cada cliente, anota-se o RG, nome e telefone.
*/
package projetopet;

public class Cliente {
    private String nome;
    private int rg, telefone;

    public Cliente(String nome, int rg, int telefone) {
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public int getRg() {
        return rg;
    }

    public int getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Paciente{" + "rg=" + rg + ", nome=" + nome + ", telefone=" + telefone + '}';
    }
}