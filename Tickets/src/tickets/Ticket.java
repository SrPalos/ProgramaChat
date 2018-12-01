package tickets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

/*
 * Código realizado por César Augusto Palos Sandoval
 */

public class Ticket {

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
		System.out.println("\n*****************************************************************************\r\n");
		System.out.println("Palos Sandoval César Augusto \t\t");
		System.out.println("Fecha: " + fecha);
		System.out.println("Programa para contabilizar producto vendido\n\n");
		System.out.println("*****************************************************************************\r\n");
		System.out.println("Cantidad \t Articulo \t Tipo \t\t Unidad \t\t Total \n");

		for (j = 0; j < i; ++j) {
			System.out.print(cantidad[j] + "\t\t");
			System.out.print(articulo[j] + "\t\t");
			System.out.print(tipo[j] + "\t\t");
			System.out.print("$" + precioArt[j] + "\t\t\t");
			System.out.print("$" + totalPagar[j] + "\n");
		}
		System.out.println("*****************************************************************************\r\n" + "\n");

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

		File directorio = new File(
				"C:\\Users\\kisse\\Desktop\\TSU\\4to Cuatrimestre\\Desarrollo de Aplicaciones II\\Tickets");
		File archivo = new File(directorio, "ticket.txt");
		if (!archivo.exists()) {
			try {
				archivo.createNewFile();
				System.out.println(archivo.getName() + "el archivo ha sido creado");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			if (archivo.exists()) {
				archivo.delete();
			}
			try {
				PrintWriter pw = new PrintWriter(archivo, "utf-8");
				pw.println("\n***************************************************************************** \n");
				pw.println("\tFactura\n");
				pw.println("\nPalos Sandoval César Augusto\t\t");
				pw.printf("%s\n", fecha);
				pw.println(" ");
				pw.println("Programa para contabilizar producto vendido\n\n");
				pw.println("*****************************************************************************\n");
				pw.println(" Cantidad \t Articulo \t Tipo \t\t Unidad \t\t Total \n");

				for (j = 0; j < i; ++j) {
					pw.printf("%d \t\t", cantidad[j]);
					pw.printf(" %s \t\t", articulo[j]);
					pw.printf(" %s \t\t", tipo[j]);
					pw.printf("$ %.2f \t\t", precioArt[j]);
					pw.printf("$ %.2f \t\t\n", totalPagar[j]);
					pw.println(" ");
				}
				pw.println("*****************************************************************************\n");

				pw.printf("Total de articulos: $ %d\n", sumaArt);
				pw.println(" ");
				pw.printf("Total a pagar (sin iva): $ %.2f\n", sumaTotal);
				pw.println(" ");
				pw.printf("IVA: $ %.2f \n", iva);
				pw.println(" ");
				pw.printf("Total a pagar con iva: $ %.2f \n", (sumaTotal + iva));
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
