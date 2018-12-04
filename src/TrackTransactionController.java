import java.time.LocalDate;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TrackTransactionController {
	
	LineChart<String,Number> chart;
	TableView<Transaction> table;
	TextField input;
	DatePicker fromDate;
	DatePicker toDate;
	Button analyzeBtn;
	
	TrackTransactionView view;
	TrackTransactionModel model;
	
	LocalDate debugDate;
	
	public TrackTransactionController () {
		
		debugDate = LocalDate.of(1990, 1, 1);
		
		view 		= new TrackTransactionView();
		model 		= new TrackTransactionModel();
		chart 		= view.getChart();
		table 		= view.getTable();
		input		= view.getTransactionTextField();
		fromDate	= view.getFromDatePicker();
		toDate		= view.getToDatePicker();
		analyzeBtn  = view.getAnalyzeButton();
		analyzeBtn.setOnAction( e -> buttonAction() );
	}
	
	public VBox getView() {
		return view.getView();
	}
	
	// Handle button click
	public void buttonAction() {
		String transactionName = input.getText();
		LocalDate from = fromDate.getValue();
		LocalDate to = toDate.getValue();
		
		List<Transaction> result = model.getTransactionsBetween(transactionName, from, to);
		view.addToTable(result);
		view.setPoints(result);
	
		//view.addRandomPoint(debugDate);
		//debugDate = debugDate.plusMonths(1);
	}
	
	// Handle plotting data points
	
	// Handle populating table with data
	
	// Handle filling out text description
	
	
}
