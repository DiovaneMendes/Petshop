/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/
package model;

import java.time.LocalDateTime;

public class Venda {
    private int id, fkVenda;
    private LocalDateTime dataEHora;
    private String pet, servico;
    private double valorTotal;
    
    public Venda(LocalDateTime dataEHora, String pet, String servico, double valorTotal){
        this.dataEHora = dataEHora;
        this.pet = pet;
        this.servico = servico;
        this.valorTotal = valorTotal;
    }
    
    public Venda( LocalDateTime dataEHora, int fkVenda, double valorTotal){
        this.dataEHora = dataEHora;
        this.fkVenda = fkVenda;
        this.valorTotal = valorTotal;
    }
    
    public Venda(int id, LocalDateTime dataEHora, int fkVenda, double valorTotal){
        this.id = id;
        this.dataEHora = dataEHora;
        this.fkVenda = fkVenda;
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataEHora() {
        return dataEHora;
    }

    public double getValorTotal() {
        return valorTotal;
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

    public void setDataEHora(LocalDateTime dataEHora) {
        this.dataEHora = dataEHora;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
