import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class AllTransactionsController {

	BarChart<String,Number> chart;
	TableView<Transaction> table;
	
	AllTransactionsView view;
	
	public AllTransactionsController() {
		view = new AllTransactionsView();
		chart = view.chart;
		table = view.table;
	}
	
	public VBox getView() {
		return view.getView();
	}
	
	public void addDataToTable( List<Transaction> transactionData ) {
		ObservableList<Transaction> list = FXCollections.observableArrayList(transactionData);
		table.getItems().addAll( list );
	}
}
