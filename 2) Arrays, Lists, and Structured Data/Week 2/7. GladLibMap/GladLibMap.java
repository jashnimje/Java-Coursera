import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> words;
    private Random myRandom;
    private int numOfReplace;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        words = new ArrayList<String>();
        numOfReplace = 0;
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        numOfReplace = 0;
    }

    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String s: labels){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));

        int index = words.indexOf(sub);
        if(index ==-1){
            words.add(sub);
        }
        else{
            sub = getSubstitute(w.substring(first+1,last));
        }
        numOfReplace += 1;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println("\n# Replace: " + numOfReplace);
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap(){
        int size = 0;
        for(ArrayList v : myMap.values()){
            size = size + v.size();
        }
        return size; 
    }

    public void makeStory(){
        words.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n" + "Number of words replaced: " + words.size());
        totalWordsInMap();
        System.out.println("Number of words in all the Arraylists in the HashMap = " + totalWordsInMap());
    }
}

