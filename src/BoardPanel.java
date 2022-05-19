import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    static final int originX=23;
    static final int originY=37;
    static final int cellSide=53;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(40,40,70,75);

    }
}
