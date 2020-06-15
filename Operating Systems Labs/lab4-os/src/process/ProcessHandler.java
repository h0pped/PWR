package process;

import java.util.Random;

public class ProcessHandler {
	private static Random rand = new Random();
	
	public static Page[] generatePages(Process[] processes, int length) {
		Page[] refs = new Page[length];
		Process proc;
		for (int i = 0; i < length; i++) {
			proc = processes[rand.nextInt(processes.length)];
			refs[i] = new Page(rand.nextInt(proc.size), proc);
		}
		return refs;
	}
	

	public static Process[] createProcesses(int num, int max_proc_size) {
		Process[] arr = new Process[num];
		for (int i = 0; i < num; i++)
			arr[i] = new Process( rand.nextInt(max_proc_size) + 1,
					new Memory(rand.nextInt(max_proc_size) + 1));
		return arr;
	}

	public static Process[] proportionalAlloc(Process[] processes,
			Memory phys_mem) {
		double total = 0;
		for (int i = 0; i < processes.length; i++)
			total += processes[i].size;
		for (int i = 0; i < processes.length; i++) {
			Process proc = processes[i];
			double ratio = (double) proc.size / total;
			int alloc_frames = (int) Math.max(1, Math.floor(ratio * phys_mem.getFramesNum()));
			proc.memory = new Memory(alloc_frames);
		}
		return processes;
	}

	public static Process[] equalAlloc(Process[] processes, 
			Memory phys_mem) {
		int alloc_frames = (int) Math.max(1, (double) phys_mem.getFramesNum() / processes.length);
		for (int i = 0; i < processes.length; i++)
			processes[i].memory = new Memory(alloc_frames);
		return processes;
	}
	
	
}
 