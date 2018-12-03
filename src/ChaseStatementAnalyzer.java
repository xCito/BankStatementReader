/*
 * ASSUMPTIONS
 *   - The bank statement is from CHASE bank. 
 *   - The bank statement is in PDF format. 
 * 	 - The bank statement is in English. 	
 *   - The bank statement uses USD.
 *   - The PDF is an accessible document.
 *   - Pattern for each transaction follows:  
 *         MM/DD  Name_of_transaction_description 000.00
 * 	 - Transaction information is on 1 Line.
 * 
 *
 */


import java.io.File;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ChaseStatementAnalyzer {
	
	File file;
	PDDocument pdf;
	ArrayList<Transaction> allTrans;
	
	public ChaseStatementAnalyzer() {
		file = null;
		allTrans = new ArrayList<>();
	}
	public ChaseStatementAnalyzer( String filename ) {
		file = new File( filename );
		allTrans = new ArrayList<>();
	}
	
	public ChaseStatementAnalyzer( File f ) {
		file = f;
		allTrans = new ArrayList<>();
	}
	
	public boolean analyze() {
		// Find all transactions and hold them in list 
		// if analyzer was successful return true, else false.
		if(file == null)
			return false;
		
		try {
			PDDocument pdf = PDDocument.load( file );
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText( pdf );
			//System.out.println(text);
			
			regexSearch(text);
			
			pdf.close();
			return true;
			
		} catch (IOException e) {
			System.out.println("could not obtain pdf file.");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void regexSearch( String text ) {
		String date2Regex = "([A-Za-z]+)\\s[0-3][0-9],\\s(\\d{4})";
		String dateRegex = "(\\d\\d/\\d\\d)";
		String moneyRegex = "\\s\\$?([\\d,]+\\.\\d\\d)";
		
		Pattern yearPatt = Pattern.compile(date2Regex);
		Pattern tranPatt = Pattern.compile(dateRegex + "\\s(.*)" + moneyRegex);
		Matcher matchYear = yearPatt.matcher(text);
		Matcher matchTrans = tranPatt.matcher(text);
		
		String year = "2000";
		//int monthInt;
		
		if ( matchYear.find() ) {
			// System.out.println("->" + matchYear.group()  + "<-");
			// System.out.println("->" + matchYear.group(1) + "<-");
			// System.out.println("->" + matchYear.group(2) + "<-");

			year = matchYear.group(2);
		}
		
		System.out.println("Regex Result: ");
		while( matchTrans.find() ) {
			// System.out.println("--> " + matchTrans.group() + " <--");
			// System.out.println("\tDate: " + matchTrans.group(1));
			// System.out.println("\tName: " + matchTrans.group(2));
			// System.out.println("\tAmt : " + matchTrans.group(3));
			
			String monthDay = matchTrans.group(1);
			String tranName = matchTrans.group(2);
			Double amount  = Double.valueOf(matchTrans.group(3).replaceAll("[\\$,]" , ""));
			String type = "expense";
			
			
			String formattedDate = matchTrans.group(1) + "/" + year;
			//System.out.println("----?>" + formattedDate);
			LocalDate date = getLocalDateFromString( formattedDate );
			tranName = tranName.replaceAll("(Card\\sPurchase\\s)?(\\d\\d\\/\\d\\d\\s)?(With\\sPin\\s)?", "");	
			
			//System.out.println("--------->" + date.toString());
			allTrans.add( new Transaction( tranName, amount, date, type ));
		}
	}
	
	// Indexes		  01 34 6789
	// Assumed format MM/DD/YYYY
	public LocalDate getLocalDateFromString( String date )  {
		int month 	= Integer.valueOf(date.substring(0, 2));
		int day 	= Integer.valueOf(date.substring(3, 5));
		int year	= Integer.valueOf(date.substring(6));
		
		return LocalDate.of(year, month, day);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> getTransactions() {

		return (ArrayList<Transaction>) allTrans.clone();
	}
	
	public void setStatement(File f) {
		this.file = f;
		allTrans.clear();
	}
	public void setStatement(String f) {
		this.file = new File(f);
		allTrans.clear();
	}
	
}
