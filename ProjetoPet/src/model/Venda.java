/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda {
    private int id, fkDono, fkServico;
    private LocalDateTime dataEHora;
    private String cliente, servico;
    private double valorTotal;
    
    public Venda(int id, LocalDateTime dataEHora, String cliente, String servico, double valorTotal){
        this.dataEHora = dataEHora;
        this.cliente = cliente;
        this.servico = servico;
        this.valorTotal = valorTotal;
    }
    
    public Venda(int id, LocalDateTime dataEHora, int fkDono, int fkServico, double valorTotal){
        this.id = id;
        this.dataEHora = dataEHora;
        this.fkDono = fkDono;
        this.fkServico = fkServico;
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

    public int getFkDono() {
        return fkDono;
    }

    public void setFkDono(int fkDono) {
        this.fkDono = fkDono;
    }

    public int getFkServico() {
        return fkServico;
    }

    public void setFkServico(int fkServico) {
        this.fkServico = fkServico;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
