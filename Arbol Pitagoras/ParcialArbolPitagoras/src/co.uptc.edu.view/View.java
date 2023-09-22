import javax.swing.*;
import java.awt.*;

class View extends JPanel {
    private Presenter presenter;

    public View() {
        setBackground(Color.BLACK);

        JButton drawButton = new JButton("Dibujar");
        JTextField iterationsField = new JTextField(20);

        drawButton.addActionListener(e -> {
            try {
                int iteration = Integer.parseInt(iterationsField.getText());
                presenter = new Presenter(new Pitagoras(), this);
                presenter.drawCuadrado(iteration);
                repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(View.this, "Ingrese un número válido.");
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Iteraciones: "));
        controlPanel.add(iterationsField);
        controlPanel.add(drawButton);
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (presenter != null) {
            presenter.paintCuadrado(g);
        }
    }
}
