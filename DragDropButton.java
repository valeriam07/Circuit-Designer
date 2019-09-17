package sample2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class DragDropButton extends Controller2 {

    int VALOR;


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



                    DoubleProperty startX = new SimpleDoubleProperty(event.getX() +20);
                    DoubleProperty startY = new SimpleDoubleProperty(event.getY() + 14);
                    DoubleProperty endX = new SimpleDoubleProperty(event.getX()+ 20);
                    DoubleProperty endY = new SimpleDoubleProperty(event.getY() + 14);

                    toAdd.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println(toAdd.getText());

                            if (toAdd.getText().equals("0")) {
                                VALOR = 1;
                                toAdd.setText("1");
                            } else if (toAdd.getText().equals("1")) {
                                VALOR = 0;
                                toAdd.setText("0");
                            }

                        }
                    });


                    DragDropButton.Anchor start = new DragDropButton.Anchor(Color.PALEGREEN, startX, startY, toAdd);
                    DragDropButton.Anchor end  = new DragDropButton.Anchor(Color.TOMATO, endX, endY, toAdd);

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
            if (color == Color.PALEGREEN){

            }else {
                enableDrag(toAdd);
            }
        }

        //_________________________________/ DRAG AND DROP PARA EXTENDER LINEAS /__________________________

        private void enableDrag(Button toAdd) {
            final DragDropButton.Anchor.Delta dragDelta = new DragDropButton.Anchor.Delta();
            setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {


                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                }
            });
            setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    getScene().setCursor(Cursor.HAND);
                    System.out.println("connected");
                    System.out.println(mouseEvent);


                    }

            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle (MouseEvent mouseEvent){
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
                @Override public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.HAND);
                    }
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                    if (!mouseEvent.isPrimaryButtonDown()) {
                        getScene().setCursor(Cursor.DEFAULT);

                    }if (mouseEvent.getPickResult().getIntersectedNode() instanceof Controller2.Anchor) {

                        Node CompLabel = (mouseEvent.getPickResult().getIntersectedNode());
                        System.out.println(CompLabel.getId());

                        if (CompLabel == null){
                            System.out.println("no hay conexion");

                        } else if (CompLabel.getId().equals("AND")) {
                            System.out.println("se conecta a un AND");

                        }


                    }
                }
            });
        }

        public class Delta { double x, y; }
    }


}

