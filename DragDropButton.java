package sample2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import sample2.ListasEnlazadas.Lista;
import sample2.Logica.*;
import sample2.TablaVerdad.TablaVerdad;

import java.util.Random;

/**
 * Esta clase se encarga de las mismas funciones de la clase Controller, sin embargo su funcionamiento
 * es a partir de botones. El propósito de esta clase es generar botones cuyo valor cambie entre 0 y 1 según
 * el usuario lo desee, esto para las entradas de los valores iniciales del circuito. Debido a que las
 * características del botón y algunas de las funciones que debe desempeñar son diferentes a las de las compuertas,
 * las cuales era Labels, se opto por crear esta nueva clase.
 */

public class DragDropButton extends Controller2 {

    /**
     *Ejecuta el movimiento y reposicionamiento del boton y asigna el valor del mismo segun el usuario
     * presiona elemnto agregado. Asigna los valores iniciales del circuito.
     *
     * @param SetValue Boton para inicializar un valor en el circuito
     * @param Stack StackPane sobre el que se realiza Drag al boton para obtener una nueva posicion
     * @param Constructor Pane donde se realiza el Drop del boton, permite las primeras conexiones con las compuertas
     *
     */

    public void MoveButton(Button SetValue, StackPane Stack, Pane Constructor) {
        SetValue.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                System.out.println("onDragDetected");
                Dragboard db = SetValue.startDragAndDrop(TransferMode.ANY);
                SetValue.startDragAndDrop(TransferMode.ANY);


                ClipboardContent content = new ClipboardContent();
                content.putString("0");
                db.setContent(content);
                event.consume();

            }

        });

        Stack.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("onDragOver");

                if (event.getGestureSource() != Stack) {
                    event.acceptTransferModes(TransferMode.MOVE);
                } else {
                    event.consume();
                }

            }

        });


        Stack.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("onDragEntered");
                if (event.getGestureSource() != Stack &&
                        event.getDragboard().hasImage()) {
                }
                event.consume();
            }
        });


        Stack.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("onDragExited");

                if (event.getPickResult().getIntersectedNode() instanceof StackPane) {

                    Button source = (Button) event.getGestureSource();
                    Button toAdd = new Button();
                    toAdd.setText("0");
                    //toAdd.setGraphic(new ImageView(IMG));

                    toAdd.setTranslateX(event.getX());
                    toAdd.setTranslateY(event.getY());
                    Constructor.getChildren().add(toAdd);


                    DoubleProperty startX = new SimpleDoubleProperty(event.getX() + 20);
                    DoubleProperty startY = new SimpleDoubleProperty(event.getY() + 14);
                    DoubleProperty endX = new SimpleDoubleProperty(event.getX() + 20);
                    DoubleProperty endY = new SimpleDoubleProperty(event.getY() + 14);


                    //_____________________________/ BUTTON ACTION /_________________________________

                    toAdd.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            if (toAdd.getText().equals("0")) {
                                toAdd.setText("1");
                            } else if (toAdd.getText().equals("1")) {
                                toAdd.setText("0");
                            }

                        }
                    });



                    DragDropButton.Anchor start = new DragDropButton.Anchor(Color.GRAY, startX, startY, toAdd);
                    DragDropButton.Anchor end = new DragDropButton.Anchor(Color.TURQUOISE, endX, endY, toAdd);


                    Constructor.getChildren().add(start);
                    Constructor.getChildren().add(end);

                    Line line = new DragDropButton.BoundLine(startX, startY, endX, endY, toAdd);

                    start.toFront();            //Mover al frente la linea
                    end.toFront();
                    line.toFront();

                    Constructor.getChildren().add(line);


                }
                event.consume();

            }

        });

        SetValue.setOnDragDone(new EventHandler<DragEvent>() {

            public void handle(DragEvent event) {
                currentComp = null;
                System.out.println("onDragDone");

                event.consume();

            }
        });

    }

    //_________________________________/ LINEA DE CONEXION /__________________________

    /**
     * BoundLine genera la linea de conexion para las salidas de las compuertas.
     */

    class BoundLine extends Line {

        /**
         *
         * @param startX
         * @param startY
         * @param endX
         * @param endY
         * @see Line
         */
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, Button toAdd) {
            Random a = new Random();
            Random b = new Random();
            Random c = new Random();
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(2);
            int A = a.nextInt(255)+1;
            int B = b.nextInt(255)+1;
            int C = c.nextInt(255)+1;

            setStroke(Color.rgb(A,B,C));
            setStrokeLineCap(StrokeLineCap.BUTT);
            setMouseTransparent(true);

        }


    }

//_________________________________/ CIRCULOS /__________________________

    /**
     * Crea los circulos que representan las entradas y salidas que extienden las conexiones.
     */

    class Anchor extends Circle {

        //_________________________________/ INICIO Y FINAL DE LAS CONEXIONES /__________________________

        /**
         *
         * @param color color del circulo nuevo
         * @param x posicion en x
         * @param y posicion en y
         * @param toAdd compuerta agregada
         * @see Circle circulos de entrda y salida
         *
         */

        Anchor(Color color, DoubleProperty x, DoubleProperty y, Button toAdd) {

            super(x.get(), y.get(), 5);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);

            x.bind(centerXProperty());
            y.bind(centerYProperty());
            if (color == Color.PALEGREEN) {

            } else {
                enableDrag(toAdd);
            }
        }

        //_________________________________/ DRAG AND DROP PARA EXTENDER LINEAS /__________________________

        /**
         * Realiza el Drag and Drop de las botones.
         *
         * @param toAdd Es el nuevo dato inicial agregado por el susuario.

         */

        private void enableDrag(Button toAdd) {
            final DragDropButton.Anchor.Delta dragDelta = new DragDropButton.Anchor.Delta();
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {


                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);




                }

            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    if (newX > 0 && newX < getScene().getWidth()) {
                        setCenterX(newX);
                    }
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < getScene().getHeight()) {
                        setCenterY(newY);
                    }

                }
            });
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);

                    }
                    sendIN(toAdd.getText());
                    sendOp(mouseEvent.getPickResult().getIntersectedNode(), toAdd);




                }

            });





        }

        /**
         * Instancia los valores de las coordenadas x y y
         */

        private class Delta {
            double x, y;
        }

//_________________________________/ SELECCIONAR OPERACION /__________________________

        /**
         *
         * @param IntersectedNode
         * @param toAdd
         * Envia a operar dos valores una vez se conectan a la misma compuerta.
         */

        public void sendOp(Node IntersectedNode, Button toAdd) {
            //String txt;
            if (toAdd.getText().equals("1")) {
                VALOR = 1;
            } else {
                VALOR = 0;
            }
            if (IntersectedNode instanceof Controller2.Anchor) {
                System.out.println("entra");
                Node CompLabel = (IntersectedNode);
                //System.out.println(CompLabel.getId());

                if (CompLabel.getId() == null) {
                    System.out.println("no hay conexion");
                } else {
                    toAdd.setDisable(true);

                    if (CompLabel.getId().equals("AND")) {
                        ListaAND.addLast(VALOR);
                        System.out.println("se conecta a un AND");

                        if (ListaAND.size() == 2) {
                            OpAND a = new OpAND();
                            a.operar(ListaAND.getIn1(), ListaAND.getIn2());
                            ListaRes.addLast(a.res);
                            VALOR = a.res;
                            ListaAND = new Lista();


                        }
                    } else if (CompLabel.getId().equals("OR")) {
                        System.out.println("se conecta a un OR");

                        ListaOR.addLast(VALOR);
                        if (ListaOR.size() == 2) {
                            System.out.println("entradas" + ListaOR.getIn1() + ListaOR.getIn2());
                            OpOR a = new OpOR();
                            a.operar(ListaOR.getIn1(), ListaOR.getIn2());
                            ListaOR = null;
                        }
                    } else if (CompLabel.getId().equals("NOT")) {
                        System.out.println("se conecta a un NOT");

                        System.out.println("entrada" + VALOR);
                        OpNOT a = new OpNOT();
                        a.operar(VALOR);

                    } else if (CompLabel.getId().equals("NAND")) {
                        System.out.println("se conecta a un NAND");

                        ListaNAND.addLast(VALOR);
                        if (ListaNAND.size() == 2) {
                            System.out.println("entradas" + ListaNAND.getIn1() + ListaNAND.getIn2());
                            OpNAND a = new OpNAND();
                            a.operar(ListaNAND.getIn1(), ListaNAND.getIn2());
                            ListaNAND = null;
                        }
                    } else if (CompLabel.getId().equals("NOR")) {
                        System.out.println("se conecta a un NOR");

                        ListaNOR.addLast(VALOR);
                        if (ListaNOR.size() == 2) {
                            System.out.println("entradas" + ListaNOR.getIn1() + ListaNOR.getIn2());
                            OpNOR a = new OpNOR();
                            a.operar(ListaNOR.getIn1(), ListaNOR.getIn2());
                            ListaNOR = null;
                        }
                    } else if (CompLabel.getId().equals("XOR")) {
                        System.out.println("se conecta a un XOR");

                        ListaXOR.addLast(VALOR);
                        if (ListaXOR.size() == 2) {
                            System.out.println("entradas" + ListaXOR.getIn1() + ListaXOR.getIn2());
                            OpXOR a = new OpXOR();
                            a.operar(ListaXOR.getIn1(), ListaXOR.getIn2());
                            ListaXOR = null;
                        }
                    } else if (CompLabel.getId().equals("XNOR")) {
                        System.out.println("se conecta a un XNOR");

                        ListaXNOR.addLast(VALOR);
                        if (ListaXNOR.size() == 2) {
                            System.out.println("entradas" + ListaXNOR.getIn1() + ListaXNOR.getIn2());
                            OpXNOR a = new OpXNOR();
                            a.operar(ListaXNOR.getIn1(), ListaXNOR.getIn2());
                            ListaXNOR = null;

                        }
                    }
                }
            }


        }

        /**
         *
         * @param valor
         * Envia los valores a la Tabla de Verdad para ser guardados en una lista.
         */

        public void sendIN(String valor){
            TablaVerdad IN = new TablaVerdad();

            if(valor.equals("1")){
                IN.addIN(1, "next");
            }else{
                IN.addIN(0, "next");
            }
        }
    }
}

