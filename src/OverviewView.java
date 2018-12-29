import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OverviewView {

	VBox main;
	Text heading1;
	Text heading2;
	Button fileBtn;
	Text numFilesLbl;
	ListView<Image> listview;
	ObservableList<Image> imgList;
	
	public OverviewView() {
		imgList = FXCollections.observableArrayList();
		main = createWelcomeView();
	}
	
	public VBox getView() {
		return main;
	}
	
	private VBox createWelcomeView() {
		VBox vbox = new VBox();
		String line1 = "-- Welcome Page --";
		String line2 = "Bank Statement Analyzer";
		
		vbox.setAlignment(Pos.CENTER);
		
		heading1 = new Text(line1);
		heading2 = new Text(line2);
		heading1.getStyleClass().add("welcomeText");
		heading2.getStyleClass().add("welcomeText");
		
		fileBtn = new Button("Choose PDF File(s)");		
	
		numFilesLbl = new Text("Number of BankStatements Attached: 0");
		
		vbox.getChildren().add(heading1);
		vbox.getChildren().add(heading2);
		vbox.getChildren().add(fileBtn);
		vbox.getChildren().add(numFilesLbl);
		vbox.getChildren().add( createPDFFileGallery() );
		return vbox;
	}
	
	private ListView<Image> createListView() {
		ListView<Image> listview = new ListView<>();
		listview.setOrientation( Orientation.HORIZONTAL );
		
		listview.setCellFactory( param -> new ListCell<Image>() {
			private ImageView imgView = new ImageView();
			
			@Override
			public void updateItem(Image img, boolean empty) {
				super.updateItem(img, empty);
				
				if(empty || img == null) {
					setText(null);
					setGraphic(null);
				}
				else {
					try {
						imgView.setImage(img);
					} catch( Exception e ) {
						System.out.println("Problem getting image");
					}
					setText( null );
					setGraphic(imgView);
				}
					
			}
		});
		return listview;
	}
	private HBox createPDFFileGallery() {
		HBox hbox = new HBox();
		listview = createListView();
		HBox.setHgrow(listview, Priority.ALWAYS);

		listview.setItems( imgList );
		hbox.getChildren().add( listview );
		
		return hbox;
	}
	
	public void setImages(List<Image> imgs) {
		imgList.clear();
		imgList.addAll(imgs);
	}
}
