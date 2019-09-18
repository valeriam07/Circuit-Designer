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

public class DragDropButton extends Controller2 {
         //se crea una nueva lista para los valores de entrada
    Lista ListaAND = new Lista();
    Lista ListaOR = new Lista();
    Lista ListaNOR = new Lista();
    Lista ListaXOR = new Lista();
    Lista ListaNAND = new Lista();
    Lista ListaXNOR = new Lista();


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
                                VALOR = 1;
                            } else if (toAdd.getText().equals("1")) {
                                toAdd.setText("0");
                                VALOR = 0;
                            }

                        }
                    });



                    DragDropButton.Anchor start = new DragDropButton.Anchor(Color.PALEGREEN, startX, startY, toAdd);
                    DragDropButton.Anchor end = new DragDropButton.Anchor(Color.TOMATO, endX, endY, toAdd);

                    //start.setId(SetValue.getId());
                    //end.setId(SetValue.getId());


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

    class BoundLine extends Line {
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, Button toAdd) {
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(2);
            setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
            setStrokeLineCap(StrokeLineCap.BUTT);
            getStrokeDashArray().setAll(10.0, 5.0);
            setMouseTransparent(true);

        }


    }

//_________________________________/ CIRCULOS /__________________________

    class Anchor extends Circle {

        //_________________________________/ INICIO Y FINAL DE LAS CONEXIONES /__________________________


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
                    sendIN(toAdd.getText());




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
                    System.out.println(VALOR);
                    sendOp(mouseEvent.getPickResult().getIntersectedNode(), VALOR, toAdd);




                }

            });





        }


        private class Delta {
            double x, y;
        }

//_________________________________/ SELECCIONAR OPERACION /__________________________

        public void sendOp(Node IntersectedNode, int VALOR, Button toAdd) {


            if (IntersectedNode instanceof Controller2.Anchor) {
                toAdd.setDisable(true);


                Node CompLabel = (IntersectedNode);
                System.out.println(CompLabel.getId());

                if (CompLabel == null) {
                    System.out.println("no hay conexion");

                }


                else if (CompLabel.getId().equals("AND")) {
                    System.out.println("se conecta a un AND");

                    ListaAND.addLast(VALOR);
                    if (ListaAND.size() == 2) {
                        System.out.println("entradas" + ListaAND.getIn1() + ListaAND.getIn2());
                        OpAND a = new OpAND();
                        Controller2.VALOR = a.operar(ListaAND.getIn1(), ListaAND.getIn2());
                        ListaAND = null;

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

        public int getVALOR(){
            return VALOR;
        }

        public void sendIN(String valor){
            TablaVerdad IN = new TablaVerdad();

            if(valor.equals("1")){
                IN.addIN(1);
            }else{
                IN.addIN(0);
            }
        }
    }
}

