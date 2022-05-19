import scala.util.control.Breaks._                  // Importing  package

object BoardEngine{

  var turn = true    //start turn with player 1

  var state =Array.ofDim[String](6, 7)

/*  def setInitialState(x: Int): List[Piece] = x match {
    case 0 => XO.ps;
    case 1 => Chess.ps;
    case 2 => Connect4.ps;
    case _ => Checkers.ps;
  }

  def selectDrawer(x:Int): Unit = x match {
    case 0 =>xoDrawer();
    case 1 =>chessDrawer();
    case 2 =>connect4Drawer();
    case _ =>checkersDrawer();
  }*/
/*
  def start(i: Int): Unit ={
    ps = setInitialState(i)
    selectDrawer(i)
  }
*/
/*
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
  }*/
def show(a: Array[Array[String]]): Unit = {     for(i<- a.indices){               // Traversing elements using loop
   for(j<- a(0).indices){
   print(" "+a(i)(j))
   }
   println()
   }   }
  def connect4Controller(input: String): Boolean  ={

  //  var col = input.input;{{ , , , , , , }
  //                         { , , , , , , }
  //                         { , , , , , , }
  //                         { , , , , , , }
  //                         {*, , , , , , }
  //                         {*, , , , , , }}

    var col =input.charAt(0)-'a'
    var colSize=0;
    var flag=0;
    var string=""
    if (turn) string="R"; else string="Y";

    while (flag==0 && colSize < 6){
      if (state(state.length-1-colSize)(col)==null){
              flag=1
      }else{
        colSize+=1
      }
    }
    if (colSize >= 6){
      println("you can't insert here the column is full !!! ")
      false
    }
    else {
      state(state.length-1-colSize)(col)=string
      turn
      //here we should call the function again to draw the connect for after adding the ps
    }


  }

  def main(args: Array[String]): Unit = {

    while (true) {
      println("select a game 0,1,2,3")
      val a = scala.io.StdIn.readLine();

      connect4Controller(a);
      show(state)
    }
  }
}
