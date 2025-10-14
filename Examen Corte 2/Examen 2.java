import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExamenColaMapa {

    static class ColaCircularEnteros {
        private int[] datos;
        private int cabeza;
        private int cola;
        private int tam;  

        public ColaCircularEnteros(int capacidad) {
            datos = new int[capacidad];
            cabeza = 0;
            cola = 0;
            tam = 0;
        }

        public boolean estaVacia() { return tam == 0; }
        public boolean estaLlena() { return tam == datos.length; }

        public void encolar(int x) {
            if (estaLlena()) throw new IllegalStateException("Cola llena");
            datos[cola] = x;
            cola = (cola + 1) % datos.length;
            tam++;
        }

        public int desencolar() {
            if (estaVacia()) throw new IllegalStateException("Cola vaca");
            int v = datos[cabeza];
            cabeza = (cabeza + 1) % datos.length;
            tam--;
            return v;
        }
    }

    // Calcula el balance de paréntesis encolando +1 y -1:
    public static int balanceConCola(String s) {
        // Usamos una cola con capacidad igual a la longitud de la cadena:
        ColaCircularEnteros cola = new ColaCircularEnteros(Math.max(1, s.length()));
        // Le pedimos encolar +1 por '(' y -1 por ')':
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cola.encolar(+1); // abrimos paréntesis
            } else if (c == ')') {
                cola.encolar(-1); // cerramos paréntesis
            }
            //otros caracteres los podemos ignoran
        }
        // Ahora le pedimos desencolar y sumar para obtener el balance final:
        int suma = 0;
        while (!cola.estaVacia()) {
            suma += cola.desencolar();
        }
        return suma;
    }

    // Registra intentos por nombre en un mapa:
    public static int registrarIntento(Map<String,Integer> intentos, String nombre) {
        // Si existe, incrementar en 1, y si no existe, iniciar en 1:
        if (intentos.containsKey(nombre)) {
            int actual = intentos.get(nombre);
            int nuevo = actual + 1;
            intentos.put(nombre, nuevo);
            return nuevo;
        } else {
            intentos.put(nombre, 1);
            return 1;
        }
    }

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        String cadena = sc.nextLine();
        System.out.println("Balance cola: " + balanceConCola(cadena));

        Map<String,Integer> intentos = new HashMap<String,Integer>();
        System.out.println("Intentos (Ana): " + registrarIntento(intentos, "Ana"));
        System.out.println("Intentos (Ana): " + registrarIntento(intentos, "Ana"));

        sc.close();
    }
}

