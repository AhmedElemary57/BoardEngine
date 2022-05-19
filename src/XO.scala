import java.util

object XO {

  var ps=new util.LinkedList[Piece]

  def play(xp: Int, yp: Int, pieceName: String, ps: util.LinkedList[Piece]): Unit ={
    ps.add(new Piece(xp,yp,true,pieceName,ps))
  }


}
