package Tarea2.ABB;

public class NodoABB {
    int valor;
    NodoABB hijoIzq;
    NodoABB hijoDer;

    NodoABB(int valor) {
        this.valor = valor;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    NodoABB insertar(int nuevo_valor) {
        NodoABB actual = this;
        while (true) {
            if (nuevo_valor < actual.valor) {
                if (actual.hijoIzq == null) {
                    actual.hijoIzq = new NodoABB(nuevo_valor);
                    return actual.hijoIzq;
                }
                actual = actual.hijoIzq;
            } else if (nuevo_valor > actual.valor) {
                if (actual.hijoDer == null) {
                    actual.hijoDer = new NodoABB(nuevo_valor);
                    return actual.hijoDer;
                }
                actual = actual.hijoDer;
            } else {
                return actual;
            }
        }
    }

    NodoABB buscar(int buscado) {
        NodoABB actual = this;
        while (actual != null) {
            if (buscado == actual.valor) {
                return actual;
            }
            if (buscado < actual.valor) {
                actual = actual.hijoIzq;
            } else {
                actual = actual.hijoDer;
            }
        }
        return null;
    }

}
