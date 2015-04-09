import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ReduceAlgorithm {
	
	public static void algorithm(){
		
		ExecutorService executor = Executors.newFixedThreadPool(Main.NT);
		
		for(int i = 0; i< Main.namefile.length; i++){
			Runnable worker = new ReduceExecutor(Main.namefile[i]);
			executor.execute(worker);
		}
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
		executor.shutdown();
	   
	    // Wait until all threads are finish
	    while (!executor.isTerminated()){}
	    
		}
}
