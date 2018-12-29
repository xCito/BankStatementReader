package cito.bsa.gui;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import cito.bsa.Transaction;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TrackTransactionView {

	VBox main;
	LineChart<String,Number> chart;		// The Line Chart
	Series<String,Number> series;		// Holds Data Points		

	Text text;							// Description of Data
	Label heading;						// Label
	Label transact;						// Label
	Label fromDate;						// Label
	Label toDate;						// Label
	TextField field1;					
	DatePicker dateFrom;				
	DatePicker dateTo;
	Button button;
	
	TableView<Transaction> table;		// The Table
	
	
	public TrackTransactionView() {
		main = new VBox();
		HBox topSection = createTopSection();
		HBox botSection = createBotSection();
		
		main.getChildren().addAll( topSection, botSection );
		main.getStyleClass().add("centerVbox");
	}
	
	
/* Getters */	
	public VBox getView() {
		return main;
	}
	public LineChart<String,Number> getChart() {
		return chart;
	}
	public Button getAnalyzeButton() {
		return button;
	}
	public DatePicker getFromDatePicker() {
		return dateFrom;
	}
	public DatePicker getToDatePicker() {
		return dateTo;
	}
	public TextField getTransactionTextField() {
		return field1;
	}
	public TableView<Transaction> getTable() {
		return table;
	}
	
	/**
	 * Creates the upper portion of the TrackTransaction View.
	 * @return Container holding User input GridPane and Line Chart
	 */
	private HBox createTopSection() {
    	HBox hbox = new HBox();
    	GridPane grid = createInputFieldsSection();
    	chart = createLineChart();
    	
    	HBox.setHgrow(chart, Priority.ALWAYS);
    	
    	hbox.getChildren().add( grid );
    	hbox.getChildren().add( chart );
    	
    	return hbox;
    }
    
	/**
	 * Creates a LineChart with X-Axis= Dollar amount, Y-Axis= Date
	 * @return
	 */
    private LineChart<String,Number> createLineChart() {
    	CategoryAxis xAxis = new CategoryAxis();
    	NumberAxis yAxis = new NumberAxis(0, 100, 10);
    	yAxis.setAutoRanging(true);
    	LineChart<String,Number> chart = new LineChart<String,Number>(xAxis, yAxis);
    	//chart.setAxisSortingPolicy(SortingPolicy.X_AXIS);
    	
    	// Instantiate and fill with dummy data
    	series = new XYChart.Series<>();
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-06",21) );
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-07",31) );
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-08",41) );
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-09",51) );
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-10",61) );
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-11",71) );
    	series.getData().add( new XYChart.Data<String,Number>("2018-08-21",11) );
    
    	// Sort the Data Points by Dates
    	series.getData().sort( (o1, o2) -> { 
    			return o1.getXValue().compareTo( o2.getXValue() );
    		} 
    	); 

    	chart.getData().add(series);

    	return chart;
    }
    
    
    /**
     * Create a GridPane container holding Labels, Input Fields, and Button. 
     * This is for user input.
     * @return Container holding input components.
     */
    private GridPane createInputFieldsSection() {
    	GridPane grid = new GridPane();
    	grid.setVgap(4);
    	grid.setHgap(40);
    	
    	// Initialize Label components
    	heading  	= new Label("Enter the transaction\nto track");
    	transact	= new Label("Transaction");
    	fromDate 	= new Label("From");
    	toDate 		= new Label("To");
    	field1 		= new TextField();
    	dateFrom 	= new DatePicker();
    	dateTo 		= new DatePicker();
    	button 		= new Button("ANALYZE");
    	    	
    	// Set CSS Ids 
    	heading.setId("heading");
    	transact.setId("transLabel");
    	fromDate.setId("fromLabel");
    	toDate.setId("toLabel");
    	
    	// Set CSS classes
    	transact.getStyleClass().add("customLabel");
    	fromDate.getStyleClass().add("customLabel");
    	toDate.  getStyleClass().add("customLabel");
    	button.getStyleClass().add("analyzeButton");
    	button.prefWidthProperty().bind( grid.widthProperty() ); // Match width of GridPane
    	
    	// Add components to GridPane (2 columns, 5 rows)
    	grid.add(heading, 0,0,2,1);
    	grid.add(transact , 0, 1);
    	grid.add(fromDate, 0, 2);
    	grid.add(toDate, 0, 3);
    	
    	grid.add(field1, 1, 1);
    	grid.add(dateFrom, 1, 2);
    	grid.add(dateTo, 1, 3);
    	
    	grid.add(button, 0, 4, 2, 1);
    	return grid;
    }
    
    /**
     * Creates container in the bottom section holding TableView 
     * and Text components.
     * @return Container holding components
     */
    private HBox createBotSection() {
    	
    	HBox hbox = new HBox();
    	FlowPane flow = new FlowPane();
    	
    	table = createTable();
    	text = new Text("Chart description");
    	text.setWrappingWidth(300);
    	HBox.setHgrow(table, Priority.ALWAYS);
    	
    	flow.getChildren().add( text );
    	hbox.getChildren().add( table );
    	hbox.getChildren().add( flow );
    	
    	return hbox;
    }
    
    /**
     * Creates a TableView Object with Columns to hold Transaction Objects
     * @return created TableView to hold Transactions
     */
    private TableView<Transaction> createTable() {
    	
    	TableView<Transaction> table = new TableView<>();
 
    	// Create Table Columns 
    	TableColumn<Transaction, String> nameCol = new TableColumn<>("Name");
    	nameCol.setMinWidth(150);														// Look for variable: name in Transaction
    	nameCol.setCellValueFactory( new PropertyValueFactory<>("name")); 				// MUST have Getters and Setters to work.
    	
    	TableColumn<Transaction, Double> amountCol = new TableColumn<>("Amount $$$");
    	amountCol.setMinWidth(100);														// Look for variable: amount in Transaction
    	amountCol.setCellValueFactory( new PropertyValueFactory<>("amount"));			// MUST have Getters and Setters to work.
    	
    	TableColumn<Transaction, String> dateCol = new TableColumn<>("Date");		
    	dateCol.setMinWidth(100);														// Look for variable: date in Transaction
    	dateCol.setCellValueFactory( new PropertyValueFactory<>("date"));				// MUST have Getters and Setters to work.    	
    	
    	TableColumn<Transaction, String> typeCol = new TableColumn<>("Type");
    	typeCol.setMinWidth(100);														// Look for variable: type in Transaction
    	typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));				// MUST have Getters and Setters to work.
    		
    	// Bind COLUMN width & TABLE width, To allow columns to resize with Table
    	nameCol.prefWidthProperty().bind(table.widthProperty().divide(2)); 		// w * 1/2
    	amountCol.prefWidthProperty().bind(table.widthProperty().divide(8)); 	// w * 1/8
    	dateCol.prefWidthProperty().bind(table.widthProperty().divide(8)); 		// w * 1/8
    	typeCol.prefWidthProperty().bind(table.widthProperty().divide(8)); 		// w * 1/8
    	
    	// Add columns to table
    	table.getColumns().add(nameCol);
    	table.getColumns().add(amountCol);
    	table.getColumns().add(dateCol);
    	table.getColumns().add(typeCol);
    	
    	return table;
    }
    
    /**
     * Clears contents of table and sets List of Transactions
     * @param rows - List of Transactions
     */
    public void setToTable(List<Transaction> rows) {
    	table.getItems().clear();
    	table.getItems().addAll(rows);
    }
    
    public void setRange(LocalDate from, LocalDate to) {
    	
    }
    
    /**
     * Using the date and amount of a Transaction, plots a point
     * onto the LineChart.  (Showing off lambdas here)
     * @param transactions
     */
    public void setPoints( List<Transaction> transactions ) {
    	List<XYChart.Data<String,Number>> points = new ArrayList<>();
    	
    	for(Transaction t: transactions) 
    		points.add( new XYChart.Data<String,Number>( t.getLocalDate().toString(), t.getAmount()) );
    	
    	series.getData().setAll(points); 												 // Set new Data points 
    	series.getData().sort( (o1, o2) ->  o1.getXValue().compareTo( o2.getXValue() )); // Sort Data Points
    }

}
