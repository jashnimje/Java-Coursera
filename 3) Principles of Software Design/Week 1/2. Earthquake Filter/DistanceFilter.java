
public class DistanceFilter implements Filter {
    private Location location;
    private double distance;
    public DistanceFilter(Location location, double distance) {
        this.location = location;
        this.distance = distance;
    }

    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(location) < distance;
    }

    public String getName(){
        return "Distance";
    }
}
