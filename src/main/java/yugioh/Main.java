package yugioh;

import javax.swing.SwingUtilities;

import yugioh.ui.MainWindow;

//Punto de entrada: abre la ventana del duelo.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().mostrar());
    }
}
