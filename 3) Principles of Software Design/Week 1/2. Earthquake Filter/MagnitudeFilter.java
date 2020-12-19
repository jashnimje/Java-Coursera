
public class MagnitudeFilter implements Filter {
    private double minMagnitude;
    private double maxMagnitude;
    public MagnitudeFilter(double minMag, double maxMag) {
        this.minMagnitude = minMag;
        this.maxMagnitude = maxMag;
    }

    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= minMagnitude &&
        qe.getMagnitude() <= maxMagnitude;
    }

    public String getName(){
        return "Magnitude";
    }
}
