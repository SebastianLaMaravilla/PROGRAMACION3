import java.util.*;

public class Dijkstra {

    // Método que implementa el algoritmo de Dijkstra
    public static void dijkstra(int[][] grafo, int origen) {
        int n = grafo.length; // Número de vértices del grafo

        int[] distancia = new int[n]; // Guarda las distancias mínimas desde el origen
        boolean[] visitado = new boolean[n]; // Marca si un vértice ya fue procesado

        // Inicializa todas las distancias en infinito y los visitados en falso
        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(visitado, false);

        distancia[origen] = 0; // La distancia del origen a sí mismo siempre es 0

        // Recorremos todos los vértices
        for (int i = 0; i < n - 1; i++) {
            // Encuentra el vértice con la distancia mínima que aún no ha sido visitado
            int u = minimo(distancia, visitado);
            visitado[u] = true; // Marcamos el vértice como visitado

            // Actualizamos las distancias de los vértices adyacentes al vértice elegido
            for (int v = 0; v < n; v++) {
                // Si el vértice v no ha sido visitado, hay conexión y se puede mejorar la distancia
                if (!visitado[v] && grafo[u][v] != 0 && distancia[u] != Integer.MAX_VALUE
                        && distancia[u] + grafo[u][v] < distancia[v]) {
                    distancia[v] = distancia[u] + grafo[u][v];
                }
            }
        }

        // Muestra los resultados
        mostrarResultados(distancia, origen);
    }

    // Método que busca el vértice con la distancia mínima aún no visitado
    private static int minimo(int[] distancia, boolean[] visitado) {
        int min = Integer.MAX_VALUE, indiceMin = -1;

        for (int v = 0; v < distancia.length; v++) {
            if (!visitado[v] && distancia[v] <= min) {
                min = distancia[v];
                indiceMin = v;
            }
        }
        return indiceMin;
    }

    // Método para imprimir las distancias desde el vértice origen
    private static void mostrarResultados(int[] distancia, int origen) {
        System.out.println("Distancias mínimas desde el vértice " + origen + ":");
        for (int i = 0; i < distancia.length; i++) {
            System.out.println("→ Hasta el vértice " + i + " = " + distancia[i]);
        }
    }

    // Método principal para probar el algoritmo
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir número de vértices
        System.out.print("Digite el número de vértices: ");
        int n = sc.nextInt();

        // Crear la matriz de adyacencia
        int[][] grafo = new int[n][n];
        System.out.println("Ingrese la matriz de adyacencia (0 si no hay conexión):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grafo[i][j] = sc.nextInt();
            }
        }

        // Pedir vértice de inicio
        System.out.print("Ingrese el vértice origen: ");
        int origen = sc.nextInt();

        // Ejecutar Dijkstra
        dijkstra(grafo, origen);
    }
}
