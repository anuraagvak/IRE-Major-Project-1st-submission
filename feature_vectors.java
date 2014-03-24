import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class feature_vectors {
	public static ArrayList<String> ext= new ArrayList<String>();
	//public static ArrayList<String>  neu= new ArrayList<String>();
	//public static ArrayList<String> agr= new ArrayList<String>();
	//public static ArrayList<String> con= new ArrayList<String>();
	//public static ArrayList<String> opn= new ArrayList<String>();
	public static void  getfeature_vectors(String filename ) throws IOException
	{
		 ext= new ArrayList<String>();
	//FileReader fr= new FileReader("/home/navya/Downloads/mypersonality_final/extrovert_features");
	FileReader fr= new FileReader(filename);

	BufferedReader br= new BufferedReader(fr);
	String currentline="";
	while ((currentline=br.readLine())!=null)
	{
		if(!ext.contains(currentline))
		ext.add(currentline);
		//System.out.println(currentline);
	}
	br.close();
	fr.close();
	/*fr= new FileReader("/home/navya/Downloads/mypersonality_final/neurotic_features");
	 br= new BufferedReader(fr);
	  currentline="";
		while ((currentline=br.readLine())!=null)
		{
			if(!ext.contains(currentline))
			ext.add(currentline);
			//System.out.println(currentline);
		}
		br.close();
		fr.close();
		
		fr= new FileReader("/home/navya/Downloads/mypersonality_final/agr_features");
		 br= new BufferedReader(fr);
		  currentline="";
			while ((currentline=br.readLine())!=null)
			{
				if(!ext.contains(currentline))
				ext.add(currentline);
				//System.out.println(currentline);
			}
			br.close();
			fr.close();
			fr= new FileReader("/home/navya/Downloads/mypersonality_final/con_features");
			 br= new BufferedReader(fr);
			  currentline="";
				while ((currentline=br.readLine())!=null)
				{
					if(!ext.contains(currentline))
					ext.add(currentline);
					//System.out.println(currentline);
				}
				br.close();
				fr.close();
				fr= new FileReader("/home/navya/Downloads/mypersonality_final/opn_features");
				 br= new BufferedReader(fr);
				  currentline="";
					while ((currentline=br.readLine())!=null)
					{
						if(!ext.contains(currentline))
						ext.add(currentline);
					//	System.out.println(currentline);
					}
					br.close();
					fr.close();*/
	}
	
	
	public static void testdata()
	{
		
	}
	public static void traindata( String train , String test, int index) throws IOException
	{
		//FileWriter fr = new FileWriter("/home/navya/Downloads/mypersonality_final/train_data.csv" , false);
		FileWriter fr = new FileWriter(train , false);
		 BufferedWriter br = new BufferedWriter(fr);
		 //br.write(ext.size());
		 //System.out.println(ext.size());
		/* br.write("\""+ext.get(0)+"\"") ;
		for(int i=1;i< ext.size();i++)
		{
			//System.out.println(","+"\""+ext.get(i)+"\"");
			br.write(","+"\""+ext.get(i)+"\"") ; 
		}
		br.newLine();*/
		FileReader fr2= new FileReader("/home/navya/Downloads/mypersonality_final/mypersonality_final.csv");
		BufferedReader br2= new BufferedReader(fr2);
		String currentline="";
		currentline=br2.readLine() ; 
		String cur_user="" ; 
	int testflag=0;
		HashMap<String , ArrayList<String>> users= new HashMap<String ,  ArrayList<String>> ();
		while ((currentline=br2.readLine())!=null)
		{
			
			
			
			
			//System.out.println(currentline);
			String[] tokens= currentline.split(",");
			String[] words = tokens[1].split(" ") ;
			if(users.get(tokens[0]) ==null)
			{
				 ArrayList<String> words_user= new ArrayList<String> ();
				 String label;
				 if(tokens[index].equals("\"y\""))
				 {
					 label="1";
				 }
				 else 
					 label="0";
				 words_user.add(label); 
				 for (int i=0; i< words.length ; i++)
				 {
					 words_user.add(words[i]);
					 
				 }
				users.put(tokens[0],  words_user) ; 
				
			}
			else
			{

				 ArrayList<String> words_user = users.get(tokens[0]);
				 for (int i=0; i< words.length ; i++)
				 {
					
			//System.out.println(words[i]);		 
words_user.add(words[i])	;				 
				 }
				 users.put(tokens[0],  words_user) ; 
			}
			
		}
		
		 int tot=0;
		 int flags=1;
		 for (Entry<String, ArrayList<String>> entry : users.entrySet())
		 {
			tot++;
				HashMap<String , Integer> count= new HashMap<String , Integer> ();
			for (int it=1 ; it < (entry.getValue()).size() ; it++)
			{
				if(count.get(entry.getValue().get(it))==null)
				{
					count.put(entry.getValue().get(it),1);
					
				}
				else
				{
					count.put(entry.getValue().get(it),count.get(entry.getValue().get(it))+1);
				}
				
			}
			String line="";
			if(testflag==0)
			{
			 line=entry.getValue().get(0)+" ";
			}
			else 
			{
				  line="";
			}
			for(int it=0;it< ext.size(); it++)
			{
				if (count.get(ext.get(it))!=null)
				{
					if(it== ext.size()-1)
					{
					line = line + (it+1)+":"+ count.get(ext.get(it));
					}
					else
					{
						line = line + (it+1)+":"+ count.get(ext.get(it))+" ";
					}
					
				}
				else
				{
					if(it== ext.size()-1)
					{
					line = line + (it+1)+":"+ 0;
					}
					else
					{
						line = line + (it+1)+":"+ 0 +" ";
					}
			//		tot++;
					//line = line + "\""+ 0 +"\""+",";
				}
			}
			
			System.out.println(tot);
			
			br.write(line);
			br.newLine();	
			if(tot> 180 && flags==1)
			{
				//testflag=1;
				br.close();
				fr.close();
				 //fr = new FileWriter("/home/navya/Downloads/mypersonality_final/test_data.csv" , false);
				 fr = new FileWriter(test , false);
				  br = new BufferedWriter(fr);
				  flags=0;
				  /*br.write("\""+ext.get(0)+"\"") ;
					for(int i=1;i< ext.size();i++)
					{
						//System.out.println(","+"\""+ext.get(i)+"\"");
						br.write(","+"\""+ext.get(i)+"\"") ; 
					}
					br.newLine();*/
			}
		 }
		br2.close();
		fr2.close();
		br.close();
		fr.close();
		
		
	}
	public static void main(String[] args) throws IOException
	{
		getfeature_vectors("/home/navya/Downloads/mypersonality_final/ext_features");
		traindata( "/home/navya/Downloads/mypersonality_final/ext_traindata","/home/navya/Downloads/mypersonality_final/ext_testdata" ,7);
		
	getfeature_vectors("/home/navya/Downloads/mypersonality_final/neu_features");
	traindata( "/home/navya/Downloads/mypersonality_final/neu_traindata","/home/navya/Downloads/mypersonality_final/neu_testdata" ,8);
	getfeature_vectors("/home/navya/Downloads/mypersonality_final/agr_features");
	traindata( "/home/navya/Downloads/mypersonality_final/agr_traindata","/home/navya/Downloads/mypersonality_final/agr_testdata" ,9);
	getfeature_vectors("/home/navya/Downloads/mypersonality_final/con_features");
	traindata( "/home/navya/Downloads/mypersonality_final/con_traindata","/home/navya/Downloads/mypersonality_final/con_testdata" ,10);
	getfeature_vectors("/home/navya/Downloads/mypersonality_final/opn_features");
	traindata( "/home/navya/Downloads/mypersonality_final/opn_traindata","/home/navya/Downloads/mypersonality_final/opn_testdata" ,11);
	
	}

}
