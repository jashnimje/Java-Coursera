
/**
 * Write a description of class CaesarCipherTwo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    // instance variables - replace the example below with your own
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int k1;
    private int k2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        k1 = key1;
        k2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encrypt = new StringBuilder(input);
        for (int i = 0; i < encrypt.length(); i += 2) {
            char currChar = encrypt.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int index = alphabet.indexOf(currChar);
                if (index != -1) {
                    char newChar = shiftedAlphabet1.charAt(index);
                    encrypt.setCharAt(i, newChar);
                }
            } else {
                char upperChar = Character.toUpperCase(currChar);
                int index = alphabet.indexOf(upperChar);
                if (index != -1) {
                    char newChar = shiftedAlphabet1.charAt(index);
                    char lowerNewChar = Character.toLowerCase(newChar);
                    encrypt.setCharAt(i, lowerNewChar);
                }
            }
        }

        for (int i = 1; i < encrypt.length(); i += 2) {
            char currChar = encrypt.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int index = alphabet.indexOf(currChar);
                if (index != -1) {
                    char newChar = shiftedAlphabet2.charAt(index);
                    encrypt.setCharAt(i, newChar);
                }
            } else {
                char upperChar = Character.toUpperCase(currChar);
                int index = alphabet.indexOf(upperChar);
                if (index != -1) {
                    char newChar = shiftedAlphabet2.charAt(index);
                    char lowerNewChar = Character.toLowerCase(newChar);
                    encrypt.setCharAt(i, lowerNewChar);
                }
            }
        }
        return (encrypt.toString());
    }

    public String decrypt(String input){
        CaesarCipherTwo a = new CaesarCipherTwo(26 - k1, 26 - k2);
        return a.encrypt(input);
    }
}
