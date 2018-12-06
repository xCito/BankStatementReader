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
	
	/**
	 * Constructor
	 * Initializes ChaseStatementAnalyzer object
	 */
	public ChaseStatementAnalyzer() {
		this.file = null;
		allTrans = new ArrayList<>();
	}
	
	/**
	 * Constructor
	 * Initializes ChaseStatementAnalyzer object
	 * @param filename - Full file name to a PDF Chase Bank 
	 *                   Statement
	 */
	public ChaseStatementAnalyzer( String filename ) {
		this.file = new File( filename );
		allTrans = new ArrayList<>();
	}
	
	/**
	 * Constructor
	 * Initializes ChaseStatementAnalyzer object
	 * @param file - File object containing reference to a PDF
	 *               Chase Bank Statement
	 */
	public ChaseStatementAnalyzer( File file ) {
		this.file = file;
		allTrans = new ArrayList<>();
	}
	
	// ----------------
	
	/**
	 * Using PDFBOX lib, loads PDF file to extract
	 * text as String. Collects matched String patterns
	 * using Regex. Creates Transaction objects using information 
	 * from matched Strings.
	 * @return True - File is readable
	 * 				  Text was extracted successfully
	 * 				  information may have been extracted
	 * 	       False- Not able to read file
	 */
	public boolean analyze() {
		// Find all transactions and hold them in list 
		// if analyzer was successful return true, else false.
		if(file == null)
			return false;
		
		try {
			PDDocument pdf = PDDocument.load( file );
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText( pdf );
			System.out.println(text);
			
			regexSearch(text);
			
			pdf.close();
			return true;
			
		} catch (IOException e) {
			System.out.println("could not obtain pdf file.");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Pattern matches for Date (Month,Day,Year). Pattern matches for a 
	 * transactions (MonthDay,Name,Amount). Creates Transaction object
	 * and add to 'allTrans' List Object. 
	 * @param text - All Text from a PDF Bank Statement
	 */
	private void regexSearch( String text ) {
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
			LocalDate date = getLocalDateFromString( formattedDate );
			tranName = tranName.replaceAll("(Card\\sPurchase\\s)?(\\d\\d\\/\\d\\d\\s)?(With\\sPin\\s)?", "");	

			allTrans.add( new Transaction( tranName, amount, date, type ));
		}
	}
	
	/**
	 * @param date - String that follows MM/DD/YYYY pattern
	 * @return LocalDate Object of date that follows MM/DD/YYYY pattern
	 * 
	 * COULD THROW EXCEPTIONS IF NOT CORRECT FORMAT
	 */

	public LocalDate getLocalDateFromString( String date )  {
		int month 	= Integer.valueOf(date.substring(0, 2));
		int day 	= Integer.valueOf(date.substring(3, 5));
		int year	= Integer.valueOf(date.substring(6));
		
		return LocalDate.of(year, month, day);
	}
	
	/**
	 * @return Shallow copy of ArrayList with transactions 
	 * extracted from last PDF analyzed. (Last time analyze() was called).
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> getTransactions() {

		return (ArrayList<Transaction>) allTrans.clone();
	}
	
	
	/**
	 * Sets a new Chase Bank Statement PDF and clears ArrayList
	 * of extracted transactions. 
	 * @param f - The Chase Bank Statement PDF
	 */
	public void setStatement(File f) {
		this.file = f;
		allTrans.clear();
	}
	
	/**
	 * Creates a File object using filename param and clears ArrayList of
	 * extracted transactions.
	 * @param filename - Full name of the Chase Bank Statement PDF
	 */
	public void setStatement(String filename) {
		this.file = new File(filename);
		allTrans.clear();
	}
	
}
