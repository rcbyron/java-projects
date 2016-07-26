import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ZetCode JavaFX tutorial
 *
 * This program draws three lines which
 * form a rectangle.
 * 
 * Author: Jan Bodnar 
 * Website: zetcode.com 
 * Last modified: June 2015
 */

public class MyCanvas extends Application {

	private Canvas canvas;
	private Scene scene;
	
    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private void initUI(Stage stage) {

        Pane root = new Pane();

        canvas = new Canvas(300, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawLines(gc);

        root.getChildren().add(canvas);

        scene = new Scene(root, 300, 250, Color.WHITESMOKE);
        
        scene.widthProperty().addListener(observable -> redraw(gc));
        scene.heightProperty().addListener(observable -> redraw(gc));

        stage.setTitle("Lines");
        stage.setScene(scene);
        stage.show();
    }

    private void redraw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, scene.getWidth(), scene.getHeight());
    }
    
    private void drawLines(GraphicsContext gc) {

        gc.beginPath();
        gc.moveTo(30.5, 30.5);
        gc.lineTo(150.5, 30.5);
        gc.lineTo(150.5, 150.5);
        gc.lineTo(30.5, 30.5);
        gc.stroke();
    }

    public static void main(String[] args) {
        launch(args);
    }
}