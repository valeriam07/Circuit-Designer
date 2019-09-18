package sample2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample2.ListasEnlazadas.Lista;

import java.io.IOException;

public class TablaVerdad {


    TableView<Dato> TablaVerdad  = new TableView();

    static Lista inList = new Lista();

    int VALOR;

    VBox tableBase = new VBox();

    static ObservableList<Dato> a = FXCollections.observableArrayList(


    );

    public void addIN(int valor){
        //System.out.println("TABLA DE VERDAD");
        VALOR = valor;
        inList.addLast(valor);
        a.add(new Dato(valor));
    }

    public void CreateTable() throws IOException {

        Scene scene = new Scene(new Group());
        Stage primaryStage = new Stage();
        primaryStage.setWidth(600);
        primaryStage.setTitle("Tabla de Verdad");
        primaryStage.setHeight(600);

        TablaVerdad.setEditable(true);

        TableColumn firstCol =new TableColumn("ENTRADAS");
        firstCol.setCellValueFactory(
                new PropertyValueFactory<Dato, Integer>("VALOR")
        );


        Label title = new Label("Tabla de Verdad");
        title.setFont(new Font("Bold Italic", 21));
        title.setTextFill(Paint.valueOf("#00eeff"));

        //tableBase.setPadding(new Insets(10, 0, 0, 10));

        TablaVerdad.setItems(a);
        TablaVerdad.getColumns().addAll(firstCol);
        tableBase.getChildren().addAll(title, TablaVerdad);


        ((Group) scene.getRoot()).getChildren().addAll(tableBase);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static class Dato {
        private final SimpleIntegerProperty VALOR;

        public Dato(int In) {
            this.VALOR = new SimpleIntegerProperty(In);
        }
        public int getVALOR(){
            return VALOR.get();
        }
    }

}
