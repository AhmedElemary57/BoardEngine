object XOInput extends Input{

  override var input: String = _

  override def getInput(): String = input

  override def setInput(input: String): Boolean = {
    val validInput = List(1,2,3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number$letter"))
    val res = validInput.contains(input)
    if(res) this.input = input
    res
  }

  def main(args: Array[String]): Unit = {
    println(setInput("1b"))
    println(getInput())
  }
}
