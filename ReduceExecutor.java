import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


public class ReduceExecutor implements Runnable{

	private String name;
	private Vector<Map<String, Integer>> v;
	
	ReduceExecutor(String name){
		this.name = name;
	}
	
	void processCommand(){
		Map<String, Integer> m = new HashMap<String, Integer>();
		Main.red.put(name, m);
		v = Main.comp.get(name);
		int s = v.size();
		
		for(Map<String, Integer> h : v){
	
			for(Map.Entry<String, Integer> e : h.entrySet()){
				int f = e.getValue();
				String term = e.getKey();
				if(m.containsKey(term)){
					f += m.get(term);
				}
				//System.out.println("adaugam termenul " + term);
				m.put(term, f);
			}
		}
		//if (name.equals("1mb-1"))
		//System.out.println(m);
	}
	
	@Override
	public void run() {
		processCommand();
		
	}

}
