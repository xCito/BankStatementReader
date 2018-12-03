import java.io.File;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ApplicationController extends Application{

	static List<File> pdfFiles;
	//static List<Transaction> transactions; 
	static HashMap<String, List<Transaction>> data;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	BorderPane border = new BorderPane();	// Main container
    	data = new HashMap<String, List<Transaction>>();
    	//File[] files = new File("C:/Users/Juanl/Documents/BankStatements/").listFiles(); // path to bank statements	 
    	
    	//ChaseStatementAnalyzer chase = new ChaseStatementAnalyzer( files[0] );
    	//chase.analyze();
    	
    	//for(Transaction x:  chase.allTrans) 
    	//	System.out.println(">" + x);
    	
    	
    	WelcomeController welcomeView = new WelcomeController( primaryStage );
    	SideNavigationView navView = new SideNavigationView();
    	TrackTransactionController trackView = new TrackTransactionController();
    	AllTransactionsController allTransView = new AllTransactionsController();
    	//allTransView.addDataToTable( data.get(data.keySet().toArray()[0]) );
    	
    	Button overviewBtn = navView.overview;
    	Button transactBtn = navView.trackTra;
    	Button allTransBtn = navView.analysis;
    	
    	overviewBtn.setOnAction( event -> border.setCenter( welcomeView.getView() ));
    	transactBtn.setOnAction( event -> border.setCenter( trackView.getView() ));
    	allTransBtn.setOnAction( event -> border.setCenter( allTransView.getView() ));
    	
    	VBox cntrVbox = welcomeView.getView();	
    	VBox menuVbox = navView.getView();		
    

    	border.setLeft(menuVbox);
    	border.setCenter(cntrVbox);
    	
        Scene scene = new Scene(border,1400,800);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bank Statement Analyzer");
        primaryStage.show();
        System.out.println(cntrVbox.widthProperty());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    

}