package sample2.Logica;

/**
 * Realiza la operacion logica NAND
 */
public class OpNAND {
    public int res;
    /**
     *
     * @param in1 entrada 1 de la compuerta
     * @param in2 entrada 2 de la compuerta
     */

    public void operar(int in1, int in2){
        //'(A * B)

        if(in1 == 1 & in2 == 1){
            res = 0;
        }else{
            res = 1;
        }System.out.printf("El resultado de la operacion es: %d", res);
    }

}
