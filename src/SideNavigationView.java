import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SideNavigationView {

	VBox sideNav;
	Button overview;
	Button analysis;
	Button trackTra;
	Button settings;
	
	
	public SideNavigationView() {
		sideNav = createSideNav();
	}
	
/* Getter Methods */
	public VBox getView() {
		return sideNav;
	}
	public Button getOverviewButton() {
		return overview;
	}
	public Button getAnalysisButton() {
		return analysis;
	}
	public Button getTrackTransactionButton() {
		return trackTra;
	}
	public Button getSettingsButton() {
		return settings;
	}
	
	
    private VBox createSideNav() {
    	VBox vbox = new VBox();
    	Image img = new Image("logo.png");
    	ImageView iv = new ImageView();
    	iv.setImage(img);
    	
    	overview = new Button("Overview");
    	analysis = new Button("Full Analysis");
    	trackTra = new Button("Track Transaction");
    	settings = new Button("Settings");
    	
    	overview.getStyleClass().add("menuButton");	// Apply css style class to label 
    	analysis.getStyleClass().add("menuButton");  // Apply css style class to label
    	trackTra.getStyleClass().add("menuButton");  // Apply css style class to label
    	settings.getStyleClass().add("menuButton"); 	// Apply css style class to label
    	
    	vbox.getChildren().add( iv );			// Add image
    	vbox.getChildren().add( overview );		// Add Label
    	vbox.getChildren().add( trackTra );		// Add Label
    	vbox.getChildren().add( analysis );		// Add Label
    	vbox.getChildren().add( settings );		// Add Label
    	
    	vbox.getStyleClass().add("sideMenu");	// Add style
    	vbox.setAlignment(Pos.BASELINE_CENTER);	// Center align horizontally
    	
    	return vbox;
    }
}
