# Tarea 2: Diseño y Análisis de Algoritmos - Splay Trees vs Árboles Binarios de Búsqueda

Este proyecto implementa una comparación entre dos estructuras de datos: los **Árboles Binarios de Búsqueda (ABB)** y los **Splay Trees**. El objetivo principal es evaluar el rendimiento de ambas estructuras bajo diferentes escenarios de prueba, considerando tanto el tiempo de acceso como el comportamiento de inserción y búsqueda, especialmente en situaciones con accesos sesgados.

## Contenido

1. **ABB**: Implementación de un Árbol Binario de Búsqueda clásico.
2. **Splay Tree**: Implementación de un Árbol de Búsqueda Binaria autoajustable.
3. **Main**: Clase principal que realiza experimentos y gestiona la entrada y salida de datos.
4. **Experimentación**: Cuatro experimentos diseñados para evaluar el rendimiento bajo diferentes condiciones de acceso y distribución de los elementos.

## Descripción de las Estructuras

### Árbol Binario de Búsqueda (ABB)

Un **Árbol Binario de Búsqueda** (ABB) es una estructura de datos en la que los nodos se organizan de tal manera que para cada nodo, todos los elementos de su subárbol izquierdo son menores y todos los del subárbol derecho son mayores. Esto permite realizar operaciones de búsqueda, inserción y eliminación en **O(log n)** en el caso promedio si el árbol está balanceado.

#### Clases

- **NodoABB**: Representa un nodo en el árbol, con atributos para almacenar el valor y las referencias a sus hijos izquierdo y derecho. Contiene los métodos `insertar` y `buscar`, ambos implementados de manera iterativa.
- **ABB**: La clase principal que gestiona el árbol binario, manteniendo una referencia a la raíz y manejando la inserción y búsqueda de elementos.

### Splay Tree

Un **Splay Tree** es una estructura de datos autoajustable que, al realizar operaciones de acceso (búsqueda, inserción), ajusta la estructura del árbol moviendo el nodo accedido a la raíz mediante rotaciones. Este proceso de reestructuración permite mejorar el tiempo de acceso en escenarios con accesos repetidos a nodos específicos.

#### Clases

- **NodoSplay**: Similar a `NodoABB`, pero con un atributo adicional para la referencia al nodo padre, necesario para realizar las rotaciones en el árbol.
- **Splay**: La clase que implementa el Splay Tree, con operaciones de rotación (zig, zag, zig-zig, etc.) y métodos para realizar inserciones y búsquedas ajustadas a la técnica de "splay".

### Main

La clase **Main** contiene la lógica de los experimentos y la gestión de la entrada y salida de datos. Esta clase incluye los siguientes métodos auxiliares:

- **generarAleatorios(N)**: Genera una lista de N enteros aleatorios y distintos.
- **crearArregloBusquedas(A, M)**: Genera una lista de búsquedas, donde cada elemento de A se repite M/|A| veces.
- **calcularC(N)**: Calcula la constante C según la fórmula dada para experimentos con distribuciones de probabilidad.
- **crearArregloBusquedasSesgadas(A, M, C)**: Crea un arreglo de búsquedas sesgadas, basado en probabilidades calculadas para cada índice de A.

### Experimentos

Los experimentos están diseñados para evaluar el desempeño de ambos árboles bajo diferentes configuraciones. En cada experimento se miden tiempos de inserción y búsqueda en ambos árboles.

1. **Experimento 1**: Inserción de enteros desordenados con un número de búsqueda constante para todos los elementos del arreglo.
2. **Experimento 2**: Inserción de un arreglo desordenado con búsquedas sesgadas según probabilidades calculadas.
3. **Experimento 3**: Inserción de un arreglo ordenado con búsquedas uniformes para todos los elementos.
4. **Experimento 4**: Inserción de un arreglo ordenado con búsquedas sesgadas.

### Resultados

Los resultados de los experimentos se almacenan en archivos `.txt` en formato CSV. Los archivos generados incluyen el tiempo de inserción y búsqueda para cada uno de los experimentos realizados.

## Requisitos

- **Java Development Kit (JDK)** instalado en el ordenador.

## Instrucciones

1. Clonar el repositorio o descargar el código fuente.
2. Abrir una terminal en la carpeta del proyecto.
3. Navegar hasta la carpeta `src/Tarea2`.
4. Abrir el archivo `Main.java` y ejecutar la función `main`, que correrá los experimentos secuencialmente (Recomendación: Ejecutar el programa en un IDE como IntelliJ).
5. El archivo ejecutará los experimentos y generará los archivos de resultados en `src` (los archivos en la carpeta `datosUsados`, como `datosUsados/experimento1.txt` fueron puestos manualmente ahí).

## Autores

- Nicolas del Valle
- Vicente López
