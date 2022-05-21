import Board.{chessButton, xoButton}
import BoardEngine.{checkersDrawer, chessController, chessDrawer, connect4Controller, connect4Drawer, state, ticTacToeController, xoDrawer}

import javax.imageio.ImageIO
import java.awt.{Color, Dimension, Graphics, Image}
import java.awt.image.BufferedImage
import java.awt.event.{ActionEvent, ActionListener}
import java.io.File
import javax.swing.{JButton, JFrame, JPanel, JTextField}

object Board extends ActionListener{
  var game:String = "chess";
  var flag=0;

  var frame = new JFrame("Game")
  frame.setSize(512,512)

  var submitButton = new JButton("Submit")
  var chessButton = new JButton("Chess")
  var xoButton = new JButton("XO")
  var connect4Button = new JButton("Connect 4")
  var checkersButton = new JButton("Checkers")
  chessButton.setBounds(128,40,192,64);
  chessButton.addActionListener(this)
  xoButton.setBounds(128,140,192,64);
  xoButton.addActionListener(this)
  connect4Button.setBounds(128,240,192,64);
  connect4Button.addActionListener(this)
  checkersButton.setBounds(128,340,192,64);
  checkersButton.addActionListener(this)
  frame.add(chessButton)
  frame.add(xoButton)
  frame.add(connect4Button)
  frame.add(checkersButton)
  def initialization(): Unit ={
    frame.setLayout(null);



  }


  val jTextField = new JTextField
  jTextField.setPreferredSize(new Dimension(50, 40))

  /**
   * Draw the initial board dynamically
   * @param xMax the board width
   * @param yMax the board height
   * @param shape zero represents oval, Rectangle otherwise
   * @param wb 1 represents white and black cells, cells are same color otherwise
   * @param game represents the game type
   * @param pieces the pieces to be drawn on board
   * */
  def draw(xMax:Int ,yMax:Int ,shape: Int , wb: Int, game:String, pieces: Array[Array[Char]]): Unit = {
    val all = ImageIO.read(new File(s".\\utils\\$game.png"))
    this.game=game;
    val imgs = new Array[Image](12)
    var ind = 0
    var y = 0
    while (y < 400) {
      var x = 0
      while (x < 1200) {
        imgs(ind) = all.getSubimage(x, y, 200, 200)
          .getScaledInstance(64, 64, BufferedImage.TYPE_USHORT_GRAY)
        ind += 1
        x += 200
      }
      y += 200
    }

  //  frame.setBounds(10,10,512*xMax/8,512*yMax/8);
    //frame.setUndecorated(true)
    var letters = Array('a','b','c','d','e','f','g','h')
    var numbers = Array('8','7','6','5','4','3','2','1')

    val pn = new JPanel() {
      override def paint(g: Graphics): Unit = {
        for (y <- 0 until yMax+1) {
          for (x <- 0 until xMax+1) {
            if(!(y==yMax || x==xMax)) {
              if ((y + x) % 2 == 0) g.setColor(Color.LIGHT_GRAY)
              else if (wb == 1) g.setColor(Color.darkGray)
              else g.setColor(Color.LIGHT_GRAY)
              if (shape == 0) g.fillOval(x * 64, y * 64, 64, 64)
              else g.fill3DRect(x * 64, y * 64, 64, 64, true)
            }else {
              if (y==yMax && x<xMax){
                g.drawString(letters(x).toString,x * 64+32,y * 64+32)
              }
              else if(x==xMax && y<xMax) {
                g.drawString(numbers(y).toString, x * 64+32, y * 64+32)

              }
            }

          }
        }

        for (i <- pieces.indices) {
          for(j <- pieces(0).indices){
            if(!pieces(i)(j).equals('.')){
              var ind = 0
              if (game.equals("chess")) ind = Chess.chessPieces(pieces(i)(j))
              else if (game.equals("xo")) {if (pieces(i)(j)=='x') ind = 0
              else if (pieces(i)(j)=='o') ind = 1}

              else if (game.equals("checkers")) {
                if (pieces(i)(j)=='w') ind = 0
              else if (pieces(i)(j)=='b') ind = 7
              }

              else if(game.equals("connect4")) {
                  if (pieces(i)(j)=='r') ind = 0
                  else if (pieces(i)(j)=='y') ind = 1
              }

              g.drawImage( imgs(ind%12) , j * 64  , i * 64 ,this)
            }
          }
        }
        // g.drawString("R", 64, 64);
      }
    }

    pn.setLayout(null);

    if (flag==0) {

      pn.setSize(xMax*64,yMax*64)
      frame.add(pn)
      jTextField.setBounds(10,64*(yMax+1)+5,63*xMax,50)
      submitButton.setBounds(64*xMax,64*(yMax+1)+5,80,50)
      submitButton.addActionListener(this)
      jTextField.setBounds(10,64*(yMax+1)+5,63*xMax,50)
      submitButton.setBounds(64*(xMax+1),64*(yMax+1)+5,80,50)

      pn.add(submitButton)
      pn.add(jTextField)
      pn.setVisible(true)
      frame.setDefaultCloseOperation(3)

      frame.setVisible(true)

      flag=1
    }


   // frame.add(jTextField)
  }


  def refresh(xMax:Int, yMax:Int, shape: Int, wb: Int, game:String, pieces: Array[Array[Char]]): Unit ={
    frame.setVisible(false)
    draw(xMax ,yMax ,shape, wb, game, pieces)
    frame.setVisible(true)
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    if (e.getSource==submitButton) {

      if (game.equals("chess")) {
        println("go to chess 4 controller ")

        BoardEngine.gameEngine(jTextField.getText,chessController,chessDrawer)
      }

      else if (game.equals("xo")){
        println("go to xo 4 controller ")

        BoardEngine.gameEngine(jTextField.getText,ticTacToeController,xoDrawer)
      }

      else if (game.equals("connect4")){
        println("go to connect 4 controller ")
        BoardEngine.gameEngine(jTextField.getText,connect4Controller,connect4Drawer)

      }
      else if (game.equals("checkers")){
        println("go to connect checkers ")
        BoardEngine.gameEngine(jTextField.getText,chessController,checkersDrawer)

      }
    };
    if (e.getSource==chessButton){
      println("in chess button")
      BoardEngine.state=Chess.initialState();
      frame.setVisible(false)
      frame = new JFrame();
      frame.setSize(700,700)
      refresh(8,8,1,1,"chess",state)


    }
    if (e.getSource==xoButton){
      println("in xo")
      BoardEngine.state=XO.initialState();
      frame.setVisible(false)

      frame = new JFrame();
      frame.setSize(500,500)
      refresh(3,3,1,0,"xo",state)

    }
    if (e.getSource==connect4Button){
      println("in connect 4")
      BoardEngine.state=Connect4.initialState();
      frame.setVisible(false)

      frame = new JFrame();
      frame.setSize(500,500)
      refresh(7,6,0,0,"connect4", state)

    }
    if (e.getSource==checkersButton){
      println("in checkersButton")
      BoardEngine.state=Checkers.board;
      frame.setVisible(false)
      frame = new JFrame();
      frame.setSize(500,500)
      refresh(8,8,1,1,"checkers",state)

    }




    }




}
