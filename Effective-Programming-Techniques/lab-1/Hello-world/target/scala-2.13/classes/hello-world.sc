import scala.math;


def difference2D(v1: (Double, Double), v2: (Double, Double)): (Double, Double) = (v1._1 - v2._1, v1._2 - v2._2)

def dotProduct(v1:(Double,Double),v2:(Double,Double)): (Double) =  v1._1 * v2._1 + v1._2 * v2._2

def distanceVectors(v1:(Double,Double),v2:(Double,Double)):(Double) = {
  var dif = difference2D(v1,v2)
  math.sqrt(dotProduct(dif,dif))
}

var v1 = (0.0,0.0)
var v2 = (5.0,3.0)
var v3 = (7.0,7.0)

difference2D(v1,v2)
difference2D(v1,v3)
difference2D(v2,v3)

dotProduct(v1,v2)
dotProduct(v1,v3)
dotProduct(v2,v3)


distanceVectors(v1,v2)
distanceVectors(v2,v1)
distanceVectors(v2,v3)


def countFiredBits(num:Int): Int ={
  if(num==0) 0
  else  countFiredBits(num >>1)+num&1;
}

countFiredBits(0)
countFiredBits(1)
countFiredBits(2)
countFiredBits(3)
countFiredBits(13)


