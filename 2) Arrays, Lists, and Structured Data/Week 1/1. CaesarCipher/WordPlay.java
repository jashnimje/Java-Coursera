
/**
 * Write a description of class WordPlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        String vowel = "AEIOUaieou";
        for (int i=0; i<vowel.length(); i++) {
            if (ch == vowel.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    
    public void testVowel() {
        System.out.println(isVowel('A'));
        System.out.println(isVowel('a'));
        System.out.println(isVowel('f'));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder str = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            if (isVowel(phrase.charAt(i)) == true) {
                str.setCharAt(i, ch);
            }
        }
        return (str.toString());
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Oello World", '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder str = new StringBuilder(phrase);
        for (int i=0; i<phrase.length(); i++) {
            if (phrase.charAt(i) == Character.toLowerCase(ch)
            || phrase.charAt(i) == Character.toUpperCase(ch)) {
                if (i%2 == 0) {
                    str.setCharAt(i, '*');
                } else {
                    str.setCharAt(i, '+');
                }
            }
        }
        return (str.toString());
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
