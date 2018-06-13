/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/
package model;

import java.time.LocalDateTime;

public class Venda {
    private int id;
    private LocalDateTime dataEHora;
    private String pet, servico;
    private double valorTotal;
    
    public Venda(LocalDateTime dataEHora, String pet, String servico, double valorTotal){
        this.dataEHora = dataEHora;
        this.pet = pet;
        this.servico = servico;
        this.valorTotal = valorTotal;
    }
    
    public Venda( LocalDateTime dataEHora, double valorTotal){
        this.dataEHora = dataEHora;
        this.valorTotal = valorTotal;
    }
    
    public Venda(int id, LocalDateTime dataEHora, double valorTotal){
        this.id = id;
        this.dataEHora = dataEHora;
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
