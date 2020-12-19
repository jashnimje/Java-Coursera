
/**
 * Write a description of class WordLengths here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int letterCount = 0;
            for (int index=0; index < word.length(); index++) {
                if ((index == 0 || index == (word.length()-1)) && 
                !Character.isLetter(word.charAt(index))) {
                    continue;
                }
                letterCount++;
            }
            if (letterCount >= counts.length) {
                counts[counts.length] += 1;
            }
            else {
                counts[letterCount] += 1;
            }
        }
    }

    public void  testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int k=1; k < counts.length; k++) {
            System.out.println("Number of " + k + "-letter words\t" + counts[k]);
        }
        System.out.println("Most common word length is " + indexOfMax(counts) +
            " letters");
    }

    public int indexOfMax(int[] values) {
        int indexOfMax = 0;
        for (int i=0; i<values.length; i++) {
            if (values[i] > values[indexOfMax]) {
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }
}
