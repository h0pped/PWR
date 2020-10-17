object App {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    var l1 = List[Int](3,5,6,7,4,4,2,7,7);
    var l2 = List[Int]();
    var l3 = List[Int](0);

    println("Sum of odd list elements(3,5,6,7,4,4,2,7,7): " + sumList(l1,l1.length));
    println("Sum of odd list elements (): " + sumList(l2,l2.length));
    println("Sum of odd list elements (0): " + sumList(l3,l3.length));

    var listOfStringsTest = List[String]("Hello", "World","Lorem Ipsum", "test1");
    var listOfStringsTest2 = List[String]("I", "Am","Test", "String");

    println("ConnectStrings Result ( '.' separator): "+connectStrings(listOfStringsTest,"."))
    println("ConnectStrings Result ( '-' separator): "+connectStrings(listOfStringsTest2,"-"))

    var arr = Array(3,5,6,7,4,4,2,7,7)
    print("\n\nArray: ")
    for(i<-0 to arr.length-1){
      print(arr(i)+",")
    }
    println()
    println("Occurrences of 2 in array: "+arrayOccurrencesNum(arr,2))
    println("Occurrences of 7 in array: "+arrayOccurrencesNum(arr,7))
    println("Occurrences of 9 in array: "+arrayOccurrencesNum(arr,9))

    for(i<-0 to 10){
      print(Fibonacci(i)+" ");
    }
  }

  //1
  def sumList(list:List[Int],N:Int):Int = {
    if(N <=0) {
      0
    } else {
      if (list(N - 1) % 2 == 0) {
        sumList(list, N - 1);
      }
      else {
        sumList(list, N - 1) + list(N - 1);
      }
    }
  }
  //2
  def connectStrings(listOfStrings:List[String],separator:String):String={
    var result:String = ""
    for(i<-0 to listOfStrings.length-1){
      result+=listOfStrings(i)+separator
    }
    result.substring(0,result.length-1) //to remove last separator
  }
  //3
  def arrayOccurrencesNum(arr:Array[Int], el:Int):Int={
    var count:Int = 0
    for(i<-0 to arr.length-1){
      if(arr(i) == el){
        count+=1
      }
    }
    count
  }

  //4 fibonacci
  def Fibonacci(n: Long): Long = n match {
    case 0 | 1 => n
    case _ => Fibonacci(n - 1) + Fibonacci(n - 2)
  }
}
