
//1

class Shape
class Rectangle extends Shape

var rectangles: Array[Rectangle] =  Array[Rectangle](new Rectangle)
//var shapes: Array[Shape] = rectangles
//var shapes: Array[Shape] = Array[Shape](new Shape)
//var rectangles2: Array[Rectangle] = shapes
//error because arrays are invariant,  Shape and Rectangle are "independent"

/*

invariant
  if (A<:B) then A and B are independent

covariant
  if (A<:B) then List[A] <: List[B]

contravariant
 if (A<:B) then Stack[B] <: Stack[A]

*/


//2

def removeDuplicatesFunc[A](list: List[A]):List[A] = list match {
  case Nil => Nil
  case x::List() => List(x)
  case x::tail if (x == tail.head) => removeDuplicatesFunc(tail)
  case x::tail => x::removeDuplicatesFunc(tail)

}

removeDuplicatesFunc(List())
removeDuplicatesFunc(List(1,1,2,3,4,5,6,6,6,6,7))
removeDuplicatesFunc(List(1))
removeDuplicatesFunc(List(2,2,2,2,2,2))

def removeDuplicatesImp [A : Manifest](array:Array[A]):Array[A] = {
  if(array == null) null
  else if(!array.isEmpty && array.length!=0){
    //count of duplicates
    var counter =0;
    var found = false;

    for(i<-0 until array.length){
      found = false;
      for(j<-i+1 until array.length){
        if(array(i)==array(j))
          found=true
      }
      if(found)
        counter+=1
    }
    val newArr = new Array[A](array.length - counter)
    counter = 0;

    for(i<-0 until array.length){
      found = false;
      for(j<-i+1 until array.length){
        if(array(i)==array(j))
          found = true
      }
      if(!found){
        newArr(counter) = array(i)
        counter+=1;
      }
    }

    newArr
  }
  else
  null
}
removeDuplicatesImp(Array(1,2,3,4,5))
removeDuplicatesImp(Array(2,1,1,2,3,4,5))
removeDuplicatesImp(Array(1,2,3,3,3,4,5,3,6,7,8,8,8,9,9,9,9,9))
removeDuplicatesImp(Array(null))
removeDuplicatesImp(Array[Int]())

removeDuplicatesImp(null)