println("hello")

case class Entry[K,V](key:K,value:V){
  def getKey():K={key}
  def getValue():V={value}
}

case class Map[K,V] (entries: List[Entry[K,V]])


def add[K,V](key: K, value: V,map: Map[K,V]):Map[K,V] = {
    def addRec(key:K,value:V,list:List[Entry[K,V]]):List[Entry[K,V]] = list match {
      case Nil=>List(Entry(key,value))
      case x::tail => if(x.getKey()==key) List(Entry(key,value)):::tail else x::addRec(key,value,tail)
    }
  Map(addRec(key,value,map.entries))
}

def getByKey[K,V](key:K,map:Map[K,V]):Option[V] ={
  map.entries.find((x)=>x.key==key).map(_.value)
}

def remove[K,V](key:K,map:Map[K,V]):(Map[K,V],Option[V])= {
  def removeRec(key: K, list: List[Entry[K, V]]): (List[Entry[K, V]], Option[V]) = list match {
    case Nil => (List(), None)
    case x :: tail => if (x.getKey() == key) (tail, Option(x.value))
    else{ val func = removeRec(key, tail); (x::func._1,func._2)}
  }

  val list = removeRec(key, map.entries)
  (Map(list._1),list._2)
}

def printMap[K,V](map:Map[K,V]):Unit={
  def printList[K,V](list:List[Entry[K,V]]):Unit = list match {
    case Nil=>
    case x::tail=> {println(x.getKey()+" - "+x.getValue()); printList(tail)}
  }

  printList(map.entries)
}

var map = Map[Int,String](List())
printMap(map)

map = add(1,"Kent",map)
map = add(2,"Ment",map)
map = add(-5,"SomeValue",map)
printMap(map)

var res = remove(2,map)

println("Delete key[2]: "+res._2)
map = res._1

res = remove(800,map)

println("Delete key[800]: "+res._2)
map = res._1




//2

case class Set[A](values:List[A])

def isDuplicate[A](value:A,set:Set[A]): Boolean = {
  def isDuplicateList[A](value:A,list:List[A]):Boolean = list match {
    case Nil=>false
    case x::tail=> if(x == value) true; else isDuplicateList(value,tail)
  }
  isDuplicateList(value,set.values)
}

def setAdd[A](value:A,set:Set[A]):Set[A]={
  if(isDuplicate(value, set)==true) set
  else{
    def listAdd[A](value:A,list:List[A]): List[A]= list match {
      case Nil=> List(value)
      case x::tail=>  x::listAdd(value, tail)
    }
    Set(listAdd(value,set.values))
  }
}
def setRemove[A](value:A,set:Set[A]):(Set[A],Option[A])={
  def listRemove[A](value:A,list:List[A]): (List[A],Option[A]) = list match {
    case Nil=> (List(),None)
    case x::tail => if(x==value)(tail,Option(value))
                    else{ val func = listRemove(value,tail); (x::func._1,func._2)}
  }
  var ListAndValue = listRemove(value,set.values)
  (Set(ListAndValue._1),ListAndValue._2)
}
def setGet[A](index:Int,set:Set[A]):Option[A]={
  def listGet[A](index:Int,list:List[A],acc:Int):Option[A] = list match {
    case Nil=> None
    case x::tail=> if(acc == index) Option(x) else listGet(index, tail, acc+1)
  }
  listGet(index,set.values,0)
}
def setContains[A](value:A,set:Set[A]):Boolean={
  def listContains[A](value:A,list:List[A]):Boolean= list match {
    case Nil=>false
    case x:: tail=> if(x==value)true else listContains(value,tail)
  }
  listContains(value,set.values)
}

def setPrint[A](set:Set[A]):Unit={
  def listPrint[A](list:List[A]):Unit= list match {
    case Nil=>
    case x:: tail=> { print(x+ ", "); listPrint(tail)}

  }
  print("\n{ ")
  listPrint(set.values)
  print("}");
}

var set = Set[Int](List())

set = setAdd(1,set);
set = setAdd(2,set);
set = setAdd(3,set);
set = setAdd(4,set);
set = setAdd(5,set);
set = setAdd(6,set);
set = setAdd(7,set);
set = setAdd(3,set);
set = setAdd(1,set);
set = setRemove(2,set)._1
set = setRemove(-1,set)._1

setPrint(set)

setGet(4,set)
setContains(0,set)
setContains(5,set)

