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
package project5;

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
            //node.setTranslateX(node.getScene().getWidth()/4);     
            //node.setTranslateY(node.getScene().getHeight()/4);
        });
        critterSelect.getItems().add("project5.Algae");
        critterSelect.getItems().add("project5.Craig");
        critterSelect.getItems().add("project5.Jon");
        critterSelect.getItems().add("project5.Sanjay");
        critterSelect.getItems().add("project5.Vallath");
        critterSelect.getItems().add("project5.Yale");
    }
    
    @FXML
    protected void resetWorld() {
    	Critter.resetWorld();
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
    	},0,50);
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
        gc.setLineWidth(1.0);

        for (int x = startX; x <= endX; x += SQ_SIZE) {
            gc.strokeLine(x, 0, x, (double)endY);
            //gc.stroke();
        }

        for (int y = startY; y <= endY; y += SQ_SIZE) {
            gc.strokeLine(0, y, (double)endX, y);
            //gc.stroke();
        }
        
        for (Critter c : Critter.getPopulation()) {
        	if (c.getX() < 0 || c.getY() < 0) System.out.println(c.getX() + " - " + c.getY());
        	gc.setFill(c.viewColor());
        	switch(c.viewShape()) {
        	case CIRCLE: gc.fillOval(startX + c.getX()*SQ_SIZE + 1, startY+c.getY()*SQ_SIZE + 1, SQ_SIZE-2, SQ_SIZE-2); break;
        	default: gc.fillRect(startX + c.getX()*SQ_SIZE + 1, startY+c.getY()*SQ_SIZE + 1, SQ_SIZE-2, SQ_SIZE-2); break;
        	
        	}
        }

    }
    
}