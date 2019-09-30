package sample2.TablaVerdad;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample2.ListasEnlazadas.Lista;

import java.io.IOException;

/**
 * Es la clase encargada generar una tabla de verdad,
 * la cual incluye los valores de entrada iniciales registrados por el usuario y la salida final obtenida.
 */

public class TablaVerdad {


    TableView<Dato> TablaVerdad  = new TableView<Dato>();

    static Lista inList = new Lista();

    VBox tableBase = new VBox();

    String in;

    String out;

    static ObservableList<Dato> row = FXCollections.observableArrayList(
    );

    /**
     * Anade numeros a inList y crea un nuevo Dato en row cada vez que se llama
     *
     * @param valor numero por agregar a la lista y a la nueva fila de la tabla
     * @param inCount determina si es una nueva entrada o la salida
     *
     */
    public void addIN(int valor, String inCount){
        String sValor = null;

        if(valor == 0){
            sValor = "0";
        }else if(valor == 1){
            sValor = "1";
        }
        if(inCount.equals("final")){
            //inList.addLast(valor);
            row.add(new Dato("-", sValor));
        }else{
            //inList.addLast(valor);
            row.add(new Dato(sValor, "-"));

        }
    }

    /**
     * Crea la ventana donde se ubican la tabla de verdad.
     * Asigna todas las caracteristicas a la tabla.
     *
     * @throws IOException Evita que surgan errores del tipo IO en el programa
     */

    public void CreateTable() throws IOException {

        Scene scene = new Scene(new Group());
        Stage primaryStage = new Stage();
        primaryStage.setWidth(450);
        primaryStage.setTitle("Tabla de Verdad");
        primaryStage.setHeight(600);
        //TablaVerdad.setStyle("-fx-background-color: #23BAC4");


        TablaVerdad.setEditable(true);

        TableColumn InCol =new TableColumn("ENTRADAS");
        TableColumn OutCol = new TableColumn("SALIDA");
        InCol.setCellValueFactory(
                new PropertyValueFactory<Dato, Integer>("In")
        );
        OutCol.setCellValueFactory(
                new PropertyValueFactory<Dato, Integer>("Out")
        );



        Label title = new Label("Tabla de Verdad");
        tableBase.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 30));
        title.setTextFill(Paint.valueOf("#49D3CC"));


        TablaVerdad.setItems(row);
        TablaVerdad.getColumns().addAll(InCol, OutCol);
        tableBase.getChildren().addAll(title, TablaVerdad);
        tableBase.relocate(87,60);

        ((Group) scene.getRoot()).getChildren().addAll(tableBase);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Instancia una cifra como una propiedad para ser agregada en una fila de la tabla
     */

    public static class Dato {
        private final SimpleStringProperty in;
        private final SimpleStringProperty out;

        /**
         *  @param In Es cada nueva entrada ingresada por el usuario
         * @param Out Es la salida final del circuito
         */

        public Dato(String In, String Out) {
            this.in = new SimpleStringProperty(In);
            this.out = new SimpleStringProperty(Out);
        }

        /**
         * @return la nueva entrada registrada
         */
        public String getIn(){
            return in.get();
        }

        /**
         *
         * @return la salia final del circuito luego de realizar todas las operaciones logicas
         */
        public String getOut(){
            return out.get();
        }
    }

}
