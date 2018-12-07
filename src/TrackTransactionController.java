import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TrackTransactionController {

	TextField input;				// Reference to view's TextField
	DatePicker fromDate;			// Reference to view's DatePicker from
	DatePicker toDate;				// Reference to view's DatePicker to
	
	TrackTransactionView view;
	TrackTransactionModel model;
	
	public TrackTransactionController () {
		
		view 		= new TrackTransactionView();			// The view
		model 		= new TrackTransactionModel();			// The model
		input		= view.getTransactionTextField();		// view's TextField 
		fromDate	= view.getFromDatePicker();				// view's DatePicker from
		toDate		= view.getToDatePicker();				// view's DatePicket to
		
		view.getAnalyzeButton().setOnAction( e -> buttonAction() );	// Set button event listener
	}
	
	/**
	 * @return VBox - Container for TrackTransaction View
	 */
	public VBox getView() {
		return view.getView();
	}
	

	/**
	 * Event Action for ANALYZE button
	 * Gets input from TextField and both DatePickers. Gets transactions
	 * containing substring from input and within specified dates. 
	 * Populates table and Plots line chart.
	 */
	public void buttonAction() {
		String transactionName = input.getText();
		LocalDate from = fromDate.getValue();
		LocalDate to = toDate.getValue();
		
		List<Transaction> result = model.getTransactionsBetween(transactionName, from, to);
		view.setToTable(result);
		view.setPoints(result);
	}

	// TODO: Handle filling out text description
	
	
}
