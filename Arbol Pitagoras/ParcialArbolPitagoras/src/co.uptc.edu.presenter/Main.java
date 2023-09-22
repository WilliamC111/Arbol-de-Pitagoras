import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Árbol de Pitágoras");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            View view = new View();
            frame.add(view);
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
