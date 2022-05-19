import java.util

object Chess {

  var ps=new util.LinkedList[Piece]

  ps.add(new Piece(0, 0, false, "rook"))
  ps.add( new Piece(1, 0, false, "knight"))
  ps.add( new Piece(2, 0, false, "bishop"))
  ps.add(new Piece(3, 0, false, "queen"))
  ps.add(new Piece(4, 0, false, "king"))
  ps.add( new Piece(5, 0, false, "bishop"))
  ps.add(new Piece(6, 0, false, "knight"))
  ps.add(new Piece(7, 0, false, "rook"))
  ps.add(new Piece(1, 1, false, "pawn"))
  ps.add(new Piece(2, 1, false, "pawn"))
  ps.add(new Piece(3, 1, false, "pawn"))
  ps.add(new Piece(4, 1, false, "pawn"))
  ps.add(new Piece(5, 1, false, "pawn"))
  ps.add(new Piece(6, 1, false, "pawn"))
  ps.add(new Piece(7, 1, false, "pawn"))
  ps.add(new Piece(0, 1, false, "pawn"))
  ps.add(new Piece(0, 7, true, "rook"))
  ps.add( new Piece(1, 7, true, "knight"))
  ps.add(new Piece(2, 7, true, "bishop"))
  ps.add(new Piece(3, 7, true, "queen"))
  ps.add(new Piece(4, 7, true, "kings"))
  ps.add( new Piece(5, 7, true, "bishop"))

  ps.add( new Piece(6, 7, true, "kni"))
  ps.add(new Piece(7, 7, true, "rook"))
  ps.add(new Piece(1, 6, true, "pawn"))
  ps.add(new Piece(2, 6, true, "pawn"))
  ps.add(new Piece(3, 6, true, "pawn"))
  ps.add(new Piece(4, 6, true, "pawn"))
  ps.add(new Piece(5, 6, true, "pawn"))
  ps.add(new Piece(6, 6, true, "pawn"))
  ps.add(new Piece(7, 6, true, "pawn"))
  ps.add(new Piece(0, 6, true, "pawn"))



  def chessPieces(i :Int , index:Int) :Int={
    var ind = index;
    if (ps.get(i).pieceName.equalsIgnoreCase("king")) ind = 0
    if (ps.get(i).pieceName.equalsIgnoreCase("queen")) ind = 1
    if (ps.get(i).pieceName.equalsIgnoreCase("bishop")) ind = 2
    if (ps.get(i).pieceName.equalsIgnoreCase("knight")) ind = 3
    if (ps.get(i).pieceName.equalsIgnoreCase("rook")) ind = 4
    if (ps.get(i).pieceName.equalsIgnoreCase("pawn")) ind = 5
    if (!ps.get(i).isWhite) ind += 6
    return ind;
  }
}
