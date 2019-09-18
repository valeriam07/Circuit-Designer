package sample2.Logica;

public class OpAND {
    int res;

    public int operar(int in1, int in2){
        if(in1 == 1 & in2 == 1){
            res = 1;
        }else{
            res = 0;
        }System.out.printf("El resultado de la operacion es: %d \n",  res);
        return res;
    }


}
