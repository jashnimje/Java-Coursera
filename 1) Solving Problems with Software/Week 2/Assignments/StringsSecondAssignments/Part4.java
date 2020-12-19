
/**
 * Write a description of class Part4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
    public void findUrls(String url) {
        URLResource myurl = new URLResource(url);
        for(String word : myurl.words()) {
            if(word.toLowerCase().indexOf("youtube.com") != -1) {
                int firstIndex = word.indexOf("\"");
                int lastIndex = word.indexOf("\"", firstIndex+1);
                System.out.println(word.substring(firstIndex+1, lastIndex));
                
            }
        }
    }
    
    public void testUrl() {
        findUrls("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
