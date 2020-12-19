
/**
 * Write a description of class TestCaesar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
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

    public void testCountLetters(){
        FileResource resource = new FileResource("data/romeo.txt");
        //FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        String message = resource.asString();       

        int[] counts = countLetters(message);
        System.out.println("Most common length is " + maxIndex(counts));
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }

    public void testDecrypt(){
        //FileResource resource = new FileResource("data/smallHamlet.txt");
        FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        String message = resource.asString();

        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, 20);
        String decrypted = decrypt(encrypted);
        System.out.println(encrypted + "   " + decrypted);
    }

    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i < message.length(); i += 2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }

    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 0));
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return (26 - dkey);
    }

    public String decryptTwoKeys(String encrypted) {
        int firstKey = getKey(halfOfString(encrypted, 0));
        int secondKey = getKey(halfOfString(encrypted, 1));
        System.out.println("The decrypted keys are: " 
            + (26-firstKey) + " " + (26-secondKey));
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, firstKey, secondKey);
        return decrypted;
    }

    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        decryptTwoKeys(message);
        System.out.println("Encrypted message:\n" + message);
        System.out.println("\nDecrypted message:\n" + decryptTwoKeys(message));

        String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println("Encrypted message:\n" + encrypted);
        System.out.println("\nDecrypted message:\n" + decryptTwoKeys(encrypted));

        encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("Encrypted message:\n" + encrypted);
        System.out.println("\nDecrypted message:\n" + decryptTwoKeys(encrypted));
    }
}