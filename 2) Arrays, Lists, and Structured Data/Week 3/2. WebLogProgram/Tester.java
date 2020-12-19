import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs.");
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPsInRange(400,499));
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.countUniqueIPs();
        ArrayList a = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(a.size());
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        int count = la.mostNumberVisitsByIP(counts);
        System.out.println("The most visited count is " + count);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        ArrayList<String> ipAddr = la.iPsMostVisits(counts);
        System.out.println("These are the ip's with more visits: ");
        System.out.println(ipAddr);
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2-short_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        HashMap<String,ArrayList<String>> daysIps = la.iPsForDays();
        System.out.println(daysIps);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        HashMap<String,ArrayList<String>> daysIps = la.iPsForDays();
        System.out.println(daysIps);
        System.out.println("Day with most different IP visits: " + la.dayWithMostIPVisits(daysIps));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        HashMap<String,ArrayList<String>> daysIps = la.iPsForDays();
        System.out.println(daysIps);
        System.out.println(la.iPsWithMostVisitsOnDay(daysIps, "Sep 29"));
    }
}
