
import scala.collection.mutable.Map;

def wordCounter(text: String): Map[String, Int] = {

  val map = Map.empty[String,Int].withDefaultValue(0);

  text.split(" ").foreach(x=>map(x)+=1)
  map;
}

println(wordCounter("This is my test string to test function called wordCounter, testing the test :)"))