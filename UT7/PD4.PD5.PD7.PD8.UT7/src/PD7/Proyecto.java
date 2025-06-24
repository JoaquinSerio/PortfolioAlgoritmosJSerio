package PD7;

import tdas.*;
import java.io.*;
import java.util.*;

public class Proyecto {

    public static void main(String[] args) {
        procesarProyecto("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\precedencias.txt", "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\orden.txt");
        procesarProyecto("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\precedencias2.txt", "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\orden2.txt");
    }

    private static void procesarProyecto(String archivoPrecedencias, String archivoSalida) {
        try {
            Map<String, Integer> tareas = cargarTareas("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\tareas.txt");
            Collection<IVertice> vertices = crearVertices(tareas);
            Collection<IArista> precedencias = cargarPrecedencias(archivoPrecedencias, tareas);

            TGrafoDirigido grafo = new TGrafoDirigido(vertices, precedencias);

            LinkedList<Comparable> orden = grafo.ordenParcial();

            grafo.listarTareas(orden);

            escribirOrden(orden, archivoSalida);

            System.out.println("Archivo " + archivoSalida + " generado exitosamente.\n");

        } catch (Exception e) {
            System.err.println("Error procesando proyecto: " + e.getMessage());
        }
    }

    private static Map<String, Integer> cargarTareas(String archivo) throws IOException {
        Map<String, Integer> tareas = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (!linea.isEmpty()) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String codigo = partes[0].trim();
                    try {
                        int tiempo = Integer.parseInt(partes[1].trim());
                        tareas.put(codigo, tiempo);
                    } catch (NumberFormatException e) {
                        System.err.println("Error en formato de tiempo para tarea: " + codigo);
                    }
                }
            }
        }
        br.close();
        return tareas;
    }

    private static Collection<IVertice> crearVertices(Map<String, Integer> tareas) {
        Collection<IVertice> vertices = new ArrayList<>();
        for (String codigo : tareas.keySet()) {
            vertices.add(new TVertice<>(codigo));
        }
        return vertices;
    }

    private static Collection<IArista> cargarPrecedencias(String archivo, Map<String, Integer> tareas) throws IOException {
        Collection<IArista> precedencias = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (!linea.isEmpty()) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String codigoOrigen = partes[0].trim();
                    String codigoDestino = partes[1].trim();

                    if (tareas.containsKey(codigoOrigen) && tareas.containsKey(codigoDestino)) {
                        precedencias.add(new TArista(codigoOrigen, codigoDestino, 1.0));
                    } else {
                        System.err.println("Tarea no encontrada en precedencia: " + codigoOrigen + " -> " + codigoDestino);
                    }
                }
            }
        }
        br.close();
        return precedencias;
    }

    private static void escribirOrden(LinkedList<Comparable> orden, String archivo) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(archivo));
        for (Comparable tarea : orden) {
            pw.println(tarea);
        }
        pw.close();
    }
}