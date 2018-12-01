package tickets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * Código realizado por César Augusto Palos Sandoval
 */

public class Ticket2 {

	public static void main(String[] args) {
		int respuesta, i = 0, j, cantidad[] = new int[20];
		float precioArt[] = new float[20], totalPagar[] = new float[10];
		String articulo[] = new String[10], tipo[] = new String[10];
		Date fecha = new Date();
		Scanner entrada = new Scanner(System.in);

		do {
			System.out.println("Nombre del artículo:");
			articulo[i] = entrada.next();
			System.out.println("Número de artículos comprados:");
			cantidad[i] = entrada.nextInt();
			System.out.println("Precio del articulo:");
			precioArt[i] = entrada.nextFloat();
			System.out.println("¿El articulo es contado en pieza, kilogramos o mililitros? (pz, kg, ml):");
			tipo[i] = entrada.next();
			totalPagar[i] = (precioArt[i] * cantidad[i]);
			i++;
			System.out.println("Presione 1 para ingresar otro alumno o presione 2 para salir");
			respuesta = entrada.nextInt();
		} while (respuesta == 1);
		System.out
				.println("\n**************************************************************************** \r\n" + "\n");
		System.out.println("Palos Sandoval César Augusto \t\t");
		System.out.println("Fecha: " + fecha);
		System.out.println("Programa para contabilizar producto vendido\n\n");
		System.out.println("***************************************************************************** \r\n" + "\n");
		System.out.println("Cantidad \t Articulo \t Tipo \t\t Unidad \t\t Total \n\n");

		for (j = 0; j < i; ++j) {
			System.out.print(cantidad[j] + "\t\t");
			System.out.print(articulo[j] + "\t\t");
			System.out.print(tipo[j] + "\t\t");
			System.out.print("$" + precioArt[j] + "\t\t\t");
			System.out.print("$" + totalPagar[j] + "\n");
		}
		System.out.println("***************************************************************************** \r\n" + "\n");

		int sumaArt = 0;
		for (int contador = 0; contador < i; ++contador)
			sumaArt += cantidad[contador];
		System.out.println("Total de articulos: " + sumaArt);

		float sumaTotal = (float) 0.0;
		for (int contadorTotal = 0; contadorTotal < i; ++contadorTotal)
			sumaTotal += totalPagar[contadorTotal];
		System.out.println("Total sin IVA): $" + sumaTotal);

		float iva = (float) 0.0;
		iva = (float) (sumaTotal * .16);
		System.out.println("IVA: $ " + iva);
		System.out.println("Total con IVA: $ " + (sumaTotal + iva));
		System.out.println("Palos Sandoval César Augusto");
		System.out.println("Fecha: " + fecha);

		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");
		String historial = hourdateFormat.format(date);
		File directorio = new File(
				"C:\\Users\\kisse\\Desktop\\TSU\\4to Cuatrimestre\\Desarrollo de Aplicaciones II\\Tickets");
		File file = new File(directorio, historial + ".txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("El archivo " + file.getName() + " ha sido creado");
				PrintWriter pw = new PrintWriter(file, "utf-8");
				pw.println(
						"\n***************************************************************************** \r\n" + " \n");
				pw.println("\tFactura\n");
				pw.println("\nPalos Sandoval César Augusto\t\t");
				pw.printf("%s\n", fecha);
				pw.println(" ");
				pw.println("Programa para contabilizar producto vendido\n\n");
				pw.println(
						"***************************************************************************** \r\n" + " \n");
				pw.println(" Cantidad \t Articulo \t Tipo \t\t Unidad \t Total \n");

				for (j = 0; j < i; ++j) {
					pw.printf("%d \t\t", cantidad[j]);
					pw.printf(" %s \t\t", articulo[j]);
					pw.printf(" %s \t\t", tipo[j]);
					pw.printf("$ %.2f \t\t", precioArt[j]);
					pw.printf("$ %.2f \n", totalPagar[j]);
					pw.println(" ");
				}
				pw.println("*****************************************************************************\n");

				pw.printf("Total de articulos: $ %d\n", sumaArt);
				pw.println(" ");
				pw.printf("Total sin IVA: $ %.2f\n", sumaTotal);
				pw.println(" ");
				pw.printf("IVA: $ %.2f \n", iva);
				pw.println(" ");
				pw.printf("Total con IVA: $ %.2f \n", (sumaTotal + iva));
				pw.println(" ");
				pw.println("\nPalos Sandoval César Augusto\t\t");
				pw.printf("%s\n", fecha);
				pw.println(" ");
				pw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
