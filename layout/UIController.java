
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UIController implements Initializable {

	@FXML
	private Label label;
	
	@FXML
	private Button button3;
	
	@FXML
	private Button button2;
	

	
	public void initialize(final URL url, final ResourceBundle bundle) {
		bindButton2Event();
		bindButton3Event();
	}
	public void bindButton2Event() {
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent evt) {
				System.out.println("Hi2");
			}
		});
	}
	public void bindButton3Event() {
		button3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent evt) {
				System.out.println("Hi3");
			}
		});
	}
}
