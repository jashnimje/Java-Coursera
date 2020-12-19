
/**
 * Write a description of class CaesarCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder encrypt = new StringBuilder(input);
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

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypt = cc.encrypt(input);
        return decrypt;
    }
}
