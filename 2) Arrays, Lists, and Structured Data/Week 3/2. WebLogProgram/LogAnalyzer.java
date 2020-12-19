import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String s: fr.lines()) {
            LogEntry newLogs = WebLogParser.parseEntry(s);
            records.add(newLogs);
        }
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le: records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>(); 
        ArrayList<String> uniqueIPsDates = new ArrayList<String>();
        for (LogEntry le: records) {
            String date = le.getAccessTime().toString();
            String subStr = date.substring(4,10); 
            String ipAddr = le.getIpAddress();
            if(subStr.equals(someday) && !uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
                uniqueIPsDates.add(subStr);
            }
        }
        return uniqueIPs;
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int count = 0;
        for (int num: counts.values()) {
            if (count < num) {
                count = num;
            }
        }
        return count;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCount) {
        int maxCount = 0;
        ArrayList<String> ipAddr = new ArrayList<String>();
        int maxVal = mostNumberVisitsByIP(ipCount);
        for (String s: ipCount.keySet()) {
            if (ipCount.get(s) == maxVal) {
                ipAddr.add(s);
            }
        }
        return ipAddr;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records) {
            ArrayList<String> ipAddr = new ArrayList<String>();
            String date = le.getAccessTime().toString();
            date = date.substring(4,10);
            String ip = le.getIpAddress();
            if (!ipDays.containsKey(date)) {
                ipAddr.add(ip);
                ipDays.put(date, ipAddr);
            } else {
                ipDays.get(date).add(ip);
            }
        }
        return ipDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int maxIps = 0;
        String day = "";
        for (String s: map.keySet()) {
            int val = map.get(s).size();
            if (maxIps < val) {
                maxIps = val;
                day = s;
            }
        }
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date) {
        HashMap<String, Integer> newMap = new HashMap<String, Integer>();
        
        for (String s: map.get(date)) {
            if(!newMap.containsKey(s)) {
                newMap.put(s,1);
            } else {
                newMap.put(s, newMap.get(s)+1);
            }
        }
        ArrayList<String> IPs = iPsMostVisits(newMap);
        return IPs;
    }
}
