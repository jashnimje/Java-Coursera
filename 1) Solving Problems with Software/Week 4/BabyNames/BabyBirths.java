
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1)
                + " Number " + rec.get(2));
            }
        }
    }
    
    public void totalBirths () {
        int totalBirths = 0, totalBoys = 0, totalGirls = 0;
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Boys Births = " + totalBoys);
        System.out.println("Total Girls Births = " + totalGirls);
    }
    
    public int getRank(int year, String name, String gender) {
        int rankCount = 0;
        String fname = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fname);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rankCount++;
                if (rec.get(0).equals(name)) {
                    return rankCount;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        int rankCount = 0;
        String fname = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fname);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rankCount += 1;
                if (rankCount == rank) {
                    return (rec.get(0));
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + 
        newName + " if she was born in " + newYear + ".");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int maxRank = 9999999, reqYear = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); 
            String fname = f.getName();
            int year = Integer.parseInt(fname.substring(3,7));
            int rank = getRank(year, name, gender);
            if ((rank != -1) && (maxRank > rank)) {
                maxRank = rank;
                reqYear = year;
            }
        }
        if (maxRank != 9999999 && reqYear != 0) {
            System.out.println("Maximum Rank: " + maxRank + " " + reqYear);
            return reqYear;
        }
        return -1;
    }
    
    public double getAverageRank(String name, String gender) {
        double avg = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); 
            String fname = f.getName();
            int year = Integer.parseInt(fname.substring(3,7));
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                avg += rank;
                count++;
            }
        }
        if ((avg != 0) && (count != 0)) {
            return avg/count;
        }
        return -1;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fname = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        //String fname = ("testing/yob" + year + "short.csv");
        FileResource fr = new FileResource(fname);
        int rankOf = getRank(year, name, gender);
        System.out.println("RankOf " + rankOf);
        int index = 0;
        int Sum = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                index++;
                if (index == rankOf) {
                    break;
                }
                Sum += Integer.parseInt(rec.get(2));
            }
        }
        return Sum;        
    }
    
    public void testGetRank() {
        /*
        //1st method
        int rank = getRank(1960, "Emily", "F");
        System.out.println("Rank is : " + rank);
        
        rank = getRank(1971, "Frank", "M");
        System.out.println("Rank is : " + rank);
        
        //2nd method
        String getNames = getName(1982, 450, "M");
        System.out.println("Name of this Rank is : " + getNames);
        
        getNames = getName(1980, 350, "F");
        System.out.println("Name of this Rank is : " + getNames);
        */
        //3rd method
        String name = "Isabella";
        whatIsNameInYear(name, 2012, 2014, "F");
        
        name = "Susan";
        whatIsNameInYear(name, 1972, 2014, "F");
        
        name = "Owen";
        whatIsNameInYear(name, 1974, 2014, "M");
        /*
        //4th method
        int highestRank = yearOfHighestRank("Mich", "M");
        System.out.println("Most popular rank is in Year " + highestRank);
        
        highestRank = yearOfHighestRank("Genevieve", "F");
        System.out.println("Most popular rank is in Year " + highestRank);
        
        
        //5th method
        double avgRank = getAverageRank("Robert", "M");
        System.out.println("Avg rank is " + avgRank);
        
        avgRank = getAverageRank("Susan", "F");
        System.out.println("Avg rank is " + avgRank);
        
       
        //6th method
        int birthHigher;
        birthHigher = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Total Births Ranked Higher " + birthHigher);
        
        birthHigher = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        System.out.println("Total Births Ranked Higher " + birthHigher);
        */
    }   
}
