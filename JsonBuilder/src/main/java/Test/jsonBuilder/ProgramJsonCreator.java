package Test.jsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class ProgramJsonCreator {

	private static String FILE_PATH = "C:\\Users\\dbenito\\Desktop\\F\\santa\\";
	private static String READ_FILE = "Programa.tsv";
	private static String WRITE_FILE = "programa.json";

	public static void main(String[] args) throws IOException {
		File fileDir = new File(FILE_PATH + READ_FILE);
		
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
		        
		String str;
		Boolean titleLine = true;
		List<Programa> programaList = new ArrayList<Programa>();
		while ((str = in.readLine()) != null) {
			if (!titleLine)
				programaList.add(getProgramaMapping(str));
			titleLine = false;
		    System.out.println(str);
		}
		
		ObjectMapper mapper = new ObjectMapper();	
	    try {  
	    	mapper.writeValue(new File(FILE_PATH + WRITE_FILE), programaList );
	    } catch (IOException e) {  
	    	e.printStackTrace();  
	    }  
	}
	
	
	
	
	private static Programa getProgramaMapping(String fileLine) {
//		String lineSplited[] = fileLine.split(";");
		//Para tabulaciones
		 String lineSplited[] = fileLine.split("\\t");
		
		Programa programa = new Programa();
		programa.setDate(lineSplited[0]);
		programa.setTime(lineSplited[1]);
		programa.setActivity(lineSplited[2]);
		programa.setDesc_activity(lineSplited[3]);
		programa.setPlace(lineSplited[4]);
		programa.setOrganized(lineSplited[5]);
		programa.setImage_url(lineSplited[6]);
		return programa;
	}

}
