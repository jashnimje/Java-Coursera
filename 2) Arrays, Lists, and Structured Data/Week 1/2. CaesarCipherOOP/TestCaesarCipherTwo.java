
/**
 * Write a description of class TestCaesarCipherTwo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i < message.length(); i += 2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i=0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k=0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();

        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
