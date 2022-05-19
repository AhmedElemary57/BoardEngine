import javax.imageio.ImageIO
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.Graphics
import java.io.File
import javax.swing.JFrame
import javax.swing.JPanel


class Board {
 // ublic void draw(int xMax , int yMax , int shape , int bw)

  def draw(xMax:Int ,yMax:Int ,shape: Int , wb: Int,game:String, pieces: List[Piece]): Unit = {
    val all = ImageIO.read(new File(s".\\utils\\$game.png"))
    val imgs = new Array[Image](12)
    var ind = 0
    var y = 0
    while ( {
      y < 400
    }) {
      var x = 0
      while ( {
        x < 1200
      }) {
        imgs(ind) = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.TYPE_USHORT_GRAY)
        ind += 1
        x += 200
      }
      y += 200
    }

    var frame = new JFrame(s"$game");
    frame.setBounds(10,10,512*xMax/8,512*yMax/8);
    //frame.setUndecorated(true)
    val pn = new JPanel() {
      override def paint(g: Graphics): Unit = {
        for (y <- 0 until yMax) {
          for (x <- 0 until xMax) {
            if ((y + x) % 2 == 0) g.setColor(Color.LIGHT_GRAY)
            else if (wb == 1) g.setColor(Color.darkGray)
            else g.setColor(Color.LIGHT_GRAY)
            if (shape == 0) g.fillOval(x * 64, y * 64, 64, 64)
            else g.fill3DRect(x * 64, y * 64, 64, 64, true)
          }
        }
        for (i <- pieces.indices) {
          var ind = 0
          if (game.equals("chess")){
            ind=Chess.chessPieces(i,ind)
          }
          else if (game.equals("xo")){
            if (pieces(i).pieceName.equals("X"))
              ind=0;
            else ind=1
          }
          else if (game.equals("checkers")){
            if (pieces(i).pieceName.equals("X"))
              ind=0;
            else ind=1
          }

          g.drawImage( imgs(ind%12), pieces(i).xp * 64 , pieces(i).yp * 64,this)

        }
        // g.drawString("R", 64, 64);
      }
    }
    frame.add(pn)
    frame.setDefaultCloseOperation(3)
    frame.setVisible(true)

  }

}
