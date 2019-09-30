package sample2;

import javafx.scene.control.Label;

/**
 * Crea una nuevas compuertas cada vez que se agrega un label al Pane Contructor para que sean
 * operables.
 */ public class Compuerta extends Label {

    private Controller2.Anchor circulo;

    /**
     *Instancia el circulo como su propia compuerta para reconoces mas facilmente el enlace
     *y ejecutar su salida.
     *
     * @param circulo es el circulo al que se le asigna el valor de la compuerta de la que proviene.
     *
     */
     public void setCirculo(Controller2.Anchor circulo){
         this.circulo = circulo;
         circulo.setMicompuerta(this);
         //System.out.println("comp " +this.getId());

     }


}
