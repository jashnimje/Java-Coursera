
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int markovOrder;
    private HashMap<String,ArrayList<String>> hashMap;

    public EfficientMarkovModel(int n) {
        markovOrder = n;
        myRandom = new Random();
        myText = "";
        hashMap = new HashMap<String, ArrayList<String>> ();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-markovOrder);
        String key = myText.substring(index, index+markovOrder);
        sb.append(key);
        for(int k=0; k < numChars-markovOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows == null || follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(next.length()) + next;
        }

        return sb.toString();
    }

    public void buildMap() {
        hashMap.clear();
        for (int k = 0; k <= myText.length() - markovOrder; k++) {
            int subEnd = k + markovOrder;
            String sub = myText.substring(k, subEnd);
            if (!hashMap.containsKey(sub)) {
                hashMap.put(sub, new ArrayList<String>());
            }
            // Add to HashMap the character that follows current substring, if there is one
            if (subEnd < myText.length()) {
                String follower = myText.substring(subEnd, subEnd + 1);
                //System.out.println(sub + ": " + follower);
                ArrayList<String> followers = hashMap.get(sub);
                followers.add(follower);
                hashMap.put(sub, followers);
            }
        }
    }

    public void printHashMapInfo () {
        //System.out.println("Hashmap: " + "\n" + hashMap);
        System.out.println("Number of keys: " + hashMap.size());
        int largestSize = 0;
        for (String key : hashMap.keySet()) {
            int keySize = hashMap.get(key).size();
            if (keySize > largestSize) {
                largestSize = keySize;
            }
        }
        System.out.println("The size of the largest ArrayList of characters: " + largestSize);
        System.out.println("The keys that have the maximum size value:");
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).size() == largestSize) {
                System.out.println(key);
            }
        }
        System.out.println("\n");
    }

    public ArrayList<String> getFollows (String key) {
        return hashMap.get(key);
    }

    public String toString() {
        return "EfficientMarkovModel class of order " + markovOrder + ".";
    }
}
