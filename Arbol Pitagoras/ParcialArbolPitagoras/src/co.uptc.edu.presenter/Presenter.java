import java.awt.Color;
import java.awt.Graphics;

public class Presenter {
    private Pitagoras model;
    private View view;
    private int width, height;

    public Presenter(Pitagoras model, View view) {
        this.model = model;
        this.view = view;
    }

    public void drawCuadrado(int depth) {
        model.setDepth(depth);
        view.repaint();
    }

    public void paintCuadrado(Graphics g) {
        width = view.getWidth();
        height = view.getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);

        int initialSize = Math.min(width, height) / 4;

        int x = (width - initialSize) / 2;
        int y = height - initialSize;

      
        drawSquare(g, x, y, initialSize);

      
        drawPitagorasArbol(g, model.getDepth(), x, y, initialSize, 0);
    }

    private void drawPitagorasArbol(Graphics g, int depth, int x, int y, int size, int iteration) {
        if (depth == 0)
            return;

        int baseX = x - size / 2;
        int baseY = y - size;
        int triangleHeight = size / 2;
        int triangleBase = size;

        if (iteration == 1) {
            drawTriangle(g, baseX, baseY, triangleBase, triangleHeight);

            int smallSquareSize = size / 2;
            drawSquare(g, x - triangleBase / 2, y - size, smallSquareSize);
            drawSquare(g, x + triangleBase / 2 - smallSquareSize, y - size, smallSquareSize);

           
            int squareSize = smallSquareSize / 2;
            int squareOffset = (int) (smallSquareSize * Math.sqrt(2) / 2);

            drawInclinedSquare(g, x - triangleBase / 2 + squareSize / 2, y - size - squareOffset - squareSize, squareSize);
            drawInclinedSquare(g, x + triangleBase / 2 - smallSquareSize - squareSize / 2, y - size - squareOffset - squareSize, squareSize);
        }

        drawPitagorasArbol(g, depth - 1, baseX - triangleBase / 2, baseY, size / 2, iteration + 1);
        drawPitagorasArbol(g, depth - 1, baseX + triangleBase / 2 - size / 2, baseY, size / 2, iteration + 1);
    }

    private void drawSquare(Graphics g, int x, int y, int size) {
        g.drawRect(x, y - size, size, size);
        g.fillRect(x, y - size, size, size);
    }

    private void drawInclinedSquare(Graphics g, int x, int y, int size) {
        int[] xPoints = {x, x + size, x + size, x};
        int[] yPoints = {y, y - size, y - size + size / 2, y + size / 2};
        g.drawPolygon(xPoints, yPoints, 4);
    }

    private void drawTriangle(Graphics g, int x, int y, int baseLength, int height) {
        int[] xPoints = { x, x + baseLength, x + baseLength / 2 };
        int[] yPoints = { y, y, y - height };
        g.drawPolygon(xPoints, yPoints, 3);
    }
}
