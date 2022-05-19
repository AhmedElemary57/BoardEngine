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
