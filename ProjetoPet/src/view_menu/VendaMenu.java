
package view_menu;

/**
 *
 * @author Diovane
 */

//Cria o modelo de menu para o objeto VendaServico
public class VendaMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Efetuar Venda\n"
                + "2- Deletar Venda\n"
                + "3- Atualizar Venda\n"
                + "4- Listar Vendas\n"
                + "5- Consultar Venda por id\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
}
