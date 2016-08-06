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


public class CritterWorldController implements Initializable {
	
	private static final int SQ_SIZE = 6;
	
	@FXML private TextField seedField;
	@FXML private TextField critterAddCount;
	@FXML private ChoiceBox<String> critterSelect;
    @FXML private Canvas critterCanvas;
    @FXML private AnchorPane critterPane;
    @FXML private ScrollPane scrollPane;
    @FXML private Slider canvasSlider;
    
    @FXML private CanvasController canvasController;

    private GraphicsContext gc;
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // initialize your logic here: all @FXML variables will have been injected
    	
        gc = critterCanvas.getGraphicsContext2D();
        
        critterCanvas.setWidth(Params.world_width * SQ_SIZE);
        critterCanvas.setHeight(Params.world_height * SQ_SIZE);
        centerNodeInScrollPane(scrollPane, (Node) critterCanvas);
        canvasSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            critterCanvas.setScaleX(2.0 * (newValue.intValue()/100.0));
            critterCanvas.setScaleY(2.0 * (newValue.intValue()/100.0));
            centerNodeInScrollPane(scrollPane, (Node) critterCanvas);
            //scrollPane.setS
            scrollPane.setMinHeight(2000);
            scrollPane.setMinWidth(2000);
//            node.setTranslateX(0 - node.getScene().getWidth()/4);     
//            node.setTranslateY(0 - node.getScene().getHeight()/4);
        });
        critterSelect.getItems().add("project4.Algae");
        critterSelect.getItems().add("project4.Craig");
        critterSelect.getItems().add("project4.Jon");
        critterSelect.getItems().add("project4.Sanjay");
        critterSelect.getItems().add("project4.Vallath");
        critterSelect.getItems().add("project4.Yale");
    }
    
    @FXML
    protected void resetWorld() {
    	//Critter.resetWorld();
    	canvasController.cool(); 
    	System.out.println("Population reset.");
    }
    
    @FXML
    protected void doStep() { 
    	Critter.worldTimeStep();
        
        draw(critterCanvas, Params.world_width*SQ_SIZE, Params.world_height*SQ_SIZE);
        System.out.println("Step "+Critter.timestep);
    }
    
    @FXML
    protected void addCritters() throws InvalidCritterException { 
    	for (int i = 0; i < Integer.parseInt(critterAddCount.getText()); i++){
    		Critter.makeCritter((String)critterSelect.getValue());
    		//System.out.println(critterSelect.getValue());
    	}
    	return; 
    }
    
    @FXML
    protected void setSeed() { 
    	Critter.setSeed(Long.parseLong(seedField.getText()));
    }
    
    @FXML
    protected void quit() { System.exit(0); }
    
    @FXML
    protected void startSim() { 
    	new Timer().scheduleAtFixedRate(new TimerTask(){
    	    @Override
    	    public void run(){
    	       doStep();
    	    }
    	},0,2000);
    }
    
    @FXML
    protected void toggleStats() { return; }

    public void centerNodeInScrollPane(ScrollPane scrollPane, Node node) {
        double h = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = (node.getBoundsInParent().getMaxY() + 
                    node.getBoundsInParent().getMinY()) / 2.0;
        double v = scrollPane.getViewportBounds().getHeight();
        scrollPane.setVvalue(scrollPane.getVmax() * ((y - 0.5 * v) / (h - v)));
    }
    
    private void draw(Canvas canvas, int width, int height) {
        
        int startX = (int)(canvas.getWidth() / 2 - width / 2.0);
        int startY = (int)(canvas.getHeight() / 2 - height / 2.0);
        int endX = (int)(canvas.getWidth() / 2 + width / 2.0);
        int endY = (int)(canvas.getHeight() / 2 + height / 2.0);
        
        gc.setFill(Color.WHITE);
        gc.fillRect(startX, startY, endX, endY);
        for (Critter c : Critter.getPopulation()) {
        	if (c.getX() < 0 || c.getY() < 0) System.out.println(c.getX() + " - " + c.getY());
        	gc.setFill(c.viewColor());
        	gc.fillRect(startX + c.getX()*SQ_SIZE, startY+c.getY()*SQ_SIZE, SQ_SIZE, SQ_SIZE);
        }
//        gc.setLineWidth(1.0);
//
//        for (int x = startX; x <= endX; x += SQ_SIZE) {
//            double x1 = x;// + 0.5;
//            gc.moveTo(x1, startY+1);
//            gc.lineTo(x1, endY);
//            gc.stroke();
//        }
//
//        for (int y = startY; y <= endY; y += SQ_SIZE) {
//        	double y1 = y;// + 0.5;
//            gc.moveTo(startX+1, y1);
//            gc.lineTo(endX, y1);
//            gc.stroke();
//        }
    }
    
}