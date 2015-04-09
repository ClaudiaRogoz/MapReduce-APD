import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

class MapTask{
	String name;
	long offset;
	int size;
	
	Map<String, Integer> map = new HashMap<String, Integer>();
	
	MapTask(String name, long offset){
		this.name = name;
		this.offset = offset;
		size = Main.D;
		
	}
	
	public boolean separator(char c){
		//;:/?~\.,><~`[]{}()!@#$%^&-+'=*"| space/tab/end-line
		String s = ";:/?~.,><~`[]{}()!@#$%^&-+'=*|";
		for(int i = 0; i< s.length(); i++){
			if(c == s.charAt(i))
				return true;
		}
		
		if(c == '\n' || c == '\t' || c == ' ' || c == '"')
			return true;
		
		return false;
	}
	
	@Override
	public String toString(){
		return name + " " + offset + " " + size;
	}

	void processTask() throws IOException {
		byte buffer[] = new byte[size];
		String str = new String();
		RandomAccessFile reader = new RandomAccessFile(name, "r");
		//System.out.println("Offset " + offset);
		reader.seek(offset);
		reader.read(buffer);
		//System.out.println((char)buffer[0] +" " + (char)buffer[1]);
		char c;
		int k = 0;
		
		//daca nu este primul fragment, atunci nu trebuie cautat primul delimitator
		if(offset != 0){
			//System.out.println("nu este primul fragment");
			c = (char) buffer[k];
			while((k+1 < size) && !(separator(c))){
				c = (char) buffer[++k];
			}
		}
		//System.out.println("k = " + k + " " + size);
		//introducem cuvintele in hash 
		for(int i = k; i< size; i++){
			c = (char) buffer[i];
			//System.out.println(c);
			if(separator(c)){
				//System.out.println("am gasit cuvantul " + str);
				insertHash(str);
				str="";
			}
			else {str += c;
			//System.out.println("Else string " + str);	
			}
		}
		
		//daca iesim din fragment
		boolean ok = true;
		//System.out.println(size + " " + offset + " " + new File(this.name).length());
		int size1 = size;
		float file_size = (new File(this.name).length());
			
		while(ok && (size1 + offset < file_size)){
			size1 ++;
			
			c = (char) reader.readByte();
			if(separator(c)){
				//System.out.println(str);
				insertHash(str);
				str="";
				ok = false;
			}
			else str += c;
		}
	}
	
	private boolean readVerify(long offset) throws IOException{
		String str = new String();
		RandomAccessFile reader = new RandomAccessFile(name, "r");
		reader.seek(offset);
		
		char c = (char) reader.readByte();
		if(separator(c)){
			//System.out.println(str);
			insertHash(str);
			str="";
			return false;
		}
		else str += c;
		
		return true;
	}

	private void insertHash(String str) {
		
		if(map.containsKey(str.toLowerCase())){
			int f = map.get(str.toLowerCase());
			//System.out.print(" frecventa " + f+1 );
			map.put(str.toLowerCase(), f+1);
		}
		else {map.put(str.toLowerCase(), 1);
		//System.out.print(" frecventa " + 1 );
		}
	}
	
	
}
