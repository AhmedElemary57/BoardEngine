object Chess {

  var board: Array[Array[Char]] = Array(
    Array('R', '.', '.', 'Q', 'K', 'B', 'N', 'R'),
    Array('.', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
    Array('.', '.', '.', '.', '.', '.', '.', '.'),
    Array('.', '.', '.', '.', '.', '.', '.', '.'),
    Array('.', '.', '.', '.', '.', '.', '.', '.'),
    Array('P', '.', '.', '.', '.', '.', '.', '.'),
    Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
    Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'))

  val EMPTY = '.'

  def isUpper(x: Char): Boolean = x >= 'A' && x <= 'Z'

  def isLower(x: Char): Boolean = x >= 'a' && x <= 'z'

  def initialState(): Array[Array[Char]] ={
    board = Array(
      Array('R', '.', '.', 'Q', 'K', 'B', 'N', 'R'),
      Array('.', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
      Array('.', '.', '.', '.', '.', '.', '.', '.'),
      Array('.', '.', '.', '.', '.', '.', '.', '.'),
      Array('.', '.', '.', '.', '.', '.', '.', '.'),
      Array('P', '.', '.', '.', '.', '.', '.', '.'),
      Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
      Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'))
    board
  }

  def changeType(x: Char, y: Char, n: Int): Boolean = {
    if (isLower(x) && n == 0) return isUpper(y)
    else if (isLower(x) && n == 1) return isLower(y)
    else if (isUpper(x) && n == 0) return isLower(y)
    else if (isUpper(x) && n == 1) return isUpper(y)
    true
  }

  def validCapturePawn(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    if (((fromRow - 1 == toRow) && (fromCol + 1 == toCol || fromCol - 1 == toCol)) &&
      isUpper(board(toRow)(toCol)) && isLower(board(fromRow)(fromCol))) true
    else ((fromRow + 1 == toRow) && (fromCol + 1 == toCol || fromCol - 1 == toCol)) &&
      isLower(board(toRow)(toCol)) && isUpper(board(fromRow)(fromCol))
  }

  def validPawnMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    var x = 0
    var y = 0
    x = if (isLower(board(fromRow)(fromCol))) -1 else 1
    y = if (isLower(board(fromRow)(fromCol))) 6 else 1
    // Attempt to move to the same cell
    if (fromRow == toRow && fromCol == toCol) return false
    // diagonal pawn attack
    if (fromCol == toCol) { // Vertical move
      if (toRow == fromRow + x) board(toRow)(toCol) == EMPTY
      else if (fromRow == y &&
        (toRow == fromRow + 2 * x && (board(fromRow + x)(toCol) == EMPTY)))
        board(toRow)(toCol) == EMPTY
      else false
    }
    else validCapturePawn(fromRow, fromCol, toRow, toCol)
  }

  def validRookMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    var i = 0
    var j = 0
    // Attempt to move to the same cell
    if (fromRow == toRow && fromCol == toCol) return false

    if (fromRow == toRow) {// Horizontal move
      val x = if (fromCol < toCol) 1 else -1
      i = fromCol + x
      while ( {i != toCol }) {
        if (board(fromRow)(i) != '.') j += 1
        i += x
      }
      if (changeType(board(fromRow)(fromCol), board(fromRow)(i), 1) || j != 0) return false
    }
    else if (fromCol == toCol) { // Vertical move
      val y = if (fromRow < toRow) 1 else -1
      i = fromRow + y
      while ( { i != toRow} ) {
        if (board(i)(fromCol) != '.') j += 1
        i += y
      }
      if (changeType(board(fromRow)(fromCol), board(i)(fromCol), 1) || j != 0) return false
    }
    else  return false // Not valid rook move
    // Return true if destination cell is free
    (board(toRow)(toCol) == '.') || changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0)
  }

  def validBishopMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    var i = 0
    var j = 0
    var k = 0
    var diagonal1 = 0
    var diagonal2 = 0
    var temp = 0
    // Attempt to move to the same cell
    if (fromRow == toRow && fromCol == toCol) return false
    diagonal1 = if (fromRow > toRow) -1 else 1
    diagonal2 = if (fromCol > toCol) -1 else 1
    if (((fromCol + fromRow) == (toCol + toRow)) || ((fromCol - fromRow) == (toCol - toRow))) {
      temp = diagonal2
      i = fromRow + diagonal1
      while ( i != toRow ) {
        j = fromCol + diagonal2
        if (board(i)(j) != EMPTY) k += 1
        diagonal2 += temp

        i += diagonal1
      }

      if (((board(i)(toCol) != EMPTY) && changeType(board(fromRow)(fromCol), board(i)(toCol), 1)) || k != 0)
        return false
      (board(toRow)(toCol) == EMPTY) || changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0)
    }
    else false
  }

  def validKingMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    if (fromRow == toRow && fromCol == toCol) return false
    val forwardStep = (fromRow + 1 == toRow) && (fromCol == toCol)
    val downwardStep = (fromRow - 1 == toRow) && (fromCol == toCol)
    val rightStep = (fromCol + 1 == toCol) && (fromRow == toRow)
    val leftStep = (fromCol - 1 == toCol) && (fromRow == toRow)
    val upperRightStep = (fromRow + 1 == toRow) && (fromCol + 1 == toCol)
    val lowerLeftStep = (fromRow - 1 == toRow) && (fromCol - 1 == toCol)
    val upperLeftStep = (fromRow + 1 == toRow) && (fromCol - 1 == toCol)
    val lowerRightStep = (fromRow - 1 == toRow) && (fromCol + 1 == toCol)

    if ((forwardStep ||  downwardStep || rightStep || leftStep) ||
      (upperRightStep ||  lowerLeftStep || upperLeftStep || lowerRightStep))
       (board(toRow)(toCol) == EMPTY) || changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0)
    else false
  }

  def validQueenMove(fromRow: Int , fromCol: Int , toRow: Int , toCol: Int): Boolean = {
    validBishopMove(fromRow, fromCol, toRow, toCol) || validRookMove(fromRow, fromCol, toRow, toCol)
  }

  def validKnightMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    // Attempt to move to the same cell
    if (fromRow == toRow && fromCol == toCol) return false
    val expr:Boolean = board(toRow)(toCol) == EMPTY ||
      changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0)
    if (fromRow == toRow - 1 || fromRow == toRow + 1) { //Horizontal then vertical
      if (fromCol == toCol - 2 || fromCol == toCol + 2) expr
      else false
    }
    else if (fromRow == toRow - 2 || fromRow == toRow + 2) { //Vertical then horizontal
      if (fromCol == toCol - 1 || fromCol == toCol + 1) expr
      else false
    }
    else false
  }

  def chessPieces(s:Char): Int = s match {
    case 'k'=> 0;
    case 'q'=> 1;
    case 'b'=> 2;
    case 'n'=> 3;
    case 'r'=> 4;
    case 'p'=> 5;

    case 'K'=> 0+6;
    case 'Q'=> 1+6;
    case 'B'=> 2+6;
    case 'N'=> 3+6;
    case 'R'=> 4+6;
    case 'P'=> 5+6;

    case  _ => 3;

  }

  def show(a: Array[Array[Char]]): Unit = {
    for(i<- a.indices){
      for(j<- a(0).indices){
        print(" "+a(i)(j))
      }
      println()
    }
  }

  def main(args: Array[String]): Unit = {
    show(board)
    println(validKnightMove(7,1,5,0))
  }
}
