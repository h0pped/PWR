
def unzip[A,B](list:LazyList[(A,B)]):(LazyList[A],LazyList[B])=list match{
  case LazyList() => (LazyList(),LazyList())
  case (x,y)#::tail=>{
    lazy val unzipres:(LazyList[A],LazyList[B]) =  unzip(tail);
    (x#::unzipres._1, y#::unzipres._2)
  }
}


val test1 = LazyList((1,"one"),(2,"two"),(3,"three"))
val test2 = LazyList()

unzip(test1)._1.take(10).toList
unzip(test1)._2.take(10).toList

unzip(test2)._1.take(10).toList
unzip(test2)._2.take(10).toList

sealed trait LList[+T]
case object LNil extends LList[Nothing]
case class LCons[T](head: T, tail: () => LList[T]) extends LList[T]


def takeL[T](llist: LList[T], n: Int): List[T] = (llist, n) match {
  case (LNil, _) => Nil
  case (_, k) if k <= 0 => Nil
  case (LCons(h, t), _) => h :: takeL(t(), n - 1)
}

def filterL[T](list:LList[T], f:(T)=>Boolean):LList[T]= list match{
  case LNil => LNil
  case LCons(h,t)=> if(f(h)){
    LCons(h,()=>filterL(t(),f))
  }
  else{
    filterL(t(),f)
  }
}

val integers: LList[Int] = {
  def next(n: Int): LList[Int] = LCons(n, () => next(n + 1))
  next(0)
}
takeL(integers,20);

takeL(filterL(integers,(x:Int)=>x>=0),10)
takeL(filterL(integers,(x:Int)=>x>=0),0)

takeL(filterL(integers,(x:Int)=>x%2==0),10)
takeL(filterL(integers,(x:Int)=>x%2==1),10)






