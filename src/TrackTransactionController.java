import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TrackTransactionController {
	
	LineChart<Number,Number> chart;
	TableView<Transaction> table;
	TextField input;
	DatePicker fromDate;
	DatePicker toDate;
	Button analyzeBtn;
	
	TrackTransactionView view;
	
	public TrackTransactionController () {
		view 	= new TrackTransactionView();
		chart 		= view.getChart();
		table 		= view.getTable();
		input		= view.getTransactionTextField();
		fromDate	= view.getFromDatePicker();
		toDate		= view.getToDatePicker();
		analyzeBtn  = view.getAnalyzeButton();
	}
	
	public VBox getView() {
		return view.getView();
	}
	
	// Handle button click
	
	// Handle plotting data points
	
	// Handle populating table with data
	
	// Handle filling out text description
	
	
}
