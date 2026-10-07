package yugioh;

import javax.swing.SwingUtilities;

import yugioh.ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        // Todo lo de Swing debe crearse en el hilo de eventos (EDT)
        SwingUtilities.invokeLater(() -> new MainWindow().mostrar());
    }
}
