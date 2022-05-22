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

  private var role = 0
  private var necessaryWhite = false
  private var necessaryBlack = false
  private var necessaryXPOS_forWhite = 10
  private var necessaryYPOS_forWhite = 10
  private var necessaryXPOS_forBlack = 10
  private var necessaryYPOS_forBlack = 10

  private var optionalneccesaryWhite = false
  private var optionalneccesaryBlack = false
  private var optionalneccesaryXPOS_forWhite = 10
  private var optionalneccesaryYPOS_forWhite = 10
  private var optionalneccesaryXPOS_forBlack = 10
  private var optionalneccesaryYPOS_forBlack = 10

  private var mayneccesaryWhite = false
  private var mayneccesaryBlack = false
  private var mayneccesaryXPOS_forWhite = 10
  private var mayneccesaryYPOS_forWhite = 10
  private var mayneccesaryXPOS_forBlack = 10
  private var mayneccesaryYPOS_forBlack = 10

  def play(indices: (Int, Int, Int, Int)): Boolean = { // 1a2b
    if (checkRole(role)) {

      if (indices._1 == 10 || indices._2 == 10 || indices._3 == 10 || indices._4 == 10) return false

      if (board(indices._1)(indices._2) == 'w' && role == 0) {//white player
        if (mayneccesaryWhite || necessaryWhite || optionalneccesaryWhite) {
          if (necessaryXPOS_forWhite == indices._1) {
            if (validateWhiteKillMove(indices._1, indices._2, indices._3, indices._4)) {
              necessaryWhite = false
              recheckMayNecessaryForBlack()
              checkNeccesaryForBlack(indices._3, indices._4)

              //recheck optional
              recheckoptionalNecessaryForBlack()
              role = 1
              return true
            }
            else if (mayneccesaryXPOS_forWhite == indices._1) {
              if (validateWhiteKillMove(indices._1, indices._2, indices._3, indices._4)) {
                recheckNecessaryForBlack()
                mayneccesaryWhite = false
                checkNeccesaryForBlack(indices._3, indices._4)
                recheckoptionalNecessaryForBlack()
                role = 1
                return true
              }
              else if (optionalneccesaryXPOS_forWhite == indices._1) {
                if (validateWhiteKillMove(indices._1, indices._2, indices._3, indices._4)) {
                  optionalneccesaryWhite = false
                  //recheck neccessary and
                  recheckNecessaryForBlack()
                  checkNeccesaryForBlack(indices._3, indices._4)
                  recheckMayNecessaryForBlack()
                  role = 1
                  return true
                }
              }
            }
          }
        }
        if (validateWhiteOriginalMove(indices._1, indices._2, indices._3, indices._4)) {
          val x = board(indices._1)(indices._2)
          board(indices._1)(indices._2) = board(indices._3)(indices._4)
          board(indices._3)(indices._4) = x

          checkNeccesaryForBlack(indices._3, indices._4)
          var ytoSend = 0
          if (indices._4 > indices._2) ytoSend = indices._4 + 2
          else ytoSend = indices._4 - 2
          optionalcheckNeccesaryForBlack(indices._3, ytoSend)
          role = 1
          return true
        }
      }
      else if (board(indices._1)(indices._2) == 'b' && role == 1) {
        if (mayneccesaryBlack || necessaryBlack || optionalneccesaryBlack) {
          if (necessaryXPOS_forBlack == indices._1) {
            if (validateBlackKillMove(indices._1, indices._2, indices._3, indices._4)) {
              necessaryBlack = false
              recheckMayNecessaryForWhite()
              checkNeccesaryForWhite(indices._3, indices._4)
              recheckoptionalNecessaryForWhite()
              role = 0
              return true
            }
            else if (mayneccesaryXPOS_forBlack == indices._1) {
              if (validateBlackKillMove(indices._1, indices._2, indices._3, indices._4)) {
                recheckNecessaryForWhite()
                checkNeccesaryForWhite(indices._3, indices._4)
                mayneccesaryBlack = false
                recheckoptionalNecessaryForWhite()
                role = 0
                return true
              }
              else if (optionalneccesaryXPOS_forBlack == indices._1) {
                if (validateBlackKillMove(indices._1, indices._2, indices._3, indices._4)) {
                  optionalneccesaryBlack = false
                  recheckNecessaryForWhite()
                  checkNeccesaryForWhite(indices._3, indices._4)
                  recheckMayNecessaryForWhite()
                  role = 0
                  return true
               }
              }
            }
          }
        }
        if (validateBlackOriginalMove(indices._1, indices._2, indices._3, indices._4)) {
          val x = board(indices._1)(indices._2)
          board(indices._1)(indices._2) = board(indices._3)(indices._4)
          board(indices._3)(indices._4) = x
          checkNeccesaryForWhite(indices._3, indices._4)
          var ytoSend = 0
          if (indices._4 > indices._2) ytoSend = indices._4 - 2
          else ytoSend = indices._4 + 2
          optionalcheckNeccesaryForBlack(indices._3, ytoSend)
          optionalcheckNeccesaryForWhite(ytoSend, indices._4)
          role = 0
          return true
        }
      }
    }
    false
  }

  // original move
  def validateWhiteOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (xPoxFrom == xPoxTo + 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == '.') return true
    false
  }

  def validateBlackOriginalMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (xPoxFrom == xPoxTo - 1 && (yPoxFrom == yPoxTo + 1 || yPoxFrom == yPoxTo - 1) && board(xPoxTo)(yPoxTo) == '.') return true
    false
  }

  // recheck neccesary
  private def recheckNecessaryForBlack(): Unit = {
    if (necessaryXPOS_forBlack != 10 || necessaryYPOS_forBlack != 10) if ((board(necessaryXPOS_forBlack + 1)(necessaryYPOS_forBlack + 1) == 'w' && board(necessaryXPOS_forBlack + 2)(necessaryYPOS_forBlack + 2) == '.') || (board(necessaryXPOS_forBlack + 1)(necessaryYPOS_forBlack - 1) == 'w' && board(necessaryXPOS_forBlack + 2)(necessaryYPOS_forBlack - 2) == '.')) necessaryBlack = true
    else necessaryBlack = false
  }

  private def recheckNecessaryForWhite(): Unit = {
    if (necessaryXPOS_forWhite != 10 || necessaryYPOS_forWhite != 10) if ((board(necessaryXPOS_forWhite - 1)(necessaryYPOS_forWhite - 1) == 'b' && board(necessaryXPOS_forWhite - 2)(necessaryYPOS_forWhite - 2) == '.') || (board(necessaryXPOS_forWhite - 1)(necessaryYPOS_forWhite + 1) == 'b' && board(necessaryXPOS_forWhite - 2)(necessaryYPOS_forWhite + 2) == '.')) necessaryWhite = true
    else necessaryWhite = false
  }

  // recheck may neccessary
  private def recheckMayNecessaryForBlack(): Unit = {
    if (mayneccesaryXPOS_forBlack != 10 || mayneccesaryYPOS_forBlack != 10) if ((board(mayneccesaryXPOS_forBlack + 1)(mayneccesaryYPOS_forBlack + 1) == 'w' && board(mayneccesaryXPOS_forBlack + 2)(mayneccesaryYPOS_forBlack + 2) == '.') || (board(mayneccesaryXPOS_forBlack + 1)(mayneccesaryYPOS_forBlack - 1) == 'w' && board(mayneccesaryXPOS_forBlack + 2)(mayneccesaryYPOS_forBlack - 2) == '.')) mayneccesaryBlack = true
    else mayneccesaryBlack = false
  }

  private def recheckMayNecessaryForWhite(): Unit = {
    if (mayneccesaryXPOS_forWhite != 10 || mayneccesaryYPOS_forWhite != 10) if ((board(mayneccesaryXPOS_forWhite - 1)(mayneccesaryYPOS_forWhite - 1) == 'b' && board(mayneccesaryXPOS_forWhite - 2)(mayneccesaryYPOS_forWhite - 2) == '.') || (board(mayneccesaryXPOS_forWhite - 1)(mayneccesaryYPOS_forWhite + 1) == 'b' && board(mayneccesaryXPOS_forWhite - 2)(mayneccesaryYPOS_forWhite + 2) == '.')) mayneccesaryWhite = true
    else mayneccesaryWhite = false
  }

  // recheck optional
  private def recheckoptionalNecessaryForBlack(): Unit = {
    if (optionalneccesaryXPOS_forBlack != 10 || optionalneccesaryYPOS_forBlack != 10) if ((board(optionalneccesaryXPOS_forBlack + 1)(optionalneccesaryYPOS_forBlack + 1) == 'w' && board(optionalneccesaryXPOS_forBlack + 2)(optionalneccesaryYPOS_forBlack + 2) == '.') || (board(optionalneccesaryXPOS_forBlack + 1)(optionalneccesaryYPOS_forBlack - 1) == 'w' && board(optionalneccesaryXPOS_forBlack + 2)(optionalneccesaryYPOS_forBlack - 2) == '.')) optionalneccesaryBlack = true
    else optionalneccesaryBlack = false
  }

  private def recheckoptionalNecessaryForWhite(): Unit = {
    if (optionalneccesaryXPOS_forWhite != 10 || optionalneccesaryYPOS_forWhite != 10) if ((board(optionalneccesaryXPOS_forWhite - 1)(optionalneccesaryYPOS_forWhite - 1) == 'b' && board(optionalneccesaryXPOS_forWhite - 2)(optionalneccesaryYPOS_forWhite - 2) == '.') || (board(optionalneccesaryXPOS_forWhite - 1)(optionalneccesaryYPOS_forWhite + 1) == 'b' && board(optionalneccesaryXPOS_forWhite - 2)(optionalneccesaryYPOS_forWhite + 2) == '.')) optionalneccesaryWhite = true
    else optionalneccesaryWhite = false
  }

  private def checkNeccesaryForBlack(curX_ofWhite: Int, curY_ofWhite: Int): Unit = {
    necessaryBlack = false
    var enter = false
    if (board(curX_ofWhite - 1)(curY_ofWhite - 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite + 1) == '.') {
      necessaryBlack = true
      necessaryXPOS_forBlack = curX_ofWhite - 1
      necessaryYPOS_forBlack = curY_ofWhite - 1
      enter = true
    }
    if (board(curX_ofWhite - 1)(curY_ofWhite + 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite - 1) == '.') {
      necessaryBlack = true
      if (enter) {
        mayneccesaryXPOS_forBlack = curX_ofWhite - 1
        mayneccesaryYPOS_forBlack = curY_ofWhite + 1
        mayneccesaryBlack = true
      }
      else {
        necessaryXPOS_forBlack = curX_ofWhite - 1
        necessaryYPOS_forBlack = curY_ofWhite + 1
        mayneccesaryBlack = false
      }
    }
  }

  private def checkNeccesaryForWhite(curX_ofBlack: Int, curY_ofBlack: Int): Unit = {
    necessaryWhite = false
    var enter = false
    if (board(curX_ofBlack + 1)(curY_ofBlack + 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack - 1) == '.') {
      necessaryWhite = true
      necessaryXPOS_forWhite = curX_ofBlack + 1
      necessaryYPOS_forWhite = curY_ofBlack + 1
      enter = true
    }
    if (board(curX_ofBlack + 1)(curY_ofBlack - 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack + 1) == '.') {
      necessaryWhite = true
      if (enter) {
        mayneccesaryXPOS_forWhite = curX_ofBlack + 1
        mayneccesaryYPOS_forWhite = curY_ofBlack - 1
        mayneccesaryWhite = true
      }
      else {
        necessaryXPOS_forWhite = curX_ofBlack + 1
        necessaryYPOS_forWhite = curY_ofBlack - 1
        mayneccesaryWhite = false
      }
    }
  }

  //  Defender leaves
  private def optionalcheckNeccesaryForBlack(curX_ofWhite: Int, curY_ofWhite: Int): Unit = {
    if (board(curX_ofWhite - 1)(curY_ofWhite - 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite + 1) == '.') {
      optionalneccesaryBlack = true
      optionalneccesaryXPOS_forBlack = curX_ofWhite - 1
      optionalneccesaryYPOS_forBlack = curY_ofWhite - 1
    }
    else if (board(curX_ofWhite - 1)(curY_ofWhite + 1) == 'b' && board(curX_ofWhite + 1)(curY_ofWhite - 1) == '.') {
      optionalneccesaryBlack = true
      optionalneccesaryXPOS_forBlack = curX_ofWhite - 1
      optionalneccesaryYPOS_forBlack = curY_ofWhite + 1
    }
    else optionalneccesaryBlack = false
  }

  private def optionalcheckNeccesaryForWhite(curX_ofBlack: Int, curY_ofBlack: Int): Unit = {
    if (board(curX_ofBlack + 1)(curY_ofBlack + 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack - 1) == '.') {
      optionalneccesaryWhite = true
      optionalneccesaryXPOS_forWhite = curX_ofBlack + 1
      optionalneccesaryYPOS_forWhite = curY_ofBlack + 1
    }
    else if (board(curX_ofBlack + 1)(curY_ofBlack - 1) == 'w' && board(curX_ofBlack - 1)(curY_ofBlack + 1) == '.') {
      optionalneccesaryWhite = true
      optionalneccesaryXPOS_forWhite = curX_ofBlack + 1
      optionalneccesaryYPOS_forWhite = curY_ofBlack - 1
    }
    else optionalneccesaryWhite = false
  }

  def validateWhiteKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo + 2 && (board(xPoxFrom - 1)(yPoxFrom + 1) == 'b')) {
      board(xPoxFrom - 1)(yPoxFrom + 1) = '-'
      val x = board(xPoxFrom)(yPoxFrom)
      board(xPoxFrom)(yPoxFrom) = '-'
      board(xPoxTo)(yPoxTo) = 'x'
      return true
    }
    else if (yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo + 2 && (board(xPoxFrom - 1)(yPoxFrom - 1) == 'b')) {
      board(xPoxFrom - 1)(yPoxFrom - 1) = '-'
      val x = board(xPoxFrom)(yPoxFrom)
      board(xPoxFrom)(yPoxFrom) = '-'
      board(xPoxTo)(yPoxTo) = 'x'
      return true
    }
    false
  }

  def validateBlackKillMove(xPoxFrom: Int, yPoxFrom: Int, xPoxTo: Int, yPoxTo: Int): Boolean = {
    if (yPoxFrom == yPoxTo - 2 && xPoxFrom == xPoxTo - 2 && (board(xPoxFrom + 1)(yPoxFrom + 1) == 'w')) {
      board(xPoxFrom + 1)(yPoxFrom + 1) = '.'
      val x = board(xPoxFrom)(yPoxFrom)
      board(xPoxFrom)(yPoxFrom) = '.'
      board(xPoxTo)(yPoxTo) = 'x'
      return true
    }
    else if (yPoxFrom == yPoxTo + 2 && xPoxFrom == xPoxTo - 2 && (board(xPoxFrom + 1)(yPoxFrom - 1) == 'w')) {
      board(xPoxFrom + 1)(yPoxFrom - 1) = '.'
      val x = board(xPoxFrom)(yPoxFrom)
      board(xPoxFrom)(yPoxFrom) = '.'
      board(xPoxTo)(yPoxTo) = 'x'
      return true
    }
    false
  }

  private def checkRole(r: Int) = r == role

  def converter(x: Char): Int = x match {
    case 'h' => 7
    case 'g' => 6
    case 'f' => 5
    case 'e' => 4
    case 'd' => 3
    case 'c' => 2
    case 'b' => 1
    case 'a' => 0
    case '1' => 7
    case '2' => 6
    case '3' => 5
    case '4' => 4
    case '5' => 3
    case '6' => 2
    case '7' => 1
    case '8' => 0
    case _ => 10
  }
}