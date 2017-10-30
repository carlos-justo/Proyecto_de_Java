package Practica_de_Java;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @file Vehiculos.java
 * @brief Clase donde se realizan las acciones de los vehiculos
 * 
 * @author Carlos Justo de Frías <100329795@alumuno.uc3m.es>
 * @version 1.0
 * @date 2017-10
 */

public class Vehiculos {

	private String matricula;
	private String modelo;
	private String color;
	private String titular;
	private String fecha_en;
	private String hora_en;
	
	/**
	 * @brief Constructor parametrizado para introducir un vehiculo nuevo al parking
	 * 
	 * @param matricula
	 */
	Vehiculos(String matricula){
		try{
			InputStreamReader I=new InputStreamReader(System.in);
			BufferedReader R=new BufferedReader(I);
		
			this.matricula = matricula;
			
			System.out.print("Introduce el modelo del vehículo: ");
			modelo = R.readLine();
			
			System.out.print("Introduce el color del vehículo: ");
			color = R.readLine();
			
			System.out.print("Introduce el titular del vehículo: ");
			titular = R.readLine();
			
			Date date = new Date();
			
			DateFormat fecha = new SimpleDateFormat("dd.MM.yyyy");
			fecha_en = fecha.format(date);
			
			DateFormat hora = new SimpleDateFormat("HH:mm");
			hora_en = hora.format(date);
			
		}catch(IOException e){System.out.println("error");}
	}
	
	/**
	 * @brief Constructor parametrizado para introducir un vehiculo desde un fichero
	 * 
	 * @param matricula 
	 * @param modelo
	 * @param color
	 * @param titular
	 * @param hora_en
	 * @param fecha_en
	 */
	Vehiculos(String matricula, String modelo, String color, String titular, String hora_en, String fecha_en){
	this.matricula=matricula;
	this.modelo=modelo;
	this.color=color;
	this.titular=titular;
	this.fecha_en=fecha_en;
	this.hora_en=hora_en;
	}
	
	/**
	 * @brief Función para mostrar los datos del vehiculo por pantalla
	 */
	void leer_coche() {
		System.out.println("\t" + matricula + " " + modelo + " " + color);
		System.out.println("\t" + "Fecha de entrada: " + fecha_en + " a las " + hora_en);
	}
	
	/**
	 * @brief Función para devolver la matricula
	 * 
	 * @return matricula
	 */
	String get_matricula() {
		return matricula;
	}
	
	/**
	 * @brief Función para devolver la fecha de entrada del vehiculo
	 * 
	 * @return Fecha de entrada
	 */
	String get_fecha(){
		return fecha_en;
	}
	
	/**
	 * @brief Función para devolver la hora de entrada del vehiculo
	 * @return
	 */
	String get_hora(){
		return hora_en;
	}
	
	/**
	 * @brief Función imprimir los datos del vehiculo de forma especial para guardarlos en un fichero txt
	 * 
	 * @return Cadena con todos los datos
	 */
	String escribir_coche_fichero() {
		String aux;
		aux = matricula + ";" + modelo + ";" + color + ";" + titular + ";" + fecha_en + ";" + hora_en + ";";
		return aux;
	}
}
