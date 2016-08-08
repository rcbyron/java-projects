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
package project5;

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
    }
    
}
