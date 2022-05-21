object XO {

  var ps:Array[Array[Char]] =Array(Array('.','x','.'),Array('.','o','.'),Array('.','.','.'))



  def play(xp: Int, yp: Int, pieceName: Char): Unit ={
    ps(xp)(yp)=pieceName
  }


}
