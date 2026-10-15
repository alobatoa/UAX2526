package com.ejemplo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }

    // Métodos para trabajar con ficheros de texto

    // Sirve para escribir texto en un fichero. Por defecto, sobrescribe el contenido anterior si el fichero ya existe

    public void escribirConFileWriter() throws IOException {
        FileWriter fw = new FileWriter("datos.txt",);

        fw.write("Hola mundo\n");
        fw.write("Segunda línea\n");

        fw.close();
    }

    //Permite escribir texto utilizando un búfer

    public void escribirConBufferedWriter() throws IOException {
        BufferedWriter bw =
                new BufferedWriter(new FileWriter("datos.txt"));

        bw.write("Primera línea");
        bw.newLine();

        bw.write("Segunda línea");
        bw.newLine();

        bw.close();
    }

    // Escribe de forma sencilla, sin convertirlos a ningún formato de texto.

    public void escribirConPrintWriter() throws IOException {
        PrintWriter pw = new PrintWriter("datos.txt");

        pw.println("Ana");
        pw.println("25");
        pw.println("Madrid");

        pw.close();
    }

    //Esta clase permite leer caracteres de un fichero de texto. Para simplificar, leeremos el contenido carácter a carácter.

    public void leerConFileReader() throws IOException {
        FileReader fr = new FileReader("datos.txt");

        int caracter;

        while ((caracter = fr.read()) != -1) {
            System.out.print((char) caracter);
        }

        fr.close();
    }

    // Esta clase permite leer líneas enteras.

    public void leerConBufferedReader() throws IOException {
        BufferedReader br =
                new BufferedReader(new FileReader("datos.txt"));

        String linea;

        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }

        br.close();
    }

    // Leer líneas o palabas

    public void leerConScanner() throws IOException {
        Scanner sc = new Scanner(new File("datos.txt"));

        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            System.out.println(linea);
        }

        sc.close();
    }

    // Métodos para trabajar con ficheros CSV

    // Método para escribir el texto con una cabecera
    public void escribirCSV() throws IOException {
        PrintWriter pw = new PrintWriter("personas.csv");

        pw.println("Nombre,Edad,Ciudad");
        pw.println("Ana,25,Madrid");
        pw.println("Luis,30,Sevilla");
        pw.println("Marta,22,Valencia");

        pw.close();
    }

    // Escribir en un fichero
    public void escribirCSVConBuffer() throws IOException {
        BufferedWriter bw =
                new BufferedWriter(new FileWriter("personas.csv"));

        bw.write("Nombre,Edad,Ciudad");
        bw.newLine();

        bw.write("Ana,25,Madrid");
        bw.newLine();

        bw.write("Luis,30,Sevilla");
        bw.newLine();

        bw.close();
    }

    // Se lee cada fila separada con coma
    public void leerCSV() throws IOException {
        BufferedReader br =
                new BufferedReader(new FileReader("personas.csv"));

        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            for (String dato : datos) {
                System.out.print(dato + " ");
            }

            System.out.println();
        }

        br.close();
    }

    public void leerCSVConScanner() throws IOException {
        Scanner sc = new Scanner(new File("personas.csv"));

        while (sc.hasNextLine()) {
            String linea = sc.nextLine();

            Scanner fila = new Scanner(linea);
            fila.useDelimiter(",");

            while (fila.hasNext()) {
                System.out.println(fila.next());
            }

            fila.close();
        }

        sc.close();
    }

    // En Java moderno es preferible cerrar los ficheros mediante try-with-resources, para que se cierren automáticamente
    // incluso si se produce un error.

    public void leerTexto() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("datos.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }
}