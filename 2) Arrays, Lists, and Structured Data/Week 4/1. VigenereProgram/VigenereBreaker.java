import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder str = new StringBuilder(message);
        String ans = "";
        for (int i=whichSlice; i<message.length(); i+=totalSlices) {
            ans += str.charAt(i);
        }
        return ans;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for (int i=0; i<klength; i++) {
            String sl = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sl);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource dict = new FileResource(f);
            System.out.println("Loading " + f.getName() + " dictionary.");
            dictionaries.put(f.getName(), readDictionary(dict));
        }
        breakForAllLangs(message, dictionaries);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line: fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0, rightKey = 0;
        for (String word: words) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = 0;
        int[] rightKey = {};
        String correctDecrypt = "";
        char mostCommon = mostCommonCharIn(dictionary);
        for (int i=1; i<=100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > maxCount) {
                maxCount = count;
                correctDecrypt = decrypted;
                rightKey = key;
            }
        }
        System.out.println("The Key is: " + rightKey.length);
        System.out.println("This file contains " + maxCount + " valid words.");
        return correctDecrypt;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> characters = new HashMap<Character, Integer>();
        int maxCount = 0;
        char mostCommon = 'e';
        for (String word: dictionary) {
            for (int i=0; i<word.length(); i++) {
                if (!characters.containsKey(word.charAt(i))) {
                    characters.put(word.charAt(i), 1);
                } else {
                    characters.put(word.charAt(i), characters.get(word.charAt(i))+1);
                }
            }
        }
        for (char c : characters.keySet()) {
            if (characters.get(c) > maxCount) {
                maxCount = characters.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = 0;
        String bestDecrypt = null;
        String detectedLanguage = null;
        for (String s: languages.keySet()) {
            String decrypt = breakForLanguage(encrypted, languages.get(s));
            int count = countWords(decrypt, languages.get(s));
            if (maxCount < count) {
                maxCount = count;
                bestDecrypt = decrypt;
                detectedLanguage = s;
            }
        }
        System.out.println("Number of correct words detected is: " + maxCount);
        System.out.println("Detected Language is: " + detectedLanguage);
        System.out.println(bestDecrypt);
    }
}
 