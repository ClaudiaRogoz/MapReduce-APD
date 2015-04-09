import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;


public class CompareExecutor implements Runnable {

	private String name1, name2;
	private Map<String, Integer> m1;
	private Map<String, Integer> m2;
	private long sum = 0;
	
	CompareExecutor(String name1, String name2){
		this.name1 = name1;
		this.name2 = name2;
		m1 = Main.red.get(name1);
		m2 = Main.red.get(name2);
	}
	
	@Override
	public void run() {
		int f1, f2;
		
		//if(name1.equals("1mb-1"))
		//System.out.println(m1);
		//System.out.println();
		//System.out.println(m2);
		
		long totalm1 = 0, totalm2 = 0;
		for (Map.Entry<String, Integer> e : m1.entrySet()) {
			totalm1 += e.getValue();
		}
		for (Map.Entry<String, Integer> e : m2.entrySet()) {
			totalm2 += e.getValue();
		}
		
		for(Map.Entry<String, Integer> e : m1.entrySet()){
			f1 = e.getValue();
			//System.out.println("Frecventa cuvant " + f1);
			if(m2.containsKey(e.getKey())){
				f2 = m2.get(e.getKey()) * 100;
			}
			else f2 = 0;
			
			sum += (long) f1* f2;
			
		}
		
		double sumx = (float)sum / (totalm1 * totalm2);
		// sum /= totalm1;
		// sum /= totalm2;
		//BigDecimal rounded = new BigDecimal(sumx);
		//rounded = rounded.setScale(4, BigDecimal.ROUND_UP);
		 BigDecimal bd = new BigDecimal(sumx);
		 bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		 sumx = bd.doubleValue();
		 System.out.println(sumx);
		//DecimalFormat df = new DecimalFormat("#.####");
		//sumx = Double.parseDouble(df.format(sumx));
		//System.out.println(name1 + ";" + name2 + ";" + rounded.toString() );
		CompareAlgorithm.c[CompareAlgorithm.k++] = new Pair(name1 + ";" + name2, sumx);
		//System.out.println(CompareAlgorithm.c[CompareAlgorithm.k-1]);
		//System.out.println("Similitudine intre "+  name1 + " " + name2 + " = "+ sumx);
	}

}
