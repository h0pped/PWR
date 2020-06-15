import javax.naming.PartialResultException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface IList<E> extends Iterable<E> {
    boolean add(E e); // add element to the list on proper position

    void add(int index, E element) throws NoSuchElementException; // not implemented

    void clear(); // delete all elements

    boolean contains(E element); // is list containing an element (equals())

    E get(int index) throws NoSuchElementException; //get element from position

    E set(int index, E element) throws NoSuchElementException; // not implemented

    int indexOf(E element); // where is element (equals())

    boolean isEmpty();

    Iterator<E> iterator();

    ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator

    E remove(int index) throws NoSuchElementException; // remove element from position index

    boolean remove(E e); // remove element

    int size();
}

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {

    private class Element {
        public Element(E e) {
            object = e;
        }

        public Element(E e, Element next, Element prev) {
            object = e;
            this.next = next;
            this.prev = prev;
        }

        public E getValue() {
            return object;
        }

        public void setNext(Element elem) {
            next = elem;

        }

        // add element e after this
        public void addAfter(Element elem) {
            // E actE = object;

        }

        public Element getNext() {
            return next;
        }

        public Element getPrevious() {
            return prev;
        }

        // assert it is NOT a sentinel
        public void remove() {
            if (object != sentinel) {
                prev.next = next;
            }
        }

        E object;
        Element next = null;
        Element prev = null;
    }


    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E> {
        Element actElem;

        //TODO
        public InnerIterator() {
            actElem = sentinel;
        }

        @Override
        public boolean hasNext() {
           return  actElem.next != sentinel;
        }

        @Override
        public E next() {
            E value = actElem.getNext().getValue();
            actElem = actElem.getNext();
            return value;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        Element actElem;

        public InnerListIterator() {
            actElem = sentinel;
        }

        @Override
        public boolean hasNext() {
            return actElem.next != sentinel;
        }

        @Override
        public E next() {
            E value = actElem.getValue();
            actElem = actElem.getNext();
            return value;
        }

        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            return actElem.prev != sentinel;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            E e = actElem.prev.getValue();
            actElem = actElem.getPrevious();
            return e;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.object = null;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            Element newElem = new Element(e);
            newElem.setNext(sentinel);
            newElem.prev = sentinel;
            sentinel.setNext(newElem);
            sentinel.prev = newElem;
            return true;
        } else {
            Element actelem = sentinel;
            while (actelem.getNext() != sentinel) {
                if (actelem.getNext().getValue() instanceof Link) {
                    if (((Link) actelem.getNext().getValue()).compareTo((Link) e) > 0) {
                        break;
                    }
                }
                actelem = actelem.getNext();
            }
            Element newElem = new Element(e);
            newElem.prev = actelem;
            newElem.setNext(actelem.getNext());
            actelem.setNext(newElem);
            newElem.getNext().prev = newElem;
            return true;
        }
    }

    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element actElem = sentinel;
        while (index > 0 && actElem.getNext() != sentinel) {
            index--;
            actElem = actElem.getNext();
        }
        if (actElem == sentinel)
            throw new IndexOutOfBoundsException();
        return actElem;
    }

    private Element getElement(E obj) {
        Element actElem = sentinel;
        while (actElem.getNext() != sentinel) {
            if (actElem.getValue().equals(obj)) {
                return actElem;
            } else {
                actElem = actElem.getNext();
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public boolean contains(E element) {
        Element actElem = sentinel;
        while (actElem.getNext() != sentinel) {
            if (actElem.getNext().object.equals(element)) {
                return true;
            } else {
                actElem = actElem.getNext();
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        return getElement(index+1).getValue();
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        int pos = 0;
        Element actElem = sentinel;
        while (actElem.getNext() != sentinel) {
            if (actElem.getValue().equals(element))
                return pos;
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (sentinel.next == sentinel);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || sentinel.getNext() == sentinel) throw new IndexOutOfBoundsException();
        if (index == 0) {
            E retValue = sentinel.getNext().getValue();
            sentinel.next = sentinel.getNext().getNext();
            return retValue;
        }
        if (index == size() - 1) {
            Element tail = this.getElement(size() - 1);
            E retValue = tail.getValue();
            tail = tail.prev;
            tail.next = sentinel;
            return retValue;
        }
        Element actElem = getElement(index);
        if (actElem.getNext() == null)
            throw new IndexOutOfBoundsException();
        E retValue = actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        actElem.getNext().prev = actElem;
        return retValue;
    }

    @Override
    public boolean remove(E e) {
        if (sentinel.next == sentinel)
            return false;
        if (sentinel.next.getValue().equals(e)) {
            sentinel.next = sentinel.next.getNext();
            sentinel.next.prev = sentinel;
            return true;
        }
        Element actElem = sentinel.next;
        while (actElem.getNext() != sentinel && !actElem.getNext().getValue().equals(e))
            actElem = actElem.getNext();

        if (actElem.getNext() == sentinel)
            return false;
        if (actElem.getNext() == getElement(size() - 1)) {
            Element tail = getElement(size() - 1);
            tail = actElem;
            tail.next = null;
            return true;
        }
        actElem.setNext(actElem.getNext().getNext());
        actElem.getNext().prev = actElem;
        return true;
    }

    @Override
    public int size() {
        if (sentinel.next == sentinel) {
            return 0;
        }
        Element actElem = sentinel.next;
        int size = 0;
        while (actElem != sentinel) {
            size++;
            actElem = actElem.getNext();
        }
        return size;
    }

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if (other != this) {
            for (E e : other) {
                this.add(e);
            }
            other.clear();
        }
    }

    public void removeAll(E e) {
        while (this.contains(e)) {
            remove(e);
        }
    }

}


class Document {
    TwoWayCycledOrderedListWithSentinel<Link> link = new TwoWayCycledOrderedListWithSentinel<Link>();
    String name;

    public Document(String name, Scanner scan) {
        this.name = name;
        loadDocument(scan);
    }

    public void loadDocument(Scanner scan) {
        Pattern pat = Pattern.compile("[L|l][I|i][N|n][K|k]=([a-z-A-Z][a-zA-Z_]*\\b)((\\(([^\\(]*)\\))|(?!\\())");
        Pattern eodPat = Pattern.compile("(.*)[E|e][O|o][D|d]");
        boolean end = true;
        while (end == true) {
            String actStr = scan.nextLine();
            Matcher checkEOD = eodPat.matcher(actStr);
            if (checkEOD.find()) {
                end = false;
                Matcher matcher = pat.matcher(checkEOD.group(0));
                while (matcher.find()) {
                    try {
                        link.add(new Link(matcher.group(1).toLowerCase(), matcher.group(4)));
                    } catch (NumberFormatException e) {
                    }
                }
            } else {
                Matcher matcher = pat.matcher(actStr);

                while (matcher.find()) {
                    try {
                        link.add(new Link(matcher.group(1).toLowerCase(), matcher.group(4)));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
    }

    public String toString() {
        String buffer = "Document: " + name;
        if (!link.isEmpty()) {
            buffer += "\n";
            Iterator iter = link.iterator();
            while (iter.hasNext()) {
                for (int i = 0; i < 10; i++) {
                    if (iter.hasNext()) {
                        buffer += iter.next();
                        if (i != 9 && iter.hasNext()) {
                            buffer += " ";
                        }
                    }
                }
                if (iter.hasNext()) {
                    buffer += "\n";
                }
            }
        }
        return buffer;
    }

    public String toStringReverse() {
        String buffer = "Document: " + name;
        if (!link.isEmpty()) {
            buffer += "\n";
            ListIterator iter = link.listIterator();
            while (iter.hasPrevious()) {
                for (int i = 0; i < 10; i++) {
                    if (iter.hasPrevious()) {
                        buffer += iter.previous();
                        if (i != 9 && iter.hasPrevious()) {
                            buffer += " ";
                        }
                    }
                }
                if (iter.hasPrevious()) {
                    buffer += "\n";
                }
            }
        }
        return buffer;
    }

    public boolean addLink(Link added) {
        return link.add(added);
    }

}

class Link implements Comparable {
    public String ref;
    public int weight;

    public Link(String ref) {
        this.ref = ref;
        this.weight = 1;
    }

    public Link(String ref, String weight) {
        this.ref = ref;
        if (weight == null) {
            this.weight = 1;
        } else if (Integer.parseInt(weight) < 0) {
            throw new NumberFormatException();
        } else {
            this.weight = Integer.parseInt(weight);
        }


    }

    @Override
    public boolean equals(Object obj) {
        Link l = (Link) obj;
        return ref.compareTo(l.ref) == 0;
    }

    @Override
    public int compareTo(Object obj) {
        Link l = (Link) obj;
        return this.ref.toLowerCase().compareTo(l.ref.toLowerCase());
    }

    public String toString() {
        return ref + "(" + weight + ")";
    }
}

public class Main {


    static Scanner scan; // for input stream


    public static void main(String[] args) {
        System.out.println("START");
        scan = new Scanner(System.in);
        Document[] doc = null;
        int currentDocNo = 0;
        int maxNo = -1;
        boolean halt = false;
        while (!halt) {
            try {

                String line = scan.nextLine();
                // empty line and comment line - read next line
                if (line.length() == 0 || line.charAt(0) == '#')
                    continue;
                // copy line to output (it is easier to find a place of a mistake)
                System.out.println("!" + line);
                String word[] = line.split(" ");
                // go n - start with array of the length n
                if (word[0].equalsIgnoreCase("go") && word.length == 2) {
                    maxNo = Integer.parseInt(word[1]);
                    doc = new Document[maxNo];
                    continue;
                }
                //ch - change index
                if (word[0].equalsIgnoreCase("ch") && word.length == 2) {
                    currentDocNo = Integer.parseInt(word[1]);
                    continue;
                }

                // ld documentName
                if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                    Pattern pat = Pattern.compile("^([a-z-A-Z][a-zA-Z_0-9]*\\b)$");
                    Matcher matcher = pat.matcher(word[1]);
                    if (matcher.find()) {
                        doc[currentDocNo] = new Document(word[1].toLowerCase(), scan);
                    } else
                        System.out.println("incorrect ID");
                    continue;
                }
                // ha
                if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                    halt = true;
                    continue;
                }
                // clear
                if (word[0].equalsIgnoreCase("clear") && word.length == 1) {
                    doc[currentDocNo].link.clear();
                    continue;
                }
                // show
                if (word[0].equalsIgnoreCase("show") && word.length == 1) {
                    System.out.println(doc[currentDocNo].toString());
                    continue;
                }
                // reverse
                if (word[0].equalsIgnoreCase("reverse") && word.length == 1) {
                    System.out.println(doc[currentDocNo].toStringReverse());
                    continue;
                }
                // size
                if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                    System.out.println(doc[currentDocNo].link.size());
                    continue;
                }
                // add str
                if (word[0].equalsIgnoreCase("add") && word.length == 2) {
                    Pattern pat = Pattern.compile("([a-z-A-Z][a-zA-Z_]*\\b)((\\(([^\\(]*)\\))|(?!\\())");
                    Matcher matcher = pat.matcher(word[1]);
                    if (matcher.find()) {
                        if (matcher.groupCount() == 4) {
                            Link link = new Link(matcher.group(1), matcher.group(4));
                            System.out.println(doc[currentDocNo].link.add(link));

                        } else {
                            Link link = new Link(matcher.group(1), matcher.group(4));
                            System.out.println(doc[currentDocNo].link.add(link));

                        }
                    } else {

                        System.out.println("error");
                    }

                    continue;
                }

                // get index
                if (word[0].equalsIgnoreCase("get") && word.length == 2) {
                    int index = Integer.parseInt(word[1]);
                    try {
                        Link l = doc[currentDocNo].link.get(index);
                        System.out.println(l.ref);
                    } catch (NoSuchElementException e) {
                        System.out.println("error");
                    }
                    continue;
                }

                // index str
                if (word[0].equalsIgnoreCase("index") && word.length == 2) {
                    int index = doc[currentDocNo].link.indexOf(new Link(word[1]));
                    System.out.println(index);
                    continue;
                }
                // remi index
                if (word[0].equalsIgnoreCase("remi") && word.length == 2) {
                    int index = Integer.parseInt(word[1]);
                    try {
                        Link l = doc[currentDocNo].link.remove(index);
                        System.out.println(l);
                    } catch (NoSuchElementException e) {
                        System.out.println("error");
                    }
                    continue;
                }
                // rem str
                if (word[0].equalsIgnoreCase("rem") && word.length == 2) {
                    System.out.println(doc[currentDocNo].link.remove(new Link(word[1])));
                    continue;
                }
                // remall str
                if (word[0].equalsIgnoreCase("remall") && word.length == 2) {
                    doc[currentDocNo].link.removeAll(new Link(word[1]));
                    continue;
                }
                // addl <indexOfListArray>
                if (word[0].equalsIgnoreCase("addl") && word.length == 2) {
                    int number = Integer.parseInt(word[1]);
                    doc[currentDocNo].link.add(doc[number].link);
                    continue;
                }
                System.out.println("Wrong command");

            }catch(Exception ex) {
            System.out.println("error");
            }


        }


        System.out.println("END OF EXECUTION");
        scan.close();

    }


}
