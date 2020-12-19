
/**
 * Write a description of class CharactersInPlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*; 
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay() {
        charNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = charNames.indexOf(person);
        if (index == -1) {
            charNames.add(person);
            myFreqs.add(1);
        } else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value+1);
        }
    }

    public void  findAllCharacters() {
        FileResource resource = new FileResource();
        for (String s : resource.lines()) {
            int index = s.indexOf(".");
            if (index != -1) {
                String name = s.substring(0, index);
                update(name.trim());
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i=0; i<charNames.size(); i++) {
            if (myFreqs.get(i) >= num1 && num2 >= myFreqs.get(i)) {
                System.out.println(charNames.get(i));
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i=0; i<charNames.size(); i++) {
            if (myFreqs.get(i) > 1) {
                System.out.println(charNames.get(i) + 
                    " " + myFreqs.get(i));
            }
        }
        charactersWithNumParts(10, 15);
    }
}