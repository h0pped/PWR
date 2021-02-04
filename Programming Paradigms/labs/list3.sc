case class Section(startPage:Int,lastPage:Int)
case class Chapter(sections:List[Section])
case class Book(chapters:List[Chapter])

type SectionLength = Int
type ChapterSectionsLengths = List[SectionLength]
type BookDescription = List[ChapterSectionsLengths]

def makeASection(startPage:Int,length:SectionLength): Section={
    Section(startPage,startPage+length-1)
}
def makeAChapter(startPage: Int,sectionLengths: ChapterSectionsLengths): Chapter={
  def tailRec(currentPage:Int, sectionLengths: ChapterSectionsLengths,acc:Chapter): Chapter = sectionLengths match {
    case Nil=>acc
    case x::tail=> tailRec(currentPage+x,tail,Chapter(acc.sections:+makeASection(currentPage,x)))
  }
  tailRec(startPage,sectionLengths,Chapter(List()))
}

def makeChapters(startPage:Int,bookDescription: BookDescription):List[Chapter]={
  def tailRec(startPage:Int,bookDescription: BookDescription,acc:List[Chapter]):List[Chapter]= bookDescription match {
    case Nil=>acc
    case x::tail=> {
      //if first chapter
      if(!acc.isEmpty) tailRec(startPage,tail,acc:+makeAChapter(acc.last.sections.last.lastPage+1,x))
      else tailRec(startPage,tail,acc:+makeAChapter(startPage,x))
    }
  }
  tailRec(startPage,bookDescription,List[Chapter]())

}
def makeABook(bookDescription: BookDescription):Book={
  Book(makeChapters(1,bookDescription))
}

var chapter1SectionLengths: ChapterSectionsLengths = List(1,2,5)
var chapter2SectionLengths: ChapterSectionsLengths = List(7,7,9)
var chapter3SectionLengths: ChapterSectionsLengths = List(2,4,4)

var description: BookDescription = List(chapter1SectionLengths,chapter2SectionLengths,chapter3SectionLengths)



makeABook(description)