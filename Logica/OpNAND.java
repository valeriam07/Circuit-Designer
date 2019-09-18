package sample2.Logica;

public class OpNAND {
    private int res;

    public void operar(int in1, int in2){
        //'(A * B)

        if(in1 == 1 & in2 == 1){
            res = 0;
        }else{
            res = 1;
        }System.out.printf("El resultado de la operacion es: %d", res);
    }

}
