import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class ArbolExpresion {
    abstract static class Nodo {
        Nodo izquierdo;
        Nodo derecho;
        abstract double evaluar(Map<String,Double> vars);
    }

    static class NodoConstante extends Nodo {
        double valor;
        NodoConstante(double v) { valor = v; }
        @Override
        double evaluar(Map<String,Double> vars) {
            return valor;
        }
    }

    static class NodoVariable extends Nodo {
        String nombre;
        NodoVariable(String n) { nombre = n; }
        @Override
        double evaluar(Map<String,Double> vars) {
            return vars.get(nombre);
        }
    }

    static class NodoOperador extends Nodo {
        char op;
        NodoOperador(char o, Nodo l, Nodo r) {
            op = o;
            izquierdo = l;
            derecho = r;
        }
        @Override
        double evaluar(Map<String,Double> vars) {
            double a = izquierdo.evaluar(vars);
            double b = derecho.evaluar(vars);
            switch(op) {
                case '+': return a + b;
                case '-': return a - b;
                case '*': return a * b;
                case '/': return a / b;
                default: throw new RuntimeException();
            }
        }
    }

    public static Nodo construirPrefija(Queue<String> tokens) {
        if (tokens.isEmpty()) throw new RuntimeException();
        String t = tokens.poll();
        if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
            Nodo l = construirPrefija(tokens);
            Nodo r = construirPrefija(tokens);
            return new NodoOperador(t.charAt(0), l, r);
        }
        if (t.matches("-?\\d+(\\.\\d+)?")) {
            return new NodoConstante(Double.parseDouble(t));
        }
        return new NodoVariable(t);
    }

    public static void main(String[] args) {
        Queue<String> q1 = new LinkedList<>(Arrays.asList("*","+","x","y","z"));
        Nodo expr1 = construirPrefija(q1);
        System.out.println(expr1.evaluar(Map.of("x",2.0,"y",3.0,"z",4.0)));

        Queue<String> q2 = new LinkedList<>(Arrays.asList("/","-","a","b","+","c","d"));
        Nodo expr2 = construirPrefija(q2);
        System.out.println(expr2.evaluar(Map.of("a",10.0,"b",4.0,"c",1.0,"d",1.0)));

        Queue<String> q3 = new LinkedList<>(Arrays.asList("*","3","+","4","5"));
        Nodo expr3 = construirPrefija(q3);
        System.out.println(expr3.evaluar(Map.of()));
    }
}

