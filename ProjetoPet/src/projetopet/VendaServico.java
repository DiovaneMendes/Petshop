/*
Venda de Serviços: registra uma venda de serviços realizados, 
relacionando o dia/hora realizado (LocalDateTime), 
o cliente, os serviços realizados vinculados com o pet do cliente.
O sistema deverá calcular o valor total da venda.
*/
package projetopet;

public class VendaServico {
    private String dataEHora;
    private Cliente cliente;
    private TipoServico tipoServico;
    private double valorTotal;
    
    public VendaServico(String dataEHora, Cliente cliente, TipoServico tipoServico, double valorTotal){
        this.dataEHora = dataEHora;
        this.cliente = cliente;
        this.tipoServico = tipoServico;
        this.valorTotal = valorTotal;
    }

    public String getDataEHora() {
        return dataEHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
