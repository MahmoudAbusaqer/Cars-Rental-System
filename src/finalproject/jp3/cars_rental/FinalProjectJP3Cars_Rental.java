/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.jp3.cars_rental;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud_abusaqer
 */
public class FinalProjectJP3Cars_Rental extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane paneStudentTableView = FXMLLoader.load(getClass().getResource("rental_car.fxml"));
        Scene scene = new Scene(paneStudentTableView);
        primaryStage.setTitle("Cars Rental System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
