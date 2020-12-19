
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public static boolean twoOccurrences(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        int second = stringb.indexOf(stringa, first + stringa.length());
        if (second != -1)
        {
            return true;
        }
        return false;
    }
    
    public String lastPart(String stringa, String stringb) {
        int firstOc = stringb.indexOf(stringa);
        int str1length = stringa.length();
        int str2length = stringb.length();
        if (firstOc != -1) {
            String remainString = stringb.substring(firstOc+str1length, str2length);
            return remainString;
        }
        return stringb;
    }
    
    public void testing() {
        //test twoOccurrences
        String str1 = "Banana";
        boolean result1 = twoOccurrences("na", str1);
        System.out.println("String repeated atleast twice: " + result1);
        
        String str2 = "ctgtatgta";
        boolean result2 = twoOccurrences("atg", str1);
        System.out.println("String repeated atleast twice: " + result2);
        
        //test lastPart
        String stra, strb, result;
        stra = "an"; 
        strb = "Banana";
        result = lastPart(stra, strb);
        System.out.println("The part of the string after "+ stra + " in "+ strb + " is " + result);
        
        stra = "zoo";
        strb = "forest";
        result = lastPart(stra, strb);
        System.out.println("The part of the string after "+ stra + " in "+ strb + " is " + result);
        
        
        
    }
    
    
}
