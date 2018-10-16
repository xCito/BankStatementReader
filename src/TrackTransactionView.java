import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	HBox topSection;
	HBox botSection;
	LineChart<Number,Number> chart;
	GridPane grid;

	Label heading;
	Label transact;
	Label fromDate;
	Label toDate;
	TextField field1;
	DatePicker dateFrom;
	DatePicker dateTo;
	Button button;
	
	TableView<Transaction> table;
	
	
	public TrackTransactionView() {
		main = new VBox();
		topSection = createTopSection();
		botSection = createBotSection();
		
		main.getChildren().add( topSection );
		main.getChildren().add( botSection );
		main.getStyleClass().add("centerVbox");
	}
	
	
/* Getters */	
	public VBox getView() {
		return main;
	}
	public LineChart<Number,Number> getChart() {
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
	
	private HBox createTopSection() {
    	HBox hbox = new HBox();
    	chart = createLineChart();
    	grid = createInputFieldsSection();
    	
    	HBox.setHgrow(chart, Priority.ALWAYS);
    	
    	hbox.getChildren().add( grid );
    	hbox.getChildren().add( chart );
    	
    	return hbox;
    }
    
    private LineChart<Number,Number> createLineChart() {
    	NumberAxis xAxis = new NumberAxis(0, 100, 10);
    	NumberAxis yAxis = new NumberAxis(0, 100, 10);
    	LineChart<Number,Number> chart = new LineChart<>(xAxis, yAxis);
    	
    	Series<Number, Number> series = new XYChart.Series<>();
    	series.getData().add( new XYChart.Data<>(10,44) );
    	series.getData().add( new XYChart.Data<>(20,44) );
    	series.getData().add( new XYChart.Data<>(30,66) );
    	series.getData().add( new XYChart.Data<>(80,77) );
    	series.getData().add( new XYChart.Data<>(90,90) );
    	chart.getData().add(series);
    	
    	return chart;
    }
    
    private GridPane createInputFieldsSection() {
    	GridPane grid = new GridPane();
    	grid.setVgap(4);
    	grid.setHgap(40);
    	
    	heading  	= new Label("Enter the transaction\nto track");
    	transact	= new Label("Transaction");
    	fromDate 	= new Label("From");
    	toDate 		= new Label("To");
    	
    	heading.setId("heading");
    	transact.setId("transLabel");
    	fromDate.setId("fromLabel");
    	toDate.setId("toLabel");
    	
    	transact.getStyleClass().add("customLabel");
    	fromDate.getStyleClass().add("customLabel");
    	toDate.  getStyleClass().add("customLabel");
    	
    	field1 = new TextField();
    	dateFrom = new DatePicker();
    	dateTo = new DatePicker();
    	button = new Button("ANALYZE");
    	
    	button.getStyleClass().add("analyzeButton");
    	button.prefWidthProperty().bind( grid.widthProperty() );
    	
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
    
    private HBox createBotSection() {
    	table = createTable();
    	HBox.setHgrow(table, Priority.ALWAYS);
    	HBox hbox = new HBox();
    	
    	FlowPane flow = new FlowPane();
    	Text text = new Text("Chart description");
    	text.setWrappingWidth(300);
    	for(int i=0; i<100; i++)
    		text.setText( text.getText()+"Yoo" );
    	
    	
    	flow.getChildren().add( text );
    	hbox.getChildren().add( table );
    	hbox.getChildren().add( flow );
    	
    	return hbox;
    }
    
    private TableView<Transaction> createTable() {
    	
    	TableView<Transaction> table = new TableView<>();
    	List<Transaction> trans = new ArrayList<>();
    	trans.add( new Transaction("Dominos", 2.3, LocalDate.now(), "expense") );
    	trans.add( new Transaction("MTA", 2.6, LocalDate.now(), "expense") );
    	trans.add( new Transaction("Food", 4.3, LocalDate.now(), "expense") );
    	ObservableList<Transaction> list = FXCollections.observableArrayList(trans);
    	table.setItems( list );
    	
    	TableColumn<Transaction, String> nameCol = new TableColumn<>("Column111");
    	nameCol.setMinWidth(150);
    	nameCol.setCellValueFactory( new PropertyValueFactory<>("name"));
    	
    	TableColumn<Transaction, Double> amountCol = new TableColumn<>("Column222");
    	amountCol.setMinWidth(100);
    	amountCol.setCellValueFactory( new PropertyValueFactory<>("amount"));
    	
    	TableColumn<Transaction, String> dateCol = new TableColumn<>("Column333");
    	dateCol.setMinWidth(100);
    	dateCol.setCellValueFactory( new PropertyValueFactory<>("date"));
    	
    	TableColumn<Transaction, String> typeCol = new TableColumn<>("Column444");
    	typeCol.setMinWidth(100);
    	typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
    		
    	nameCol.prefWidthProperty().bind(table.widthProperty().divide(4)); 		// w * 1/4
    	amountCol.prefWidthProperty().bind(table.widthProperty().divide(4)); 	// w * 1/4
    	dateCol.prefWidthProperty().bind(table.widthProperty().divide(4)); 		// w * 1/4
    	typeCol.prefWidthProperty().bind(table.widthProperty().divide(4)); 		// w * 1/4
    	table.getColumns().add(nameCol);
    	table.getColumns().add(amountCol);
    	table.getColumns().add(dateCol);
    	table.getColumns().add(typeCol);
    	
    	return table;
    }
}
