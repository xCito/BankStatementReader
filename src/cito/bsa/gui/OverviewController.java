package cito.bsa.gui;
import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OverviewController {

	Stage stage;
	FileChooser chooser;
	Button fileBtn;
	
	OverviewView view;
	OverviewModel model;
	
	
	public OverviewController( Stage primaryStage ) {
		stage = primaryStage;
		view = new OverviewView();
		model = new OverviewModel();
		
		chooser = setUpFileChooser();
		
		fileBtn = view.fileBtn;
		fileBtn.setOnAction( event -> fileButtonAction() );
	}
	
	public VBox getView() {
		return view.getView();
	}
	
	/**
	 * Initialized FileChooser and specifies to hold retrieve PDF files
	 * @return Instance of FileChooser
	 */
	private FileChooser setUpFileChooser() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF", "*.pdf") );
		
		return chooser;
	}
	
	/**
	 * Event Action for button click. Opens the FileChooser.
	 * If File(s) were retrieved, 
	 * 		- Hold PDF in data structure.
	 * 		- Attempt to Extract Transaction Data. 
	 * 		- Get Thumbnail of first page.
	 * 		- Set images in ListView
	 */
	private void fileButtonAction() {
		List<File> files = chooser.showOpenMultipleDialog(stage);
		if( files != null ) {
			
			model.setFiles( files );
			model.extractData(files);
			
			List<Image> images = model.getImagesOfPDF();
			view.setImages(images);
			
			view.numFilesLbl.setText("Number of BankStatements Attached: " 
					+ files.size());
			
		}
	}
	
	
	
}
