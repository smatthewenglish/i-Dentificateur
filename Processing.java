

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Processing 
{
	static double falsePositiveProbability = 0.1;
	static int expectedSize = 500000;
	
	//GERMAN
	static BloomFilter<String> de_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	//ENGLISH
	static BloomFilter<String> eng_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	//FRENCH
	static BloomFilter<String> fr_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	//SPANISH
	static BloomFilter<String> es_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	//ITALIAN
	static BloomFilter<String> it_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	//PORTUGESE
	static BloomFilter<String> pt_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	//DUTCH
	static BloomFilter<String> nl_bloomFilter = new BloomFilter<String>(falsePositiveProbability, expectedSize);
	
	public static void process_input( List<String> input_text ) throws URISyntaxException, IOException
    {
        
        //THIS SEGMENT IS FOR DYNAMICALLY LOCATING THE DIRECTORY, SO THE PROGRAM WORKS "OUT OF THE BOX"
/*******************************************************************************************************************************************/
		//this holds all the dictionary files, i.e. word lists garners from language folders
        ArrayList<Path> dictionary_files = new ArrayList<Path>();
		
		File currentDir = new File(".");
	    
	    File targetDir = new File( currentDir, "ascii_word_lists" ); 
	    //File targetDir = new File( currentDir, "unicode_word_lists" ); 

	    if (targetDir.exists()) 
	    {
	    	SearchDirectories.listDirectoryAndFiles( targetDir.toPath(), dictionary_files );
	    }

/*******************************************************************************************************************************************/
       
        //this populates word presence data structs for each language
        for(Path dir : dictionary_files)
        {
        	
        	String word_holding_directory_path = dir.toString().toLowerCase();
        	
        	
        	
        	//BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream( dir.toString() ), "UTF-16LE") );
        	
            BufferedReader br = new BufferedReader( new FileReader( dir.toString() ) );
            String line = null;
            
            BloomFilter<String> bloomFilter;
            if (word_holding_directory_path.toLowerCase().contains("/de/")) 
            {
            	bloomFilter = de_bloomFilter;
            } 
            else if (word_holding_directory_path.toLowerCase().contains("/eng/")) 
            {
            	bloomFilter = eng_bloomFilter;
            } 
            else if (word_holding_directory_path.toLowerCase().contains("/fr/")) 
            {
            	bloomFilter = fr_bloomFilter;
            } 
            else if (word_holding_directory_path.toLowerCase().contains("/es/")) 
            {
            	bloomFilter = es_bloomFilter;
            } 
            else if (word_holding_directory_path.toLowerCase().contains("/it/")) 
            {
            	bloomFilter = it_bloomFilter;
            } 
            else if (word_holding_directory_path.toLowerCase().contains("/nl/")) 
            {
            	bloomFilter = nl_bloomFilter;
            } 
            else if (word_holding_directory_path.toLowerCase().contains("/pt/")) 
            {
            	bloomFilter = pt_bloomFilter;
            } 
            else 
            {
                continue;
            }

            while ( ( line = br.readLine() ) != null ) 
            {
            	bloomFilter.add( line.toLowerCase().trim() );
            }
            
        }

       
        Map<String, BloomFilter<String>> langMaps = new HashMap<>();
        langMaps.put( "Italiano, (Italian)", it_bloomFilter);
        langMaps.put( "Français, (French)", fr_bloomFilter);
        langMaps.put( "English, (English)", eng_bloomFilter);
        langMaps.put( "Deutsch, (German)", de_bloomFilter);
        langMaps.put( "Español, (Spanish)", es_bloomFilter);
        langMaps.put( "Nederlandse, (Dutch)", nl_bloomFilter);
        langMaps.put( "Português, (Portugese)", pt_bloomFilter);

 
        int maxCount = 0;
        String maxLang = null;

        for (Map.Entry<String, BloomFilter<String>> entry : langMaps.entrySet()) 
        {
            int count = 0;
            BloomFilter<String> words = entry.getValue();

            for (String word : input_text) 
            {
                String normalized = word.trim().toLowerCase();
                if (words.contains(normalized)) 
                {
                    ++count;
                }
            }
            
            if (count > maxCount) 
            {
                maxLang = entry.getKey();
                maxCount = count;
            }
        }

        //System.out.println( "Language is: " + maxLang );
        JOptionPane.showMessageDialog(null,"Language is: " + maxLang);

        
        
        
    }
}
