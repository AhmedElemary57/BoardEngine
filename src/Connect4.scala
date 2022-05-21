object Connect4 {
  var ps: Array[Array[Char]] = Array(Array('.','.','.','.','.','.','.'),
                                     Array('r','.','.','.','.','.','.'),
                                     Array('r','.','.','.','.','.','.'),
                                     Array('r','.','.','.','.','.','.'),
                                     Array('r','.','.','.','.','.','.'),
                                     Array('r','y','.','.','.','.','.'))

  def play(xp: Int, pieceName: Char): Unit ={
    var i = 0

    ps(xp)(5)=pieceName
  }
}
