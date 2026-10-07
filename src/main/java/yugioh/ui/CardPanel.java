package yugioh.ui;

import javax.swing.*;
import java.awt.*;

/*
 Vista de una carta: imagen, nombre, ATK, DEF y botón "Elegir carta".
 El diseño está en CardPanel.form (IntelliJ GUI Designer). A partir de ese
 archivo IntelliJ genera automáticamente el método $$$setupUI$$$(), que:
 crea rootPanel con GridBagLayout y un borde grabado (etched).</li>
 apila en una sola columna (filas 0 a 4): imagen, nombre, ATK, DEF y botón.<
 Deja 5 px de margen alrededor de la imagen y del botón.</li>

 No se debe editar $$$setupUI$$$() a mano: se reescribe al guardar el .form.
Esta misma vista se usa para las cartas del jugador y de la máquina.
 */
public class CardPanel {
    /* Panel raíz de la carta; es el que se inserta en la ventana principal. */
    private JPanel rootPanel;
    /* Imagen oficial de la carta (168 x 246 px, tamaño de la imagen pequeña de la API). */
    private JLabel lblImagen;
    /* Nombre de la carta. */
    private JLabel lblNombre;
    /*Puntos de ataque (ATK). */
    private JLabel lblAtk;
    /* Puntos de defensa (DEF). */
    private JLabel lblDef;
    /* Botón para que el jugador use esta carta en el turno. */
    private JButton btnElegir;

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEtchedBorder());
        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(0);
        lblImagen.setPreferredSize(new Dimension(168, 246));
        lblImagen.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        rootPanel.add(lblImagen, gbc);
        lblNombre = new JLabel();
        lblNombre.setText("Nombre");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        rootPanel.add(lblNombre, gbc);
        lblAtk = new JLabel();
        lblAtk.setText("ATK: -");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        rootPanel.add(lblAtk, gbc);
        lblDef = new JLabel();
        lblDef.setText("DEF: -");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        rootPanel.add(lblDef, gbc);
        btnElegir = new JButton();
        btnElegir.setText("Elegir carta");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        rootPanel.add(btnElegir, gbc);
    }

    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
