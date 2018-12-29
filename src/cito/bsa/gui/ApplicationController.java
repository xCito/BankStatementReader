package cito.bsa.gui;
import java.util.HashMap;
import java.util.List;

import cito.bsa.Transaction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/** 
 * Basically the root/main of this application
 * @author Juanl - Me
 */
public class ApplicationController extends Application{

	static HashMap<String, List<Transaction>> data;		// Key: Filename / Value: Transactions in File

    @Override
    public void start(Stage primaryStage) throws Exception {
    	BorderPane border = new BorderPane();					// Main container (root)
    	data = new HashMap<String, List<Transaction>>();		// Data structure
    	
    	// The Views
    	OverviewController welcomeView 		   = new OverviewController( primaryStage );
    	SideNavigationView navView 			   = new SideNavigationView();
    	TrackTransactionController trackView   = new TrackTransactionController();
    	AllTransactionsController allTransView = new AllTransactionsController();

  
    	// Apply Event Listeners to Side Nav buttons
    	navView.getOverviewButton()
    	       .setOnAction( event -> border.setCenter( welcomeView.getView() ));	// Set BorderPane Center
    	navView.getTrackTransButton()
    	       .setOnAction( event -> border.setCenter( trackView.getView() ));		// Set BorderPane Center
    	navView.getAnalysisButton()
    	       .setOnAction( event -> border.setCenter( allTransView.getView() ));	// Set BorderPane Center

    	// Set view containers in BorderPane
    	VBox menuVbox = navView.getView();	
    	VBox cntrVbox = welcomeView.getView();	
    	border.setLeft(menuVbox);
    	border.setCenter(cntrVbox);
    	
    	// Set scene and launch window
        Scene scene = new Scene(border,1400,800);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bank Statement Analyzer");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    

}