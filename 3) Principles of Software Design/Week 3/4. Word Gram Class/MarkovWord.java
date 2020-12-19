
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int value) {
        myRandom = new Random();
        myOrder = value;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - myOrder; i++) {
            WordGram word = new WordGram(words, i, myOrder);
            if (word.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int index = 0;
        while (true) {
            index = indexOf(myText, kGram, index);
            if (index == -1 || (index + myOrder) >= myText.length) {
                break;
            }
            follows.add(myText[index + myOrder]);
            index = index + myOrder;
        }
        return follows;
    }

    public String toString() {
        return "MarkovWord";
    }
}

