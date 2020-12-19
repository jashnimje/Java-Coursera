import edu.duke.*;

public class Tester {
    public void testCC() {
        CaesarCipher cc = new CaesarCipher(12);
        String encrypt = cc.encrypt("Hello World!");
        String decrypt = cc.decrypt(encrypt);
        System.out.println(encrypt);
        System.out.println(decrypt);
    }

    public void testCCFile() {
        CaesarCipher cc = new CaesarCipher(12);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }

    public void testCCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = cc.getKey(message);
        System.out.println(key);
        String decrypt = cc.decrypt(message);
        System.out.println(decrypt);
    }

    public void testVC() {
        int[] key = {2, 4, 6, 8, 10, 12};
        VigenereCipher vc = new VigenereCipher(key);
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "OLDISGOLD";
        System.out.println(key);
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }

    public void testVB() {
        VigenereBreaker vb = new VigenereBreaker();
        String ans;
        ans = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println(ans);
        ans = vb.sliceString("abcdefghijklm", 1, 3);
        System.out.println(ans);
        ans = vb.sliceString("abcdefghijklm", 2, 3);
        System.out.println(ans);
        ans = vb.sliceString("abcdefghijklm", 0, 4);
        System.out.println(ans);
        ans = vb.sliceString("abcdefghijklm", 1, 4);
        System.out.println(ans);
    }
    
    public void testKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] key = vb.tryKeyLength(message, 4, 'e');
        for (int i=0; i<key.length; i++) {
            System.out.println(key[i]);
        }
    }
}
