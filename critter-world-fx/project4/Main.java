/* CRITTERS Main.java
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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    public static void main(String[] args) {
        Critter.displayWorld();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("world.fxml"));
        primaryStage.setTitle("Critter World 3000: A Tale of Electrical Engineering Pupils and Professors (Ultra Deluxe Platinum Edition)");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("canvas.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("World View");
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch (Exception e) {
        	e.printStackTrace(System.out);
        }
    }
    
}
