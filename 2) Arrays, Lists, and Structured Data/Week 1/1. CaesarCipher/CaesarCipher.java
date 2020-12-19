
/**
 * Write a description of class CaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypt = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)
            + alphabet.substring(0, key);
        for (int i = 0; i < encrypt.length(); i++) {
            char currChar = encrypt.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int index = alphabet.indexOf(currChar);
                if (index != -1) {
                    char newChar = shiftedAlphabet.charAt(index);
                    encrypt.setCharAt(i, newChar);
                }
            } else {
                char upperChar = Character.toUpperCase(currChar);
                int index = alphabet.indexOf(upperChar);
                if (index != -1) {
                    char newChar = shiftedAlphabet.charAt(index);
                    char lowerNewChar = Character.toLowerCase(newChar);
                    encrypt.setCharAt(i, lowerNewChar);
                }
            }
        }
        return encrypt.toString();
    }
    
    public String decrypt(String input, int key){
        String decrypt = encrypt(input, 26 - key);
        return decrypt;
    }

    public void testCaesar() {
        String message, encrypt, decrypt;
        int key;
        /*
        key = 17;
        FileResource fr = new FileResource();
        message = fr.asString();
        encrypt = encrypt(message, key);
        System.out.println(encrypt);
        decrypt = encrypt(encrypt, 26-key);
        System.out.println(decrypt);
         */

        key = 15;
        message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        encrypt = encrypt(message, key);
        System.out.println(encrypt);
        decrypt = encrypt(encrypt, 26-key);
        System.out.println(decrypt);
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        String key1Input = encrypt(input, key1);
        String key2Input = encrypt(input, key2);
        StringBuilder str = new StringBuilder(key1Input);
        for (int i=0; i<input.length(); i++) {
            if (i%2 != 0) {
                str.setCharAt(i, key2Input.charAt(i));
            }
        }
        return (str.toString());
    }

    public void testEncryptTwoKeys() {
        String enc; 
        enc = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8);
        System.out.println(enc);

        enc = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(enc);
    }
}
