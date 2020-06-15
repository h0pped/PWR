import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface IList<E> extends Iterable<E> {
    boolean add(E e); // qdd element to the end of list

    void add(int index, E element) throws NoSuchElementException; // add element on position index

    void clear(); // delete all elements

    boolean contains(E element); // is list containing an element (equals())

    E get(int index) throws NoSuchElementException; //get element from position

    E set(int index, E element) throws NoSuchElementException; // set new value on position

    int indexOf(E element); // where is element (equals())

    boolean isEmpty();

    Iterator<E> iterator();

    ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator

    E remove(int index) throws NoSuchElementException; // remove element from position index

    boolean remove(E e); // remove element

    int size();
}

class TwoWayLinkedListWithHead<E> implements IList<E> {

    private class Element {
        public Element(E e) {
            object = e;
        }

        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }

        public void setValue(E value) {
            this.object = value;
        }

        public void setNext(Element next) {
            this.next = next;
            this.next.prev = this;
        }

        public E getValue() {
            return object;
        }

        public Element getNext() {
            return next;
        }

        public Element getPrevious() {
            return prev;
        }

        E object;
        Element next = null;
        Element prev = null;
    }

    Element head;
    Element tail;

    // can be realization with the field size or without
    int size;

    private class InnerIterator implements Iterator<E> {
        Element pos;
        // TODO maybe more fields....

        public InnerIterator() {
            pos = head;
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }


        @Override
        public E next() {
            E value = pos.getValue();
            pos = pos.getNext();
            return value;
        }

        public E previous() {
            E value = pos.prev.getValue();
            pos = pos.getPrevious();
            return value;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        Element p = new Element(null);
        // TODO maybe more fields....

        public InnerListIterator() {
            p.next = head;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return p.next != null;
        }

        @Override
        public boolean hasPrevious() {
            return p != null;
        }

        public E getCurrent() {
            E el = p.getValue();
            return el;
        }

        @Override
        public E next() {
            E e = p.next.getValue();
            p = p.getNext();
            return e;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            E e = p.getValue();
            p = p.getPrevious();
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
        public void set(E e) {
            p.setValue(e);
        }
    }

    public TwoWayLinkedListWithHead() {
        // make a head
        head = null;
    }

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Element(e);
            tail = head;
            head.prev = null;
            tail.next = null;
            return true;

        } else {
            Element el = new Element(e);
            tail.next = el;
            el.prev = tail;
            tail = el;
            tail.next = null;
            return true;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element newElem = new Element(element);


        if (index == 0) {
            if (head == null) {
                head = newElem;
                tail = head;
                return;
            }
            else {

                newElem.setNext(head);
                head = newElem;
                return;
            }
        } else if (index == indexOf(tail.getValue())) {
            Element actElem = getElement(index - 1);
            newElem.setNext(tail);
            tail.prev = newElem;
            actElem.setNext(newElem);
            actElem.getNext().prev = actElem;

        } else if (index == indexOf(tail.getValue()) + 1) {
            Element actElem = tail;
            actElem.next = newElem;
            newElem.next = null;
            newElem.prev = tail;
            tail = newElem;

        } else {
            Element actElem = getElement(index - 1);
            newElem.setNext(actElem.getNext());
            actElem.setNext(newElem);
            actElem.getNext().prev = actElem;
        }
    }


    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public E get(int index) {
        return getElement(index).getValue();
    }

    @Override
    public E set(int index, E element) {
        Element actElem = getElement(index);
        E elemData = actElem.getValue();
        actElem.setValue(element);
        return elemData;
    }

    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element actElem = head;
        while (index > 0 && actElem != null) {
            index--;
            actElem = actElem.getNext();
        }
        if (actElem == null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }


    @Override
    public int indexOf(E element) {
        int pos = 0;
        Element actElem = head;
        while (actElem != null) {
            if (actElem.getValue().equals(element))
                return pos;
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || head == null) throw new IndexOutOfBoundsException();
        if (index == 0) {
            E retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }
        if (index == size() - 1) {
            E retValue = tail.getValue();
            tail = tail.prev;
            tail.next = null;
            return retValue;
        }
        Element actElem = getElement(index - 1);
        if (actElem.getNext() == null)
            throw new IndexOutOfBoundsException();
        E retValue = actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        actElem.getNext().prev = actElem;
        return retValue;
    }

    @Override
    public boolean remove(E e) {
        if (head == null)
            return false;
        if (head.getValue().equals(e)) {
            head = head.getNext();
            return true;
        }
        /*if(tail.getValue().equals(e)){
            tail = tail.getPrevious();
            tail.next= null;
        }*/

        Element actElem = head;
        while (actElem.getNext() != null && !actElem.getNext().getValue().equals(e))
            actElem = actElem.getNext();

        if (actElem.getNext() == null)
            return false;
        if (actElem.getNext() == tail) {
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
        if (head == null) {
            return 0;
        }
        Element actElem = head;
        int size = 0;
        while (actElem != null) {
            size++;
            actElem = actElem.getNext();
        }
        return size;
    }

    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();
        while (iter.hasNext())
            iter.next();

        String retStr = "";

        while (iter.hasPrevious()) {
            E e = iter.previous();
            if (e != null) {
                retStr += "\n" + e;
            }
        }
        return retStr;
    }

    public void add(TwoWayLinkedListWithHead<E> other) {
        if (other == null) {
            return;

        } else {
            if (other.head == null) {
                return;
            }
            if (head == null) {
                Element lhead = other.head;
                Element actElem = new Element(lhead.getValue());
                while (lhead.getNext() != null) {
                    actElem.setNext(new Element(lhead.getNext().getValue()));
                    lhead = lhead.getNext();
                    actElem = actElem.getNext();
                    //  actElem.getNext().prev = actElem;

                }
                other.clear();
                while (actElem.getPrevious() != null) {
                    actElem = actElem.getPrevious();
                }
                head = actElem;

            } else if (other != this) {
                Element e = getElement(size() - 1);
                Element lhead = other.head;

                if (e != null) {
                    while (lhead != null) {
                        e.setNext(new Element(lhead.object));
                        e = e.getNext();
                        lhead = lhead.getNext();
                    }
                    tail = e;
                    other.clear();
                }
            }
        }

    }
}
