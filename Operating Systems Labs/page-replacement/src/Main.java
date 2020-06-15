import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void GeneratePages(int[]pages, int max){ // fill out array of pages by random values
        Random rand = new Random();
        for(int i = 0;i<pages.length;i++){
            pages[i] =rand.nextInt(max*2)+1;
        }
    }

    public static void printArr(int[] arr){// just to print array
        for(int x:arr){
            System.out.print(x+" ");
        }

    }

    //1 - SET SIZE OF PHYSICAL MEMORY (COUNT OF FRAMES)
    //2 - SET SIZE OF VIRTUAL MEMORY (PAGES)
    //3 - GENERATE PAGES
    //4 - START SIMULATION WITH ENTERED PHYSICAL AND VIRTUAL MEMORY
    //5 - START SIMULATION WITH TEST STRING WHICH REPRESENTS ALL BEAUTY OF ALGORITHMS
    //6 - EXIT

    //if we have  same amount of physical memory and virtual memory - all algorithms have same amount of
    //hits and faults, because it just fills physical memory without any logic

    //RECOMMENDATION - RUN PROGRAM WITH PHYSICAL MEMORY <10 AND VIRTUAL MEMORY >1000 TO SEE HOW ALGORITHMS WORK
    //OR YOU CAN JUST RUN TEST STRING SIMULATION AND THE RESULT WILL BE AS SHOWN IN PRESENTATION
    //(NOTE - 0 IS REPLACED BY 9, BECAUSE IN MY IMPLEMENTATION 0 MEANS THAT FRAME IS EMPTY)
    //SIMULATION STRING - 7 9 1 2 0 3 0 4 2 3 9 3 2 1 2 0 1 7 9 1

    public static void main(String[]args){
        boolean end = false;
        int physical_size = 0;
        int virtual_size = 0;
        int[]pages = new int[virtual_size];
        int[]frames = new int[physical_size];
        Scanner sc = new Scanner(System.in);
        while(!end){
            System.out.println("Menu");

            System.out.println("1. Set Size of physical memory(current: "+physical_size+")");
            System.out.println("2. Set Size of virtual memory(current: "+virtual_size+")");
            System.out.println("3. Generate pages");
            System.out.println("4. Start simulation");
            System.out.println("5. Simulation with test string");
            //test string is the string which was presented in presentation
            //7,9,1,2,9,3,9,4,2,3,9,3,2,1,2,9,1,7,9,1 (0 replaced by 9, because in my simulation 0 means free space)
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter size of physical memory: ");
                    physical_size = sc.nextInt();
                    frames = new int[physical_size];
                    System.out.println("done!");
                    continue;
                case 2:
                    System.out.print("Enter size of virtual memory: ");
                    virtual_size = sc.nextInt();
                    pages = new int[virtual_size];
                    System.out.println("done!");
                    continue;
                case 3: //todo
                    GeneratePages(pages,frames.length);
                    System.out.println("done!");
                    continue;
                case 4:
                    System.out.println("-------------------------");
                    FIFO f = new FIFO(pages,frames);
                    f.simulate();
                    System.out.println("-------------------------");
                    LRU lru = new LRU(pages,frames);
                    lru.simulate();
                    System.out.println("-------------------------");
                    OPT opt = new OPT(pages,frames);
                    opt.simulate();
                    System.out.println("-------------------------");
                    RAND rand = new RAND(pages,frames);
                    rand.simulate();
                    System.out.println("-------------------------");
                    continue;
                case 5:
                    int[] p = {7,9,1,2,9,3,9,4,2,3,9,3,2,1,2,9,1,7,9,1};
                    int[]fr = new int [3];
                    System.out.print("Test string Pages: ");
                    printArr(p);
                    System.out.println("\nPhysical memory size: "+fr.length);
                    System.out.println("-------------------------");

                    System.out.println("Simulation:");

                    FIFO ft = new FIFO(p,fr);
                    ft.simulate();
                    System.out.println("-------------------------");
                    LRU lrut = new LRU(p,fr);
                    lrut.simulate();
                    System.out.println("-------------------------");
                    OPT optt = new OPT(p,fr);
                    optt.simulate();
                    System.out.println("-------------------------");
                    RAND randt = new RAND(p,fr);
                    randt.simulate();
                    continue;
                case 6:
                    end = true;
                    continue;
                default:
                    System.out.println("error!");
                    continue;
            }
        }
    }
}
