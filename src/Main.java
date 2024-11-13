import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import Tarea2.ABB.*;
import Tarea2.Splay.*;

public class Main {

    // funcion para generar numeros aleatorios sin repeticion
    static int[] generarAleatorios(int N) {
        Set<Integer> numerosUnicos = new HashSet<>();
        Random rand = new Random();

        //generar N números únicos aleatorios
        while (numerosUnicos.size() < N) {
            // usamos Integer.MAX_VALUE para limite superior
            numerosUnicos.add(rand.nextInt(Integer.MAX_VALUE));
        }

        return numerosUnicos.stream().mapToInt(Integer::intValue).toArray();
    }

    //funcion para crear arreglo B con las M busquedas
    private static int[] crearArregloBusquedas(int[] A, int M) {
        int N = A.length;
        int busquedasPorElemento = M / N;
        ArrayList<Integer> B = new ArrayList<>();

        // para cada elemento en A, lo agregamos M/N veces a B
        for (int elemento : A) {
            for (int j = 0; j < busquedasPorElemento; j++) {
                B.add(elemento);
            }
        }

        Collections.shuffle(B); //mezclar aleatoriamente las busquedas
        return B.stream().mapToInt(Integer::intValue).toArray();
    }

    //calculo de C
    private static double calcularC(int N) {
        double sumatoria = 0;
        for (int i = 0; i < N; i++) {
            sumatoria += 1.0 / Math.pow(i+1.0,2);
        }
        double C = 1.0 /sumatoria;

        return C;
    }

    // funcion para crear B con distribucion sesgada
    private static int[] crearArregloBusquedasSesgadas(int[] A, int M, double C) {
        ArrayList<Integer> B = new ArrayList<>();
        int N = A.length;
        int totalRepeticiones = 0;

        // para cada elemento i, calcular cuantas veces debe aparecer
        for (int i = 0; i < N; i++) {
            double probabilidad = C / Math.pow(i+1.0,2);
            int repeticiones = (int) Math.floor(M * probabilidad);
            totalRepeticiones += repeticiones;

            // agregar el elemento 'repeticiones' veces
            for (int j = 0; j < repeticiones; j++) {
                B.add(A[i]);
            }
        }

        Collections.shuffle(B);
        return B.stream().mapToInt(Integer::intValue).toArray();
    }


    //EXPERIMENTOS

    //exp1
    public static void experimento1() {
        try {
            FileWriter writer = new FileWriter("experimento1.txt");
            writer.write("N,Tiempo_ABB,Tiempo_Splay\n"); // header del archivo

            //casos para cada N desde 0.1*M hasta 1*M
            for (int N = 100000; N <= 1000000; N += 100000) {
                int M = 100 * N;

                //generar datos
                int[] A = generarAleatorios(N);
                int[] B = crearArregloBusquedas(A, M);

                //experimento con ABB
                ABB abb = new ABB();
                for (int num : A) {
                    abb.insertando(num);
                }

                //tiempo incial
                long startTime = System.nanoTime();
                for (int num : B) {
                    abb.buscando(num);
                }
                long tiempoABB = System.nanoTime() - startTime;

                //experimento con Splay
                Splay splay = new Splay();
                for (int num : A) {
                    splay.insertando(num);
                }

                long startTime2 = System.nanoTime();
                for (int num : B) {
                    splay.buscando(num);
                }
                long tiempoSplay = System.nanoTime() - startTime2;

                // guardar resultados
                writer.write(N + "," + tiempoABB + "," + tiempoSplay + "\n");
                System.out.println("Completado N = " + N);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //bloque try/catch para manejar posibles errores en el manejo de archivos
    }

    //exp2
    public static void experimento2() {
        try {
            FileWriter writer = new FileWriter("experimento2.txt");
            writer.write("N,Tiempo_ABB,Tiempo_Splay\n");

            //casos para cada N
            for (int N = 100000; N <= 1000000; N += 100000) {
                int M = 100 * N;

                //generar datos
                int[] A = generarAleatorios(N);
                double C = calcularC(N);
                int[] B = crearArregloBusquedasSesgadas(A, M, C);

                // experimento con ABB
                ABB abb = new ABB();
                for (int num : A) {
                    abb.insertando(num);
                }

                long startTime = System.nanoTime();
                for (int num : B) {
                    abb.buscando(num);
                }
                long tiempoABB = System.nanoTime() - startTime;

                //experimento con Splay
                Splay splay = new Splay();
                for (int num : A) {
                    splay.insertando(num);
                }

                long startTime2 = System.nanoTime();
                for (int num : B) {
                    splay.buscando(num);
                }
                long tiempoSplay = System.nanoTime() - startTime2;

                // guardar resultados
                writer.write(N + "," + tiempoABB + "," + tiempoSplay + "\n");
                System.out.println("Completado N = " + N);
            }

            writer.close();
        } catch (IOException e) { //manejo de excepciones con el archivo
            e.printStackTrace();
        }
    }

    //exp3
    public static void experimento3() {
        try {
            FileWriter writer = new FileWriter("experimento3.txt");
            writer.write("N,Tiempo_ABB,Tiempo_Splay\n");

            for (int N = 100000; N <= 1000000; N += 100000) {
                int M = 100 * N;

                //generar datos y ordenarlos
                int[] A = generarAleatorios(N);
                Arrays.sort(A);  //diferencia con exp1, ordenamiento

                // resto de la implementacion es como en el primer experimento
                int[] B = crearArregloBusquedas(A, M);

                // Experimento con ABB
                ABB abb = new ABB();
                for (int num : A) {  // Insertamos en orden
                    abb.insertando(num);
                }

                long startTime = System.nanoTime();
                for (int num : B) {
                    abb.buscando(num);
                }
                long tiempoABB = System.nanoTime() - startTime;

                //experimento con Splay
                Splay splay = new Splay();
                for (int num : A) {  // insertamos en orden
                    splay.insertando(num);
                }

                startTime = System.nanoTime();
                for (int num : B) {
                    splay.buscando(num);
                }
                long tiempoSplay = System.nanoTime() - startTime;

                writer.write(N + "," + tiempoABB + "," + tiempoSplay + "\n");
                System.out.println("Completado N = " + N);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //exp4
    public static void experimento4() {
        try {
            FileWriter writer = new FileWriter("experimento4.txt");
            writer.write("N,Tiempo_ABB,Tiempo_Splay\n");
            writer.flush(); // forzar escritura del header

            //para cada N
            for (int N = 100000; N <= 1000000; N += 100000) {
                int M = 100 * N;

                // generar datos
                int[] A = generarAleatorios(N);
                int[] C = A.clone();  // copia para mantener A desordenado
                Arrays.sort(C);       //ordenamos C para insercion ordenada

                // calculo de busquedas sesgadas usando A
                double constC = calcularC(N);
                int[] B = crearArregloBusquedasSesgadas(A, M, constC);

                // experimento con ABB (insercion ordenada)
                ABB abb = new ABB();
                for (int num : C) {  //usamos C para insertar
                    abb.insertando(num);
                }

                long startTime = System.nanoTime();
                for (int num : B) {
                    abb.buscando(num);
                }
                long tiempoABB = System.nanoTime() - startTime;

                //exp con Splay (insercion ordenada)
                Splay splay = new Splay();
                for (int num : C) {  //C para insertar
                    splay.insertando(num);
                }

                startTime = System.nanoTime();
                for (int num : B) {
                    splay.buscando(num);
                }
                long tiempoSplay = System.nanoTime() - startTime;

                writer.write(N + "," + tiempoABB + "," + tiempoSplay + "\n");
                writer.flush(); // forzar escritura después de cada N
                System.out.println("Completado N = " + N);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //funcion main
    public static void main(String[] args) {
        //ejecutamos los experimentos secuencialmente
        //System.out.println("Iniciando Experimento 1...");
        //experimento1();
        //System.out.println("Iniciando Experimento 2...");
        //experimento2();
        //System.out.println("Iniciando Experimento 3...");
        //experimento3();
        System.out.println("Iniciando Experimento 4...");
        experimento4();
        System.out.println("Todos los experimentos completados.");
    }
}