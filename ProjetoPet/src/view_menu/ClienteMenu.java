
package view_menu;

/**
 *
 * @author Diovane
 */

//Cria o modelo de menu para o objeto cliente
public class ClienteMenu {
    public static final int OP_ADICIONAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Adicionar Clientes\n"
                + "2- Listar Clientes\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}