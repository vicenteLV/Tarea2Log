package Tarea2.Splay;

public class NodoSplay {

    int valor;
    NodoSplay hijoIzq, hijoDer, padre;

    NodoSplay (int valor) {
        this.valor = valor;
        this.hijoIzq = this.hijoDer = this.padre = null;
    }


}
