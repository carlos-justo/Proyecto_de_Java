package Practica_de_Java;
import java.util.*;
import java.io.*;

/**
 * @file Main.java
 * @brief Main de la gestión de un parking
 * 
 * @author Carlos Justo de Frías <100329795@alumuno.uc3m.es>
 * @version 1.0
 * @date 2017-10
 */

public class Main {

	/**
	 * @brief El método main contiene todo lo necesario para poder introducir, retirar o buscar un vehiculo. Así como pedir información del Parking
	 * 
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	
	public static void main(String[] args) {
		//try{
			InputStreamReader I=new InputStreamReader(System.in);
			BufferedReader R=new BufferedReader(I);
		
			Parking p = new Parking();
			String matricula="";
			String aux="", opcion="";
			int num=0, opc=0;
			boolean comprobar=false;
			double precio;
			p.leer_fichero();
			
			System.out.println("\n" + "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
			System.out.println("////////////////////////////      PARKING      ////////////////////////////");
			System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

			do {	//bucle para realizar todo el rato el main hasta que le de a salir
				System.out.println("\n¿Qué desea realizar?\n");
				System.out.println("\t1.- \tIntroducir o retirar un vehiculo");
				System.out.println("\t2.- \tBuscar un vehiculo");
				System.out.println("\t3.- \tObtener información del parking o cambiar coste por minuto");
				System.out.println("\t4.- \tSalir");
			
				do{	//bucle para comprobar que el número introducido es correcto 	
					try{
						System.out.print("Introducir numero: ");
						opcion = R.readLine();
						opc = Integer.parseInt(opcion);
					}
					catch(IOException e){System.out.println("Error al leer linea");}
				    catch(java.lang.NumberFormatException nfe){System.out.println("Error al introducir un numero");	}
					
				}while(opc<1 || opc>4); 
				System.out.println();
				switch (opc) { //Switch Case para las opciones del menu
				case 1:
					do{
						try{
							System.out.println("Indique la matricula del vehículo: ");
							matricula = R.readLine();
							matricula = matricula.toUpperCase();
							comprobar= p.comprobar_matricula(matricula);
						}catch(IOException e){System.out.println("Error al leer linea");}
					}while(comprobar==false);
					
					if(p.buscar_vehiculo(matricula)<0) {
						System.out.println("La matricula introducida no coincide con ninguna de las del Parking");
						do{
							try{
								System.out.println("¿Desea añadir un vehículo con esa matrícula? (S/N)");
								aux = R.readLine();
							}catch(IOException e){System.out.println("Error al leer linea");}
						}while(!(aux.equals("S")) && !(aux.equals("s")) && !(aux.equals("N")) && !(aux.equals("n")));
						
						if(aux.equals("S") || aux.equals("s")) {
							p.introducir_vehiculo(matricula);
							p.leer_coche(p.buscar_vehiculo(matricula));
							p.escribir_fichero();
						}
						
						else if(aux.equals("N") || aux.equals("n")) {
							System.out.println("Ok");
							num=0;
							}
					}
					else if(p.buscar_vehiculo(matricula)>=0) {
						System.out.println("Se ha encontrado: ");
						p.leer_coche(p.buscar_vehiculo(matricula));
						precio = p.calcular_precio(p.buscar_vehiculo(matricula));
						do{
							try{
								System.out.println("¿Desea retirar el vehiculo? (S/N)");
								aux = R.readLine();
							}catch(IOException e){System.out.println("Error al leer linea");}
							if(aux.equals("S") || aux.equals("s")) {
								p.retirar_vehiculo(p.buscar_vehiculo(matricula));
								p.set_ganancias(precio);
								p.escribir_fichero();
							}
							else if(aux.equals("N") || aux.equals("n")) {
								System.out.println("Vale.");
								}
						}while(!(aux.equals("S")) && !(aux.equals("s")) && !(aux.equals("N")) && !(aux.equals("n")));
						
					}
					break;
				case 2:
					do{
						try{
							System.out.println("Indique la matricula del vehículo: ");
							matricula = R.readLine();
							matricula = matricula.toUpperCase();
							comprobar= p.comprobar_matricula(matricula);
						}catch(IOException e){System.out.println("Error al leer linea");}
					}while(comprobar==false);
					if(p.buscar_vehiculo(matricula)>=0) {
						p.leer_coche(p.buscar_vehiculo(matricula));
					}
					else System.out.println("Vehiculo no encontrado.");
					break;
				case 3:
					p.informacion_parking();
					p.escribir_fichero();
					break;
				case 4:
					num=1;
					break;
				default: break;
				}
			}while(num==0);	// salimos del bucle
		//}catch(java.lang.Exception e){e.printStackTrace();}
	}

}
