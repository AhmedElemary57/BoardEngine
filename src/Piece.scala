import java.util
class Piece {

  var xp: Int = 0
  var yp: Int = 0
  val ps: util.LinkedList[Piece] = null
  var isWhite: Boolean = false
  var pieceName: String = null

  def this(xp: Int, yp: Int, isWhite: Boolean, pieceName: String) {
    this()
    this.xp = xp
    this.yp = yp
    this.isWhite = isWhite
    this.pieceName = pieceName
  }

  def getPieces: util.LinkedList[Piece] = {
    return ps
  }
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

  def getXp: Int = xp

  def setXp(xp: Int): Unit = {
    this.xp = xp
  }

  def getYp: Int = yp

  def setYp(yp: Int): Unit = {
    this.yp = yp
  }
  def kill(): Unit = {
    ps.remove(this)
  }




}
