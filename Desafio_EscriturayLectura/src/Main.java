import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Perro");
        lista.add("Gato");
        lista.add("Juan");
        lista.add("Daniel");
        lista.add("Juan");
        lista.add("Gato");
        lista.add("Perro");
        lista.add("Camila");
        lista.add("Daniel");
        lista.add("Camila");

        // Rutas fijas
        String directorio = "src/miDirectorio";
        String archivo = "archivo.txt";

        crearArchivo(directorio, archivo, lista);
        buscarTexto(directorio, archivo, "Gato");
    }

    public static void crearArchivo(String directorio, String archivo, ArrayList<String> lista) {
        File dir = new File(directorio);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
                return;
            }
        } else {
            System.out.println("El directorio ya existe");
        }

        File file = new File(dir.getAbsolutePath() + "/" + archivo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            Iterator<String> it = lista.iterator();
            while (it.hasNext()) {
                bw.write(it.next());
                bw.newLine();
            }
            System.out.println("Archivo creado y datos escritos correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buscarTexto(String directorio, String archivo, String texto) {
        File file = new File(directorio + "/" + archivo);

        if (!file.exists()) {
            System.out.println("El archivo ingresado no existe");
            return;
        }

        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(texto)) {
                    count++;
                }
            }
            System.out.printf("Cantidad de veces que se repite '%s' es : -> %d%n", texto, count);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
