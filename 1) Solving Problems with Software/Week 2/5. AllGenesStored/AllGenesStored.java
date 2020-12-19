
/**
 * Write a description of AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllGenesStored {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void testCode() {
        // TAA
        String str, result;
        str = "xxxyyyzzzATGxxxyyyzzzTAATAGTGA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // TAG
        str = "xxxyyyzzzATGxxxyyyzzzTAGTGATAA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // TGA
        str = "xxxyyyzzzATGxxxyyyzzzTGATAGTAA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public void testOn(String dna) {
        System.out.println("Test printAllGenes " + dna);
        printAllGenes(dna);
    }
    
    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
    }
}
