
import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }
    
    public void testFindStopCodon() {
        String dna;
        int result;
        // DNA with genes
        dna = "xxxyyyzzzATGzzzxxxyyyTAA";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA Strands stop codon Index is: " + result);
        // DNA without genes
        dna = "xxxyyyzzzzzzxxxyyy";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA Strands stop codon Index is: " + result);
        // Other stopCodon
        dna = "xxxyyyzzzATGzzzxxxyyyTAATAG";
        result = findStopCodon(dna, 0, "TAG");
        System.out.println("DNA Strands stop codon Index is: " + result);
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
    
    public void testFindGene() {
        String str, result;
        // DNA with no "ATG"
        str = "xxxyyyzzzxxxyyyzzzTAATAGTGA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // DNA with "ATG"
        str = "xxxyyyzzzxxxATGyyyzzzTAAxxx";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // DNA with "ATG" and multiple valid stop codons
        str = "xxxyyyzzzATGxxxyyyzzzTAATAGTGA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        //  DNA with "ATG" and no valid stop codons
        str = "xxxyyyzzzxxxyyyzzz";
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int foundGenes = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            geneList.add(currGene);
            foundGenes=foundGenes+1;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        System.out.println("Found " + foundGenes + " genes");
        return geneList;
    }
    
    public void testOn(String dna) {
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public void testGetAllGenes() {
        String str;
        str = "xxxyyyzzzxxxyyyzzzTAATAGTGA";
        testOn(str);
        
        // DNA with "ATG"
        str = "xxxyyyzzzxxxATGyyyzzzTAAxxx";
        testOn(str);
        
        // DNA with "ATG" and multiple valid stop codons
        str = "xxxyyyzzzATGxxxyyyzzzTAATAGTGA";
        testOn(str);
        
        //  DNA with "ATG" and no valid stop codons
        str = "xxxyyyzzzxxxyyyzzz";
        testOn(str);
        
        // TGA
        str = "xxxyyyzzzATGxxxyyyzzzTGATAGTAA";
        testOn(str);
    }
    
    public float cgRatio(String dna) {
        int count = dna.length() - dna.replace("C", "").length();
        count = count + dna.length() - dna.replace("G", "").length();
        //System.out.println("The Count is " + count);
        float ratio = (float)count/dna.length();
        //System.out.println(ratio);
        return ratio;
    }
    
    public void testCgRatio() {
        String str;
        double answer;
        str = "ATCCATGCCGGACTAGTAA";
        answer = cgRatio(str);
        System.out.println("CG Ratio: " + answer);
    }
    
    public int countCTG(String dna) {
        int code = dna.indexOf("CTG");
        int count = 0;
        while (code != -1) {
            count++;
            code = dna.indexOf("CTG", code+3);
        }
        return count;
    }
    
    public void testCountCTG() {
        String str;
        int answer;
        str = "ATCCATGCTGCTGGACTAGTAA";
        answer = countCTG(str);
        System.out.println("CTG count: " + answer);
    }
    
    public void processGenes(StorageResource sr) {
        int lcount = 0;
        int cgcount = 0;
        int maxLength = 0;
        int totalGene = 0;
        for (String g: sr.data()) {
            g = findGene(g, 0);
            totalGene++;
            if (g.length() > 60) {
                //System.out.println("Strings longer than 60: " + g);
                lcount++;
            }
            if (cgRatio(g) > 0.35) {
                //System.out.println("Strings with cgRatio > 0.35: " + cgRatio(g));
                cgcount++;
            }
            if (g.length() > maxLength) {
                maxLength = g.length();
            }
        }
        
        System.out.println("Total Genes: " + totalGene);
        System.out.println("Count of Strings > 60: " + lcount);
        System.out.println("Count of Strings with cgRatio > 0.35: " + cgcount);
        System.out.println("Max Length: " + maxLength);
    }
    
    public void testProcessGenes() {
        // genes longer than 9 characters
        StorageResource genes = new StorageResource();
        genes.add("ATGAGCGCAGTATAG"); // 15
        genes.add("ATGGCAGTATAA"); // 12
        
        // no genes longer than 9 characters
        genes.add("AAATGTAGGG"); // 6
        genes.add("AAATGACGTGAGG"); // 9
        
        // C-G-ratio is higher than 0.35
        genes.add("ATCCATGCCGGACTAGTAA"); //0.5833333333333333
        
        // C-G-ratio is lower than 0.35
        genes.add("ATCCATGxxxyyyzzCTAA"); //0.1333333333333333
        
        processGenes(genes);
    }
    
    
    // Real Data DNA
    public void testOnRealData(String dna) {
        System.out.println("CTG count is " + countCTG(dna));
        //cgRatio(dna);
        System.out.println("Testing process AllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
    }
    
    public void realData() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        testOnRealData(dna);
    }
}
