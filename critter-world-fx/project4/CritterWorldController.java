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
	@FXML
	private TextField critterAddCount;
	@FXML
	private ChoiceBox critterSelect;
	@FXML
	private TextField seedField;
	
	private static final int SQ_SIZE = 6;

    @FXML //  fx:id="myButton"
    private Canvas critterCanvas; // Value injected by FXMLLoader
    
    @FXML
    private AnchorPane critterPane;
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private Slider canvasSlider;

    private GraphicsContext gc;
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // initialize your logic here: all @FXML variables will have been injected
    	
        gc = critterCanvas.getGraphicsContext2D();
        
        canvasSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            critterCanvas.setScaleX(2.0 * (newValue.intValue()/100.0));
            critterCanvas.setScaleY(2.0 * (newValue.intValue()/100.0));
//            node.setTranslateX(0 - node.getScene().getWidth()/4);     
//            node.setTranslateY(0 - node.getScene().getHeight()/4);
        });
    }
    
    @FXML
    protected void resetWorld() { 

    	return;
    }
    
    @FXML
    protected void doStep() { 
    	Critter.worldTimeStep();
        System.out.println("Step "+Critter.timestep);
        try {
        	for (int i = 0; i < 20; i++)
        		Critter.makeCritter("project4.Vallath");
        } catch (Exception e) {
        	System.out.print("wah wah... wuhhh.");
        }
        
        critterCanvas.setWidth(Params.world_width * SQ_SIZE);
        critterCanvas.setHeight(Params.world_height * SQ_SIZE);
        centerNodeInScrollPane(scrollPane, (Node) critterCanvas);
        createCanvasGrid(critterCanvas, Params.world_width * SQ_SIZE, Params.world_height * SQ_SIZE, true);
    }
    
    @FXML
    protected void addCritters() throws InvalidCritterException { 
    	for (int i = 0; i < Integer.parseInt(critterAddCount.getText()); i++){
    		Critter.makeCritter(critterSelect.getValue().toString());
    	}
    	return; 
    }
    
    @FXML
    protected void setSeed() { 
    	Critter.setSeed(Long.parseLong(seedField.getText()));
    	return; 
    }
    
    @FXML
    protected void quit() { System.exit(0); return; }
    
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
    
    private Canvas createCanvasGrid(Canvas canvas, int width, int height, boolean sharp) {
    	
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        int startX = (int)(canvas.getWidth() / 2 - width / 2);
        int startY = (int)(canvas.getHeight() / 2 - height / 2);
        int endX = (int)(canvas.getWidth() / 2 + width / 2);
        int endY = (int)(canvas.getHeight() / 2 + height / 2);
        
        gc.setFill(Color.WHITE);
        gc.fillRect(startX, startY, endX, endY);
        for (Critter c : Critter.getPopulation()) {
        	if (c.getX() < 0 || c.getY() < 0) System.out.println(c.getX() + " - " + c.getY());
        	gc.setFill(c.getColor());
        	gc.fillRect(startX + c.getX()*SQ_SIZE, startY+c.getY()*SQ_SIZE, SQ_SIZE, SQ_SIZE);
        }
        gc.setLineWidth(1.0);

        for (int x = startX; x <= endX; x += SQ_SIZE) {
            double x1 = x + (sharp ? 0.5 : 0);
            gc.moveTo(x1, startY+1);
            gc.lineTo(x1, endY);
            gc.stroke();
        }

        for (int y = startY; y <= endY; y += SQ_SIZE) {
        	double y1 = y + (sharp ? 0.5 : 0);
            gc.moveTo(startX+1, y1);
            gc.lineTo(endX, y1);
            gc.stroke();
        }
        return canvas;
    }
    
}