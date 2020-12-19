
public class PhraseFilter implements Filter{
    private String whereToSearch;
    private String whatToSearch;
    public PhraseFilter(String where, String what){
        this.whereToSearch = where;
        this.whatToSearch = what;
    }

    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        switch (whereToSearch) {
            case "start":
            if (title.startsWith(whatToSearch)) {
                return true;
            }
            break;
            case "end":
            if (title.endsWith(whatToSearch)) {
                return true;
            }
            break;
            case "any":
            if (title.contains(whatToSearch)) {
                return true;
            }
            break;
        }
        return false;
    }

    public String getName(){
        return "Phrase";
    }
}