package project4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;


public class CritterWorldController implements Initializable {
	
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
    protected void doSomething() throws InvalidCritterException {
        System.out.println("The button was clicked!");
        for (int i = 0; i < 20; i++)
        	Critter.makeCritter("project4.Vallath");
        
        critterCanvas.setWidth(scrollPane.getWidth());
        critterCanvas.setHeight(scrollPane.getHeight());
//        //gc.setFill(Color.BLACK);
//        System.out.println(scrollPane.getWidth());
        //gc.fillRect(0, 0, critterCanvas.getWidth(), critterCanvas.getHeight());
        createCanvasGrid(critterCanvas, Params.world_width * SQ_SIZE, Params.world_height * SQ_SIZE, true);
        centerNodeInScrollPane(scrollPane, (Node) critterCanvas);
    }

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
        
        for (Critter c : Critter.getPopulation()) {
        	gc.setFill(c.getColor());
        	gc.fillRect(startX + c.getX()*SQ_SIZE, startY+c.getY()*SQ_SIZE, SQ_SIZE, SQ_SIZE);
        }
        System.out.println(startX);
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
        return canvas ;
    }
    
}