
public class DisjointSetForest implements DisjointSetDataStructure {
    public class Element {
        public Element(int item) {
            parent = item;
            rank = 1;
        }

        int rank;
        int parent;

        int getParent() {
            return parent;
        }

        int getRank() {
            return rank;
        }
    }

    Element[] arr;

    public DisjointSetForest(int size) {
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
        int parent = item;
        while (arr[parent].getParent() != parent) {
            parent = arr[parent].getParent();
        }

        arr[item].parent = parent;

        return parent;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        itemA = findSet(itemA);
        itemB = findSet(itemB);

        if (itemA == -1 || itemB == -1 || itemA == itemB) {
            return false;
        }

        if (arr[itemA].getRank() <= arr[itemB].getRank()) {
            arr[itemA].parent = itemB;
        } else if (arr[itemA].getRank() > arr[itemB].getRank()) {
            arr[itemB].parent = itemA;
        }

        if (arr[itemA].getRank() == arr[itemB].getRank()) {
            arr[itemB].rank++;
        }

        return true;
    }

    @Override
    public String toString() {
        String str = "Disjoint sets as forest:\n";
        for (int i = 0; i < arr.length; i++) {
            str+=i+" -> "+ arr[i].parent;
            if (i < arr.length - 1) str+="\n";
        }
        return str;
    }
}
