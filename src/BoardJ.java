import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
/*
public class BoardJ {

    public void draw(int xMax , int yMax , int shape , int bw) {

        LinkedList<Piece> pieces = new LinkedList<>();
        Piece s=new Piece(1,1,true,"R",pieces);
        Piece q=new Piece(2,2,true,"QQ",pieces);

        JFrame frame = new JFrame("Board");
        frame.setBounds(10,10,512*xMax/8,512*yMax/8);
        JPanel pn =new JPanel(){

            @Override
            public void paint(Graphics g) {
                for (int y = 0 ; y < yMax ; y++) {

                    for (int x = 0 ; x < xMax ; x++ ) {

                        if ( (y + x) % 2 == 0) {
                            g.setColor(Color.LIGHT_GRAY);

                        } else {
                            if (bw ==1)
                                g.setColor(Color.BLACK);
                            else
                                g.setColor(Color.LIGHT_GRAY);
                        }

                        if (shape==0)
                            g.fillOval(x * 64, y * 64, 64, 64);
                        else g.fill3DRect(x * 64, y * 64, 64, 64,true);

                    }

                }

                for (Piece p : pieces) {
                    g.drawString(p.pieceName, p.xp * 64, p.yp * 64);
                }

               // g.drawString("R", 64, 64);


            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);



    }
}*/
