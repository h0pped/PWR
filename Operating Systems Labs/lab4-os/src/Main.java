import java.util.Scanner;

import Algorithms.LRU;
import process.Memory;
import process.ProcessHandler;
import process.Process;

public class Main {
    static String menu = "Menu!\n1.Set memory size\n2.Start\n3.End";
    static Scanner sc;
    static Memory memory;
    static int virtual_memory_length = 2000;
    static int processors_number = 10;
    static LRU lru_algorithm;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        memory = new Memory(200);

        boolean end = false;

        while (!end) {
            System.out.println(menu);
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Input size of memory: ");
                    memory = new Memory(sc.nextInt());
                    continue;
                case 2:
                    System.out.print("\nStart!\n");
                    lru_algorithm = new LRU();
                    Process[] processes = ProcessHandler.proportionalAlloc(
                            ProcessHandler.createProcesses(processors_number, 150),
                            memory);
                    System.out.println("Proportional allocation");
                    lru_algorithm.global(memory,
                            processes,
                            ProcessHandler.generatePages(processes, virtual_memory_length));
                    System.out.println(lru_algorithm);
                    lru_algorithm.global(memory,
                            processes,
                            ProcessHandler.generatePages(processes, virtual_memory_length));
                    System.out.println(lru_algorithm);

                    processes = ProcessHandler.equalAlloc(
                            ProcessHandler.createProcesses(processors_number, 150),
                            memory);
                    System.out.println("Equal allocation.");
                    lru_algorithm.global(memory,
                            processes,
                            ProcessHandler.generatePages(processes, virtual_memory_length));
                    System.out.println(lru_algorithm);
                    lru_algorithm.local(memory,
                            processes,
                            ProcessHandler.generatePages(processes, virtual_memory_length));
                    System.out.println(lru_algorithm);
                    continue;
                case 3: //todo
                    end = true;
                    continue;
                default:
                    System.out.println("error!");
                    continue;
            }

        }
        System.out.println("END");
        sc.close();
    }
}
