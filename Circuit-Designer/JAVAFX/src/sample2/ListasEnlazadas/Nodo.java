package sample2.ListasEnlazadas;

/**
 * Instancia variables del dato actual y Nodo next.
 */
public class Nodo {
    int dato;
    Nodo next;

    /**
     * Guarda los valores de las variables de la clase
     * @param dato Se debe guardar para ser agregado a la lista
     */
    public Nodo(int dato){
        this.dato = dato;
        this.next = null;
    }

    public int getValor() {
        return dato;
    }
}
