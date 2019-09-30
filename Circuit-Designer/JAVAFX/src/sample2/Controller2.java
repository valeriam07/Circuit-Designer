package sample2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
import sample2.ListasEnlazadas.Lista;
import sample2.Logica.*;
import sample2.TablaVerdad.TablaVerdad;

import java.io.IOException;
import java.util.Random;

/**
 * @author Valeria Morales Alvarado
 */

/**
 * Esta clase se es la encaragada de ejecutar las funciones relacionadas con la pagina principal,
 * el archivo de la misma, de nombre App.fxml, esta desarrollada en JavaFx. En esta se encuentran
 * los elementos principales del Circuit Designer como la paleta de compuertas, en la cual se
 * encuentran las diferentes operaciones que pueden ser utilizadas para un circuito; el pane
 * constructor, sobre el cual se dibuja el circuito; botón para simular o resolver el circuito; entre otros.
 */
public class Controller2 {

    /**
     * Guarda valores temporalmente para realizar operaciones basicas y guardar datos en listas.
     */
    public static int VALOR;

    @FXML
    VBox palette;
    @FXML
    StackPane Stack;
    @FXML
    Pane root_pane;
    @FXML
    AnchorPane Constructor;
    @FXML
    AnchorPane BasePane;

    Label currentComp = null;

    static Lista ListaAND = new Lista();      //se crea una nueva lista para los valores de entrada
    static Lista ListaOR = new Lista();
    static Lista ListaNOR = new Lista();
    static Lista ListaXOR = new Lista();
    static Lista ListaNAND = new Lista();
    static Lista ListaXNOR = new Lista();

    static Lista ListaRes = new Lista();


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

    /**
     * @param event Representa la accion de presionar el boton Simulate
     * @throws IOException Objeto IOException para evitar errores del mismo tipo
     * @see TablaVerdad Cabe destacar que esta funcion llama a la clase que genera la tabla
     * de verdad para la operacion general.
     */

    public void boton(ActionEvent event) throws IOException {
        TablaVerdad B = new TablaVerdad();
        B.CreateTable();
        B.addIN(VALOR, "final");
        SaveOut newLb = new SaveOut();
        newLb.addLabel(palette);

    }



//_________________________________/ ADD IMAGES & LABELS /__________________________

    /**
     * Esta funcion se encarga de posicionar las imagenes de las compuertas a los labels
     * correspondientes. Ademas realiza el .setId de los labels para que sean mas facilmente reconocibles
     * a la hora de seleccionar la operacion.
     */

    public void addImage() {

        palette.getChildren().add(SetValue);
        SetValue.setText("Add Entry");
        SetValue.setStyle("-fx-background-color: #49D3CC");


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

    /**
     * Selecciona la compuerta a la que se le realizara Drag and Drop para evitar errores a la hora de que se
     * genere una nueva imagen sobre el constructor de circuitos.
     *
     * @param COMP Representa el label de la compuerta con la que se esta trabajando.
     */

    public void SelectComp(Label COMP) {
        COMP.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (currentComp == null) {
                    currentComp = COMP;
                    if (COMP == AND) {
                        DragDrop(AND, LIand);

                    } else if (COMP == OR) {
                        DragDrop(OR, LIor);

                    } else if (COMP == NOT) {
                        DragDrop(NOT, LInot);

                    } else if (COMP == NAND) {
                        DragDrop(NAND, LInand);

                    } else if (COMP == NOR) {
                        DragDrop(NOR, LInor);

                    } else if (COMP == XOR) {
                        DragDrop(XOR, LIxor);

                    } else if (COMP == XNOR) {
                        DragDrop(XNOR, LIxnor);

                    } else if (COMP.getId().equals("MyCircuit")){
                        //DragDrop(AND, new Image(getClass().getResourceAsStream("./IMAGES/mycircuit.jpg")));
                    }
                }
            }
        });
    }

//_________________________________/ DRAG AND DROP PARA COMPUERTAS /__________________________

    /**
     * Otorga la posibilidad de moverse y reposicionarse a los labels de las compuertas, así como generar un nuevo
     * label cada vez que se realiza el movimiento de una imagen
     *
     * @param COMP Compuerta a la que se le realiza el movimiento para reproducir la imagen y sus propiedades para
     *             poder realizar las respectivas conexiones.
     * @param IMG  Imagen de la compuerta seleccionada, sirve para reestablecer la imagen en la paleta de compuertas
     *             y para agregarla donde el usuario desee.
     */

    public void DragDrop(Label COMP, Image IMG) {

        COMP.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent event) {
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
                if (event.getGestureSource() != Stack &&
                        event.getDragboard().hasImage()) {
                }
                event.consume();
            }
        });


        Stack.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getPickResult().getIntersectedNode() instanceof StackPane) {

                    Label source = (Label) event.getGestureSource();
                    Compuerta toAdd = new Compuerta();
                    toAdd.setGraphic(new ImageView(IMG));

                    toAdd.setTranslateX(event.getX());
                    toAdd.setTranslateY(event.getY());
                    Constructor.getChildren().add(toAdd);


                    if (COMP == NOT) {

                        DoubleProperty startX = new SimpleDoubleProperty(event.getX());
                        DoubleProperty startY = new SimpleDoubleProperty(event.getY() + 25);
                        DoubleProperty startX_OUT = new SimpleDoubleProperty(event.getX() + 100);
                        DoubleProperty startY_OUT = new SimpleDoubleProperty(event.getY() + 25);
                        DoubleProperty endX_OUT = new SimpleDoubleProperty(event.getX() + 100);
                        DoubleProperty endY_OUT = new SimpleDoubleProperty(event.getY() + 25);

                        Controller2.Anchor start_IN1 = new Controller2.Anchor(Color.GRAY, startX, startY, toAdd, COMP.getId(), null);
                        Controller2.Anchor start_OUT = new Controller2.Anchor(Color.GRAY, startX_OUT, startY_OUT, toAdd, COMP.getId(), null);
                        Controller2.Anchor end_OUT = new Controller2.Anchor(Color.TURQUOISE, endX_OUT, endY_OUT, toAdd, COMP.getId(), "OUT");

                        Constructor.getChildren().add(start_IN1);
                        Constructor.getChildren().add(start_OUT);
                        Constructor.getChildren().add(end_OUT);

                        Line lineOUT = new Controller2.BoundLine(startX_OUT, startY_OUT, endX_OUT, endY_OUT, toAdd);

                        Constructor.getChildren().add(lineOUT);

                        toAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.SECONDARY){
                                    System.out.println("DELETE");
                                    Constructor.getChildren().remove(toAdd);
                                    Constructor.getChildren().remove(start_IN1);
                                    Constructor.getChildren().remove(start_OUT);
                                    Constructor.getChildren().remove(end_OUT);
                                }
                            }
                        });

                    } else {

                        DoubleProperty startX = new SimpleDoubleProperty(event.getX());
                        DoubleProperty startY = new SimpleDoubleProperty(event.getY() + 15);
                        DoubleProperty endX = new SimpleDoubleProperty(event.getX());
                        DoubleProperty endY = new SimpleDoubleProperty(event.getY() + 15);

                        DoubleProperty startX_2 = new SimpleDoubleProperty(event.getX());
                        DoubleProperty startY_2 = new SimpleDoubleProperty(event.getY() + 35);
                        DoubleProperty endX_2 = new SimpleDoubleProperty(event.getX());
                        DoubleProperty endY_2 = new SimpleDoubleProperty(event.getY() + 35);

                        DoubleProperty startX_OUT = new SimpleDoubleProperty(event.getX() + 100);
                        DoubleProperty startY_OUT = new SimpleDoubleProperty(event.getY() + 25);
                        DoubleProperty endX_OUT = new SimpleDoubleProperty(event.getX() + 100);
                        DoubleProperty endY_OUT = new SimpleDoubleProperty(event.getY() + 25);

                        Controller2.Anchor start_IN1 = new Controller2.Anchor(Color.GRAY, startX, startY, toAdd, COMP.getId(), null);

                        Controller2.Anchor start_IN2 = new Controller2.Anchor(Color.GRAY, startX_2, startY_2, toAdd, COMP.getId(), null);

                        Controller2.Anchor start_OUT = new Controller2.Anchor(Color.GRAY, startX_OUT, startY_OUT, toAdd, COMP.getId(), null);
                        Controller2.Anchor end_OUT = new Controller2.Anchor(Color.TURQUOISE, endX_OUT, endY_OUT, toAdd, COMP.getId(), "OUT");


                        Constructor.getChildren().add(start_IN1);

                        Constructor.getChildren().add(start_IN2);

                        Constructor.getChildren().add(start_OUT);
                        Constructor.getChildren().add(end_OUT);


                        Line line = new Controller2.BoundLine(startX, startY, endX, endY, toAdd);
                        Line line2 = new Controller2.BoundLine(startX_2, startY_2, endX_2, endY_2, toAdd);
                        Line lineOUT = new Controller2.BoundLine(startX_OUT, startY_OUT, endX_OUT, endY_OUT, toAdd);

                        line.toFront();

                        Constructor.getChildren().add(line);
                        Constructor.getChildren().add(line2);
                        Constructor.getChildren().add(lineOUT);

                        toAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.SECONDARY){
                                    System.out.println("DELETE");
                                    Constructor.getChildren().remove(toAdd);
                                    Constructor.getChildren().remove(start_IN1);
                                    Constructor.getChildren().remove(start_IN2);
                                    Constructor.getChildren().remove(start_OUT);
                                    Constructor.getChildren().remove(end_OUT);


                                }
                            }
                        });
                    }


                }
                event.consume();

            }

        });

        COMP.setOnDragDone(new EventHandler<DragEvent>() {

            public void handle(DragEvent event) {
                currentComp = null;
                DragDropButton BTN = new DragDropButton();
                BTN.MoveButton(SetValue, Stack, Constructor);

                event.consume();

            }
        });




    }

//_________________________________/ LINEA DE CONEXION /__________________________

    /**
     * Su funcion es crear las funciones relacionadas con la conexión de nodos, compuertas y relación de datos para operar.
     */
    class BoundLine extends Line {
        /**
         * Define las caracteristicas de la linea que realiza la conexion de las compuertas . Ademas hace set de los valores de inicio y final de la línea,
         * los cuales se conectan a un círculo respectivo.
         *
         * @param startX es el valor inicial de la linea en el eje X.
         * @param startY es el valor inicial de la linea en el eje Y.
         * @param endX   es el valor final de la linea en el eje X.
         * @param endY   es el valor final de la linea en el eje Y.
         * @param toAdd  Es la compuerta agregada al espacio de trabajo, la linea se posiciona originalente sobre este label.
         */
        BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, Compuerta toAdd) {
            Random a = new Random();
            Random b = new Random();
            Random c = new Random();
            startXProperty().bind(startX);
            startYProperty().bind(startY);
            endXProperty().bind(endX);
            endYProperty().bind(endY);
            setStrokeWidth(2);
            int A = a.nextInt(255) + 1;
            int B = b.nextInt(255) + 1;
            int C = c.nextInt(255) + 1;

            //setStroke(Color.rgb(A,B,C));
            setStroke(Color.BLACK);
            setStrokeLineCap(StrokeLineCap.BUTT);
            setMouseTransparent(true);

        }


    }

//_________________________________/ CIRCULOS /__________________________

    /**
     * Esta clase crea la linea que conecta los circulos creados en la clase BoundLine, esto para conectar nodos y entradas.
     * Graficamente enlaza una salida con el final de la linea el cual se conecta a una nueva entrada.
     */
    public class Anchor extends Circle {


        private Compuerta micompuerta;

        /**
         * Define el valor de la compuerta creada.
         *
         * @param compuerta Representa la compuerta creada.
         */
        public void setMicompuerta(Compuerta compuerta) {

            micompuerta = compuerta;
        }

        //_________________________________/ INICIO Y FINAL DE LAS CONEXIONES /__________________________


        Anchor(Color color, DoubleProperty x, DoubleProperty y, Compuerta toAdd, String identifier, String OutID) {

            super(x.get(), y.get(), 5);
            setFill(color.deriveColor(1, 1, 1, 0.5));
            setStroke(color);
            setStrokeWidth(2);
            setStrokeType(StrokeType.OUTSIDE);

            this.setId(identifier);

            x.bind(centerXProperty());
            y.bind(centerYProperty());
            if (color == Color.GRAY) {

            } else {
                enableDrag(toAdd);
            }
        }

        //_________________________________/ DRAG AND DROP PARA EXTENDER LINEAS /__________________________

        private void enableDrag(Compuerta toAdd) {
            final Controller2.Anchor.Delta dragDelta = new Controller2.Anchor.Delta();

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
                    sendOp(mouseEvent.getPickResult().getIntersectedNode());
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

                }
            });
        }

        private class Delta {
            double x, y;
        }

        //_________________________________/ SELECCIONAR OPERACION /__________________________


        /**
         * En caso de que el circulo final de una linea sea conectado a un circulo de entrada de una compuerta, obtiene el ID
         * del nodo intersectado para agregar el valor a una lista de la compuerta respectiva. Una vez la lista contiene dos elementos,
         * esta envia a operar los valores de la lista, guarda el resultado en una variable y vacía la lista para las nuevas operaciones
         * del mismo tipo.
         *
         * @param IntersectedNode Nodo intersectado por el circulo final de la linea, contiene la conexion a una entrada.
         */
        public void sendOp(Node IntersectedNode) {

            if (IntersectedNode instanceof Controller2.Anchor) {
                System.out.println("COMPUERTA CON COMPUERTA");

                Node CompLabel = (IntersectedNode);

                if (CompLabel == null) {
                    System.out.println("no hay conexion");

                } else if (CompLabel.getId().equals("AND")) {
                    ListaAND.addLast(ListaRes.obtenerValor(0));
                    if (ListaAND.size() == 2) {
                        OpAND a = new OpAND();
                        a.operar(ListaAND.getIn1(), ListaAND.getIn2());
                        ListaRes.addLast(a.res);
                        ListaAND = new Lista();
                        ListaRes.deleteValor(0);

                    }
                } else if (CompLabel.getId().equals("OR")) {
                    System.out.println("se conecta a un OR");

                    ListaOR.addLast(ListaRes.obtenerValor(0));
                    if (ListaOR.size() == 2) {
                        OpOR a = new OpOR();
                        a.operar(ListaOR.getIn1(), ListaOR.getIn2());
                        VALOR = a.res;
                        ListaOR = new Lista();
                        ListaRes.addLast(a.res);
                        ListaRes.deleteValor(0);
                    }
                } else if (CompLabel.getId().equals("NOT")) {
                    System.out.println("se conecta a un NOT");
                    OpNOT a = new OpNOT();
                    a.operar(VALOR);
                    VALOR = a.res;
                    ListaRes.addLast(a.res);
                    ListaRes.deleteValor(0);

                } else if (CompLabel.getId().equals("NAND")) {
                    System.out.println("se conecta a un NAND");

                    ListaNAND.addLast(ListaRes.obtenerValor(0));
                    if (ListaNAND.size() == 2) {
                        OpNAND a = new OpNAND();
                        a.operar(ListaNAND.getIn1(), ListaNAND.getIn2());
                        VALOR = a.res;
                        ListaNAND = new Lista();
                        ListaRes.addLast(a.res);
                        ListaRes.deleteValor(0);
                    }
                } else if (CompLabel.getId().equals("NOR")) {
                    System.out.println("se conecta a un NOR");

                    ListaNOR.addLast(ListaRes.obtenerValor(0));
                    if (ListaNOR.size() == 2) {
                        OpNOR a = new OpNOR();
                        a.operar(ListaNOR.getIn1(), ListaNOR.getIn2());
                        VALOR = a.res;
                        ListaNOR = new Lista();
                        ListaRes.addLast(a.res);
                        ListaRes.deleteValor(0);
                    }
                } else if (CompLabel.getId().equals("XOR")) {
                    System.out.println("se conecta a un XOR");

                    ListaXOR.addLast(ListaRes.obtenerValor(0));
                    if (ListaXOR.size() == 2) {
                        OpXOR a = new OpXOR();
                        a.operar(ListaXOR.getIn1(), ListaXOR.getIn2());
                        VALOR = a.res;
                        ListaRes.addLast(a.res);
                        ListaXOR = new Lista();
                        ListaRes.deleteValor(0);
                    }
                } else if (CompLabel.getId().equals("XNOR")) {
                    System.out.println("se conecta a un XNOR");

                    ListaXNOR.addLast(ListaRes.obtenerValor(0));
                    if (ListaXNOR.size() == 2) {
                        OpXNOR a = new OpXNOR();
                        a.operar(ListaXNOR.getIn1(), ListaXNOR.getIn2());
                        VALOR = a.res;
                        ListaXNOR = new Lista();
                        ListaRes.addLast(a.res);
                        ListaRes.deleteValor(0);

                    }
                }

            }


        }


    }
}


