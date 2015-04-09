import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


public class MapExecutor implements Runnable{

	private long size;
	private long offset;
	private String name;
	private long fsize;
	private Map<String, Integer> m;
	MapExecutor(){
		
	}
	
	MapExecutor(String name, long offset){
			this.name = name;
			this.offset = offset;
			this.size = Main.D;
			fsize = new File(name).length();
	}
	
	public boolean separator(char c){
		//;:/?~\.,><~`[]{}()!@#$%^&-+'=*"| space/tab/end-line
		String s = ";:/?~.,><~`[]{}()!@#$%^&-+'=*|_" + '\r' + '\n' + '\t' + ' ' + '"';
		for(int i = 0; i< s.length(); i++){
			if(c == s.charAt(i))
				return true;
		}
		
		return false;
	}
	
	public void setSize(long size){
		this.size = size;
	}
	
	public void processCommand(){
		
		if(!Main.comp.containsKey(name)){
			Main.comp.put(name, new Vector<Map<String, Integer>>());
		}
		Vector<Map<String, Integer>> v = Main.comp.get(name);
		m = new HashMap<String, Integer>();
		v.add(m);
		
		String delim = ";:/?~.,><~`[]{}()!@#$%^&-+'=*|" + '\r' + '\n' + '\t' + ' ' + '"';
		byte buffer[] = new byte[(int)this.size];
		String str = new String();
		String sbuff;
		StringTokenizer stringTokenizer;
		RandomAccessFile reader;
		try {
			reader = new RandomAccessFile(name, "r");
			//System.out.println("Offset-ul de pornire: " +  offset);
			byte previousChar = ' ';
			if (offset > 0) {
				reader.seek(offset - 1);
				previousChar = reader.readByte();
			}
			
			reader.seek(offset);
			reader.read(buffer);
			sbuff = new String(buffer);
			stringTokenizer = new StringTokenizer(sbuff, delim);
			/*if(offset ==  3072 && name.equals("1mb-1")){
				System.out.println("Fisier: " + name);
				System.out.println(sbuff);
			}*/
			boolean ok = false;
			if(offset != 0){
				for(int i = 0; i< delim.length(); i++){
					if (sbuff.charAt(0) == delim.charAt(i)){
						ok = true;
						break;
					}
				}
				
				if(!ok){
					if (!separator((char)previousChar)) {
						stringTokenizer.nextElement();
					}
				}
			}
			
			while(stringTokenizer.hasMoreElements()){
				str = stringTokenizer.nextElement().toString();
				if(stringTokenizer.hasMoreElements()){
					/*if(offset == 3072 && name.equals("1mb-1")){
						System.out.println("inseram " + str);
					}*/
					insertHash(str);
				}
			}
			/*
			if(offset == 3072 && name.equals("1mb-1")){
			System.out.println("Am ramas la " + str);
			}*/
			for(int i = 0; i< delim.length(); i++){
			if(sbuff.charAt(sbuff.length() -1) == delim.charAt(i)){
				/*if(offset == 3072 && name.equals("1mb-1")){
					System.out.println("inseram " + str);
				}*/
				insertHash(str);
				return;
			}
			}
			
			String lastStr = str;
			
			try {
				byte nextChar = reader.readByte();
				while (!separator((char)nextChar)) {
					lastStr += (char)nextChar;
					//System.out.println(this.name+ " : " + (char) nextChar);

					nextChar = reader.readByte();
				}
			} catch(Exception e) {
				
			}
			
			insertHash(lastStr);
		// 	System.out.println("LAST:" + lastStr);
			
			/**
			buffer = new byte[100];
			ok = false;
			if(offset + Main.D < fsize){
			
				reader.seek(offset + Main.D);
				reader.read(buffer);
				sbuff = new String(buffer);
				stringTokenizer = new StringTokenizer(sbuff, delim);
				String str1 = stringTokenizer.nextElement().toString();
				if(offset == 3072 && name.equals("1mb-1")){
					System.out.println("Sbuuf " + sbuff.charAt(0));
				}
				for(int i = 0; i< delim.length(); i++){
				if(sbuff.charAt(0) == delim.charAt(i)){
					ok = true;
					if(offset == 3072 && name.equals("1mb-1")){
						System.out.println("inseram " + str);
					}
				
					insertHash(str);
					return;
					}
				}
				if(offset == 3072 && name.equals("1mb-1")){
					System.out.println("inseram " + str + str1);
				}
				insertHash(str + str1);
			}
			else {
				if(offset == 3072 && name.equals("1mb-1")){
					System.out.println("inseram " + str);
				}
				insertHash(str);
			}
			**/
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}
	
	private void insertHash(String str) {
		
		if(m.containsKey(str.toLowerCase())){
			int f = m.get(str.toLowerCase());
			m.put(str.toLowerCase(), f+1);
		}
		else {
			m.put(str.toLowerCase(), 1);
		}
	}

	@Override
	public void run() {
		
		processCommand();
		
	}

}
