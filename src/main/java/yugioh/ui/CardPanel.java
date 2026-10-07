package yugioh.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import yugioh.model.Card;

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

    // ---- Comportamiento (fuera del código generado por el diseñador) ----

    /* Tamaño con el que se dibuja la imagen (la de la API es de 168 x 246). */
    private static final int IMG_W = 112;
    private static final int IMG_H = 164;

    /* Carta que muestra este panel (null si está vacío). */
    private Card card;

    public CardPanel() {
        lblImagen.setPreferredSize(new Dimension(IMG_W, IMG_H));
    }

    /* Muestra una carta con su imagen (si la imagen no cargó, se muestra un texto). */
    public void showCard(Card card, ImageIcon image) {
        this.card = card;
        lblImagen.setEnabled(true);
        lblImagen.setIcon(image == null ? null : new ImageIcon(
                image.getImage().getScaledInstance(IMG_W, IMG_H, Image.SCALE_SMOOTH)));
        lblImagen.setText(image == null ? "Sin imagen" : "");
        lblNombre.setText("<html><div style='text-align:center;width:130px'>"
                + escapeHtml(card.getName()) + "</div></html>");
        lblAtk.setText("ATK: " + card.getAtk());
        lblDef.setText("DEF: " + card.getDef());
    }

    /* Deja el panel vacío, como al abrir la aplicación. */
    public void clear() {
        card = null;
        lblImagen.setEnabled(true);
        lblImagen.setIcon(null);
        lblImagen.setText("");
        lblNombre.setText("Nombre");
        lblAtk.setText("ATK: -");
        lblDef.setText("DEF: -");
        btnElegir.setEnabled(false);
    }

    /* Marca la carta como ya jugada: imagen en gris y botón deshabilitado. */
    public void markUsed() {
        lblImagen.setEnabled(false);
        btnElegir.setEnabled(false);
    }

    public Card getCard() { return card; }

    public void setChooseEnabled(boolean enabled) { btnElegir.setEnabled(enabled); }

    /* Las cartas de la máquina no se eligen, por eso se les oculta el botón. */
    public void setChooseVisible(boolean visible) { btnElegir.setVisible(visible); }

    public void addChooseListener(ActionListener listener) { btnElegir.addActionListener(listener); }

    private static String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

}
