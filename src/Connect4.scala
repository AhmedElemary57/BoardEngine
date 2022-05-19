import java.util

object Connect4 {
  var ps=new util.LinkedList[Piece]

  def play(xp: Int, pieceName: String, ps: util.LinkedList[Piece]): Unit ={
    var i=0;

    ps.add(new Piece(xp,5,true,pieceName))
  }

}
