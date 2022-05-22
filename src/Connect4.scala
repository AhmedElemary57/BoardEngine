object Connect4 {

  var ps: Array[Array[Char]] = Array(
    Array('.','.','.','.','.','.','.'),
    Array('r','.','.','.','.','.','.'),
    Array('r','y','.','.','.','.','.'),
    Array('r','y','.','.','.','.','.'),
    Array('r','y','.','.','.','.','.'),
    Array('r','y','.','.','.','.','.'))

  def initialState(): Array[Array[Char]] = {
    ps = Array(
        Array('.','.','.','.','.','.','.'),
        Array('.','.','.','.','.','.','.'),
        Array('.','.','.','.','.','.','.'),
        Array('y','y','.','.','.','.','.'),
        Array('r','y','.','.','.','.','.'),
        Array('r','y','.','.','.','.','.'))
    ps
  }

  def play(xp: Int, pieceName: Char): Unit ={
    var i = 0
    ps(xp)(5)=pieceName
  }
}