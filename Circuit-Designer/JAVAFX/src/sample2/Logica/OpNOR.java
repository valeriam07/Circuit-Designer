package sample2.Logica;

/**
 * Realiza la operacion logica NOR
 */
public class OpNOR {
    public int res;

    /**
     *
     * @param in1 entrada 1 de la compuerta
     * @param in2 entrada 2 de la compuerta
     */

    public void operar(int in1, int in2){
        if(in1 == 0 & in2 == 0){
            res = 1;
        }else{
            res = 0;
        }System.out.printf("El resultado de la operacion es: %d \n",  res);
    }


}
