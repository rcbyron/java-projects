
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;


public class CritterWorldController implements Initializable {

    @FXML //  fx:id="myButton"
    private Canvas critterCanvas; // Value injected by FXMLLoader
    
    @FXML
    private ScrollPane critterPane;

    private GraphicsContext gc;
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        //assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
    	
        gc = critterCanvas.getGraphicsContext2D();

    }
    
    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
        critterCanvas.setWidth(critterPane.getWidth());
        critterCanvas.setHeight(critterPane.getHeight());
        gc.setFill(Color.BLACK);
        System.out.println(critterPane.getWidth());
        gc.fillRect(0, 0, critterCanvas.getWidth(), critterCanvas.getHeight());
    }


    
}