import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.StdIn



//1
def sumList(list:List[Int]):Int = {
  @tailrec
  def sumListInner(xs:List[Int],sum:Int):Int={
    xs match {
      case x::tail=>sumListInner(tail,sum+x)
      case Nil=>sum
    }
  }
  sumListInner(list,0)
}

//2
def reverseListTail[A](list:List[A]) = {
  @tailrec
  def rvReg(result:List[A],list:List[A]): List[A] ={
    list match{
      case Nil=>result
      case x::xs=>rvReg(x::result,xs)
    }
  }
  rvReg(Nil,list);
}
def reverseListReg[A](list:List[A]):List[A]= list match {
  case Nil =>list
  case x::tail=>reverseListReg(tail):::List(x)
}

//3
def MergeLists(list1:List[Int],list2:List[Int]):List[Int]={
  var result = new ListBuffer[Int]
  var result1 = List()
  val loopLength = if(list1.length<list2.length) list2.length-1 else list1.length-1
  for(i<-0 to loopLength) {
    if(i<list1.length) result.append(list1(i))
    if(i<list2.length) result.append(list2(i))
  }
  result.toList
}

//4
def getFibonacci(index: Int): Int = {
  @tailrec
  def getTailRec(index: Int, prev: Int, current: Int): Int = {
    if (index <=  0) {
      current
    } else {
      getTailRec(index - 1, prev + current, prev)
    }
  }
  getTailRec(index, prev = 1, current = 0)
}

//5
def splitLists(list:List[Int]):(List[Int],List[Int])={

  @tailrec
  def SplitRec(list:List[Int],odd:List[Int],even:List[Int]):(List[Int],List[Int])= list match {
    case Nil =>(even,odd)
    case x::tail=>{ if(x%2==0) SplitRec(tail,odd, even:::List(x)) else SplitRec(tail,odd:::List(x),even)}
    case x => (even,odd)
  }

  SplitRec(list, List(),List())
}

//6
@tailrec
def isSorted(list:List[Int],N:Int):Boolean={
  if(N==1||N==0){
    true
  }
  else{
    if(list(N-1)<list(N-2)) false
    else isSorted(list,N-1)
  }
}

//7
def replaceNth[A](xs:List[A],n:Int,elem:A):List[A]= xs match {
  case Nil=>println("Index out of bound")
    xs
  case x::tail=>if(n==0) elem::tail
  else x::replaceNth(tail,n-1,elem)

}

//8
def converter(atm:Int)(units:String):Double = units match {
  case "psi" => atm*14.696
  case "torr" => atm*760
  case "pa" => atm*101325
  case "bar" => atm*1.013
  case _ => 0
}

println("Hello world")

println(sumList(List.range(1,8)))

var l1 = List(1, 2, 10, 20, 6, 7);
print("List: ");
for (i <- 0 to l1.length - 1) {
  print(l1(i) + " ")
}
println();
println("Sum of elements: " + sumList(l1))

l1 = reverseListTail(l1);


print("Reversed List: ");
for (i <- 0 to l1.length - 1) {
  print(l1(i) + " ")
}

var l2 = List[Int](1, 2, 3, 4, 5)
var l3 = List[Int](6, 7, 8, 9, 10, 11, 12, 14)
var result = MergeLists(l2, l3)
print("\nMerged lists: ")
for (i <- 0 to result.length - 1) {
  print(result(i) + " ")
}


var fibIndex = 5
println(fibIndex + "'th number of Fibonacci: " + getFibonacci(fibIndex));


var list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
var lists = splitLists(list)
print("\nList of numbers: ");
for (i <- 0 to list.length - 1) {
  print(result(i) + " ")
}
print("\nEven numbers: ");
for (i <- 0 to lists._1.length - 1) {
  print(lists._1(i) + " ")
}
print("\nOdd numbers: ");
for (i <- 0 to lists._2.length - 1) {
  print(lists._2(i) + " ")
}

var sortedList = List(1,2,3,4,5,6)
var unsortedList = List(4,5,2,1,5,7)

println("\n\nIs "+sortedList+" sorted: "+isSorted(sortedList,sortedList.length));
println("Is "+unsortedList+" sorted: "+isSorted(unsortedList,unsortedList.length));

var replaceList = List(1,2,3,4,5)

replaceList = replaceNth(replaceList,3,10)
replaceList = replaceNth(replaceList,0,10)
replaceList = replaceNth(replaceList,4,10)



println("Replace list: "+replaceList)

println("\n\nCurried: ");
var conv = converter(1)_

println(conv("psi"))
println(conv("torr"))
println(conv("pa"))
println(conv("bar"))