package sample2.ListasEnlazadas;

import org.omg.CORBA.Object;

public class Lista {
    private Nodo first;
    private int size = 0;
    private String Lista = "";
    int in1;
    int in2;


    public Lista() {
        first = null;
        this.Lista = Lista;
    }

    public void addLast(int e) {

        if (this.first == null) {
            System.out.println("first");
            this.first = new Nodo(e);
            in1 = e;

            Lista = Lista + e +" , ";
            System.out.println(e);

        } else {
            System.out.println("next");
            Nodo current = this.first;

            Nodo current1;
            while(current.next != null) {
                current1 = current.next;

            }
            Lista = Lista + e +" , ";
            in2 = e;

        }
        size++;
        System.out.println("[" + Lista + "]");


    }

    public int size(){
        System.out.println(size);
        return size;
    }

    public Object obtenerValor(int index){
        int contador = 0;
        Nodo temp = first;
        while (contador < index){
            temp = temp.next;
            contador++;
        } System.out.println("el valor es:" + temp);
        return (Object) temp;

    }

    public void Vaciar(){
        Nodo current = this.first;
        while(size != 1){
            current = null;
            current = current.next;
        }

    }
    public int getIn1(){
        return in1;
    }
    public int getIn2(){
        return in2;
    }




}


