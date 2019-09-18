package sample2.Logica;

public class OpOR {
    int res;

    public void operar(int in1, int in2){
        if(in1 == 0 & in2 == 0){
            res = 0;
        }else{
            res = 1;
        }System.out.printf("El resultado de la operacion es: %d \n",  res);
    }

}
