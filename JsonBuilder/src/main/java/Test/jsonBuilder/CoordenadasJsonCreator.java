package Test.jsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class CoordenadasJsonCreator {

	private static String FILE_PATH = "C:\\Users\\dbenito\\Desktop\\F\\santa\\";
	private static String READ_FILE = "Coordenadas.tsv";
	private static String WRITE_FILE = "coordenadas.json";

	public static void main(String[] args) throws IOException {
		File fileDir = new File(FILE_PATH + READ_FILE);
		
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(
		   new InputStreamReader(
                      new FileInputStream(fileDir), "UTF8"));
		        
		String str;
		Boolean titleLine = true;
		List<Coordenadas> coordenadasList = new ArrayList<Coordenadas>();
		while ((str = in.readLine()) != null) {
			if (!titleLine)
				coordenadasList.add(getMapping(str));
			titleLine = false;
		    System.out.println(str);
		}
		
		ObjectMapper mapper = new ObjectMapper();	
	    try {  
	    	mapper.writeValue(new File(FILE_PATH + WRITE_FILE), coordenadasList );
	    } catch (IOException e) {  
	    	e.printStackTrace();  
	    }  
	}
	
	
	
	
	private static Coordenadas getMapping(String fileLine) {
//		String lineSplited[] = fileLine.split(";");
		//Para tabulaciones
		 String lineSplited[] = fileLine.split("\\t");
		
		Coordenadas coordenadas = new Coordenadas();
		coordenadas.setName(lineSplited[0]);
		coordenadas.setLatitude(lineSplited[1]);
		coordenadas.setLongitude(lineSplited[2]);
		coordenadas.setType_activity(lineSplited[3]);
		return coordenadas;
	}

}
