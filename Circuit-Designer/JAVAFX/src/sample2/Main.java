package sample2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = new Pane();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("./App2.fxml"));

        fxmlLoader.setRoot(root);

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller2 and = (Controller2) fxmlLoader.getController();
        and.addImage();







        primaryStage.setScene(scene);
        primaryStage.setTitle("Proyecto I - Circuit Designer");
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }


}
