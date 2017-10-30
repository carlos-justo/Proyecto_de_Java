package Practica_de_Java;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @file Parking.java
 * @brief Clase donde se realizan las acciones del parking
 * 
 * @author Carlos Justo de Frías <100329795@alumuno.uc3m.es>
 * @version 1.0
 * @date 2017-10
 */

public class Parking {

	private float coste;				// Coste por minuto 
	private double ganancias;			// Sumatorio de todas las ganancias
	private int plazas_1, plazas_2;		// Variables enteras para las plazas de las dos plantas 
	private Vector<Vehiculos> planta_1; // Vector para los vehiculos de la primera planta
	private Vector<Vehiculos> planta_2;	// Vector para los vehiculos de la segunda planta
		
	/**
	 * @brief Constructor vacío de la clase
	 */
	Parking(){
		coste=1;
		ganancias=0;
		plazas_1=100;
		plazas_2=100;
		planta_1 = new Vector<Vehiculos>(5,2);
		planta_2 = new Vector<Vehiculos>(5,2);
	}
	
	/**
	 * @brief Funcion para introducir un vehiculo en el parking, puediendo elegir entre una de las dos plantas
	 * 
	 * @param matricula del vehiculo
	 */
	void introducir_vehiculo(String matricula) {
		int opc=0;
		try{
			InputStreamReader I=new InputStreamReader(System.in);
			BufferedReader R=new BufferedReader(I);
			String aux;
			int bucle=0;
			System.out.println("Coches introducidos en la planta 1: " + planta_1.size() + ", quedan " + plazas_1 + " plazas.");
			System.out.println("Coches introducidos en la planta 2: " + planta_2.size() + ", quedan " + plazas_2 + " plazas.");
			do{
				do{
					System.out.println("¿En qué planta desea guardar el coche? (1/2)");
					aux = R.readLine();
					opc = Integer.parseInt(aux);
				}while(opc != 1 && opc != 2);
				
				if(opc == 1 && planta_1.size()<100) {
					Vehiculos v = new Vehiculos(matricula);
					planta_1.addElement(v);
					plazas_1--;
					bucle=1;
				}
				else if(planta_1.size()==100){System.out.println("Esta planta está llena, prueba en la otra planta");}
				else if(opc == 2 && planta_2.size()<100) {
					Vehiculos w = new Vehiculos(matricula);
					planta_2.addElement(w);
					plazas_2--;
					bucle=1;
				}
				else if(planta_2.size()==100){System.out.println("Esta planta está llena, prueba en la otra planta");}
			}while(bucle==0);
			

		}catch(IOException e){System.out.println("Error en la introducción de vehiculos");}
	}
	
	/**
	 * @brief Función que te devuelve la posicion y la planta del vehiculo en caso de que encuentre el vehiculo
	 * 
	 * @param matricula
	 * @return la posición del vehiculo (+1000 si es planta 1 o +2000 si es planta 2)
	 */
	int buscar_vehiculo(String matricula) {
		int aux=-1;
		for(int i=0; i<planta_1.size(); i++){
			 if(matricula.equals(planta_1.elementAt(i).get_matricula())==true) {
				 aux=i+1000;
				 i=planta_1.size();
				 }
			 }
		for(int i=0; i<planta_2.size(); i++){
			 if(matricula.equals(planta_2.elementAt(i).get_matricula())==true) {
				 aux=i+2000;
				 i=planta_2.size();
				 }
			 }
		return aux;      
	}
	
	/**
	 * @brief Funcion para retirar un vehiculo
	 * 
	 * @param posicion
	 */
	void retirar_vehiculo(int posicion) {
		if((posicion-1000) >=0 && (posicion-1000) < 100) {
			planta_1.removeElementAt(posicion-1000);
			System.out.println("Vehículo retirado.");
			plazas_1++;
		}
		else{
			planta_2.removeElementAt(posicion-2000);
			System.out.println("Vehículo retirado.");
			plazas_2++;
		}
	}

	/**
	 * @brief Función para buscar la informacion sobre un vehiculo en concreto
	 * 
	 * @param posicion
	 */
	void leer_coche(int posicion) {
		if((posicion-1000) >=0 && (posicion-1000) < 100) {
			planta_1.elementAt(posicion-1000).leer_coche();
			System.out.println("Vehiculo de la planta 1");
		}
		else {
			planta_2.elementAt(posicion-2000).leer_coche();
			System.out.println("Vehiculo de la planta 2");

		}
	}
	
	/**
	 * @brief Función para actualizar los ficheros de texto 
	 */
	void escribir_fichero() {
		PrintWriter salida = null;
		PrintWriter salida2 = null;
		PrintWriter salida3 = null;
		
		try{
			salida = new PrintWriter(new BufferedWriter(new FileWriter("Planta_1.txt")));
			for(int i=0; i<planta_1.size(); i++){
				 salida.println(planta_1.elementAt(i).escribir_coche_fichero());
				 }		
		}catch(IOException e){System.out.println("Errror al escribir en el fichero 1");}
		finally{if (salida != null) {salida.close();}
		}
	
		try{
			salida2 = new PrintWriter(new BufferedWriter(new FileWriter("Planta_2.txt")));
			for(int i=0; i<planta_2.size(); i++){
				 salida2.println(planta_2.elementAt(i).escribir_coche_fichero());
				 }
		}catch(IOException e){System.out.println("Errror al escribir en el fichero 2");}
		finally{if (salida2 != null) {salida2.close();}}
		
		try {
			salida3 = new PrintWriter(new BufferedWriter(new FileWriter("Ganancias.txt")));
				 salida3.println(coste);
				 salida3.println(ganancias);
		}catch(IOException e){System.out.println("Errror al escribir en el fichero ganancias");}
		finally{if (salida3 != null) {salida3.close();}}
		
		}

	/**
	 * @brief Función para introducir en el sistema del parking todos los datos y vehiculos de ambas plantas
	 */
	void leer_fichero() {
		BufferedReader entrada = null;
		BufferedReader entrada2 = null;
		BufferedReader entrada3 = null;
		
		try{			
			entrada = new BufferedReader(new FileReader("Planta_1.txt"));
			String aux;
			String matricula=new String(), modelo=new String(), color=new String(), titular=new String(), fecha=new String(), hora=new String();
			int pos1=0, pos2=0;
			Vehiculos v;
			while((aux = entrada.readLine())!=null){
				for(int i=0; i<6; i++) {
					if (i==0) {
						pos1=aux.indexOf(";");
						matricula=aux.substring(0, pos1);
					}
					else if(i==1) {
						pos2=aux.indexOf(";", pos1 + 1);
						modelo=aux.substring(pos1 + 1, pos2);
					}
					else if(i==2) {
						pos1=aux.indexOf(";", pos2 + 1);
						color=aux.substring(pos2 + 1, pos1);
					}
					else if(i==3) {
						pos2=aux.indexOf(";", pos1 + 1);
						titular=aux.substring(pos1 + 1, pos2);
					}
					else if(i==4) {
						pos1=aux.indexOf(";", pos2 + 1);
						fecha=aux.substring(pos2 + 1, pos1);
					}
					else if(i==5) {
						pos2=aux.indexOf(";", pos1 + 1);
						hora=aux.substring(pos1 + 1, pos2);
					}
				}
				v = new Vehiculos(matricula, modelo, color, titular, hora, fecha);
				planta_1.addElement(v);	
				plazas_1--;
			}				
		}catch(IOException e){System.out.println("Error en el fichero de la planta 1");}
		finally{
			try {
			if (entrada != null) {entrada.close();}
			} catch (IOException e) {e.printStackTrace();}
		}
		
		try{			
			entrada2 = new BufferedReader(new FileReader("Planta_2.txt"));
			String aux;
			String matricula=new String(), modelo=new String(), color=new String(), titular=new String(), fecha=new String(), hora=new String();
			int pos1=0, pos2=0;
			Vehiculos v;
			while((aux = entrada2.readLine())!=null){
				for(int i=0; i<6; i++) {
					if (i==0) {
						pos1=aux.indexOf(";");
						matricula=aux.substring(0, pos1);
					}
					else if(i==1) {
						pos2=aux.indexOf(";", pos1 + 1);
						modelo=aux.substring(pos1 + 1, pos2);
					}
					else if(i==2) {
						pos1=aux.indexOf(";", pos2 + 1);
						color=aux.substring(pos2 + 1, pos1);
					}
					else if(i==3) {
						pos2=aux.indexOf(";", pos1 + 1);
						titular=aux.substring(pos1 + 1, pos2);
					}
					else if(i==4) {
						pos1=aux.indexOf(";", pos2 + 1);
						fecha=aux.substring(pos2 + 1, pos1);
					}
					else if(i==5) {
						pos2=aux.indexOf(";", pos1 + 1);
						hora=aux.substring(pos1 + 1, pos2);
					}
				}
				v = new Vehiculos(matricula, modelo, color, titular, hora, fecha);
				planta_2.addElement(v);	
				plazas_2--;
			}
		}catch(Exception e){System.out.println("Error en el fichero de la planta 2");}
		finally{
			try {
			if (entrada2 != null) {entrada2.close();}
			} catch (IOException e) {e.printStackTrace();}
		}
		
		try {
			entrada3 = new BufferedReader(new FileReader("Ganancias.txt"));
			String aux;
			int i=0;
			while((aux = entrada3.readLine())!=null){
					
				if(i==0) {
					coste = Float.parseFloat(aux);
					}
				else if(i==1) {
					ganancias = Double.parseDouble(aux);
					}
				i++;			
			}			
			entrada3.close();
			
		}catch(IOException e){System.out.println("Error en el fichero de las ganancias y coste");}
		finally{
			try {
			if (entrada3 != null) {entrada3.close();}
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	/**
	 * @brief Función para calcular el dinero a pagar del vehiculo que se desea sacar
	 * 
	 * @param posicion
	 * @return precio total
	 */
	double calcular_precio(int posicion){
		Date date = new Date();
		String fecha_actual, hora_actual;
		double dia_aux=0, año_aux=0, mes_aux=0, minutos_aux=0, hora_aux=0, tiempo_final=0;
		double precio_total;
		int resta;
		Vector<Vehiculos> aux;
		if((posicion-1000) >=0 && (posicion-1000) < 100) {
			resta=1000;
			aux=planta_1;
		}
		else {
			resta=2000;
			aux=planta_2;
		}
		DateFormat fecha = new SimpleDateFormat("dd.MM.yyyy");
		fecha_actual = fecha.format(date);
		
		DateFormat hora = new SimpleDateFormat("HH:mm");
		hora_actual = hora.format(date);
		
		año_aux =  Double.parseDouble(fecha_actual.substring(6, 10)) - Double.parseDouble(aux.elementAt(posicion-resta).get_fecha().substring(6, 10));
		
		mes_aux =  Double.parseDouble(fecha_actual.substring(3, 5)) - Double.parseDouble(aux.elementAt(posicion-resta).get_fecha().substring(3, 5));
		
		dia_aux =  Double.parseDouble(fecha_actual.substring(0, 2)) - Double.parseDouble(aux.elementAt(posicion-resta).get_fecha().substring(0, 2));

		hora_aux = Double.parseDouble(hora_actual.substring(0, 2)) - Double.parseDouble(aux.elementAt(posicion-resta).get_hora().substring(0, 2));

		minutos_aux = Double.parseDouble(hora_actual.substring(3, 5)) - Double.parseDouble(aux.elementAt(posicion-resta).get_hora().substring(3, 5  ));
		
		if (minutos_aux<0){
			hora_aux--;
			minutos_aux +=60;}
		
		if (hora_aux<0){
			dia_aux--;
			hora_aux +=24;}
		
		if(dia_aux<0){
			if(mes_aux<0)mes_aux++;
			else if(mes_aux>=0)mes_aux--;
			dia_aux+=30;}
		
		if(mes_aux<0){
			mes_aux+=12;
			año_aux--;}
		
		tiempo_final = (año_aux *360*24*60) + ( mes_aux *30*24*60) + (dia_aux * 24*60) + (hora_aux*60) + minutos_aux;
		precio_total=tiempo_final/100;
		precio_total*=coste;
		System.out.println("Precio total: " + precio_total + " euros");

		return precio_total;
	}

	/**
	 * @brief Funcion para el sumatorio de ganancias
	 * 
	 * @param precio
	 */
	void set_ganancias(double precio) {
		ganancias+=precio;
	}

	/**
	 * @brief Función para imprimir información variada del parking y para cambiar el precio por minuto.
	 */
	void informacion_parking() {
		try{
			InputStreamReader I=new InputStreamReader(System.in);
			BufferedReader R=new BufferedReader(I);
			String aux;
			System.out.println("Vehiculos de la planta 1:");
			for(int i=0; i<planta_1.size(); i++){
				 planta_1.elementAt(i).leer_coche();
				 }
			System.out.println("\n" + "Vehiculos de la plaza 2:");
			for(int i=0; i<planta_2.size(); i++){
				 planta_2.elementAt(i).leer_coche();
				 }
			
			System.out.println("\n" + "Plazas de la planta 1: " + plazas_1);
			System.out.println("Plazas de la planta 2: " + plazas_2);
			System.out.println("Coste actual: " + coste + " ctms/minuto");
			System.out.println("Ganancias totales: " + ganancias + " euros");
		
			do{
				System.out.println("¿Desea usted cambiar el coste por minuto? (S/N)");
				aux = R.readLine();
			}while(!aux.equals("S") && !aux.equals("s") && !aux.equals("N") && !aux.equals("n"));
			if(aux.equals("S") || aux.equals("s")) {
				System.out.print("Introduzca el nuevo precio: ");
				aux = R.readLine();
				coste = Float.parseFloat(aux);
			}
			else if(aux.equals("N") || aux.equals("n")) {
				System.out.println("Vale.");
			}
			
		}catch(IOException e){System.out.println("Error en la información del parking");}
	}

	/**
	 * @brief Función para comprobar si la matricula está en un formato adecuado
	 * 
	 * @param matricula
	 * @return comprobante (tipo bool)
	 */
	boolean comprobar_matricula(String matricula){
		boolean comprobar=true;
		int parte_num=0;
		
		if(matricula.length()>7) {
			comprobar=false;
			System.out.println("Tamaño de la cadena incorrecto");
		}
		try{
			parte_num = Integer.parseInt(matricula.substring(0, 4));
			for(int i=0; i<3; i++){
				if((int)matricula.charAt(4+i)<65 || (int)matricula.charAt(4+i)>90); //provocamos excepcion
				}
		}catch(java.lang.StringIndexOutOfBoundsException e){System.out.println("Tamaño de la cadena incorrecto");
		comprobar=false;}
		catch(java.lang.NumberFormatException nfe) {comprobar=false;}
		if(comprobar==false) System.out.println("Formato incorrecto");
		return comprobar;
	}
}
	