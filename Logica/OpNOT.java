package sample2.Logica;

public class OpNOT {
    int res;
    public void operar(int in){
        if(in == 1){
            res = 0;
        }else if(in == 0){
            res = 1;
        }System.out.printf("El resultado de la operacion es: %d \n",  res);
    }

}
