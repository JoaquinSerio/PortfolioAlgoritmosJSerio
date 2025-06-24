package tdas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneradorGraficos {

    public static void main(String[] args) {
        generarDatosParaGrafico();
    }

    public static void generarDatosParaGrafico() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("UT6/PD2UT6/rendimiento_hash.csv"));
            List<String> lineasHTML = new ArrayList<>();

            lineasHTML.add("<!DOCTYPE html>");
            lineasHTML.add("<html>");
            lineasHTML.add("<head>");
            lineasHTML.add("<title>Rendimiento Tabla Hash</title>");
            lineasHTML.add("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js\"></script>");
            lineasHTML.add("</head>");
            lineasHTML.add("<body>");
            lineasHTML.add("<h1>Evaluación de Rendimiento - Tabla Hash</h1>");
            lineasHTML.add("<canvas id=\"grafico\" width=\"800\" height=\"400\"></canvas>");
            lineasHTML.add("<script>");

            List<String> factores = new ArrayList<>();
            List<String> insercion = new ArrayList<>();
            List<String> busquedaExitosa = new ArrayList<>();
            List<String> busquedaSinExito = new ArrayList<>();

            String linea;
            reader.readLine();

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                factores.add(datos[0]);
                insercion.add(datos[1]);
                busquedaExitosa.add(datos[2]);
                busquedaSinExito.add(datos[3]);
            }

            lineasHTML.add("const ctx = document.getElementById('grafico').getContext('2d');");
            lineasHTML.add("const chart = new Chart(ctx, {");
            lineasHTML.add("    type: 'line',");
            lineasHTML.add("    data: {");
            lineasHTML.add("        labels: [" + String.join(",", factores) + "],");
            lineasHTML.add("        datasets: [{");
            lineasHTML.add("            label: 'Inserción',");
            lineasHTML.add("            data: [" + String.join(",", insercion) + "],");
            lineasHTML.add("            borderColor: 'rgb(255, 99, 132)',");
            lineasHTML.add("            backgroundColor: 'rgba(255, 99, 132, 0.2)'");
            lineasHTML.add("        }, {");
            lineasHTML.add("            label: 'Búsqueda Exitosa',");
            lineasHTML.add("            data: [" + String.join(",", busquedaExitosa) + "],");
            lineasHTML.add("            borderColor: 'rgb(54, 162, 235)',");
            lineasHTML.add("            backgroundColor: 'rgba(54, 162, 235, 0.2)'");
            lineasHTML.add("        }, {");
            lineasHTML.add("            label: 'Búsqueda Sin Éxito',");
            lineasHTML.add("            data: [" + String.join(",", busquedaSinExito) + "],");
            lineasHTML.add("            borderColor: 'rgb(255, 205, 86)',");
            lineasHTML.add("            backgroundColor: 'rgba(255, 205, 86, 0.2)'");
            lineasHTML.add("        }]");
            lineasHTML.add("    },");
            lineasHTML.add("    options: {");
            lineasHTML.add("        responsive: true,");
            lineasHTML.add("        plugins: {");
            lineasHTML.add("            title: {");
            lineasHTML.add("                display: true,");
            lineasHTML.add("                text: 'Rendimiento Tabla Hash vs Factor de Carga'");
            lineasHTML.add("            }");
            lineasHTML.add("        },");
            lineasHTML.add("        scales: {");
            lineasHTML.add("            x: {");
            lineasHTML.add("                display: true,");
            lineasHTML.add("                title: {");
            lineasHTML.add("                    display: true,");
            lineasHTML.add("                    text: 'Factor de Carga (%)'");
            lineasHTML.add("                }");
            lineasHTML.add("            },");
            lineasHTML.add("            y: {");
            lineasHTML.add("                display: true,");
            lineasHTML.add("                title: {");
            lineasHTML.add("                    display: true,");
            lineasHTML.add("                    text: 'Promedio de Comparaciones'");
            lineasHTML.add("                }");
            lineasHTML.add("            }");
            lineasHTML.add("        }");
            lineasHTML.add("    }");
            lineasHTML.add("});");
            lineasHTML.add("</script>");
            lineasHTML.add("</body>");
            lineasHTML.add("</html>");

            try (FileWriter writer = new FileWriter("UT6/PD2UT6/grafico_rendimiento.html")) {
                for (String lineaHTML : lineasHTML) {
                    writer.write(lineaHTML + "\n");
                }
            }

            System.out.println("Gráfico generado en grafico_rendimiento.html");
            reader.close();

        } catch (IOException e) {
            System.err.println("Error al procesar archivo: " + e.getMessage());
        }
    }
}