import java.util.Scanner

object Checkers {

  val board: Array[Array[Char]] = Array(
    Array('b', '.', 'b', '.', 'b', '.', 'b', '.'),
    Array('.', 'b', '.', 'b', '.', 'b', '.', 'b'),
    Array('b', '.', 'b', '.', 'b', '.', 'b', '.'),
    Array('.', '.', '.', '.', '.', '.', '.', '.'),
    Array('.', '.', '.', '.', '.', '.', '.', '.'),
    Array('.', 'w', '.', 'w', '.', 'w', '.', 'w'),
    Array('w', '.', 'w', '.', 'w', '.', 'w', '.'),
    Array('.', 'w', '.', 'w', '.', 'w', '.', 'w'))

  def play(input:(Int,Int,Int,Int),role:Boolean): Boolean = {
      val xPoxFrom = input._1
      val yPoxFrom = input._2
      val xPoxTo = input._3
      val yPoxTo = input._4
      if (xPoxFrom == 10 || yPoxFrom == 10 || xPoxTo == 10 || yPoxTo == 10)  return false

      if (role ){ // white role
        if (validateWhiteKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          if (xPoxTo == 0)board(xPoxTo)( yPoxTo) ='W'
          return true
        }
        else if(validateWhiteOriginalMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          if (xPoxTo == 0)board(xPoxTo)( yPoxTo) ='W'
          return true
        }

      }
      else{ // black role
        if (validateBlackKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          if (xPoxTo == 7)board(xPoxTo)( yPoxTo) ='B'
          return true
        }
        else if(validateBlackOriginalMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          if (xPoxTo == 7)board(xPoxTo)( yPoxTo) ='B'
          return true
        }
      }
    false

  }

  private val empty = '.'

  ///////////////////////////////////////////////////////////////////////////////////////// original move
  def validateWhiteOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if(board(xPoxFrom)(yPoxFrom) == 'w' && (xPoxFrom == xPoxTo + 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == empty)&&moreValidityOnOriginalWhiteMove()){
      board(xPoxTo)(yPoxTo) = 'w'
      board(xPoxFrom)(yPoxFrom) = empty
      return true
    }else if (board(xPoxFrom)(yPoxFrom) == 'W' && (xPoxFrom == xPoxTo + 1 || xPoxFrom == xPoxTo - 1) && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == empty &&moreValidityOnOriginalWhiteMove()){
      board(xPoxTo)(yPoxTo) = 'W'
      board(xPoxFrom)(yPoxFrom) = empty
      return true
    }
    false
  }
  def validateBlackOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (board(xPoxFrom)(yPoxFrom) == 'b' &&(xPoxFrom == xPoxTo - 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == empty)&&moreValidityOnOriginalBlackMove()) {
      board(xPoxTo)(yPoxTo) = 'b'
      board(xPoxFrom)(yPoxFrom) = empty
      return true
    }else if (board(xPoxFrom)(yPoxFrom) == 'B' && (xPoxFrom == xPoxTo + 1 || xPoxFrom == xPoxTo - 1) && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == empty &&moreValidityOnOriginalBlackMove()){
      board(xPoxTo)(yPoxTo) = 'B'
      board(xPoxFrom)(yPoxFrom) = empty
      return true
    }
    false
  }


  private def validateWhiteKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    var bol = false
    if (board(xPoxFrom)(yPoxFrom) == 'w' && yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo + 2 && (board(xPoxFrom - 1)(yPoxFrom + 1) == 'b'||board(xPoxFrom - 1)(yPoxFrom + 1) == 'B') && board(xPoxTo)(yPoxTo)==empty) { // right
      board(xPoxFrom - 1)(yPoxFrom + 1) = empty
      bol = true
    }
    else if (board(xPoxFrom)(yPoxFrom) == 'w' && yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo + 2 && (board(xPoxFrom - 1)(yPoxFrom - 1) == 'b' || board(xPoxFrom - 1)(yPoxFrom - 1) == 'B') && board(xPoxTo)(yPoxTo)==empty) {
      board(xPoxFrom - 1)(yPoxFrom - 1) = empty
      bol = true
    }
    else if (board(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom -2 && (board(xPoxFrom - 1)(yPoxFrom + 1) == 'b' || board(xPoxFrom - 1)(yPoxFrom + 1) == 'B') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom - 1)(yPoxFrom + 1) = empty
      bol = true
    }
    else if (board(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom -2 && (board(xPoxFrom - 1)(yPoxFrom - 1) == 'b' || board(xPoxFrom - 1)(yPoxFrom - 1) == 'B') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom - 1)(yPoxFrom - 1) = empty
      bol = true
    }
    else if(board(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom +2 && (board(xPoxFrom + 1)(yPoxFrom + 1) == 'b' || board(xPoxFrom + 1)(yPoxFrom + 1) == 'B') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom + 1)(yPoxFrom + 1) = empty
      bol = true
    }
    else if (board(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom +2 && (board(xPoxFrom + 1)(yPoxFrom - 1) == 'b' || board(xPoxFrom + 1)(yPoxFrom - 1) == 'B') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom + 1)(yPoxFrom - 1) = empty
      bol = true
    }
    if (bol){
      val x = board(xPoxFrom)(yPoxFrom)
      board(xPoxTo)(yPoxTo) = x
      board(xPoxFrom)(yPoxFrom) = empty
      recehck_white(xPoxTo, yPoxTo)
      return true
    }
    false
  }
  private def validateBlackKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    var bol = false
    if (yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo - 2 && (board(xPoxFrom + 1)(yPoxFrom + 1) == 'w' || board(xPoxFrom + 1)(yPoxFrom + 1) == 'W') && board(xPoxTo)(yPoxTo)==empty) {
      board(xPoxFrom + 1)(yPoxFrom + 1) = empty
      bol = true
    }
    else if (yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo - 2 && (board(xPoxFrom + 1)(yPoxFrom - 1) == 'w' || board(xPoxFrom + 1)(yPoxFrom - 1) == 'W') && board(xPoxTo)(yPoxTo)==empty) {
      board(xPoxFrom + 1)(yPoxFrom - 1) = empty
      bol = true
    }else if (board(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom -2 && (board(xPoxFrom - 1)(yPoxFrom + 1) == 'w' || board(xPoxFrom - 1)(yPoxFrom + 1) == 'W') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom - 1)(yPoxFrom + 1) = empty
      bol = true
    }
    else if (board(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom -2 && (board(xPoxFrom - 1)(yPoxFrom - 1) == 'w' || board(xPoxFrom - 1)(yPoxFrom - 1) == 'W') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom - 1)(yPoxFrom - 1) = empty
      bol = true
    }
    else if(board(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom +2 && (board(xPoxFrom + 1)(yPoxFrom + 1) == 'w' || board(xPoxFrom + 1)(yPoxFrom + 1) == 'W') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom + 1)(yPoxFrom + 1) = empty
      bol = true
    }
    else if (board(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom +2 && (board(xPoxFrom + 1)(yPoxFrom - 1) == 'w' || board(xPoxFrom + 1)(yPoxFrom - 1) == 'W') && board(xPoxTo)(yPoxTo)==empty){
      board(xPoxFrom + 1)(yPoxFrom - 1) = empty
      bol = true
    }
    if (bol ){
      val x = board(xPoxFrom)(yPoxFrom)
      board(xPoxTo)(yPoxTo) = x
      board(xPoxFrom)(yPoxFrom) = empty
      recehck_black(xPoxTo, yPoxTo)
      return true
    }
    false
  }


  private def recehck_white(curX: Int, curY: Int)={
    //go left Up
    val xtoUp = curX - 2
    val yto_lUp = curY - 2
    val yto_rUp = curY + 2
    val xtoDown = curX + 2
    if (inbound(xtoUp)){
      if (curY == yto_lUp + 2 && curX == xtoUp + 2 && (board(curX - 1)(curY - 1) == 'b' || board(curX - 1)(curY - 1) == 'B') && board(xtoUp)(yto_lUp)==empty){
        validateWhiteKillMove(curX,curY,xtoUp,yto_lUp)
      }
      if (curY == yto_rUp - 2 && curX == xtoUp + 2 && (board(curX - 1)(curY + 1) == 'b' || board(curX - 1)(curY + 1) == 'B') && board(xtoUp)(yto_rUp)==empty) { //right Up
        validateWhiteKillMove(curX,curY,xtoUp,yto_rUp)
      }
      if(board(curX)(curY) == 'W' && curY == yto_lUp + 2 && curX == xtoDown - 2 && (board(curX + 1)(curY - 1) == 'b' || board(curX + 1)(curY - 1) == 'B') && board(xtoDown)(yto_lUp)==empty){ //left Down
        validateWhiteKillMove(curX,curY,xtoDown,yto_rUp)
      }
      if (board(curX)(curY) == 'W' && curY == yto_rUp - 2 && curX == xtoDown - 2 && (board(curX + 1)(curY + 1) == 'b' || board(curX + 1)(curY + 1) == 'B') && board(xtoDown)(yto_rUp)==empty){  //right Down
        validateWhiteKillMove(curX,curY,xtoDown,yto_rUp)
      }

    }
  }
  private def recehck_black(curX: Int, curY: Int)={
    //go left
    val xtoDown = curX + 2
    val xtoUp = curX - 2
    val yto_lDown = curY - 2
    val yto_rDown = curY + 2
    if (inbound(xtoDown)){
      if (curY == yto_lDown + 2 && curX == xtoDown - 2 && (board(curX + 1)(curY - 1) == 'w' || board(curX + 1)(curY - 1) == 'W') && board(xtoDown)(yto_lDown)==empty){
        validateBlackKillMove(curX,curY,xtoDown,yto_lDown)
      }
      if (curY == yto_rDown - 2 && curX == xtoDown - 2 && (board(curX + 1)(curY + 1) == 'w' || board(curX + 1)(curY + 1) == 'W') && board(xtoDown)(yto_rDown)==empty) {  //right
        validateBlackKillMove(curX,curY,xtoDown,yto_rDown)
      }
      if (board(curX)(curY) == 'B' && curY == yto_lDown + 2 && curX == xtoUp + 2 && (board(curX - 1)(curY - 1) == 'w' || board(curX - 1)(curY - 1) == 'W') && board(xtoUp)(yto_lDown)==empty){ //left Up
        validateBlackKillMove(curX,curY,xtoUp,yto_lDown)
      }
      if (board(curX)(curY) == 'B' && curY == yto_rDown - 2 && curX == xtoUp + 2 && (board(curX - 1)(curY + 1) == 'w' || board(curX - 1)(curY + 1) == 'W') && board(xtoDown)(yto_rDown)==empty){ //right up
        validateBlackKillMove(curX,curY,xtoUp,yto_lDown)
      }

    }
  }


  private def neccessaryWhite(xPos: Int, yPos : Int):Boolean={
    if (board(xPos)(yPos)=='w'){
      if (xPos == 0 || xPos == 1) return false
      else if (yPos == 0 || yPos == 1)return  ((board(xPos-1)(yPos+1)=='b' || board(xPos-1)(yPos+1)=='B')&&board(xPos-2)(yPos+2)==empty)
      else if (yPos == 6 || yPos == 7)return ((board(xPos-1)(yPos-1)=='b' || board(xPos-1)(yPos-1)=='B')&&board(xPos-2)(yPos-2)==empty)
      else return ((board(xPos-1)(yPos-1)=='b' || board(xPos-1)(yPos-1)=='B')&&board(xPos-2)(yPos-2)==empty)||((board(xPos-1)(yPos+1)=='b' || board(xPos-1)(yPos+1)=='B')&&board(xPos-2)(yPos+2)==empty)
    }else if (board(xPos)(yPos)=='W'){
      //speacial cases on corners
      if((yPos == 0 || yPos == 1) && (xPos==0||xPos==1))
        return(board(xPos+1)(yPos+1) == 'b' || board(xPos+1)(yPos+1) == 'B') && board(xPos+2)(yPos+2) == empty
      else if((yPos == 0 || yPos == 1) && (xPos==6||xPos==7)) return(board(xPos-1)(yPos+1) == 'b' || board(xPos-1)(yPos+1) == 'B') && board(xPos-2)(yPos+2) == empty
      else if((yPos == 6 || yPos == 7) && (xPos==6||xPos==7)) return(board(xPos-1)(yPos-1) == 'b' || board(xPos-1)(yPos-1) == 'B') && board(xPos-2)(yPos-2) == empty
      else if((yPos == 6 || yPos == 7) && (xPos==0||xPos==1)) return(board(xPos+1)(yPos-1) == 'b' || board(xPos+1)(yPos-1) == 'B') && board(xPos+2)(yPos-2) == empty

        // speacial cases on rows and cols
      else if(xPos==6 || xPos==7) return ((board(xPos-1)(yPos+1) == 'b' || board(xPos-1)(yPos+1) == 'B') && board(xPos-2)(yPos+2) == empty) || ((board(xPos-1)(yPos-1) == 'b' || board(xPos-1)(yPos-1) == 'B') && board(xPos-2)(yPos-2) == empty)
      else if (xPos==0 || xPos==1) return((board(xPos+1)(yPos+1) == 'b' || board(xPos+1)(yPos+1) == 'B') && board(xPos+2)(yPos+2) == empty) || ((board(xPos+1)(yPos-1) == 'b' || board(xPos+1)(yPos-1) == 'B') && board(xPos+2)(yPos-2) == empty)
      else if (yPos == 0 || yPos == 1)  return((board(xPos-1)(yPos+1)=='b' || board(xPos-1)(yPos+1)=='B')&&board(xPos-2)(yPos+2)==empty)||((board(xPos+1)(yPos+1)=='b' || board(xPos+1)(yPos+1)=='B')&&board(xPos+2)(yPos+2)==empty)
      else if (yPos == 6 || yPos == 7) return((board(xPos-1)(yPos-1)=='b' || board(xPos-1)(yPos-1)=='B')&&board(xPos-2)(yPos-2)==empty)||((board(xPos-1)(yPos-1)=='b'||board(xPos-1)(yPos-1)=='B')&&board(xPos-2)(yPos-2)==empty)

      else return((board(xPos-1)(yPos-1)=='b' || board(xPos-1)(yPos-1)=='B')&&board(xPos-2)(yPos-2)==empty)||
        ((board(xPos-1)(yPos+1)=='b' || board(xPos-1)(yPos+1)=='B')&&board(xPos-2)(yPos+2)==empty) ||
        ((board(xPos+1)(yPos-1)=='b' || board(xPos+1)(yPos-1)=='B')&&board(xPos+2)(yPos-2)==empty) ||
        ((board(xPos+1)(yPos+1)=='b' || board(xPos+1)(yPos+1)=='B')&&board(xPos+2)(yPos+2)==empty)
    }
    false
  }
  private def neccessaryBlack(xPos: Int, yPos : Int):Boolean={
    if (board(xPos)(yPos)=='b'){
      if (xPos == 6 || xPos == 7) return false
      else if (yPos == 0|| yPos==1) return((board(xPos+1)(yPos+1)=='w'||board(xPos+1)(yPos+1)=='W')&&board(xPos+2)(yPos+2)==empty)
      else if(yPos == 6 || yPos == 7) return((board(xPos+1)(yPos-1)=='w'||board(xPos+1)(yPos-1)=='W')&&board(xPos+2)(yPos-2)==empty)
      else return((board(xPos+1)(yPos+1)=='w'||board(xPos+1)(yPos+1)=='W')&&board(xPos+2)(yPos+2)==empty)||((board(xPos+1)(yPos-1)=='w'||board(xPos+1)(yPos-1)=='W')&&board(xPos+2)(yPos-2)==empty)
    }else if (board(xPos)(yPos)=='B'){
      //speacial cases on corners
      if((yPos == 0 || yPos == 1) && (xPos==0||xPos==1)) return(board(xPos+1)(yPos+1) == 'w' || board(xPos+1)(yPos+1) == 'W') && board(xPos+2)(yPos+2) == empty
      else if((yPos == 0 || yPos == 1) && (xPos==6||xPos==7)) return(board(xPos-1)(yPos+1) == 'w' || board(xPos-1)(yPos+1) == 'W') && board(xPos-2)(yPos+2) == empty
      else if((yPos == 6 || yPos == 7) && (xPos==6||xPos==7)) return(board(xPos-1)(yPos-1) == 'w' || board(xPos-1)(yPos-1) == 'W') && board(xPos-2)(yPos-2) == empty
      else if((yPos == 6 || yPos == 7) && (xPos==0||xPos==1)) return(board(xPos+1)(yPos-1) == 'w' || board(xPos+1)(yPos-1) == 'W') && board(xPos+2)(yPos-2) == empty

      // speacial cases on rows and cols
      else if(xPos==6 || xPos==7) return((board(xPos-1)(yPos+1) == 'w' || board(xPos-1)(yPos+1) == 'W') && board(xPos-2)(yPos+2) == empty) || ((board(xPos-1)(yPos-1) == 'w' || board(xPos-1)(yPos-1) == 'W') && board(xPos-2)(yPos-2) == empty)
      else if (xPos==0 || xPos==1) return((board(xPos+1)(yPos+1) == 'w' || board(xPos+1)(yPos+1) == 'W') && board(xPos+2)(yPos+2) == empty) || ((board(xPos+1)(yPos-1) == 'w' || board(xPos+1)(yPos-1) == 'W') && board(xPos+2)(yPos-2) == empty)
      else if (yPos == 0 || yPos == 1) return ((board(xPos-1)(yPos+1)=='w' || board(xPos-1)(yPos+1)=='W')&&board(xPos-2)(yPos+2)==empty)||((board(xPos+1)(yPos+1)=='w' || board(xPos+1)(yPos+1)=='W')&&board(xPos+2)(yPos+2)==empty)
      else if (yPos == 6 || yPos == 7) return((board(xPos-1)(yPos-1)=='w' || board(xPos-1)(yPos-1)=='W')&&board(xPos-2)(yPos-2)==empty)||((board(xPos-1)(yPos-1)=='w'||board(xPos-1)(yPos-1)=='W')&&board(xPos-2)(yPos-2)==empty)

      else return((board(xPos-1)(yPos-1)=='w'||board(xPos-1)(yPos-1)=='W')&&board(xPos-2)(yPos-2)==empty)||
        ((board(xPos-1)(yPos+1)=='w'||board(xPos-1)(yPos+1)=='W')&&board(xPos-2)(yPos+2)==empty) ||
        ((board(xPos+1)(yPos-1)=='w'||board(xPos+1)(yPos-1)=='W')&&board(xPos+2)(yPos-2)==empty) ||
        ((board(xPos+1)(yPos+1)=='w'||board(xPos+1)(yPos+1)=='W')&&board(xPos+2)(yPos+2)==empty)
    }
    false

  }


  private def moreValidityOnOriginalWhiteMove():Boolean={
    for (i <- 0 to 7){
      for (j <- 0 to 7){
        if (board(i)(j) == 'w' || board(i)(j) == 'W'){
          if (neccessaryWhite(i,j)) return false
        }
      }
    }
    true
  }
  private def moreValidityOnOriginalBlackMove():Boolean={
    for (i <- 0 to 7){
      for (j <- 0 to 7){
        if (board(i)(j) == 'b' || board(i)(j) == 'B'){
          if (neccessaryBlack(i,j)) return false
        }
      }
    }
    true
  }
/////////////////////////////////////////////////////////////////////////////////////

  private def inbound(r:Int) = r>=0 && r<=7
  def print(): Unit = {
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        System.out.print(board(i)(j) + "  ")
      }
      System.out.print("\n")
    }
  }

}
