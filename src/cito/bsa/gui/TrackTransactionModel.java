package cito.bsa.gui;
import java.util.List;
import java.util.stream.Collectors;

import cito.bsa.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrackTransactionModel {
	
	public TrackTransactionModel() {
		// Nothing
	}
	
	
	/**
	 * @param transName - Name/substring to search for
	 * @param from		- Lower bound date range
	 * @param to		- Upper bound date range
	 * @return		- List of Transaction that contain 'transName' and
	 * 				  between from date and to date.
	 */
	public List<Transaction> getTransactionsBetween(String transName, LocalDate from, LocalDate to) {	
		List<Transaction> listByName = getTransByName(transName);
		
		// Filter list of Transaction and keep those : FROM <= date <= TO
		List<Transaction> result = listByName.stream()
				  .filter( t -> (t.getLocalDate().isAfter(from) || t.getLocalDate().isEqual(from)) && 
						         (t.getLocalDate().isBefore(to)) || t.getLocalDate().isEqual(to))
				  .collect(Collectors.toList());

		return result;
	}
	
	/**
	 * Searches HashMap for all transactions that contain the name
	 * 'transName'. The search is case-insensitive.
	 * @param transName - substring to match
	 * @return			- List of Transaction that contain 'transName' 
	 * 					  substring in name.
	 */
	public List<Transaction> getTransByName(String transName) {
		List<Transaction> matched = new ArrayList<>();
		
		// For each key ()
		for(String key: ApplicationController.data.keySet()) 
		{
			// Hold shallow copy of hashMap list (for that key)
			List<Transaction> list = new ArrayList<>(ApplicationController.data.get(key));
			
			// Filter for transactions that contain substring 'transName'
			List<Transaction> contains = list.stream()						
				.filter( e -> e.getName().toLowerCase().contains(transName.toLowerCase()) )	// CASE INSENSITIVE
				.collect(Collectors.toList());
			
			matched.addAll(contains);									
		}
		
		return matched;
	}
	
}
