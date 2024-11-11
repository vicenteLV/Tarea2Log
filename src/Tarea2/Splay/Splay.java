package Tarea2.Splay;

public class Splay {

    NodoSplay raiz;

    //constructor
    public Splay() {
        this.raiz = null;
    }

    //rotacion derecha (zig)
    void rotDer(NodoSplay nodo) {
        NodoSplay y = nodo.hijoIzq;
        nodo.hijoIzq = y.hijoDer;

        if (y.hijoDer != null) {
            y.hijoDer.padre = nodo;
        }

        y.padre = nodo.padre;

        if (nodo.padre == null) {
            this.raiz = y;
        } else if (nodo == nodo.padre.hijoDer) {
            nodo.padre.hijoDer = y;
        } else {
            nodo.padre.hijoIzq = y;
        }

        y.hijoDer = nodo;
        nodo.padre = y;
    }

    // rotacion izquierda (zag)
    void rotIzq(NodoSplay nodo) {
        NodoSplay y = nodo.hijoDer;
        nodo.hijoDer = y.hijoIzq;

        if (y.hijoIzq != null) {
            y.hijoIzq.padre = nodo;
        }

        y.padre = nodo.padre;

        if (nodo.padre == null) {
            this.raiz = y;
        } else if (nodo == nodo.padre.hijoIzq) {
            nodo.padre.hijoIzq = y;
        } else {
            nodo.padre.hijoDer = y;
        }

        y.hijoIzq = nodo;
        nodo.padre = y;
    }

    // operacion splay
    public void splay(NodoSplay x) {
        while (x.padre != null) {
            NodoSplay papa = x.padre;
            NodoSplay abuelo = papa.padre;

            if (abuelo == null) {
                // zig o zag (rotacion simple)
                if (x == papa.hijoIzq) {
                    rotDer(papa);
                } else {
                    rotIzq(papa);
                }
            } else {
                // rotacion doble
                if (x == papa.hijoIzq && papa == abuelo.hijoIzq) {
                    // zig-zig
                    rotDer(abuelo);
                    rotDer(papa);
                } else if (x == papa.hijoDer && papa == abuelo.hijoDer) {
                    // zag-zag
                    rotIzq(abuelo);
                    rotIzq(papa);
                } else if (x == papa.hijoDer && papa == abuelo.hijoIzq) {
                    // zig-zag
                    rotIzq(papa);
                    rotDer(abuelo);
                } else {
                    // Zag-zig
                    rotDer(papa);
                    rotIzq(abuelo);
                }
            }
        }
    }

    //busqueda
    public NodoSplay buscando(int numero) {
        //primero usar funcion auxiliar para buscar nodo
        NodoSplay nodo = busquedaAux(raiz, numero);

        //si encuentra el nodo se sube a la raiz con splay
        if (nodo != null) {
            splay(nodo);
        }
        return nodo;
    }

    //funcion auxiliar para busqueda
    NodoSplay busquedaAux(NodoSplay raiz, int numero) {
        NodoSplay actual = raiz;
        while (actual != null) {
            if (actual.valor == numero) {
                return actual;
            }
            if (numero < actual.valor) {
                if (actual.hijoIzq == null) {
                    return actual;
                }
                actual = actual.hijoIzq;
            } else {
                if (actual.hijoDer == null) {
                    return actual;
                }
                actual = actual.hijoDer;
            }
        }
        return null;
    }

    //insercion
    public void insertando(int entero) {

        //caso arbol vacio
        if (raiz == null) {
            raiz = new NodoSplay(entero);
            return;
        }

        //busca donde insertar
        NodoSplay nodo = busquedaAux(raiz, entero);

        //si ya existe el valor solo se hace splay
        if (nodo.valor == entero) {
            splay(nodo);
            return;
        }

        //creacion nuevo nodo
        NodoSplay nuevo = new NodoSplay(entero);
        nuevo.padre = nodo;

        //insercion segun corresponda
        if (entero < nodo.valor) {
            nodo.hijoIzq = nuevo;
        } else {
            nodo.hijoDer = nuevo;
        }

        splay(nuevo);
    }
}
