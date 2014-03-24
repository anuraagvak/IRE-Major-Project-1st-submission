import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;


public class feature_words {
	
	
	
	
	public static void  getFeatures (String filename , int index) throws IOException
	{
		FileReader fr= new FileReader("/home/navya/Downloads/mypersonality_final/mypersonality_final.csv");
		
		BufferedReader br2= new BufferedReader(fr);
		String currentline="";

		 System.out.println("ete");
		HashMap<String, ArrayList<String>> extrovert = new  HashMap<String, ArrayList<String>>();
		HashMap<String, Double> idf_extrovert = new  HashMap<String, Double >();
		HashMap <String , Double > total_freq= new HashMap <String , Double >();
		currentline=br2.readLine();
		int total_count=0;
		while((currentline=br2.readLine())!=null)
			
		 {
			total_count++;
			 String[] tokens= currentline.split(",");
			 tokens[1]=tokens[1].replaceAll("\"", "");
			 String[] words = tokens[1].split(" ");
			 for (int i=0;i<words.length;i++)
			 {
				 if (total_freq.get(words[i])==null )
				 {
					
					 total_freq.put((String)words[i], (double)1);
				 }
				 else
				 {
					
					 total_freq.put((String)words[i],total_freq.get(words[i])+1);
				 }
			 }
			
		 }
		System.out.println(total_count);
		fr.close();
		br2.close();
int count_extrovert =0;
FileReader fr2= new FileReader("/home/navya/Downloads/mypersonality_final/mypersonality_final.csv");
TreeMap<Double, String>Y_X_extrovert= new   TreeMap<Double, String>();


BufferedReader br= new BufferedReader(fr2);
currentline=br.readLine();
		while((currentline=br.readLine())!=null)
				
		 {
			 String[] tokens= currentline.split(",");
			 for(int i=0; i < tokens.length; i++)
			 {
				// System.out.println(i+tokens[i]);
			 }
			
				 // System.out.println("ete");
			 if (extrovert.get(tokens[0])==null ) 
			 {
				 
				
				 if (tokens[index].equals("\"y\""))
				 {
					
					// System.out.println("etere");
					 tokens[1]=tokens[1].replaceAll("\"","") ; 
				 String[]  words= tokens[1].split(" ");
				 ArrayList<String> words_user= new ArrayList<String> ();
				 for (int i=0; i< words.length ; i++)
				 {
					 words_user.add(words[i]);
					 
				 }
				 extrovert.put(tokens[0], words_user) ; 
				 }
				 count_extrovert ++;
			 }
			 else
			 {
				 if (tokens[index].equals("\"y\""))
				 {
					// System.out.println("enn");
					 tokens[1]=tokens[1].replaceAll("\"","") ; 
							 String[]  words= tokens[1].split(" ");
					 
				 
				 ArrayList<String> words_user = extrovert.get(tokens[0]);
				 for (int i=0; i< words.length ; i++)
				 {
					
			//System.out.println(words[i]);		 
words_user.add(words[i])	;				 
				 }
				 extrovert.put(tokens[0], words_user) ; 
				 }
			 }
				 
			 
		 }
		
		 for (Entry<String, ArrayList<String>> entry : extrovert.entrySet())
		 {
			
ArrayList words= entry.getValue() ; 
for (int i=0; i< words.size() ; i++)
{
	if (idf_extrovert.get(words.get(i))==null)
	{
		idf_extrovert.put((String) words.get(i), (double) 1);
		
	}
	else
	{
		idf_extrovert.put((String) words.get(i), idf_extrovert.get((String) words.get(i))+1);
	}
	
}

			 
		 }
		 TreeMap< Double,String> X = new  TreeMap< Double,String> ();
		 
		 for (Entry<String, Double> entry : idf_extrovert.entrySet())
		 {
			 X.put(-(entry.getValue()), entry.getKey());
		 }
		
		 int cout=0;
		 for (Entry<Double, String> entry : X.entrySet())
		 {
			 if(cout>200)
			 {
				 break;
			 }
			// System.out.println(entry.getValue() + ""+(-(entry.getKey()) ));
				 cout++;
			 //System.out.println((entry.getKey())+" "+ total_freq.get(entry.getKey()) +" "+ entry.getValue() ); 
			 Double val = total_freq.get(entry.getValue());
			Y_X_extrovert.put( (val-entry.getKey()), entry.getValue());
			
			 
			 
		 }
		 int count=0;
		 FileWriter fw= new FileWriter(filename, false);
		 //FileWriter fw= new FileWriter("/home/navya/Downloads/mypersonality_final/opn_features", false);
			
			BufferedWriter bre= new BufferedWriter(fw);
		 for (Entry<Double, String> entry :Y_X_extrovert.entrySet())
		 {
			 bre.write(entry.getValue());
			 bre.newLine();
			 count++;
			 if(count>=50)
			 {
				 break; 
			 }
		 }
	
		 bre.close();
		 fw.close();
	}
	public static void main(String[] args) throws IOException
	{
		getFeatures("/home/navya/Downloads/mypersonality_final/ext_features" ,7) ;
		getFeatures("/home/navya/Downloads/mypersonality_final/neu_features" ,8) ;
		getFeatures("/home/navya/Downloads/mypersonality_final/agr_features" ,9) ;
		getFeatures("/home/navya/Downloads/mypersonality_final/con_features" ,10) ;
		getFeatures("/home/navya/Downloads/mypersonality_final/opn_features" ,11) ;
		
	}
	
}
