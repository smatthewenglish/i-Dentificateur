

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class SearchDirectories 
{ 

    
    static void listDirectoryAndFiles( Path path , ArrayList<Path> dictionary_files ) throws IOException
    {

        DirectoryStream<Path> dirStream = Files.newDirectoryStream( path );
        for ( Path p : dirStream )
        {
            //System.out.println( p.getFileName() );
            if ( p.toFile().isDirectory() )
            {
                listDirectoryAndFiles( p, dictionary_files );
            }
            else
            {
            	dictionary_files.add( p );
            }
        }
    }
}
