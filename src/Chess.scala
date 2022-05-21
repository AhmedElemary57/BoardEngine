object Chess {

  var ps: List[Piece] = List()

  ps = ps :+ new Piece(0, 0, false, "rook")
  ps = ps:+ new Piece(1, 0, false, "knight")
  ps = ps:+ new Piece(2, 0, false, "bishop")
  ps = ps:+ new Piece(3, 0, false, "queen")
  ps = ps:+ new Piece(4, 0, false, "king")
  ps = ps:+ new Piece(5, 0, false, "bishop")
  ps = ps:+ new Piece(6, 0, false, "knight")
  ps = ps:+ new Piece(7, 0, false, "rook")
  ps = ps:+ new Piece(1, 1, false, "pawn")
  ps = ps:+ new Piece(2, 1, false, "pawn")
  ps = ps:+ new Piece(3, 1, false, "pawn")
  ps = ps:+ new Piece(4, 1, false, "pawn")
  ps = ps:+ new Piece(5, 1, false, "pawn")
  ps = ps:+ new Piece(6, 1, false, "pawn")
  ps = ps:+ new Piece(7, 1, false, "pawn")
  ps = ps:+ new Piece(0, 1, false, "pawn")
  ps = ps:+ new Piece(0, 7, true, "rook")
  ps = ps:+ new Piece(1, 7, true, "knight")
  ps = ps:+ new Piece(2, 7, true, "bishop")
  ps = ps:+ new Piece(3, 7, true, "queen")
  ps = ps:+ new Piece(4, 7, true, "kings")
  ps = ps:+ new Piece(5, 7, true, "bishop")
  ps = ps:+ new Piece(6, 7, true, "kni")
  ps = ps:+ new Piece(7, 7, true, "rook")
  ps = ps:+ new Piece(1, 6, true, "pawn")
  ps = ps:+ new Piece(2, 6, true, "pawn")
  ps = ps:+ new Piece(3, 6, true, "pawn")
  ps = ps:+ new Piece(4, 6, true, "pawn")
  ps = ps:+ new Piece(5, 6, true, "pawn")
  ps = ps:+ new Piece(6, 6, true, "pawn")
  ps = ps:+ new Piece(7, 6, true, "pawn")
  ps = ps:+ new Piece(0, 6, true, "pawn")

  val emptyBoard: Array[Array[Char]] = Array(Array('.', '-', '.', '-', '.', '-', '.', '-'), Array('-', '.', '-', '.', '-', '.', '-', '.'), Array('.', '-', '.', '-', '.', '-', '.', '-'), Array('-', '.', '-', '.', '-', '.', '-', '.'), Array('.', '-', '.', '-', '.', '-', '.', '-'), Array('-', '.', '-', '.', '-', '.', '-', '.'), Array('.', '-', '.', '-', '.', '-', '.', '-'), Array('-', '.', '-', '.', '-', '.', '-', '.'))
  val board: Array[Array[Char]] = Array(Array('R', '.', '.', 'Q', 'K', 'B', 'N', 'R'), Array('.', 'P', 'P', 'P', 'P', 'P', 'P', 'P'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'), Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'))
  val tempBoard: Array[Array[Char]] = Array(Array('R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'), Array('P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('.', '.', '.', '.', '.', '.', '.', '.'), Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'), Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'))
  val EMPTY='.'
  def isUpper(x: Char): Boolean = (x >= 'A' && x <= 'Z')

  //function return true if char is lowercase
  def isLower(x: Char): Boolean = (x >= 'a' && x <= 'z')

  def changeType(x: Char, y: Char, n: Int): Boolean = {
    if (isLower(x) && n == 0) return isUpper(y)
    else if (isLower(x) && n == 1) return isLower(y)
    else if (isUpper(x) && n == 0) return isLower(y)
    else if (isUpper(x) && n == 1) return isUpper(y)
    true
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
    else { // Not valid rook move
      return false
    }
    // Return true if destination cell is free
    (board(toRow)(toCol) == '.') || changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0)
  }
  def show(a: Array[Array[Char]]): Unit = {
    for(i<- a.indices){
      for(j<- a(0).indices){
        print(" "+a(i)(j))
      }
      println()
    }
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
      while ( { i != toRow }) {
        j = fromCol + diagonal2
        if (board(i)(j) != EMPTY) k += 1
        diagonal2 += temp

        i += diagonal1
      }

      if (((board(i)(toCol) != EMPTY) && changeType(board(fromRow)(fromCol), board(i)(toCol), 1)) || k != 0) return false
    }
    else return false
    (board(toRow)(toCol) == EMPTY) || (changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0))
  }

  def validKingMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = { // Attempt to move to the same cell
    if (fromRow == toRow && fromCol == toCol) return false
    if ((((fromRow + 1 == toRow) && (fromCol == toCol)) || ((fromRow - 1 == toRow) && (fromCol == toCol)) ||
      ((fromCol + 1 == toCol) && (fromRow == toRow)) || ((fromCol - 1 == toCol) && (fromRow == toRow))) ||
      (((fromRow + 1 == toRow) && (fromCol + 1 == toCol)) || ((fromRow - 1 == toRow) && (fromCol - 1 == toCol)) ||
        ((fromRow + 1 == toRow) && (fromCol - 1 == toCol)) || ((fromRow - 1 == toRow) && (fromCol + 1 == toCol))))

      return (board(toRow)(toCol) == EMPTY) || (changeType(board(fromRow)(fromCol), board(toRow)(toCol), 0))
    else false
  }
  def validQueenMove(fromRow: Int , fromCol: Int , toRow: Int , toCol: Int): Boolean = (validBishopMove(fromRow, fromCol, toRow, toCol) || validRookMove(fromRow, fromCol, toRow, toCol))

  def main(args: Array[String]): Unit = {
    show(board)
    var x=validKingMove(6,2,5,1)
    println(x)
  }

  def chessPieces(i :Int , index:Int) :Int={
    var ind = index;
    if (ps(i).pieceName.equalsIgnoreCase("king")) ind = 0
    if (ps(i).pieceName.equalsIgnoreCase("queen")) ind = 1
    if (ps(i).pieceName.equalsIgnoreCase("bishop")) ind = 2
    if (ps(i).pieceName.equalsIgnoreCase("knight")) ind = 3
    if (ps(i).pieceName.equalsIgnoreCase("rook")) ind = 4
    if (ps(i).pieceName.equalsIgnoreCase("pawn")) ind = 5
    if (!ps(i).isWhite) ind += 6
    ind
  }
}
