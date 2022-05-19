/*class MyLinkedList {
  var size = 0
  private var head: Node = null
  private var tail: Node = null
  case class Node(value: Piece2, var next: Node = null) {
    size = size + 1
    override def toString: String = s"(${value.t})->$next"
  }
  def addFirst(v: Piece2) = {    head = new Node(v, head)
    if (tail == null) tail = head
  }
  def addLast(v: Piece2) = {
    if (tail == null) addFirst(v)
    else tail.next = new Node(v)
  }
  def removeFirst(): Unit ={
    head=head.next
    size=size-1;
  }
  def removeLast(): Unit ={
    var temp=head;
    while (temp.next!=null && temp.next.next!=null){
      temp=temp.next;
    }
    if (temp.next==null){
      tail=null;
      head=null;
    }
    tail=temp;
    tail.next=null;
    size=size-1;

  }
  def getHead(): Piece2 ={
    return head.value;
  }
  override def toString: String = "" + head
}*/
