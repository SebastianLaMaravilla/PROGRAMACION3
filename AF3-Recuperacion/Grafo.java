import java.util.*;

class Grafo {
    private Map<String, List<Arista>> adyacencia = new HashMap<>();

    public void agregarCiudad(String ciudad) {
        adyacencia.putIfAbsent(ciudad, new ArrayList<>());
    }

    public void agregarRuta(String origen, String destino, int distancia) {
        adyacencia.get(origen).add(new Arista(destino, distancia));
        adyacencia.get(destino).add(new Arista(origen, distancia)); // grafo no dirigido
    }

    public Map<String, Integer> dijkstra(String inicio) {
        Map<String, Integer> distancias = new HashMap<>();
        for (String ciudad : adyacencia.keySet()) {
            distancias.put(ciudad, Integer.MAX_VALUE);
        }
        distancias.put(inicio, 0);

        PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distancia));
        pq.add(new Arista(inicio, 0));

        while (!pq.isEmpty()) {
            Arista actual = pq.poll();
            for (Arista vecino : adyacencia.get(actual.destino)) {
                int nuevaDistancia = distancias.get(actual.destino) + vecino.distancia;
                if (nuevaDistancia < distancias.get(vecino.destino)) {
                    distancias.put(vecino.destino, nuevaDistancia);
                    pq.add(new Arista(vecino.destino, nuevaDistancia));
                }
            }
        }
        return distancias;
    }
}

class Arista {
    String destino;
    int distancia;
    Arista(String d, int dist) {
        destino = d;
        distancia = dist;
    }
}

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.agregarCiudad("Cali");
        grafo.agregarCiudad("Medellín");
        grafo.agregarCiudad("Cartagena");
        grafo.agregarCiudad("Bucaramanga");

        grafo.agregarRuta("Cali", "Medellín", 218);
        grafo.agregarRuta("Medellín", "Cartagena", 261);
        grafo.agregarRuta("Cali", "Bucaramanga", 389);
        grafo.agregarRuta("Bucaramanga", "Cartagena", 365);

        Map<String, Integer> resultado = grafo.dijkstra("Cali");
        System.out.println("Distancias desde Cali: " + resultado);
    }
}