object HelloWorld {
  def main(args: Array[String]): Unit = {
    if (args.length > 0)
      println("Hello, world, " + args(1)+"!");
    else
      println("Hello, world!");

    var arr = Array[Double](5,4,2,1,5);
    println("Sum of array: "+arraySum(arr));
    println("Sum of array(recursive way): "+arraySum(arr));


  }

  def arraySum(arr:Array[Double]):(Double) = {
   var sum:Double = 0;
    for(i<-0 to arr.length-1){
      sum+=arr(i);
    }
    sum
  }
  def arraySumRecursive(arr:Array[Double],n:Int):(Double)={
  if(n<=0){
    return 0;
  }
    else{
    arraySumRecursive(arr,n-1)+arr(n-1);
  }
  }
}