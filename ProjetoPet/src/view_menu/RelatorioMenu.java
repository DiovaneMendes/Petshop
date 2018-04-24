
package view_menu;

/**
 *
 * @author Diovane
 */

//Cria o modelo de menu para o objeto cliente
public class RelatorioMenu{
    public static final int OP_CLIENTE_MAIS_FREQUENTE = 1;
    public static final int OP_MAIS_GASTAM = 2;
    public static final int OP_PET_GERA_MAIS_LUCRO= 3;
    public static final int OP_SERVICO_MAIS_VENDIDO= 4;
    public static final int OP_VENDAS_POR_MES = 5;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){
        return ("\n============MENU RELATORIO============\n"
                + "1- Clientes mais frequentes\n"
                + "2- Clientes que mais gastam\n"
                + "3- Pets que geram mais lucros\n"
                + "4- Servicos mais vendidos\n"
                + "5- Vendas por mes\n"
                + "0- Voltar"
                + "\n======================================");
    }    
}
