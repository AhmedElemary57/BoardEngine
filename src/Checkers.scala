import java.util.Scanner

object Checkers {

  val Arr: Array[Array[Char]] = Array(
    Array('-', '-', 'b', '-', 'b', '-', 'b', '-'),
    Array('-', 'W', '-', 'b', '-', 'b', '-', 'b'),
    Array('b', '-', 'b', '-', 'b', '-', 'b', '-'),
    Array('-', '-', '-', '-', '-', '-', '-', '-'),
    Array('-', '-', '-', '-', '-', '-', '-', '-'),
    Array('-', 'w', '-', 'w', '-', 'w', '-', 'w'),
    Array('w', '-', 'w', '-', 'w', '-', 'w', '-'),
    Array('-', 'w', '-', 'w', '-', 'w', '-', 'w'))

  private var role = 0
  ///////////////////////////////////////////////////////////////////////////////////////////////
  def play(input: String): Boolean = {
      val xPoxFrom = converter(input.charAt(0))
      val yPoxFrom = converter(input.charAt(1))
      val xPoxTo = converter(input.charAt(2))
      val yPoxTo = converter(input.charAt(3))
      if (xPoxFrom == 10 || yPoxFrom == 10 || xPoxTo == 10 || yPoxTo == 10)  return false

      if (role==0 ){ // white role
        if (validateWhiteKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          role = 1
          if (xPoxTo == 0)Arr(xPoxTo)( yPoxTo) ='W'
          return true
        }
        else if(validateWhiteOriginalMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          role = 1
          if (xPoxTo == 0)Arr(xPoxTo)( yPoxTo) ='W'
          return true
        }

      }
      else{ // black role
        if (validateBlackKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          role = 0
          if (xPoxTo == 7)Arr(xPoxTo)( yPoxTo) ='B'
          return true
        }
        else if(validateBlackOriginalMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)){
          role = 0
          if (xPoxTo == 7)Arr(xPoxTo)( yPoxTo) ='B'
          return true
        }
      }
    false
  }

  ///////////////////////////////////////////////////////////////////////////////////////// original move
  def validateWhiteOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if(Arr(xPoxFrom)(yPoxFrom) == 'w' && (xPoxFrom == xPoxTo + 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && Arr(xPoxTo)(yPoxTo) == '-')&&moreValidityOnOriginalWhiteMove()){
      Arr(xPoxTo)(yPoxTo) = 'w'
      Arr(xPoxFrom)(yPoxFrom) = '-'
      return true
    }else if (Arr(xPoxFrom)(yPoxFrom) == 'W' && (xPoxFrom == xPoxTo + 1 || xPoxFrom == xPoxTo - 1) && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && Arr(xPoxTo)(yPoxTo) == '-' &&moreValidityOnOriginalWhiteMove()){
      Arr(xPoxTo)(yPoxTo) = 'W'
      Arr(xPoxFrom)(yPoxFrom) = '-'
      return true
    }
    false
  }
  def validateBlackOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (Arr(xPoxFrom)(yPoxFrom) == 'b' &&(xPoxFrom == xPoxTo - 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && Arr(xPoxTo)(yPoxTo) == '-')&&moreValidityOnOriginalBlackMove()) {
      Arr(xPoxTo)(yPoxTo) = 'b'
      Arr(xPoxFrom)(yPoxFrom) = '-'
      return true
    }else if (Arr(xPoxFrom)(yPoxFrom) == 'B' && (xPoxFrom == xPoxTo + 1 || xPoxFrom == xPoxTo - 1) && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && Arr(xPoxTo)(yPoxTo) == '-' &&moreValidityOnOriginalBlackMove()){
      Arr(xPoxTo)(yPoxTo) = 'B'
      Arr(xPoxFrom)(yPoxFrom) = '-'
      return true
    }
    false
  }


  private def validateWhiteKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    var bol = false
    if (Arr(xPoxFrom)(yPoxFrom) == 'w' && yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo + 2 && (Arr(xPoxFrom - 1)(yPoxFrom + 1) == 'b'||Arr(xPoxFrom - 1)(yPoxFrom + 1) == 'B') && Arr(xPoxTo)(yPoxTo)=='-') { // right
      Arr(xPoxFrom - 1)(yPoxFrom + 1) = '-'
      bol = true
    }
    else if (Arr(xPoxFrom)(yPoxFrom) == 'w' && yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo + 2 && (Arr(xPoxFrom - 1)(yPoxFrom - 1) == 'b' || Arr(xPoxFrom - 1)(yPoxFrom - 1) == 'B') && Arr(xPoxTo)(yPoxTo)=='-') {
      Arr(xPoxFrom - 1)(yPoxFrom - 1) = '-'
      bol = true
    }
    else if (Arr(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom -2 && (Arr(xPoxFrom - 1)(yPoxFrom + 1) == 'b' || Arr(xPoxFrom - 1)(yPoxFrom + 1) == 'B') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom - 1)(yPoxFrom + 1) = '-'
      bol = true
    }
    else if (Arr(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom -2 && (Arr(xPoxFrom - 1)(yPoxFrom - 1) == 'b' || Arr(xPoxFrom - 1)(yPoxFrom - 1) == 'B') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom - 1)(yPoxFrom - 1) = '-'
      bol = true
    }
    else if(Arr(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom +2 && (Arr(xPoxFrom + 1)(yPoxFrom + 1) == 'b' || Arr(xPoxFrom + 1)(yPoxFrom + 1) == 'B') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom + 1)(yPoxFrom + 1) = '-'
      bol = true
    }
    else if (Arr(xPoxFrom)(yPoxFrom) == 'W' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom +2 && (Arr(xPoxFrom + 1)(yPoxFrom - 1) == 'b' || Arr(xPoxFrom + 1)(yPoxFrom - 1) == 'B') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom + 1)(yPoxFrom - 1) = '-'
      bol = true
    }
    if (bol){
      val x = Arr(xPoxFrom)(yPoxFrom)
      Arr(xPoxTo)(yPoxTo) = x
      Arr(xPoxFrom)(yPoxFrom) = '-'
      recehck_white(xPoxTo, yPoxTo)
      return true
    }
    false
  }
  private def validateBlackKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    var bol = false
    if (yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo - 2 && (Arr(xPoxFrom + 1)(yPoxFrom + 1) == 'w' || Arr(xPoxFrom + 1)(yPoxFrom + 1) == 'W') && Arr(xPoxTo)(yPoxTo)=='-') {
      Arr(xPoxFrom + 1)(yPoxFrom + 1) = '-'
      bol = true
    }
    else if (yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo - 2 && (Arr(xPoxFrom + 1)(yPoxFrom - 1) == 'w' || Arr(xPoxFrom + 1)(yPoxFrom - 1) == 'W') && Arr(xPoxTo)(yPoxTo)=='-') {
      Arr(xPoxFrom + 1)(yPoxFrom - 1) = '-'
      bol = true
    }else if (Arr(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom -2 && (Arr(xPoxFrom - 1)(yPoxFrom + 1) == 'w' || Arr(xPoxFrom - 1)(yPoxFrom + 1) == 'W') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom - 1)(yPoxFrom + 1) = '-'
      bol = true
    }
    else if (Arr(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom -2 && (Arr(xPoxFrom - 1)(yPoxFrom - 1) == 'w' || Arr(xPoxFrom - 1)(yPoxFrom - 1) == 'W') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom - 1)(yPoxFrom - 1) = '-'
      bol = true
    }
    else if(Arr(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom + 2 && xPoxTo == xPoxFrom +2 && (Arr(xPoxFrom + 1)(yPoxFrom + 1) == 'w' || Arr(xPoxFrom + 1)(yPoxFrom + 1) == 'W') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom + 1)(yPoxFrom + 1) = '-'
      bol = true
    }
    else if (Arr(xPoxFrom)(yPoxFrom) == 'B' && yPoxTo == yPoxFrom - 2 && xPoxTo == xPoxFrom +2 && (Arr(xPoxFrom + 1)(yPoxFrom - 1) == 'w' || Arr(xPoxFrom + 1)(yPoxFrom - 1) == 'W') && Arr(xPoxTo)(yPoxTo)=='-'){
      Arr(xPoxFrom + 1)(yPoxFrom - 1) = '-'
      bol = true
    }
    if (bol ){
      val x = Arr(xPoxFrom)(yPoxFrom)
      Arr(xPoxTo)(yPoxTo) = x
      Arr(xPoxFrom)(yPoxFrom) = '-'
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
      if (curY == yto_lUp + 2 && curX == xtoUp + 2 && (Arr(curX - 1)(curY - 1) == 'b' || Arr(curX - 1)(curY - 1) == 'B') && Arr(xtoUp)(yto_lUp)=='-'){
        validateWhiteKillMove(curX,curY,xtoUp,yto_lUp)
      }
      if (curY == yto_rUp - 2 && curX == xtoUp + 2 && (Arr(curX - 1)(curY + 1) == 'b' || Arr(curX - 1)(curY + 1) == 'B') && Arr(xtoUp)(yto_rUp)=='-') { //right Up
        validateWhiteKillMove(curX,curY,xtoUp,yto_rUp)
      }
      if(Arr(curX)(curY) == 'W' && curY == yto_lUp + 2 && curX == xtoDown - 2 && (Arr(curX + 1)(curY - 1) == 'b' || Arr(curX + 1)(curY - 1) == 'B') && Arr(xtoDown)(yto_lUp)=='-'){ //left Down
        validateWhiteKillMove(curX,curY,xtoDown,yto_rUp)
      }
      if (Arr(curX)(curY) == 'W' && curY == yto_rUp - 2 && curX == xtoDown - 2 && (Arr(curX + 1)(curY + 1) == 'b' || Arr(curX + 1)(curY + 1) == 'B') && Arr(xtoDown)(yto_rUp)=='-'){  //right Down
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
      if (curY == yto_lDown + 2 && curX == xtoDown - 2 && (Arr(curX + 1)(curY - 1) == 'w' || Arr(curX + 1)(curY - 1) == 'W') && Arr(xtoDown)(yto_lDown)=='-'){
        validateBlackKillMove(curX,curY,xtoDown,yto_lDown)
      }
      if (curY == yto_rDown - 2 && curX == xtoDown - 2 && (Arr(curX + 1)(curY + 1) == 'w' || Arr(curX + 1)(curY + 1) == 'W') && Arr(xtoDown)(yto_rDown)=='-') {  //right
        validateBlackKillMove(curX,curY,xtoDown,yto_rDown)
      }
      if (Arr(curX)(curY) == 'B' && curY == yto_lDown + 2 && curX == xtoUp + 2 && (Arr(curX - 1)(curY - 1) == 'w' || Arr(curX - 1)(curY - 1) == 'W') && Arr(xtoUp)(yto_lDown)=='-'){ //left Up
        validateBlackKillMove(curX,curY,xtoUp,yto_lDown)
      }
      if (Arr(curX)(curY) == 'B' && curY == yto_rDown - 2 && curX == xtoUp + 2 && (Arr(curX - 1)(curY + 1) == 'w' || Arr(curX - 1)(curY + 1) == 'W') && Arr(xtoDown)(yto_rDown)=='-'){ //right up
        validateBlackKillMove(curX,curY,xtoUp,yto_lDown)
      }

    }
  }


  private def neccessaryWhite(xPos: Int, yPos : Int):Boolean={
    if (Arr(xPos)(yPos)=='w'){
      if (xPos == 0 || xPos == 1) return false
      else if (yPos == 0 || yPos == 1)return  ((Arr(xPos-1)(yPos+1)=='b' || Arr(xPos-1)(yPos+1)=='B')&&Arr(xPos-2)(yPos+2)=='-')
      else if (yPos == 6 || yPos == 7)return ((Arr(xPos-1)(yPos-1)=='b' || Arr(xPos-1)(yPos-1)=='B')&&Arr(xPos-2)(yPos-2)=='-')
      else return ((Arr(xPos-1)(yPos-1)=='b' || Arr(xPos-1)(yPos-1)=='B')&&Arr(xPos-2)(yPos-2)=='-')||((Arr(xPos-1)(yPos+1)=='b' || Arr(xPos-1)(yPos+1)=='B')&&Arr(xPos-2)(yPos+2)=='-')
    }else if (Arr(xPos)(yPos)=='W'){
      //speacial cases on corners
      if((yPos == 0 || yPos == 1) && (xPos==0||xPos==1))
        return(Arr(xPos+1)(yPos+1) == 'b' || Arr(xPos+1)(yPos+1) == 'B') && Arr(xPos+2)(yPos+2) == '-'
      else if((yPos == 0 || yPos == 1) && (xPos==6||xPos==7)) return(Arr(xPos-1)(yPos+1) == 'b' || Arr(xPos-1)(yPos+1) == 'B') && Arr(xPos-2)(yPos+2) == '-'
      else if((yPos == 6 || yPos == 7) && (xPos==6||xPos==7)) return(Arr(xPos-1)(yPos-1) == 'b' || Arr(xPos-1)(yPos-1) == 'B') && Arr(xPos-2)(yPos-2) == '-'
      else if((yPos == 6 || yPos == 7) && (xPos==0||xPos==1)) return(Arr(xPos+1)(yPos-1) == 'b' || Arr(xPos+1)(yPos-1) == 'B') && Arr(xPos+2)(yPos-2) == '-'

        // speacial cases on rows and cols
      else if(xPos==6 || xPos==7) return ((Arr(xPos-1)(yPos+1) == 'b' || Arr(xPos-1)(yPos+1) == 'B') && Arr(xPos-2)(yPos+2) == '-') || ((Arr(xPos-1)(yPos-1) == 'b' || Arr(xPos-1)(yPos-1) == 'B') && Arr(xPos-2)(yPos-2) == '-')
      else if (xPos==0 || xPos==1) return((Arr(xPos+1)(yPos+1) == 'b' || Arr(xPos+1)(yPos+1) == 'B') && Arr(xPos+2)(yPos+2) == '-') || ((Arr(xPos+1)(yPos-1) == 'b' || Arr(xPos+1)(yPos-1) == 'B') && Arr(xPos+2)(yPos-2) == '-')
      else if (yPos == 0 || yPos == 1)  return((Arr(xPos-1)(yPos+1)=='b' || Arr(xPos-1)(yPos+1)=='B')&&Arr(xPos-2)(yPos+2)=='-')||((Arr(xPos+1)(yPos+1)=='b' || Arr(xPos+1)(yPos+1)=='B')&&Arr(xPos+2)(yPos+2)=='-')
      else if (yPos == 6 || yPos == 7) return((Arr(xPos-1)(yPos-1)=='b' || Arr(xPos-1)(yPos-1)=='B')&&Arr(xPos-2)(yPos-2)=='-')||((Arr(xPos-1)(yPos-1)=='b'||Arr(xPos-1)(yPos-1)=='B')&&Arr(xPos-2)(yPos-2)=='-')

      else return((Arr(xPos-1)(yPos-1)=='b' || Arr(xPos-1)(yPos-1)=='B')&&Arr(xPos-2)(yPos-2)=='-')||
        ((Arr(xPos-1)(yPos+1)=='b' || Arr(xPos-1)(yPos+1)=='B')&&Arr(xPos-2)(yPos+2)=='-') ||
        ((Arr(xPos+1)(yPos-1)=='b' || Arr(xPos+1)(yPos-1)=='B')&&Arr(xPos+2)(yPos-2)=='-') ||
        ((Arr(xPos+1)(yPos+1)=='b' || Arr(xPos+1)(yPos+1)=='B')&&Arr(xPos+2)(yPos+2)=='-')
    }
    false
  }
  private def neccessaryBlack(xPos: Int, yPos : Int):Boolean={
    if (Arr(xPos)(yPos)=='b'){
      if (xPos == 6 || xPos == 7) return false
      else if (yPos == 0|| yPos==1) return((Arr(xPos+1)(yPos+1)=='w'||Arr(xPos+1)(yPos+1)=='W')&&Arr(xPos+2)(yPos+2)=='-')
      else if(yPos == 6 || yPos == 7) return((Arr(xPos+1)(yPos-1)=='w'||Arr(xPos+1)(yPos-1)=='W')&&Arr(xPos+2)(yPos-2)=='-')
      else return((Arr(xPos+1)(yPos+1)=='w'||Arr(xPos+1)(yPos+1)=='W')&&Arr(xPos+2)(yPos+2)=='-')||((Arr(xPos+1)(yPos-1)=='w'||Arr(xPos+1)(yPos-1)=='W')&&Arr(xPos+2)(yPos-2)=='-')
    }else if (Arr(xPos)(yPos)=='B'){
      //speacial cases on corners
      if((yPos == 0 || yPos == 1) && (xPos==0||xPos==1)) return(Arr(xPos+1)(yPos+1) == 'w' || Arr(xPos+1)(yPos+1) == 'W') && Arr(xPos+2)(yPos+2) == '-'
      else if((yPos == 0 || yPos == 1) && (xPos==6||xPos==7)) return(Arr(xPos-1)(yPos+1) == 'w' || Arr(xPos-1)(yPos+1) == 'W') && Arr(xPos-2)(yPos+2) == '-'
      else if((yPos == 6 || yPos == 7) && (xPos==6||xPos==7)) return(Arr(xPos-1)(yPos-1) == 'w' || Arr(xPos-1)(yPos-1) == 'W') && Arr(xPos-2)(yPos-2) == '-'
      else if((yPos == 6 || yPos == 7) && (xPos==0||xPos==1)) return(Arr(xPos+1)(yPos-1) == 'w' || Arr(xPos+1)(yPos-1) == 'W') && Arr(xPos+2)(yPos-2) == '-'

      // speacial cases on rows and cols
      else if(xPos==6 || xPos==7) return((Arr(xPos-1)(yPos+1) == 'w' || Arr(xPos-1)(yPos+1) == 'W') && Arr(xPos-2)(yPos+2) == '-') || ((Arr(xPos-1)(yPos-1) == 'w' || Arr(xPos-1)(yPos-1) == 'W') && Arr(xPos-2)(yPos-2) == '-')
      else if (xPos==0 || xPos==1) return((Arr(xPos+1)(yPos+1) == 'w' || Arr(xPos+1)(yPos+1) == 'W') && Arr(xPos+2)(yPos+2) == '-') || ((Arr(xPos+1)(yPos-1) == 'w' || Arr(xPos+1)(yPos-1) == 'W') && Arr(xPos+2)(yPos-2) == '-')
      else if (yPos == 0 || yPos == 1) return ((Arr(xPos-1)(yPos+1)=='w' || Arr(xPos-1)(yPos+1)=='W')&&Arr(xPos-2)(yPos+2)=='-')||((Arr(xPos+1)(yPos+1)=='w' || Arr(xPos+1)(yPos+1)=='W')&&Arr(xPos+2)(yPos+2)=='-')
      else if (yPos == 6 || yPos == 7) return((Arr(xPos-1)(yPos-1)=='w' || Arr(xPos-1)(yPos-1)=='W')&&Arr(xPos-2)(yPos-2)=='-')||((Arr(xPos-1)(yPos-1)=='w'||Arr(xPos-1)(yPos-1)=='W')&&Arr(xPos-2)(yPos-2)=='-')

      else return((Arr(xPos-1)(yPos-1)=='w'||Arr(xPos-1)(yPos-1)=='W')&&Arr(xPos-2)(yPos-2)=='-')||
        ((Arr(xPos-1)(yPos+1)=='w'||Arr(xPos-1)(yPos+1)=='W')&&Arr(xPos-2)(yPos+2)=='-') ||
        ((Arr(xPos+1)(yPos-1)=='w'||Arr(xPos+1)(yPos-1)=='W')&&Arr(xPos+2)(yPos-2)=='-') ||
        ((Arr(xPos+1)(yPos+1)=='w'||Arr(xPos+1)(yPos+1)=='W')&&Arr(xPos+2)(yPos+2)=='-')
    }
    false

  }


  private def moreValidityOnOriginalWhiteMove():Boolean={
    for (i <- 0 to 7){
      for (j <- 0 to 7){
        if (Arr(i)(j) == 'w' || Arr(i)(j) == 'W'){
          if (neccessaryWhite(i,j)) return false
        }
      }
    }
    true
  }
  private def moreValidityOnOriginalBlackMove():Boolean={
    for (i <- 0 to 7){
      for (j <- 0 to 7){
        if (Arr(i)(j) == 'b' || Arr(i)(j) == 'B'){
          if (neccessaryBlack(i,j)) return false
        }
      }
    }
    true
  }
/////////////////////////////////////////////////////////////////////////////////////

  private def inbound(r:Int) = r>=0 && r<=7
  private def converter(x: Char) = if (x == 'h') 0
  else if (x == 'g') 1
  else if (x == 'f') 2
  else if (x == 'e') 3
  else if (x == 'd') 4
  else if (x == 'c') 5
  else if (x == 'b') 6
  else if (x == 'a') 7
  else if (x == '1') 0
  else if (x == '2') 1
  else if (x == '3') 2
  else if (x == '4') 3
  else if (x == '5') 4
  else if (x == '6') 5
  else if (x == '7') 6
  else if (x == '8') 7
  else 10
  def print(): Unit = {
    for (i <- 0 until 8) {
      for (j <- 0 until 8) {
        System.out.print(Arr(i)(j) + "  ")
      }
      System.out.print("\n")
    }
  }
  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    print()
    while (true) {
      if (role==0 ) System.out.print("White role : ")
      else  System.out.print("Black role : ")
      val s: String = sc.next
      play(s)
      print()
    }
  }
}
