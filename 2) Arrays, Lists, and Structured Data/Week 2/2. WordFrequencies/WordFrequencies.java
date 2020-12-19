
/**
 * Write a description of class WordFrequencies here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*; 
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (!myWords.contains(s)) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }

    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int i=0; i<myFreqs.size(); i++) {
            if (max < myFreqs.get(i)) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
/*
        for (int i=0; i<myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
*/
        int max = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "
            + myWords.get(max) + " " + myFreqs.get(max));
    }
}
