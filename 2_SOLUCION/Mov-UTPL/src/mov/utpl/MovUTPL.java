package mov.utpl;

import Vista.VistaPrincipal;
import javax.swing.SwingUtilities;

public class MovUTPL {

    public static void main(String[] args) {
        
       
       
    SwingUtilities.invokeLater(() -> {
            new VistaPrincipal().setVisible(true);
        });
    }

}