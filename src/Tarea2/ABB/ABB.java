package Tarea2.ABB;

public class ABB {
    NodoABB raiz;

    //constructor
    public ABB () {
        this.raiz = null;
    }

    public NodoABB insertando(int nuevo_valor) {
        if (this.raiz == null) {
            this.raiz = new NodoABB(nuevo_valor);
            return this.raiz;
        } else {
            return this.raiz.insertar(nuevo_valor);
        }
    }

    public NodoABB buscando(int buscado) {
        if (this.raiz == null) {
            return null;
        } else {
            return this.raiz.buscar(buscado);
        }
    }

}
