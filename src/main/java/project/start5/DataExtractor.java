package project.start5;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
 
public class DataExtractor {

    public static List<String> fileToList(File file) throws IOException{ 
        //Path path = Paths.get(file.getAbsolutePath());
        return Files.readAllLines(file.toPath()); 
    }
    
    public static String fileToString(String filePath) throws IOException {
    	Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        String result = "";
        for (String line : lines) {
            result += " " + line + "\n"; 
        }
        return result; 
    }
}