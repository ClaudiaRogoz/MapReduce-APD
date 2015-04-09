import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CompareAlgorithm {
	
	static int n = Main.namefile.length;
	static Pair<String, Double> c[] = new Pair[(n-1)* n/2];
	static int k = 0;
	static int size = 0;
	public static void algorithm() {
		
		ExecutorService executor = Executors.newFixedThreadPool(Main.NT);
		
		for(int i = 0; i< Main.namefile.length; i++){
			for(int j = i+1; j < Main.namefile.length; j++){
				Runnable worker = new CompareExecutor(Main.namefile[i], Main.namefile[j]);
				executor.execute(worker);
				size ++;
			}
		}
		
		
		//System.out.println(c[k-1]);
	
		
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
		executor.shutdown();
	   
	    // Wait until all threads are finish
	    while (!executor.isTerminated()){}
	 
	    Arrays.sort(c);
	  
	    
	}

}
