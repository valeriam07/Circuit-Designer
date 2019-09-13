package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

public class Controller {
    @FXML
    VBox palette;
    @FXML
    StackPane Stack;
    @FXML
    Pane root_pane;
    @FXML
    AnchorPane Constructor;

    Label currentComp = null;


    private Image LIand = new Image(getClass().getResourceAsStream("./and.gif"));
    private Image LIor = new Image(getClass().getResourceAsStream("./or.png"));

    private Label AND = new Label();
    private Label OR = new Label();

    public void boton(ActionEvent event) {
        System.out.println("in");

    }

    public void addImage(){

        AND.setGraphic(new ImageView(LIand));  //LI = Label Image
        palette.getChildren().add(AND);
        OR.setGraphic(new ImageView(LIor));
        palette.getChildren().add(OR);

        SelectComp(AND);
        SelectComp(OR);



    }

    public void SelectComp(Label COMP) {
        COMP.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! " + event.getSource());

                if (currentComp == null) {
                    currentComp = COMP;
                    if (COMP == AND) {
                        DragDrop(AND, LIand);
                    } else if (COMP == OR) {
                        DragDrop(OR, LIor);
                    }
                }
            }
        });
    }
//hola


    public void DragDrop (Label COMP, Image IMG){

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
            @Override
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


        Stack.setOnDragEntered(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                System.out.println("onDragEntered");
                if (event.getGestureSource() != Stack &&
                        event.getDragboard().hasImage()) {
                }
                event.consume();

            }
        });


        Stack.setOnDragExited(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                System.out.println("onDragExited");

                if(event.getTarget() instanceof StackPane) {

                    Label source = (Label) event.getGestureSource();
                    Label toAdd = new Label();
                    toAdd.setGraphic(new ImageView(IMG));

                    toAdd.setTranslateX(event.getX());
                    toAdd.setTranslateY(event.getY());
                    Constructor.getChildren().add(toAdd);

                    toAdd.setGraphic(new ImageView(IMG));




                }

                event.consume();

            }

        });

        COMP.setOnDragDone(new EventHandler <DragEvent>() {
            @Override

            public void handle(DragEvent event) {

                System.out.println("onDragDone");
                currentComp = null;



                event.consume();

            }
        });
    }

}


