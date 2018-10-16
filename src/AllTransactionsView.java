import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AllTransactionsView {
	
	VBox main;
	HBox topSection;
	HBox botSection;
	
	BarChart<String,Number> chart;
	TableView<Transaction> table;

	
	public AllTransactionsView() {
		main = new VBox();
		
		topSection = createTopSection();
		botSection = createBotSection();
		
		main.getChildren().add( topSection );
		main.getChildren().add( botSection );
	}
	
	
	public VBox getView() {
		return main;
	}
	public HBox createTopSection() {
		HBox hbox = new HBox();
		CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> chart = new BarChart<String, Number>(xAxis, yAxis);
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.getData().add( new XYChart.Data<String, Number>("Spotify", 120));
		series1.getData().add( new XYChart.Data<String, Number>("Dominos", 37));
		series1.getData().add( new XYChart.Data<String, Number>("MTA", 432));
		series1.getData().add( new XYChart.Data<String, Number>("T-Mobile", 122));
		chart.getData().add(series1);
		
		hbox.getChildren().add(chart);
		
		return hbox;
	}
	
	public HBox createBotSection() {
		HBox hbox = new HBox();
		table = new TableView<Transaction>();
		HBox.setHgrow(table, Priority.ALWAYS);
		
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
    	
    	hbox.getChildren().add(table);
    	
		return hbox;
	}
}






