package yugioh;

import javax.swing.SwingUtilities;

import yugioh.ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().mostrar());
    }
}
