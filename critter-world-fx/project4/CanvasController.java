/* CRITTERS CritterWorldController.java
 * EE422C Project 4 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Joel Guo
 * jg55475
 * 76550
 * Slip days used: 1
 * Spring 2016
 */
package project4;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class CanvasController implements Initializable {
	
	private static final int SQ_SIZE = 6;
	
    @FXML private Canvas critterCanvas;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // initialize your logic here: all @FXML variables will have been injected

    	critterCanvas.setWidth(Params.world_width * SQ_SIZE);
    	critterCanvas.setHeight(Params.world_height * SQ_SIZE);
        //centerNodeInScrollPane(scrollPane, (Node) critterCanvas);
    }
    
    public void cool() {
    	System.out.println("cool");
    }
}