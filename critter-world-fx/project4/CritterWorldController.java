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
        //assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

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
        createCanvasGrid(critterCanvas, 600, 400, true);
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
        	gc.fillRect(startX + c.getX()*10, startY+c.getY()*10, 10, 10);
        }
        System.out.println(startX);
        gc.setLineWidth(1.0);

        for (int x = startX; x <= endX; x+=10) {
            double x1;
            if (sharp) {
                x1 = x + 0.5 ;
            } else {
                x1 = x ;
            }
            gc.moveTo(x1, startY+1);
            gc.lineTo(x1, endY);
            gc.stroke();
        }

        for (int y = startY; y <= endY; y+=10) {
            double y1;
            if (sharp) {
                y1 = y + 0.5 ;
            } else {
                y1 = y ;
            }
            gc.moveTo(startX+1, y1);
            gc.lineTo(endX, y1);
            gc.stroke();
        }
        return canvas ;
    }
    
}