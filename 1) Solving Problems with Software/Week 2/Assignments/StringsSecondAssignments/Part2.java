
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int currIndex = stringb.indexOf(stringa);
        while (currIndex != -1) {
            count += 1;
            currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
        }
        return count;
    }
    
    public void test() {
        int result;
        result = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("Count: " + result);
        
        result = howMany("AA", "ATAAAA");
        System.out.println("Count: " + result);
    }
}
