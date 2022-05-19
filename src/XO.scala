object XO {

  var ps:List[Piece] = List()

  def play(xp: Int, yp: Int, pieceName: String, ps: List[Piece]): Unit ={
    ps :+ new Piece(xp,yp,true,pieceName)
  }


}
