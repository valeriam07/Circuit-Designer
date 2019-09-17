package sample2;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;



public class Controller2 {
    @FXML
    VBox palette;
    @FXML
    StackPane Stack;
    @FXML
    Pane root_pane;
    @FXML
    AnchorPane Constructor;

    Label currentComp = null;

    Line line = new Line();


    private Image LIand = new Image(getClass().getResourceAsStream("./IMAGES/AND.png"));
    private Image LIor = new Image(getClass().getResourceAsStream("./IMAGES/or.png"));
    private Image LInot = new Image(getClass().getResourceAsStream("./IMAGES/NOT.png"));
    private Image LInand = new Image(getClass().getResourceAsStream("./IMAGES/NAND.png"));
    private Image LInor = new Image(getClass().getResourceAsStream("./IMAGES/NOR.png"));
    private Image LIxor = new Image(getClass().getResourceAsStream("./IMAGES/XOR.png"));
    private Image LIxnor = new Image(getClass().getResourceAsStream("./IMAGES/XNOR.png"));

    private Label AND = new Label();
    private Label OR = new Label();
    private Label NOT = new Label();
    private Label NAND = new Label();
    private Label NOR = new Label();
    private Label XOR = new Label();
    private Label XNOR = new Label();
    private Button SetValue = new Button();


//_________________________________/ SIMULATE BUTTON /__________________________

    public void boton(ActionEvent event) {
        System.out.println("in");

    }


//_________________________________/ ADD IMAGES & LABELS /__________________________


    public void addImage() {

        palette.getChildren().add(SetValue);
        SetValue.setText("Entry");
        //SetValue.setStyle("-fx-text-fill: #ECECEC");
        SetValue.setStyle("-fx-background-color: #23BAC4");




        AND.setGraphic(new ImageView(LIand));  //LI = Label Image
        palette.getChildren().add(AND);
        AND.setId("AND");

        OR.setGraphic(new ImageView(LIor));
        palette.getChildren().add(OR);
        OR.setId("OR");

        NOT.setGraphic(new ImageView(LInot));
        palette.getChildren().add(NOT);
        NOT.setId("NOT");

        NAND.setGraphic(new ImageView(LInand));
        palette.getChildren().add(NAND);
        NAND.setId("NAND");

        NOR.setGraphic(new ImageView(LInor));
        palette.getChildren().add(NOR);
        NOR.setId("NOR");

        XOR.setGraphic(new ImageView(LIxor));
        palette.getChildren().add(XOR);
        XOR.setId("XOR");

        XNOR.setGraphic(new ImageView(LIxnor));
        palette.getChildren().add(XNOR);
        XNOR.setId("XNOR");



        SelectComp(AND);
        SelectComp(OR);
        SelectComp(NOT);
        SelectComp(NAND);
        SelectComp(NOR);
        SelectComp(XOR);
        SelectComp(XNOR);
        DragDropButton BTN = new DragDropButton();
        BTN.MoveButton(SetValue, Stack, Constructor);


    }

//_________________________________/ SELECT COMPUERTA PARA DRAG AND DROP /__________________________

    public void SelectComp(Label COMP) {
        COMP.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! " + event.getSource());

                if (currentComp == null) {
                    currentComp = COMP;
                    if (COMP == AND) {
                        DragDrop(AND, LIand);

                    } else if (COMP == OR) {
                        DragDrop(OR, LIor);

                    }else if (COMP == NOT) {
                        DragDrop(NOT, LInot);

                    }else if (COMP == NAND) {
                        DragDrop(NAND, LInand);

                    }else if (COMP == NOR) {
                        DragDrop(NOR, LInor);

                    }else if (COMP == XOR) {
                        DragDrop(XOR, LIxor);

                    }else if (COMP == XNOR) {
                        DragDrop(XNOR, LIxnor);
                    }
                }
            }
        });
    }

//_________________________________/ DRAG AND DROP PARA COMPUERTAS /__________________________

    public void DragDrop(Label COMP, Image IMG) {

        COMP.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
                System.out.println("onDragDetected");
                Dragboard db = COMP.startDragAndDrop(TransferMode.ANY);
                COMP.startDragAndDrop(TransferMode.ANY);


                ClipboardContent content = new ClipboardContent();
                content.putImage(IMG);
                db.setContent(content);
                event.consume();

            }

        });

        Stack.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("onDragOver");

                if (event.getGestureSource() != Stack &&
                        event.getDragboard().hasImage()) {
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

                    Label source = (Label) event.getGestureSource();
                    Compuerta toAdd = new Compuerta();
                    toAdd.setGraphic(new ImageView(IMG));

                    toAdd.setTranslateX(event.getX());
                    toAdd.setTranslateY(event.getY());
                    Constructor.getChildren().add(toAdd);




                    DoubleProperty startX = new SimpleDoubleProperty(event.getX());
                    DoubleProperty startY = new SimpleDoubleProperty(event.getY()+10);
                    DoubleProperty endX = new SimpleDoubleProperty(event.getX());
                    DoubleProperty endY = new SimpleDoubleProperty(event.getY()+10);



                    Controller2.Anchor start = new Controller2.Anchor(Color.PALEGREEN, startX, startY, toAdd, COMP.getId());
                    Controller2.Anchor end  = new Controller2.Anchor(Color.TOMATO, endX, endY, toAdd, COMP.getId());

                    //start.setId(COMP.getId());
                    //end.setId(COMP.getId());
                    //toAdd.setId(COMP.getId());

                    System.out.println("ID " + toAdd.getId());


                    Constructor.getChildren().add(start);
                    Constructor.getChildren().add(end);

                    Line line = new Controller2.BoundLine(startX, startY, endX, endY, toAdd);

                    start.toFront();            //Mover al frente la linea
                    end.toFront();
                    line.toFront();

                    Constructor.getChildren().add(line);


                }
                event.consume();

            }

        });

        COMP.setOnDragDone(new EventHandler<DragEvent>() {

            public void handle(DragEvent event) {
                currentComp = null;
                System.out.println("onDragDone");
                DragDropButton BTN = new DragDropButton();
                BTN.MoveButton(SetValue, Stack, Constructor);



                event.consume();

            }
        });

    }

//_________________________________/ LINEA DE CONEXION /__________________________

    class BoundLine extends Line {
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, Compuerta toAdd) {
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


        private Compuerta micompuerta;

        public void setMicompuerta(Compuerta compuerta)
        {
            micompuerta = compuerta;
        }

        //_________________________________/ INICIO Y FINAL DE LAS CONEXIONES /__________________________


        Anchor(Color color, DoubleProperty x, DoubleProperty y, Compuerta toAdd, String identifier) {

            super(x.get(), y.get(), 10);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);

            setId(identifier);
            System.out.println("TO ADD " + identifier);

            x.bind(centerXProperty());
            y.bind(centerYProperty());
            if (color == Color.PALEGREEN){

            }else {
                enableDrag(toAdd);
            }
        }

        //_________________________________/ DRAG AND DROP PARA EXTENDER LINEAS /__________________________

        private void enableDrag(Compuerta toAdd) {
            final Controller2.Anchor.Delta dragDelta = new Controller2.Anchor.Delta();
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


                    if (mouseEvent.getPickResult().getIntersectedNode() instanceof Controller2.Anchor ){

                        //toAdd.setCirculo((Anchor) mouseEvent.getPickResult().getIntersectedNode());

                        String CompLabel =(mouseEvent.getPickResult().getIntersectedNode()).getId();
                        System.out.println(CompLabel);


                        if (CompLabel.equals("AND")){
                            System.out.println("se conecta a un AND");
                        }


                    }
                }
            });
            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
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
                    }
                }
            });
        }

        private class Delta { double x, y; }
    }


}






















