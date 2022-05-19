import java.util

object BoardEngine{

  var turn = 0    //start turn with player 1
  //var input
var ps=new util.LinkedList[Piece];
  def setInitialState(x:Int): util.LinkedList[Piece] = x match {
    case 0 =>XO.ps;
    case 1 =>Chess.ps;
    case 2=>Connect4.ps;
    case _=>Checkers.ps;
  }

  def selectDrawer(x:Int): Unit = x match {
    case 0 =>xoDrawer();
    case 1 =>chessDrawer();
    case 2=>connect4Drawer();
    case _=>checkersDrawer();
  }

  def start(i: Int): Unit ={
    ps =setInitialState(i)
    selectDrawer(i)

  }


  def xoDrawer( ): Unit ={
    var b = new Board;
   b.draw(3,3,1,0,"xo",ps)/*,here we should but the shapes 'state to be drawn'*/

  }
  def chessDrawer( ): Unit ={
    var b = new Board;
    b.draw(8,8,1,1,"chess",ps)/*,here we should but the shapes 'state to be drawn'*/
    //the bo
  }
  def connect4Drawer( ): Unit ={
    var b = new Board;
    b.draw(7,6,0,0,"connect4",ps)/*,here we should but the shapes 'state to be drawn'*/
    //the bo

  }
  def checkersDrawer( ): Unit ={
    var b = new Board;
    b.draw(8,8,1,1,"checkers",ps)/*,here we should but the shapes 'state to be drawn'*/
    //the bo
  }



  def main(args: Array[String]): Unit = {
    while (true) {
      println("select a game 0,1,2,3")

      val a = scala.io.StdIn.readInt();
      start(a);
  }
  }


}
