import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1Info = q1.getInfo();
        String q2Info = q2.getInfo();
        int infoTest = q1Info.compareTo(q2Info);
        if (infoTest != 0) {
            return infoTest;
        } else {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
    }
}
