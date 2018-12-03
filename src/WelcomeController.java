import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WelcomeController {

	Stage stage;
	FileChooser chooser;
	Button fileBtn;
	
	WelcomeView view;
	WelcomeModel model;
	
	
	public WelcomeController( Stage primaryStage ) {
		stage = primaryStage;
		view = new WelcomeView();
		model = new WelcomeModel();
		
		chooser = setUpFileChooser();
		
		fileBtn = view.fileBtn;
		fileBtn.setOnAction( event -> fileButtonAction() );
	}
	
	public VBox getView() {
		return view.getView();
	}
	
	private FileChooser setUpFileChooser() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF", "*.pdf") );
		
		return chooser;
	}
	
	private void fileButtonAction() {
		List<File> files = chooser.showOpenMultipleDialog(stage);
		if( files != null ) {
			model.setFiles( files );
			view.numFilesLbl.setText("Number of BankStatements Attached: " + model.getNumFiles());
			List<Image> images = model.getImagesOfPDF();
			view.setImages(images);
			model.extractData(files);
		}
	}
	
	
	
}
