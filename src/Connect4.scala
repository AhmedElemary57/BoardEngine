object Connect4 {
  var ps:List[Piece] = List()

  def play(xp: Int, pieceName: String, ps: List[Piece]): Unit ={
    var i = 0

    ps :+ new Piece(xp,5,true,pieceName)
  }
}
