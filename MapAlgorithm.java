import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MapAlgorithm {

	public static void algorithm() throws InterruptedException {
		
		ExecutorService executor = Executors.newFixedThreadPool(Main.NT);
		long size, offset;
		
		for(int i = 0; i< Main.namefile.length; i++){
			
			size = new File(Main.namefile[i]).length();
			//System.out.println("Fragment " + size);
			offset = 0;
			
			while(offset + Main.D - 1 < size){
				//System.out.println("Offsetul : " +  offset+" " +  Main.D);
				Runnable worker = new MapExecutor(Main.namefile[i], offset);
				executor.execute(worker);
				offset += Main.D;
			}
			
			if(offset <= size - 1){
				System.out.println("Si aici\n\n");
				Runnable worker = new MapExecutor(Main.namefile[i], offset);
				((MapExecutor)worker).setSize((int)size - offset);
				executor.execute(worker);
			}
		
		}
		
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
		executor.shutdown();
	   
	    // Wait until all threads are finish
	    while (!executor.isTerminated()){}
	    
		}
}

