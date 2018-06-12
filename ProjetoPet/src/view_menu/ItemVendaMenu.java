
package view_menu;

/**
 *
 * @author Diovane
 */
public class ItemVendaMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Item\n"
                + "2- Deletar Item\n"
                + "3- Atualizar dados do Item\n"
                + "0- Sair"
                + "\n--------------------------------------");
    }    
}