import javax.print.Doc;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface DisjointSetDataStructure {
    void makeSet(int item);

    int findSet(int item);

    boolean union(int itemA, int itemB);

    int countSets();
}


class DisjointSetForest implements DisjointSetDataStructure {

    private class Element {
        int rank;
        int parent;
    }

    Element[] arr;

    public DisjointSetForest(int size) {
        arr = new Element[size];
        makeSet(size);
    }

    @Override
    public void makeSet(int size) {
        for (int i = 0; i < size; i++) {
            arr[i] = new Element();
            arr[i].parent = i;
            arr[i].rank = 1;
        }
    }

    @Override
    public int findSet(int item) {
        int tempItem = item;
        if (item < arr.length && item >= 0) {
            if (arr[item].parent == item) {
                return item;
            } else {
                Element temp = arr[item];
                while (temp.parent != item) {
                    item = temp.parent;
                    temp = arr[item];
                }
                arr[tempItem].parent = item;
                return item;
            }
        }
        return -1;
    }

    @Override
    public int countSets() {
        LinkedList<Integer> found = new LinkedList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!found.contains(arr[i].parent)) {
                found.add(arr[i].parent);
            }
        }
        return found.size();
    }

    @Override
    public boolean union(int itemA, int itemB) {
        if (findSet(itemA) != findSet(itemB)) {
            if (arr[findSet(itemA)].rank > arr[findSet(itemB)].rank) {
                arr[findSet(itemB)].parent = findSet(itemA);
                return true;
            } else if (arr[findSet(itemA)].rank == arr[findSet(itemB)].rank) {
                arr[findSet(Math.max(itemA, itemB))].rank += arr[findSet(Math.min(itemA, itemB))].rank;
                arr[findSet(Math.min(itemA, itemB))].parent = findSet(Math.max(itemA, itemB));
                return true;
            } else {
                arr[findSet(itemA)].parent = findSet(itemB);
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        String buffer = "Disjoint sets as forest:\n";
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                buffer += "\n";
            }
            buffer += i + " -> " + arr[i].parent;
        }
        return buffer;
    }
}

class Document implements IWithName {
    public String name;
    public LinkedList<Link> link;

    public Document(String name) {
        this.name = name.toLowerCase();
        link = new LinkedList<>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new LinkedList<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        Pattern pat = Pattern.compile("[L|l][I|i][N|n][K|k]=(([a-z-A-Z][a-zA-Z_]*\\b)((\\(([^\\(]*)\\))|(?!\\()))");
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
                        link.add(new Link(matcher.group(1)));
                    } catch (NumberFormatException e) {
                    }
                }
            } else {
                Matcher matcher = pat.matcher(actStr);

                while (matcher.find()) {
                    try {
                        link.add(new Link(matcher.group(1)));
                    } catch (NumberFormatException e) {
                    }

                }
            }
        }

    }

    public static boolean isCorrectId(String id) {
        id = id.toLowerCase();
        if (id.length() == 0) return false;
        if (id.charAt(0) < 'a' || id.charAt(0) > 'z')
            return false;
        for (int i = 1; i < id.length(); i++) {
            if (!(id.charAt(i) >= 'a' && id.charAt(i) <= 'z'
                    || id.charAt(i) >= '0' && id.charAt(i) <= '9'
                    || id.charAt(i) == '_'))
                return false;
        }
        return true;
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static Link createLink(String link) {
        try {
            return new Link(link);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        String retStr = "Document: " + name + "\n";
        //TODO?
        retStr += link;
        return retStr;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.compareTo(((Document)obj).name) == 0;
    }
}

class Graph {
    int arr[][];
    //TODO? Collection to map Document to index of vertex
    // You can change it
    HashMap<String, Integer> name2Int;
    @SuppressWarnings("unchecked")
    //TODO? Collection to map index of vertex to Document
            // You can change it
    HashMap<Integer, Document> int2Doc;


    // The argument type depend on a selected collection in the Main class
    public Graph(SortedMap<String, Document> internet) {
        int size = internet.size();
        arr = new int[size][size];

        Set s = internet.entrySet();
        Iterator iter = s.iterator();
        int counter = 0;
        name2Int = new HashMap<>();
        int2Doc = new HashMap<>();
        while (iter.hasNext()) {
            Map.Entry m = (Map.Entry) iter.next();
            name2Int.put(((Document) m.getValue()).name, counter);
            int2Doc.put(counter, (Document) m.getValue());
            counter++;
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                arr[i][j] = Integer.MIN_VALUE;
            }
        }
        for (int i = 0; i < size; i++) {
            arr[i][i] = 0;
        }
        for (int i = 0; i < size; i++){
            for(Link l: int2Doc.get(i).link){
                if(name2Int.get(l.ref) != null){
                    arr[i][name2Int.get(l.ref)] = l.weight;
                }
            }
        }
    }

    public String bfs(String start) {
        if(this.name2Int.get(start) != null) {
            String buffer = "";
            boolean[] visited = new boolean[arr[0].length];

            LinkedList<Integer> queue = new LinkedList();

            visited[this.name2Int.get(start)] = true;
            queue.add(name2Int.get(start));

            while (queue.size() != 0) {
                start = int2Doc.get(queue.poll()).getName();
                buffer += start + ", ";
                for (int i = 0; i < arr[0].length; i++) {
                    if (this.arr[name2Int.get(start)][i] != 0 && this.arr[name2Int.get(start)][i] != Integer.MIN_VALUE && visited[i] == false) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
            if(buffer.length() != 0){
                buffer = buffer.substring(0, buffer.length() - 2);
            }
            return buffer;
        }
        return null;
    }



    public String dfs(String start) {
        if(this.name2Int.get(start) != null){
            boolean[] visited = new boolean[arr[0].length];
            visited[this.name2Int.get(start)] = true;
            ///TUTUTUTUTUTUUT
            String ret = printDfs(start, visited);
            return ret.substring(0, ret.length() - 2);
        }
        return null;
    }

    public String printDfs(String start, boolean[] visited) {
        if(this.name2Int.get(start) != null) {
            String buffer = start + ", ";
            for (int i = 0; i < this.arr[0].length; i++) {
                if(this.arr[name2Int.get(start)][i] != 0 && this.arr[name2Int.get(start)][i] != Integer.MIN_VALUE){
                    if (visited[i] == false) {
                        visited[i] = true;
                        buffer += printDfs(int2Doc.get(i).getName(), visited);
                    }
                }
            }
            return buffer;
        }
        return null;
    }

    public int connectedComponents() {
        try {
            DisjointSetForest set = new DisjointSetForest(arr[0].length);

            for (int i = 0; i < arr[0].length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] != Integer.MIN_VALUE && arr[i][j] != Integer.MIN_VALUE) {
                        set.union(i, j);
                    }
                }
            }
            return set.countSets();
        }
        catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }
}

interface IWithName {
    String getName();
}

class Link implements Comparable<Link> {
    public String ref;
    public int weight;

    public Link(String input) {
        Pattern pat = Pattern.compile("([a-z-A-Z][a-zA-Z_]*\\b)((\\(([^\\(]*)\\))|(?!\\())");
        Matcher matcher = pat.matcher(input);
        if (matcher.find()) {
            if (matcher.group(1) == null) {
                throw new NullPointerException();
            }
            if (matcher.group(4) == null) {
                this.weight = 1;
            } else if (Integer.parseInt(matcher.group(4)) < 0) {
                throw new NumberFormatException();
            } else {
                this.weight = Integer.parseInt(matcher.group(4));
            }
            this.ref = matcher.group(1).toLowerCase();
        } else {
            throw new NumberFormatException();
        }
    }

    public Link(String ref, int weight) {
        this.ref = ref;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Link) obj).ref.equalsIgnoreCase(ref);
    }

    @Override
    public String toString() {
        return ref + "(" + weight + ")";
    }

    @Override
    public int compareTo(Link another) {
        return ref.compareTo(another.ref);
    }
}

class Main {

    static Scanner scan; // for input stream

    public static void main(String[] args) {
        System.out.println("START");
        scan = new Scanner(System.in);
        SortedMap<String, Document> sortedMap = new TreeMap<String, Document>();
        Document currentDoc = null;
        boolean halt = false;
        while (!halt) {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);
            String word[] = line.split(" ");
            //getdoc name - change document to name
            if (word[0].equalsIgnoreCase("getdoc") && word.length == 2) {
                currentDoc = (Document) sortedMap.get(word[1]);
                continue;
            }

            // ld documentName
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                if (Document.isCorrectId(word[1])) {
                    currentDoc = new Document(word[1], scan);
                    sortedMap.put(currentDoc.name, currentDoc);
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
            if (word[0].equalsIgnoreCase("clear") &&

            word.length == 1) {
                if (currentDoc != null)
                    currentDoc.link.clear();
                else
                    System.out.println("no current document");
                continue;
            }
            // show
            // it depends of the representation so it will be NOT in tests
            if (word[0].equalsIgnoreCase("show") && word.length == 1) {
                if (currentDoc != null)
                    System.out.println(currentDoc.toString());
                else
                    System.out.println("no current document");
                continue;
            }
            // size
            if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                if (currentDoc != null)
                    System.out.println(currentDoc.link.size());
                else
                    System.out.println("no current document");
                continue;
            }
            // add str
            if (word[0].equalsIgnoreCase("add") && word.length == 2) {
                if (currentDoc != null) {
                    Link link = Document.createLink(word[1]);
                    if (link == null)
                        System.out.println("error");
                    else {
                        currentDoc.link.add(link);
                        System.out.println("true");
                    }
                } else
                    System.out.println("no current document");
                continue;
            }
            // get str
            if (word[0].equalsIgnoreCase("get") && word.length == 2) {
                try {
                    if (currentDoc != null) {
                        Link l = currentDoc.link.get(currentDoc.link.indexOf(new Link(word[1])));
                        if (l != null) {
                            System.out.println(l);
                        } else {
                            System.out.println("error");
                        }
                    } else
                        System.out.println("no current document");
                    continue;
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("error");
                    continue;
                }
            }
            // rem str
            if (word[0].equalsIgnoreCase("rem") && word.length == 2) {
                try {
                    if (currentDoc != null) {
                        Link l = currentDoc.link.get(currentDoc.link.indexOf(new Link(word[1])));
                        ;
                        currentDoc.link.remove(l);
                        if (l != null) {
                            // write the removed link
                            System.out.println(l);
                        } else {
                            System.out.println("error");
                        }
                    } else
                        System.out.println("no current document");

                    continue;
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("error");
                    continue;
                }
            }

            // bfs str
            if (word[0].equalsIgnoreCase("bfs") && word.length == 2) {
                Graph graph = new Graph(sortedMap);
                String str = graph.bfs(word[1]);
                if (str != null) {
                    System.out.println(str);
                } else {
                    System.out.println("error");
                }
                continue;
            }
            // dfs str
            if (word[0].equalsIgnoreCase("dfs") && word.length == 2) {
                Graph graph = new Graph(sortedMap);
                String str = graph.dfs(word[1]);
                if (str != null) {
                    System.out.println(str);
                } else {
                    System.out.println("error");
                }
                continue;
            }
            // cc
            if (word[0].equalsIgnoreCase("cc") && word.length == 1) {

                Graph graph = new Graph(sortedMap);
                int str = graph.connectedComponents();
                System.out.println(str);
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();
    }

}
