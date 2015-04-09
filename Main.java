import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


public class Main {
	 public static int NT, D, ND;
	 public static String fisin, fisout;
	 public static float X;
	 public static String[] namefile;
	 
	 public static Map<String, Vector<Map<String, Integer>>> comp = new ConcurrentHashMap< String, Vector<Map<String, Integer> >>();
	 public static Map<String, Map<String, Integer>> red = new ConcurrentHashMap<String, Map<String, Integer>>();
	 public static Map<Map.Entry<String, String>, Float> freq = new ConcurrentHashMap<Map.Entry<String, String>, Float>();
	 
	 public static void main(String[] args) throws IOException {
	    
	    if(args.length != 3){
			System.out.println("Programul este rulat cu argumentele NT, fisin, fisout");
			System.exit(1);
		}
		
		NT = Integer.parseInt(args[0]);
		fisin = args[1];
		fisout = args[2];
		
		readInput();
		System.out.println(NT + " " + fisin + " " + fisout);
		
	try {
		MapAlgorithm.algorithm();
		PrintWriter p = new PrintWriter("outM.txt");
		for(Map.Entry<String, Vector<Map<String, Integer>>> e : comp.entrySet()){
			p.println(e.getKey());
			for(int i = 0; i< e.getValue().size(); i++)
				p.println(e.getValue().elementAt(i));
		}
		p.close();
		ReduceAlgorithm.algorithm();
		p = new PrintWriter("outR.txt");
		for(Map.Entry<String, Map<String, Integer>> e : red.entrySet()){
			p.println(e.getKey());
				p.println(e.getValue());
		}
		p.close();
		CompareAlgorithm.algorithm();
	    try {
	    	System.out.println(fisout);
			PrintWriter p1 = new PrintWriter("AHAM");
			for(int i = 0; i< CompareAlgorithm.c.length; i++)
		    	System.out.println(CompareAlgorithm.c[i]);
			p1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
		
		
}

private static void readInput() throws IOException {
		BufferedReader br = null;
		String sCurrentLine;
		
		System.out.println(fisin);
		br = new BufferedReader(new FileReader(fisin));
		
		//dimensiunea in octeti a fragmentelor
		sCurrentLine = br.readLine();
		D = Integer.parseInt(sCurrentLine);
		
		//gradul de similaritate
		sCurrentLine = br.readLine();
		X = Float.parseFloat(sCurrentLine);
		
		//numarul de fisiere
		sCurrentLine = br.readLine();
		ND = Integer.parseInt(sCurrentLine);
		
		System.out.println(D + " " + X + " " + ND );
		namefile = new String[ND];
		for(int i = 0; i< ND; i++){
			namefile[i] = br.readLine(); 
			System.out.println(namefile[i] + " ");
		}
		
		
		br.close();
	}
}
