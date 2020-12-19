
/**
 * Write a description of findGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class findGeneWhile {
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex+3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return dna.substring(startIndex, currIndex + 3);
            }
            else {
                currIndex = dna.indexOf("TAA", currIndex + 1);                
            }
        }
        return "";
    }
    
    public void testing() {
        //No Multiple of 3
        String str1, result;
        str1 = "ATGCCGATAA";
        result = findGene(str1);
        System.out.println("Extracted String: " + result);
        
        // Multiple of 3
        str1 = "ATGCCAGAGTAAA";
        result = findGene(str1);
        System.out.println("Extracted String: " + result);
        
        // Multiple of 3 after 1 TAA
        str1 = "ATGCCGATAADATAACD";
        result = findGene(str1);
        System.out.println("Extracted String: " + result);
    }
}
