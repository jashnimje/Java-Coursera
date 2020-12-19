
import java.util.*;
import edu.duke.*;

public class LargestQuakes {
    private int indexOfLargest(ArrayList<QuakeEntry> data){
        int index = -1;
        double max = 0;
        for(QuakeEntry qe : data){
            double mag = qe.getMagnitude();
            if(max < mag){
                max = mag;
                index = data.indexOf(qe);
            }
        }
        return index;
    }

    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(int i = 0; i<howMany; i++) {
            int index = indexOfLargest(quakeDataCopy);

            if(index == -1) { //convert to try/catch block
                break;
            }

            QuakeEntry quake = quakeDataCopy.get(index);
            answer.add(quake);
            quakeDataCopy.remove(quake);

        }
        return answer;
    }

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());

        /*
        //Step 1
        for(QuakeEntry qe : list){
        System.out.println(qe);
        }
         */

        //Step 2
        int maxIndex = indexOfLargest(list);
        System.out.println( "Index of largest quake is: " + maxIndex + 
            " and has magnitude " + list.get(maxIndex).getMagnitude() );

        //Step 3
        int howMany = 50;
        ArrayList<QuakeEntry> large = getLargest(list, howMany);
        for(QuakeEntry qe : large){
            System.out.println(qe);
        }
        System.out.println("Total number of earthquakes found: "+list.size());
    }
}
