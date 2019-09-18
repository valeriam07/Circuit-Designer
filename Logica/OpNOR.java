package sample2.Logica;

public class OpNOR {
    int res;

    public void operar(int in1, int in2){
        if(in1 == 0 & in2 == 0){
            res = 1;
        }else{
            res = 0;
        }System.out.printf("El resultado de la operacion es: %d \n",  res);
    }


}
