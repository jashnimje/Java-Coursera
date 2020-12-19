
/**
 * Write a description of class TestCaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
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
        
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        
        String breakCC = breakCaesarCipher(encrypted);
        System.out.println("Break CaesarCipher: " + breakCC);
    }

    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
}
