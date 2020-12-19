import edu.duke.*;
import java.io.File;
import java.util.*;

public class WordsInFiles {
    private HashMap <String, ArrayList> hashWords;
    public WordsInFiles() {
        hashWords = new HashMap <String, ArrayList>();
    }

    public void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
            if (!hashWords.containsKey(word)) {
                ArrayList<String> file = new ArrayList<String>();
                file.add(f.getName());
                hashWords.put(word, file);
            } else {
                ArrayList<String> file = new ArrayList<String>();
                file = hashWords.get(word);
                if (!file.contains(f.getName())) {
                    file.add(f.getName());
                }
            }
        }
    }

    public void buildWordFileMap() {
        hashWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int maxSize = 0;
        for (ArrayList s: hashWords.values()) {
            if (s.size() > maxSize) {
                maxSize = s.size();
            }
        }
        return maxSize;
    }

    public ArrayList wordsInNumFiles(int number) {
        System.out.println("\nThis words appear " + number + " times: ");
        ArrayList<String> words = new ArrayList<String>();
        int counting = 0;
        for (String word: hashWords.keySet()) {
            int counts = hashWords.get(word).size();
            if (counts == number) {
                words.add(word);
                counting++;
            }
        }
        System.out.println("total of words repeated " + number + " times: " + counting);
        return words;
    }

    public void printFilesIn(String word) {
        System.out.println("\nThe word " + word + " is in the following files: ");
        for (String s: hashWords.keySet()) {
            if (s.equals(word)) {
                ArrayList wordAndFiles = hashWords.get(s);
                for (int i=0; i < wordAndFiles.size(); i++){
                    System.out.println(wordAndFiles.get(i));
                }
            }
        }
    }

    public void tester(){
        buildWordFileMap();
        ArrayList wordsInNumFiles = wordsInNumFiles(4);
        for (int i=0; i < wordsInNumFiles.size(); i++){
            System.out.println(wordsInNumFiles.get(i));
        }
        System.out.println("\nMaximum number of words in all the files given = " +maxNumber());
        printFilesIn("tree");
        System.out.println("\n");
        for (String s :hashWords.keySet() ){
            //System.out.println(s + hashWords.get(s) );
        }
    }
}