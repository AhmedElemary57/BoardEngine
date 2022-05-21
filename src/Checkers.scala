object Checkers {

  val board: Array[Array[Char]] = Array(Array('b', '-', 'b', '-', 'b', '-', 'b', '-'),
    Array('-', 'b', '-', 'b', '-', 'b', '-', 'b'),
    Array('b', '-', 'b', '-', 'b', '-', 'b', '-'),
    Array('-', '-', '-', '-', '-', '-', '-', '-'),
    Array('-', '-', '-', '-', '-', '-', '-', '-'),
    Array('-', 'w', '-', 'w', '-', 'w', '-', 'w'),
    Array('w', '-', 'w', '-', 'w', '-', 'w', '-'),
    Array('-', 'w', '-', 'w', '-', 'w', '-', 'w'))

  private var role = 0
  private var neccesaryWhite = false
  private var neccesaryBlack = false
  private var neccesaryXPOS_forWhite = 10
  private var neccesaryYPOS_forWhite = 10
  private var neccesaryXPOS_forBlack = 10
  private var neccesaryYPOS_forBlack = 10
  ///////////////////////////////////////////////////////////////////////////////
  private var optionalneccesaryWhite = false
  private var optionalneccesaryBlack = false
  private var optionalneccesaryXPOS_forWhite = 10
  private var optionalneccesaryYPOS_forWhite = 10
  private var optionalneccesaryXPOS_forBlack = 10
  private var optionalneccesaryYPOS_forBlack = 10
  ///////////////////////////////////////////////////////////////////////////////////optional
  private var mayneccesaryWhite = false
  private var mayneccesaryBlack = false
  private var mayneccesaryXPOS_forWhite = 10
  private var mayneccesaryYPOS_forWhite = 10
  private var mayneccesaryXPOS_forBlack = 10
  private var mayneccesaryYPOS_forBlack = 10
  ///////////////////////////////////////////////////////////////////////////////////////////////
  def play(input: String, currentState: Array[Array[Char]]): Boolean = {
    if (checkRole(role)) {
      val xPoxFrom = converter(input.charAt(0))
      val yPoxFrom = converter(input.charAt(1))
      val xPoxTo = converter(input.charAt(2))
      val yPoxTo = converter(input.charAt(3))
      if (xPoxFrom == 10 || yPoxFrom == 10 || xPoxTo == 10 || yPoxTo == 10) return false
      if (board(xPoxFrom)(yPoxFrom) == 'w' && role == 0) {
        if (mayneccesaryWhite || neccesaryWhite || optionalneccesaryWhite) if (neccesaryXPOS_forWhite == xPoxFrom) if (validateWhiteKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          neccesaryWhite = false
          recheckMayNecessaryForBlack()
          checkNeccesaryForBlack(xPoxTo, yPoxTo) //////////////////////

          //recheck optional
          recheckoptionalNecessaryForBlack()
          role = 1
          return true
        }
        else if (mayneccesaryXPOS_forWhite == xPoxFrom) if (validateWhiteKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          recheckNecessaryForBlack()
          mayneccesaryWhite = false
          checkNeccesaryForBlack(xPoxTo, yPoxTo)
          recheckoptionalNecessaryForBlack()
          role = 1
          return true
        }
        else if (optionalneccesaryXPOS_forWhite == xPoxFrom) if (validateWhiteKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          optionalneccesaryWhite = false
          //recheck neccessary and
          recheckNecessaryForBlack()
          checkNeccesaryForBlack(xPoxTo, yPoxTo)
          recheckMayNecessaryForBlack()
          role = 1
          return true
        }
        if (validateWhiteOriginalMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) { ////////////////////////////////////////////////////////////////
          val x = board(xPoxFrom)(yPoxFrom)
          board(xPoxFrom)(yPoxFrom) = board(xPoxTo)(yPoxTo)
          board(xPoxTo)(yPoxTo) = x
          /////////////////////////////////////////////////////////
          checkNeccesaryForBlack(xPoxTo, yPoxTo)
          var ytoSend = 0
          if (yPoxTo > yPoxFrom) ytoSend = yPoxTo + 2
          else ytoSend = yPoxTo - 2
          optionalcheckNeccesaryForBlack(xPoxTo, ytoSend)
          role = 1
          return true
        }
      }
      else if (board(xPoxFrom)(yPoxFrom) == 'b' && role == 1) {
        if (mayneccesaryBlack || neccesaryBlack || optionalneccesaryBlack) if (neccesaryXPOS_forBlack == xPoxFrom) if (validateBlackKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          neccesaryBlack = false
          recheckMayNecessaryForWhite()
          checkNeccesaryForWhite(xPoxTo, yPoxTo)
          recheckoptionalNecessaryForWhite()
          role = 0
          return true
        }
        else if (mayneccesaryXPOS_forBlack == xPoxFrom) if (validateBlackKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          recheckNecessaryForWhite()
          checkNeccesaryForWhite(xPoxTo, yPoxTo)
          mayneccesaryBlack = false
          recheckoptionalNecessaryForWhite()
          role = 0
          return true
        }
        else if (optionalneccesaryXPOS_forBlack == xPoxFrom) if (validateBlackKillMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          optionalneccesaryBlack = false
          recheckNecessaryForWhite()
          checkNeccesaryForWhite(xPoxTo, yPoxTo)
          recheckMayNecessaryForWhite()
          role = 0
          return true
        }
        if (validateBlackOriginalMove(xPoxFrom, yPoxFrom, xPoxTo, yPoxTo)) {
          val x = board(xPoxFrom)(yPoxFrom)
          board(xPoxFrom)(yPoxFrom) = board(xPoxTo)(yPoxTo)
          board(xPoxTo)(yPoxTo) = x
          checkNeccesaryForWhite(xPoxTo, yPoxTo)
          var ytoSend = 0
          if (yPoxTo > yPoxFrom) ytoSend = yPoxTo - 2
          else ytoSend = yPoxTo + 2
          optionalcheckNeccesaryForBlack(xPoxTo, ytoSend)
          optionalcheckNeccesaryForWhite(ytoSend, yPoxTo)
          role = 0
          return true
        }
      }
    }
    false
  }

  ///////////////////////////////////////////////////////////////////////////////////////// original move
  def validateWhiteOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (xPoxFrom == xPoxTo + 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == '-') return true
    false
  }

  def validateBlackOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (xPoxFrom == xPoxTo - 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == '-') return true
    false
  }
  //////////////////////////////////////////////////////////////////////////////////////////////////

  ////////////////////////////////////////////////////////////////////////////////////////////// recheck neccesary
  private def recheckNecessaryForBlack(): Unit = {
    if (neccesaryXPOS_forBlack != 10 || neccesaryYPOS_forBlack != 10) if ((board(neccesaryXPOS_forBlack + 1)(neccesaryYPOS_forBlack + 1) == 'w' && board(neccesaryXPOS_forBlack + 2)(neccesaryYPOS_forBlack + 2) == '-') || (board(neccesaryXPOS_forBlack + 1)(neccesaryYPOS_forBlack - 1) == 'w' && board(neccesaryXPOS_forBlack + 2)(neccesaryYPOS_forBlack - 2) == '-')) neccesaryBlack = true
    else neccesaryBlack = false
  }

  private def recheckNecessaryForWhite(): Unit = {
    if (neccesaryXPOS_forWhite != 10 || neccesaryYPOS_forWhite != 10) if ((board(neccesaryXPOS_forWhite - 1)(neccesaryYPOS_forWhite - 1) == 'b' && board(neccesaryXPOS_forWhite - 2)(neccesaryYPOS_forWhite - 2) == '-') || (board(neccesaryXPOS_forWhite - 1)(neccesaryYPOS_forWhite + 1) == 'b' && board(neccesaryXPOS_forWhite - 2)(neccesaryYPOS_forWhite + 2) == '-')) neccesaryWhite = true
    else neccesaryWhite = false
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////////////////////////////////// recheck may neccessary
  private def recheckMayNecessaryForBlack(): Unit = {
    if (mayneccesaryXPOS_forBlack != 10 || mayneccesaryYPOS_forBlack != 10) if ((board(mayneccesaryXPOS_forBlack + 1)(mayneccesaryYPOS_forBlack + 1) == 'w' && board(mayneccesaryXPOS_forBlack + 2)(mayneccesaryYPOS_forBlack + 2) == '-') || (board(mayneccesaryXPOS_forBlack + 1)(mayneccesaryYPOS_forBlack - 1) == 'w' && board(mayneccesaryXPOS_forBlack + 2)(mayneccesaryYPOS_forBlack - 2) == '-')) mayneccesaryBlack = true
    else mayneccesaryBlack = false
  }

  private def recheckMayNecessaryForWhite(): Unit = {
    if (mayneccesaryXPOS_forWhite != 10 || mayneccesaryYPOS_forWhite != 10) if ((board(mayneccesaryXPOS_forWhite - 1)(mayneccesaryYPOS_forWhite - 1) == 'b' && board(mayneccesaryXPOS_forWhite - 2)(mayneccesaryYPOS_forWhite - 2) == '-') || (board(mayneccesaryXPOS_forWhite - 1)(mayneccesaryYPOS_forWhite + 1) == 'b' && board(mayneccesaryXPOS_forWhite - 2)(mayneccesaryYPOS_forWhite + 2) == '-')) mayneccesaryWhite = true
    else mayneccesaryWhite = false
  }


  ///////////////////////////////////////////////////////////////////////////////////////////////////////recheck optional
  private def recheckoptionalNecessaryForBlack(): Unit = {
    if (optionalneccesaryXPOS_forBlack != 10 || optionalneccesaryYPOS_forBlack != 10) if ((board(optionalneccesaryXPOS_forBlack + 1)(optionalneccesaryYPOS_forBlack + 1) == 'w' && board(optionalneccesaryXPOS_forBlack + 2)(optionalneccesaryYPOS_forBlack + 2) == '-') || (board(optionalneccesaryXPOS_forBlack + 1)(optionalneccesaryYPOS_forBlack - 1) == 'w' && board(optionalneccesaryXPOS_forBlack + 2)(optionalneccesaryYPOS_forBlack - 2) == '-')) optionalneccesaryBlack = true
    else optionalneccesaryBlack = false
  }

  private def recheckoptionalNecessaryForWhite(): Unit = {
    if (optionalneccesaryXPOS_forWhite != 10 || optionalneccesaryYPOS_forWhite != 10) if ((board(optionalneccesaryXPOS_forWhite - 1)(optionalneccesaryYPOS_forWhite - 1) == 'b' && board(optionalneccesaryXPOS_forWhite - 2)(optionalneccesaryYPOS_forWhite - 2) == '-') || (board(optionalneccesaryXPOS_forWhite - 1)(optionalneccesaryYPOS_forWhite + 1) == 'b' && board(optionalneccesaryXPOS_forWhite - 2)(optionalneccesaryYPOS_forWhite + 2) == '-')) optionalneccesaryWhite = true
    else optionalneccesaryWhite = false
  }
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  private def checkNeccesaryForBlack(curX_ofWhite: Int, curY_ofWhite: Int): Unit = {
    neccesaryBlack = false
    var enter = false
    if (board(curX_ofWhite - 1)(curY_ofWhite - 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite + 1) == '-') {
      neccesaryBlack = true
      neccesaryXPOS_forBlack = curX_ofWhite - 1
      neccesaryYPOS_forBlack = curY_ofWhite - 1
      enter = true
    }
    if (board(curX_ofWhite - 1)(curY_ofWhite + 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite - 1) == '-') {
      neccesaryBlack = true
      if (enter) {
        mayneccesaryXPOS_forBlack = curX_ofWhite - 1
        mayneccesaryYPOS_forBlack = curY_ofWhite + 1
        mayneccesaryBlack = true
      }
      else {
        neccesaryXPOS_forBlack = curX_ofWhite - 1
        neccesaryYPOS_forBlack = curY_ofWhite + 1
        mayneccesaryBlack = false
      }
    }
  }

  private def checkNeccesaryForWhite(curX_ofBlack: Int, curY_ofBlack: Int): Unit = {
    neccesaryWhite = false
    var enter = false
    if (board(curX_ofBlack + 1)(curY_ofBlack + 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack - 1) == '-') {
      neccesaryWhite = true
      neccesaryXPOS_forWhite = curX_ofBlack + 1
      neccesaryYPOS_forWhite = curY_ofBlack + 1
      enter = true
    }
    if (board(curX_ofBlack + 1)(curY_ofBlack - 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack + 1) == '-') {
      neccesaryWhite = true
      if (enter) {
        mayneccesaryXPOS_forWhite = curX_ofBlack + 1
        mayneccesaryYPOS_forWhite = curY_ofBlack - 1
        mayneccesaryWhite = true
      }
      else {
        neccesaryXPOS_forWhite = curX_ofBlack + 1
        neccesaryYPOS_forWhite = curY_ofBlack - 1
        mayneccesaryWhite = false
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  Defender leaves
  private def optionalcheckNeccesaryForBlack(curX_ofWhite: Int, curY_ofWhite: Int): Unit = {
    if (board(curX_ofWhite - 1)(curY_ofWhite - 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite + 1) == '-') {
      optionalneccesaryBlack = true
      optionalneccesaryXPOS_forBlack = curX_ofWhite - 1
      optionalneccesaryYPOS_forBlack = curY_ofWhite - 1
    }
    else if (board(curX_ofWhite - 1)(curY_ofWhite + 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite - 1) == '-') {
      optionalneccesaryBlack = true
      optionalneccesaryXPOS_forBlack = curX_ofWhite - 1
      optionalneccesaryYPOS_forBlack = curY_ofWhite + 1
    }
    else optionalneccesaryBlack = false
  }

  private def optionalcheckNeccesaryForWhite(curX_ofBlack: Int, curY_ofBlack: Int): Unit = {
    if (board(curX_ofBlack + 1)(curY_ofBlack + 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack - 1) == '-') {
      optionalneccesaryWhite = true
      optionalneccesaryXPOS_forWhite = curX_ofBlack + 1
      optionalneccesaryYPOS_forWhite = curY_ofBlack + 1
    }
    else if (board(curX_ofBlack + 1)(curY_ofBlack - 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack + 1) == '-') {
      optionalneccesaryWhite = true
      optionalneccesaryXPOS_forWhite = curX_ofBlack + 1
      optionalneccesaryYPOS_forWhite = curY_ofBlack - 1
    }
    else optionalneccesaryWhite = false
  }
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  def validateWhiteKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo + 2 && board(xPoxFrom - 1)(yPoxFrom + 1) == 'b') {
      board(xPoxFrom - 1)(yPoxFrom + 1) = '-'
      return true
    }
    else if (yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo + 2 && board(xPoxFrom - 1)(yPoxFrom - 1) == 'b') {
      board(xPoxFrom - 1)(yPoxFrom - 1) = '-'
      return true
    }
    false
  }

  def validateBlackKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo - 2 && board(xPoxFrom + 1)(yPoxFrom + 1) == 'w') {
      board(xPoxFrom + 1)(yPoxFrom + 1) = '-'
      return true
    }
    else if (yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo - 2 && board(xPoxFrom + 1)(yPoxFrom - 1) == 'w') {
      board(xPoxFrom + 1)(yPoxFrom - 1) = '-'
      return true
    }
    false
  }

  private def checkRole(r: Int) = r == role

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








}