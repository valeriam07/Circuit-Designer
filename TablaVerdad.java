package sample2;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample2.ListasEnlazadas.Lista;
import sample2.ListasEnlazadas.Nodo;

import java.io.IOException;

public class TablaVerdad {

    static Lista inList = new Lista();
    int VALOR;

    VBox tableBase = new VBox();

    public void addIN(int valor){
        System.out.println("TABLA DE VERDAD");
        VALOR = valor;
        inList.addLast(valor);
    }
    public int getIN(){
        return VALOR;
    }

    public void CreateTable() throws IOException {
        Parent root = new AnchorPane();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("./TablaVerdad.fxml"));
        fxmlLoader.setRoot(root);
        fxmlLoader.load();

        Scene scene = new Scene(new Group());
        Stage primaryStage = new Stage();
        primaryStage.setWidth(600);
        primaryStage.setTitle("Tabla de Verdad");
        primaryStage.setHeight(600);


        TableView TablaVerdad  = new TableView();
        Label title = new Label("Tabla de Verdad");
        title.setFont(new Font("Bold Italic", 21));
        title.setTextFill(Paint.valueOf("#00eeff"));
        //title.setTextAlignment(TextAlignment.RIGHT);

        tableBase.setPadding(new Insets(10, 0, 0, 10));

        TableColumn firstCol =new TableColumn("ENTRADAS");
        TablaVerdad.getColumns().setAll(firstCol);
        tableBase.getChildren().add(title);
        tableBase.getChildren().add(TablaVerdad);





        ((Group) scene.getRoot()).getChildren().addAll(tableBase);

        System.out.println(tableBase);

        primaryStage.setScene(scene);
        primaryStage.show();





    }
}
