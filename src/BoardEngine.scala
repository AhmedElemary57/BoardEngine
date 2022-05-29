object BoardEngine {

  var isPlayerOne: Boolean = true //start turn with player 1

  var state: Array[Array[Char]] = null

  def gameEngine(input :String, controller: (String) => Boolean, drawer:()=>Unit): Boolean = {
    val validMove = controller(input)
    if(validMove){
      drawer()
      isPlayerOne = !isPlayerOne
    }
    validMove
  }

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

  def xoDrawer(): Unit = {
    Board.refresh(3,3,1,0,"xo", state)
  }

  def chessDrawer(): Unit = {
    Board.refresh(8,8,1,1,"chess", state)
  }

  def connect4Drawer(): Unit = {
    Board.refresh(7,6,0,0,"connect4", state)
  }

  def checkersDrawer( ): Unit ={



   Board.refresh(8,8,1,1,"checkers",state)
    /*,here we should but the shapes 'state to be drawn'*/


  //the bo


 }
  def validConnect4InputRange(input: String): Boolean = {
     List('a', 'b', 'c', 'd', 'e', 'f', 'g').contains(input.charAt(0))
  }


  def connect4Controller(input: String): Boolean = {
    if (input.length>1 || !validConnect4InputRange(input)) return false;
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
      val i = '3'-input.charAt(0)   //1a => 00
      val j = input.charAt(1) - 'a'
      if(state(i)(j).equals('.')) {
        if(isPlayerOne) state(i)(j) = 'x' else state(i)(j) = 'o'
        true
      }
      else false //tell drawer do not draw this state
    }
  }

  def validChesInputRange(input: String): Boolean = {
    val fromTo = input.split("\\s")
    val validator = List(1,2,3,4,5,6,7,8).flatMap(number =>
      List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h').map(letter => s"$number$letter"))
    validator.contains(fromTo(0)) && validator.contains(fromTo(1))
  }

  def stateIndices(input: String): (Int, Int, Int, Int) = {
    val i = '8' - input.charAt(0)
    val j = input.charAt(1) - 'a'
    val k = '8' - input.charAt(3)
    val l = input.charAt(4) - 'a'
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

    if ( ((isPlayerOne && state(indices._1)(indices._2).isLower) ||
      (!isPlayerOne && state(indices._1)(indices._2).isUpper)) &&
      matchPiece(state(indices._1)(indices._2).toString.toLowerCase, indices)) {
      Chess.board(indices._3)(indices._4) = Chess.board(indices._1)(indices._2)
      Chess.board(indices._1)(indices._2) ='.'
      true
    }
    else false
  }

  def checkersController(input: String): Boolean ={

    if (!validChesInputRange(input)) return false
    val indices = stateIndices(input)
    val validMove = Checkers.play(indices,isPlayerOne)
    validMove
  }

  def main(args: Array[String]): Unit = {

    Board.initialization()
    Board.frame2.setVisible(true)
  }
}
