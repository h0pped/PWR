public class DisjointSetLinkedList implements DisjointSetDataStructure {

    private class Element {


        int representant;
        int next = -1;
        int length = 1;
        int last;

        private Element(int num) {
            representant = num;
            last = num;
        }

        public int getRepresentant() {
            return representant;
        }
        public void setRepresentant(int item) {
            representant = item;
        }
    }

    Element arr[];

    public DisjointSetLinkedList(int size) {
        arr = new Element[size];

    }

    @Override
    public void makeSet(int item) {
        arr[item] = new Element(item);
    }

    @Override
    public int findSet(int item) {
        if (arr[item] == null) {
            return -1;
        }

        return arr[item].getRepresentant();
    }

    @Override
    public boolean union(int itemA, int itemB) {

        itemA = findSet(itemA);
        itemB = findSet(itemB);
        if (itemA == itemB) {
            return false;
        }
        if (arr[itemA].length < arr[itemB].length) {
            int tmp = itemA;
            itemA = itemB;
            itemB = tmp;
        }
        arr[arr[itemA].last].next = itemB;
        arr[itemA].length += arr[itemB].length;
        arr[itemA].last = arr[itemB].last;

        while (itemB != -1) {
            arr[itemB].setRepresentant(itemA);
            itemB = arr[itemB].next;
        }

        return true;
    }


    @Override
    public String toString() {
        String str = "Disjoint sets as linked list:";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getRepresentant() != i) {
                continue; //if element is not representant of the set
            }
            //found representant ->
            str+="\n";
            while (true) {
                str+=i; //element
                if (arr[i].next != -1) {
                    str+=", "; //if we have next element
                } else { //if we don't have next element
                    i = arr[i].getRepresentant();
                    break;
                }
                i = arr[i].next;
            }
        }
        return str;
    }
}
