case class Piece(var xp: Int, var yp: Int, var isWhite: Boolean, pieceName: String) {

  def getXp: Int = xp

  def setXp(xp: Int): Unit = this.xp = xp

  def getYp: Int = yp

  def setYp(yp: Int): Unit = this.yp = yp

  /*
  def move(xp: Int, yp: Int): Unit = {
    for (p <- ps) {
      if ( p== xp && p.yp == yp) {
        p.kill()
      }
    }
    this.xp = xp
    this.yp = yp
  }*/
}
