package sample2.ListasEnlazadas;

/**
 * Esta función crea listas enlazadas para guardar los valores por operar,
 * así como los resultados de las compuertas logicas y datos del circuito
 * creado por el usuario.
 */
public class Lista {
    private Nodo first;
    private int size = 0;
    private String Lista = "";
    int in1;
    int in2;

    /**
     * Es el constructor de la clase "Lista", instancia el valor de first; el cual representa
     * la cabeza o primer nodo de la lista.
     * */
    public Lista() {
        first = null;
        this.Lista = Lista;
    }

    /**
     *Crea nodos al final de una lista para guardar valores.
     *
     * @param e Es el valor que se ingresara a la lista.
     */
    public void addLast(int e) {

        if (this.first == null) {
            this.first = new Nodo(e);
            in1 = e;

            Lista = Lista + e +" , ";

        } else {
            //System.out.println("next");
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

    /**
     *
     * @return size, el cual representa el tamano de la lista o la cantidad de nodos que contiene.
     */

    public int size(){
        System.out.println(size);
        return size;
    }


    public int obtenerValor(int posicion){
        if(posicion>=0 && posicion<size){
            if (posicion == 0) {
                return first.getValor();
            }else{
                Nodo aux = first;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.next;
                }
                return aux.getValor();
            }
        }
        return posicion;
    }

    public void deleteValor(int posicion){
        if(posicion>=0 && posicion<size){
            if (posicion == 0) {
                first = null;
            }else{
                Nodo aux = first;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.next;
                }
            }
        }
    }



    /**
     *En el caso de las listas para los valores conectados a las compuertas:
     * @return in1 Es la primera entrada de la operacion.
     */
    public int getIn1(){
        return in1;
    }

    /**
     *
     * @return in2 Es la segunda entrada a operar.
     */
    public int getIn2(){
        return in2;
    }

    public void delete(){
        Nodo current = first;
        if (current != null){
            current = null;
            current = current.next;
        }else{
            System.out.println("la lista esta vacia");
        }
    }




}


