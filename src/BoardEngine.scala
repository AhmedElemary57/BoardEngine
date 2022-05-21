object BoardEngine {

  var isPlayerOne: Boolean = true //start turn with player 1

  var state: Array[Array[Char]] = null

    def setInitialState(x: Int): Array[Array[Char]] = x match {
      case 0 => XO.ps;
     case 1 => Chess.board;
      case 2 => Connect4.ps;

}
  def selectDrawer(x:Int): Unit = x match {
    case 0 =>xoDrawer();
    case 1 =>chessDrawer();
    case 2 =>connect4Drawer();
   //case _ =>checkersDrawer();
  }

  def start(i: Int): Unit ={
    state = setInitialState(i)
    selectDrawer(i)
  }


  def xoDrawer(): Unit ={
   Board.draw(3,3,1,0,"xo",state)
  }
  def chessDrawer( ): Unit ={
    Board.draw(8,8,1,1,"chess",state)/*,here we should but the shapes 'state to be drawn'*/
    //the bo
  }
  def connect4Drawer( ): Unit ={
    Board.draw(7,6,0,0,"connect4", state)
    //the bo

  }

  def checkersDrawer( ): Unit ={



   Board.draw(8,8,1,1,"checkers",state)
    /*,here we should but the shapes 'state to be drawn'*/


  //the bo


 }

  def connect4Controller(input: String): Boolean  ={
    val col = input.charAt(0) - 'a'
    var colSize = 0
    var flag = 0
    var piece = '.'
    if (isPlayerOne) piece = 'r' else piece = 'y'

    if(!List('a', 'b', 'c', 'd', 'e', 'f', 'g').contains(input.charAt(0))) return false

    while (flag == 0 && colSize < 6) {
      if (state(state.length-1-colSize)(col) == '.') flag = 1
      else colSize += 1
    }

    if (colSize >= 6) {
      false
    }
    else {
      state(state.length-1-colSize)(col) = piece
      println("add to array "+piece)
      Board.refresh(7,6,0,0,"connect4", state)
      isPlayerOne = !isPlayerOne

      true
    }
  }

  def ticTacToeController(input: String): Boolean = {
    val validInput: Boolean = List(1,2,3)
      .flatMap(number => List('a', 'b', 'c').map(letter => s"$number$letter")).contains(input)
    if (!validInput) false
    else{
      val i = '3'-input.charAt(0)   //1a => 00
      val j = input.charAt(1) - 'a'
      if(state(i)(j).equals('.')) {
        if(isPlayerOne) state(i)(j) = 'x' else state(i)(j) = 'o'
        Board.refresh(3,3,1,0,"xo",state)
        isPlayerOne = !isPlayerOne
        true

      }
      else false //tell drawer do not draw this state
    }
  }

  def validChesInputRange(input: String): Boolean = {
    val fromTo = input.split("\\s")
    val validator = List(1,2,3,4,5,6,7,8).flatMap(number => List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h').map(letter => s"$number$letter"))
    validator.contains(fromTo(0)) && validator.contains(fromTo(1))
  }

  def stateIndices(input: String): (Int, Int, Int, Int) = {//2b3a
    val i = '8' - input.charAt(0) //6
    val j = input.charAt(1) - 'a' //1
    val k = '8' - input.charAt(3) //5
    val l = input.charAt(4) - 'a' //0
    (i, j, k, l)
  }

  def matchPiece(piece: String, input: (Int, Int, Int, Int)): Boolean = piece match {
    case "p" => Chess.validPawnMove(input._1, input._2, input._3, input._4)
    case "k" => Chess.validKingMove(input._1, input._2, input._3, input._4)
    case "r" => Chess.validRookMove(input._1, input._2, input._3, input._4)
    case "n" => Chess.validKnightMove(input._1, input._2, input._3, input._4)
    case "q" => Chess.validQueenMove(input._1, input._2, input._3, input._4)
    case "b" => Chess.validBishopMove(input._1, input._2, input._3, input._4)
  }

  def chessController(input: String): Boolean = {

    println(s"input is $input")
    if (!validChesInputRange(input)) return false
    val indices = stateIndices(input)
    Chess.board = state

    if (((isPlayerOne && state(indices._1)(indices._2).isLower )||
      (!isPlayerOne && state(indices._1)(indices._2).isUpper))&& matchPiece(state(indices._1)(indices._2).toString.toLowerCase, indices)){
      println("valid move")
      Chess.board(indices._3)(indices._4)= Chess.board(indices._1)(indices._2)
      Chess.board(indices._1)(indices._2)='.'

      Board.refresh(8,8,1,1,"chess",state)
      isPlayerOne = !isPlayerOne
      true

    }
    else false

  }


  def gameEngine(input :String, controller: (String) => Boolean, drawer:()=>Unit): Boolean = {
    val validMove = controller(input)
    if(validMove) drawer()
    validMove
  }
  def checkersController(input: String): Unit ={

    if( Checkers.play(input) ) {

  //    state(Checkers.converter(input.charAt(2)))( Checkers.converter(input.charAt(3))) = state(Checkers.converter(input.charAt(0)))(Checkers.converter(input.charAt(1)))
      //state(Checkers.converter(input.charAt(0)))(Checkers.converter(input.charAt(1)))='.'
      Board.refresh(8,8,1,1,"checkers",state)

    }

    Board.refresh(8,8,1,1,"checkers",state)
  }





  def main(args: Array[String]): Unit = {
    Board.initialization()
    //Board.frame.add(Board.connect4Button)


    Board.frame.setVisible(true)


  }
//    state = Array(Array("X", "_", "O"), Array("O", "X", "_"), Array("O", "_", "X"))
   /* state = Array(
      Array('R', '.', '.', 'Q', 'K', 'B', 'N', 'R'),
      Array('.', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
      Array('.', '.', '.', '.', '.', '.', '.', '.'),
      Array('.', '.', '.', '.', '.', '.', '.', '.'),
      Array('.', '.', '.', '.', '.', '.', '.', '.'),
      Array('P', '.', '.', '.', '.', '.', '.', '.'),
      Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
      Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'))
//    val x = ticTacToeController("2c")
//    show(state)
//    xoDrawer()
//    connect4Drawer()
    val x =chessController("8a 4a")
    println(x)*/

}
