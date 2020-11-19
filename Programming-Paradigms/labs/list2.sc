
def squeeze[A,B](list:List[A],f: (A,A)=>B):List[B]={
  def tailRec(list:List[A],acc:List[B]): List[B] = list match {
    case x::y::tail=>tailRec(y::tail,acc:+f(x,y))
    case _ => acc
  }
  tailRec(list,List());
}


squeeze(List(1, 2, 3, 4, 5), (a: Int, b:Int) => 0.5 * (a + b))
squeeze(List(), (a: Int, b:Int) => 0.5 * (a + b))
squeeze(List("Hello","World"), (a: String, b:String)=> a+" "+b)



def flatten[A](list: List[List[A]]):List[A] =  list match{
    case Nil=> List[A]()
    case x::tail=>x++flatten(tail)
}

flatten(Nil)
flatten(List(List(1, 2), List(3, 4)))
flatten(List(List("Hello", 2,Nil), List("not hello", 4)))
flatten(List(List(List(1, 2)), List(List(3, 4))))