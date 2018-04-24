
package view_menu;

/**
 *
 * @author Diovane
 */

//Cria o modelo de menu para o objeto VendaServico
public class VendaMenu{
    public static final int OP_ADICIONAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){
        return ("\n=============MENU VENDA===============\n"
                + "1- Adicionar Vendas\n"
                + "2- Listar Vendas\n"
                + "0- Voltar"
                + "\n======================================");
    } 
}
