package sample2.Logica;

import javafx.scene.Node;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import sample2.Controller2;
import sample2.ListasEnlazadas.Lista;

public class SelectOp{
    Lista ListaAND = new Lista();      //se crea una nueva lista para los valores de entrada
    Lista ListaOR = new Lista();
    private Lista ListaNOR = new Lista();
    Lista ListaXOR = new Lista();
    Lista ListaNAND = new Lista();
    Lista ListaXNOR = new Lista();

    public void sendOp(Node IntersectedNode, int VALOR){

        if (IntersectedNode instanceof Controller2.Anchor) {


            Node CompLabel = (IntersectedNode);
            System.out.println(CompLabel.getId());

            if (CompLabel == null) {
                System.out.println("no hay conexion");

            }

            //CONDICIONALES PARA ENVIAR A OPERAR

            if (CompLabel.getId().equals("AND")) {
                System.out.println("se conecta a un AND");

                ListaAND.addLast(VALOR);
                if (ListaAND.size() == 2) {
                    System.out.println("entradas" + ListaAND.getIn1() + ListaAND.getIn2());
                    OpAND a = new OpAND();
                    a.operar(ListaAND.getIn1(), ListaAND.getIn2());
                    ListaAND = null;
                }
            } else if (CompLabel.getId().equals("OR")) {
                System.out.println("se conecta a un OR");

                ListaOR.addLast(VALOR);
                if (ListaOR.size() == 2) {
                    System.out.println("entradas" + ListaOR.getIn1() + ListaOR.getIn2());
                    OpOR a = new OpOR();
                    a.operar(ListaOR.getIn1(), ListaOR.getIn2());
                    ListaOR = null;
                }
            } else if (CompLabel.getId().equals("NOT")) {
                System.out.println("se conecta a un NOT");

                System.out.println("entrada" + VALOR);
                OpNOT a = new OpNOT();
                a.operar(VALOR);

            } else if (CompLabel.getId().equals("NAND")) {
                System.out.println("se conecta a un NAND");

                ListaNAND.addLast(VALOR);
                if (ListaNAND.size() == 2) {
                    System.out.println("entradas" + ListaNAND.getIn1() + ListaNAND.getIn2());
                    OpNAND a = new OpNAND();
                    a.operar(ListaNAND.getIn1(), ListaNAND.getIn2());
                    ListaNAND = null;
                }
            } else if (CompLabel.getId().equals("NOR")) {
                System.out.println("se conecta a un NOR");

                ListaNOR.addLast(VALOR);
                if (ListaNOR.size() == 2) {
                    System.out.println("entradas" + ListaNOR.getIn1() + ListaNOR.getIn2());
                    OpNOR a = new OpNOR();
                    a.operar(ListaNOR.getIn1(), ListaNOR.getIn2());
                    ListaNOR = null;
                }
            } else if (CompLabel.getId().equals("XOR")) {
                System.out.println("se conecta a un XOR");

                ListaXOR.addLast(VALOR);
                if (ListaXOR.size() == 2) {
                    System.out.println("entradas" + ListaXOR.getIn1() + ListaXOR.getIn2());
                    OpXOR a = new OpXOR();
                    a.operar(ListaXOR.getIn1(), ListaXOR.getIn2());
                    ListaXOR = null;
                }
            } else if (CompLabel.getId().equals("XNOR")) {
                System.out.println("se conecta a un XNOR");

                ListaXNOR.addLast(VALOR);
                if (ListaXNOR.size() == 2) {
                    System.out.println("entradas" + ListaXNOR.getIn1() + ListaXNOR.getIn2());
                    OpXNOR a = new OpXNOR();
                    a.operar(ListaXNOR.getIn1(), ListaXNOR.getIn2());
                    ListaXNOR = null;

                }
            }
        }
        }

    }

