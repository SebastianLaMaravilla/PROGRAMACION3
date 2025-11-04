import java.util.Random;

public class ArbolBinario {

    static class Nodo {
        int valor;
        Nodo izq, der;
    }

    static Nodo nuevoNodo(int valor) {
        Nodo n = new Nodo();
        n.valor = valor;
        return n;
    }

    static Nodo insertar(Nodo raiz, int valor) {
        if (raiz == null) return nuevoNodo(valor);
        if (valor < raiz.valor)
            raiz.izq = insertar(raiz.izq, valor);
        else
            raiz.der = insertar(raiz.der, valor);
        return raiz;
    }

    static void preOrden(Nodo n) {
        if (n != null) {
            System.out.print(n.valor + " ");
            preOrden(n.izq);
            preOrden(n.der);
        }
    }

    static void inOrden(Nodo n) {
        if (n != null) {
            inOrden(n.izq);
            System.out.print(n.valor + " ");
            inOrden(n.der);
        }
    }

    static void postOrden(Nodo n) {
        if (n != null) {
            postOrden(n.izq);
            postOrden(n.der);
            System.out.print(n.valor + " ");
        }
    }

    static int altura(Nodo n) {
        if (n == null) return 0;
        return 1 + Math.max(altura(n.izq), altura(n.der));
    }

    static boolean estaBalanceado(Nodo n) {
        if (n == null) return true;
        int altIzq = altura(n.izq);
        int altDer = altura(n.der);
        if (Math.abs(altIzq - altDer) > 1) return false;
        return estaBalanceado(n.izq) && estaBalanceado(n.der);
    }

    public static void main(String[] args) {
        Random random = new Random();
        Nodo raiz = null;

        for (int i = 0; i < 10; i++) {
            int valor = random.nextInt(100);
            raiz = insertar(raiz, valor);
        }

        System.out.println("Recorrido en preorden:");
        preOrden(raiz);

        System.out.println("\nRecorrido en inorden:");
        inOrden(raiz);

        System.out.println("\nRecorrido en postorden:");
        postOrden(raiz);

        if (estaBalanceado(raiz))
            System.out.println("\n\nEl árbol está balanceado");
        else
            System.out.println("\n\nEl árbol no está balanceado");
    }
}