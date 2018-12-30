package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatchingTest {
	ArrayList<String> matchedGroups;
	
	public PatternMatchingTest() {
		matchedGroups = new ArrayList<String>();
	}
	
	
	public boolean match1(String regex, String input) {
		Pattern patt = Pattern.compile(regex);
		Matcher match = patt.matcher(input);
		matchedGroups.clear();
		
		while(match.find()) {
			int numGrps = match.groupCount();
			
			for(int i=1; i<=numGrps; ++i) 
				matchedGroups.add( match.group(i) );
			
			return true;
		}
		return false;
	}

	public String[] getGroups() {
		String[] groups = new String[matchedGroups.size()]; 
		groups = matchedGroups.toArray(groups);
		
		for(int i=0; i<groups.length; ++i) {
			groups[i] = groups[i].replaceAll("(\\s+|\\n)", " ");
		}
		
		return groups;
	}
	
	public static void main(String[] args) {
		String bankOfAmericaRegex = "(\\d{1,2}\\/\\d{1,2}\\/\\d{2})\\s((?:.*)\\n?|(?:.*)\\n?(?:.*)\\n?)\\s(-?[\\d,]+\\.\\d\\d)";
		String jpMorganChaseRegex = "(\\d\\d/\\d\\d)\\s(.*)\\s\\$?([\\d,]+\\.\\d\\d)";
		
		String[] inputs = {
			// Bank of america test inputs
			"11/30/18 CHECKCARD  1129 AMZN MKTP US*M03OS70U0 AMZN.COM/BILLWA\n" + "24431068333083312978570\n" + "-5.99",
			"11/30/18 CHECKCARD  1129 PAYPAL *CREATIVEBUG 402-935-7733 CA 24492158333894059152087 -1.00",
			"12/03/18 KEEP THE CHANGE TRANSFER TO ACCT 6889 FOR 12/03/18 -1.27",
			"12/04/18 CHECKCARD  1204 AMZN MKTP US*M00KB8HA2 AMZN.COM/BILLWA 24431068338083712163570" + "-10.00",
			"12/04/18 CHECKCARD  1203 CMSVEND*CV FARMINGDALE AMITYVILLE   NY 24445008338000622947645 -1.25",
			"12/04/18 BKOFAMERICA ATM 12/04 #000007926 WITHDRWL SOUTH BRONX CONC   SOUTH BRONX   NY -20.00",

			// Chase test inputs
			"09/07 Card Purchase 09/06 USA*Minute Key, Inc. Bronx NY Card 4545 $3.27", 
			"09/07 Card Purchase 09/06 Express Mini Market Bronx NY Card 4545 10.18",
			"09/10 Card Purchase With Pin 09/08 1040 Sherman Ave M Bronx NY Card 4545 16.10", 
			"09/10 Card Purchase 09/09 Uber *Trip Rkqdu 800-592-8996 CA Card 4545 5.00",
			"10/01 09/29 Payment To Chase Card Ending IN 9452 100.71",
			"09/25 Recurring Card Purchase 09/25 Playstationnetwork 800-3457669 CA Card 4545 59.99"
		};
		PatternMatchingTest test = new PatternMatchingTest();
		
		
		// BANK OF AMERICA 
		System.out.println("Bank of America Tests: ");
		for(String input: inputs) {
			System.out.println("\t" + test.match1(bankOfAmericaRegex, input));
			//Arrays.stream(test.getGroups()).forEach(x -> System.out.println("\t>"+x));
		}
		
		// JPMORGAN CHASE
		System.out.println("JP Morgan Chase Tests: ");
		for(String input: inputs) {
			System.out.println("\t" + test.match1(jpMorganChaseRegex, input));
			//Arrays.stream(test.getGroups()).forEach(x -> System.out.println("\t>"+x));
		}
	}
	
}
