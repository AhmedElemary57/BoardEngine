import java.util

object Chess {

  var ps=new util.LinkedList[Piece]

  val brook = new Piece(0, 0, false, "rook", ps)
  val bkinght = new Piece(1, 0, false, "knight", ps)
  val bbishop = new Piece(2, 0, false, "bishop", ps)
  val bqueen = new Piece(3, 0, false, "queen", ps)
  val bking = new Piece(4, 0, false, "king", ps)
  val bbishop2 = new Piece(5, 0, false, "bishop", ps)
  val bkight2 = new Piece(6, 0, false, "knight", ps)
  val brook2 = new Piece(7, 0, false, "rook", ps)
  val bpawn1 = new Piece(1, 1, false, "pawn", ps)
  val bpawn2 = new Piece(2, 1, false, "pawn", ps)
  val bpawn3 = new Piece(3, 1, false, "pawn", ps)
  val bpawn4 = new Piece(4, 1, false, "pawn", ps)
  val bpawn5 = new Piece(5, 1, false, "pawn", ps)
  val bpawn6 = new Piece(6, 1, false, "pawn", ps)
  val bpawn7 = new Piece(7, 1, false, "pawn", ps)
  val bpawn8 = new Piece(0, 1, false, "pawn", ps)

  val wrook = new Piece(0, 7, true, "rook", ps)
  val wkinght = new Piece(1, 7, true, "knight", ps)
  val wbishop = new Piece(2, 7, true, "bishop", ps)
  val wqueen = new Piece(3, 7, true, "queen", ps)
  val wking = new Piece(4, 7, true, "king", ps)
  val wbishop2 = new Piece(5, 7, true, "bishop", ps)
  val wkight2 = new Piece(6, 7, true, "knight", ps)
  val wrook2 = new Piece(7, 7, true, "rook", ps)
  val wpawn1 = new Piece(1, 6, true, "pawn", ps)
  val wpawn2 = new Piece(2, 6, true, "pawn", ps)
  val wpawn3 = new Piece(3, 6, true, "pawn", ps)
  val wpawn4 = new Piece(4, 6, true, "pawn", ps)
  val wpawn5 = new Piece(5, 6, true, "pawn", ps)
  val wpawn6 = new Piece(6, 6, true, "pawn", ps)
  val wpawn7 = new Piece(7, 6, true, "pawn", ps)
  val wpawn8 = new Piece(0, 6, true, "pawn", ps)



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
