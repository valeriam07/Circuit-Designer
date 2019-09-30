package sample2.Logica;

/**
 * Realiza la operacion logica NOT
 */
public class OpNOT {
    public int res;

    /**
     *
     * @param in entrada de la compuerta
     */
    public void operar(int in){
        if(in == 1){
            res = 0;
        }else if(in == 0){
            res = 1;
        }System.out.printf("El resultado de la operacion es: %d \n",  res);
    }

}
