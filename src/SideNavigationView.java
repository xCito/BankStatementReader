import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SideNavigationView {

	VBox sideNav;			// Container for Side Navigation Bar
	Button overviewBtn;		// Button to welcome view
	Button trackTraBtn;		// Button to track transaction view
	Button analysisBtn;		// Button to spending analysis view
	Button settingsBtn;		// Button to settings view (unused)
	
	
	public SideNavigationView() {
		sideNav = createSideNav();
	}
	
/* Getter Methods */
	public VBox getView() {
		return sideNav;
	}
	public Button getOverviewButton() {
		return overviewBtn;
	}
	public Button getAnalysisButton() {
		return analysisBtn;
	}
	public Button getTrackTransButton() {
		return trackTraBtn;
	}
	public Button getSettingsButton() {
		return settingsBtn;
	}
	
	
    private VBox createSideNav() {
    	VBox vbox = new VBox();
    	Image img = new Image("logo.png");
    	ImageView iv = new ImageView();
    	iv.setImage(img);
    	
    	overviewBtn = new Button("Overview");
    	trackTraBtn = new Button("Track Transaction");
    	analysisBtn = new Button("Full Analysis");
    	settingsBtn = new Button("Settings");
    	
    	overviewBtn.getStyleClass().add("menuButton");		// Apply css style class to label
    	trackTraBtn.getStyleClass().add("menuButton");  	// Apply css style class to label
    	analysisBtn.getStyleClass().add("menuButton");  	// Apply css style class to label
    	settingsBtn.getStyleClass().add("menuButton"); 		// Apply css style class to label
    	
    	vbox.getChildren().add( iv );			// Add image
    	vbox.getChildren().add( overviewBtn );		// Add Label
    	vbox.getChildren().add( trackTraBtn );		// Add Label
    	vbox.getChildren().add( analysisBtn );		// Add Label
    	vbox.getChildren().add( settingsBtn );		// Add Label
    	
    	vbox.getStyleClass().add("sideMenu");	// Add style
    	vbox.setAlignment(Pos.BASELINE_CENTER);	// Center align horizontally
    	
    	return vbox;
    }
}
