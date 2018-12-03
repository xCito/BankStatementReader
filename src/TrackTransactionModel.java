import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class TrackTransactionModel {
	
	//HashMap<String, List<Transaction>> statementMap;
	
	public TrackTransactionModel() {
		// Nothing
	}
	
	
	// KEY: FILE NAME
	// VALUE: List of all transactions
	public List<Transaction> getTransactionsBetween(String transName, LocalDate from, LocalDate to) {
		List<Transaction> listByName = getTransByName(transName);
		List<Transaction> result = listByName.stream()
				  .filter( t -> (t.date.isAfter(from) || t.date.isEqual(from)) && 
						         (t.date.isBefore(to)) || t.date.isEqual(to))
				  .collect(Collectors.toList());

		return result;
	}
	
	public List<Transaction> getTransByName(String transName) {
		List<Transaction> matched = new ArrayList<>();
		
		for(String key: ApplicationController.data.keySet()) {				// For each file
			List<Transaction> list = ApplicationController.data.get(key).subList(0, ApplicationController.data.get(key).size());
			List<Transaction> contains = list.stream()						// If transaction name contains 'transName'
				.filter( e -> e.name.toLowerCase().contains(transName.toLowerCase()) )	// CASE INSENSITIVE
				.collect(Collectors.toList());
			
			matched.addAll(contains);									
		}
		
		return matched;
	}
	
}
