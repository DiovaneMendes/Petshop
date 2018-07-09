
package petshopfx.view;

import javax.swing.JOptionPane;

public class PrintUtil {
    //Se for JavaFX 8, alterar para Alert
    public static void printMessageError(String msg) {
            JOptionPane.showMessageDialog(null, 
                    msg,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);        
    }

    public static void printMessageSucesso(String msg) {
            JOptionPane.showMessageDialog(null, 
                    msg,
                    "Sucesso",
                    JOptionPane.PLAIN_MESSAGE);        
    }       
}
