package sample2;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.*;

public class SaveOut extends Controller2 {
    int output;
    public void addLabel(VBox palette){
        Label finalOut = new Label();
        Image A = new Image(getClass().getResourceAsStream("./IMAGES/mycircuit.jpg"));
        finalOut.setStyle("-fx-background-color: #49D3CC");
        finalOut.setPadding(new Insets(10, 10, 10, 10));
        finalOut.setText("My Circuit Output");
        palette.getChildren().add(finalOut);
        finalOut.setId("MyCircuit");
        output = VALOR;

        SelectComp(finalOut);
    }
}
