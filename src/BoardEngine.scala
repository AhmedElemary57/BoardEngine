object BoardEngine{

  var isPlayerOne: Boolean = true     //start turn with player 1

  var state: Array[Array[String]] = null

//  def setInitialState(x: Int): List[Piece] = x match {
//    case 0 => XO.ps;
//    case 1 => Chess.ps;
//    case 2 => Connect4.ps;
//    case _ => Checkers.ps;
//  }
//
//  def selectDrawer(x:Int): Unit = x match {
//    case 0 =>xoDrawer();
//    case 1 =>chessDrawer();
//    case 2 =>connect4Drawer();
//    case _ =>checkersDrawer();
//  }
//
//  def start(i: Int): Unit ={
//    ps = setInitialState(i)
//    selectDrawer(i)
//  }
//
//
  def xoDrawer(): Unit ={
    var board = new Board
   board.draw(3,3,1,0,"xo",state)
  }
//  def chessDrawer( ): Unit ={
//    var b = new Board;
//    b.draw(8,8,1,1,"chess",ps)/*,here we should but the shapes 'state to be drawn'*/
//    //the bo
//  }
  def connect4Drawer( ): Unit ={
    var board = new Board;
    board.draw(7,6,0,0,"connect4", state)
    //the bo

  }
//  def checkersDrawer( ): Unit ={
//    var b = new Board;
//    b.draw(8,8,1,1,"checkers",ps)/*,here we should but the shapes 'state to be drawn'*/
//    //the bo
//  }

  def connect4Controller(input: String): Boolean  ={
    val col = input.charAt(0) - 'a'
    var colSize = 0
    var flag = 0
    var piece = ""
    if (isPlayerOne) piece = "R" else piece = "Y"

    if(!List('a', 'b', 'c', 'd', 'e', 'f', 'g').contains(input)) return false

    while (flag == 0 && colSize < 6) {
      if (state(state.length-1-colSize)(col) == null) flag = 1
      else colSize += 1
    }
    if (colSize >= 6) false
    else {
      state(state.length-1-colSize)(col) = piece
      true
    }
  }

  def ticTacToeController(input: String): Boolean = {
    val validInput: Boolean = List(1,2,3)
      .flatMap(number => List('a', 'b', 'c').map(letter => s"$number$letter")).contains(input)
    if (!validInput) false
    else{
      val i = input.charAt(0) - '1'  //1a => 00
      val j = input.charAt(1) - 'a'
      if(state(i)(j).equals("_")) {
        if(isPlayerOne) state(i)(j) = "X" else state(i)(j) = "O"
        true
      }
      else false //tell drawer do not draw this state
    }
  }

  def show(a: Array[Array[String]]): Unit = {
    for(i<- a.indices){
      for(j<- a(0).indices){
        print(" "+a(i)(j))
      }
      println()
    }
  }

  def main(args: Array[String]): Unit = {
//    while (true) {
//      println("select a game 0,1,2,3")
//      val a = scala.io.StdIn.readInt();
//      start(a)
//    }

//    state = Array(Array("X", "_", "O"), Array("O", "X", "_"), Array("O", "_", "X"))
    state = Array(
      Array("_", "_", "_", "_", "_", "_", "_"),
      Array("_", "_", "_", "_", "_", "_", "_"),
      Array("_", "_", "Y", "_", "_", "_", "_"),
      Array("_", "_", "R", "_", "_", "_", "_"),
      Array("_", "_", "R", "_", "_", "_", "_"),
      Array("R", "Y", "R", "Y", "Y", "R", "Y"))
//    val x = ticTacToeController("2c")
//    show(state)
//    xoDrawer()
    connect4Drawer()
  }
}
