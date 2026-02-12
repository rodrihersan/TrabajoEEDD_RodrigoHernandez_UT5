package ProyectoEEDD;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//Principio: Nombres significativos
//Los nombres como Jugador, asesino, tripulante, vivo,
//describen claramentesu propósito dentro del programa.
public class Jugador {
	
	/*Principio: Encapsulación
	Los atributos son privados para proteger el estado interno
	del objeto y solo se accede a ellos mediante métodos públicos.*/
	private String nombre;
	private String color;
	private int id;
	private boolean tripulante;
	private boolean asesino;
	private boolean vivo;
	
	/*Principio: Se evita la duplicación de código
	El método leerLinea centraliza la validación de texto,
	evitando repetir la misma comprobación en varias partes del código.*/
	private String leerLinea(BufferedReader leer) throws IOException {
		String texto;
		do {
			texto = leer.readLine().trim();
			if (texto.isEmpty()) {
				System.out.print("Debes escribir algo: ");
			}
		} while (texto.isEmpty());
		return texto;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean esTripulante() {
		return tripulante;
	}
	
	public boolean esAsesino() {
	        return asesino;
	}

	public boolean esVivo() {
        return vivo;
    }

	//Principio: Métodos pequeños y claros
	//Se aprecia un método pequeño, claro y con un nombre entendible y con una accion sencilla y especifica,
	//en este caso el estado de vivo (true) pasa a false (Muerto).
    public void morir() {
        vivo = false;
    }

	private boolean estaRepetido(ArrayList<Jugador> jugadores, int idGenerado) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getId() == idGenerado) {
				System.err.println("El numero esta repetido");
				return true;
			}

		}
		return false;
	}
	
	// Principio: Bajo acoplamiento
    // El objeto se crea fuera y no depende directamente del menú.
	public void pedirDatos(BufferedReader leer, ArrayList<Jugador> jugadores, int asesinosActuales) throws IOException {
		System.out.print("Nombre: ");
		nombre = leerLinea(leer);
		
		Random rand = new Random();
		int idGenerado = -1;
		do {
			idGenerado = rand.nextInt(10) + 1;
			System.out.println("He generado la id: " + idGenerado + " para el juagador " + nombre);
		} while (estaRepetido(jugadores, idGenerado));
		this.id=idGenerado;
        vivo = true;
        tripulante = false;
        asesino = false;
        
        if (asesinosActuales < 2) {
            boolean asignar = rand.nextBoolean();
            if (asignar) 
            	asesino = true;
        }
		
		do {
			System.out.println("Introduce el color(Rojo/Azul/Rosa/Amarillo): ");
			color = leerLinea(leer);
			if (!color.equalsIgnoreCase("Rojo") && !color.equalsIgnoreCase("Azul") && !color.equalsIgnoreCase("Amarillo") && !color.equalsIgnoreCase("Rosa") )
				System.out.println("Tienes que introducir un color valido (Rojo/Azul/Rosa/Amarillo)");
		} while (!color.equalsIgnoreCase("Rojo") && !color.equalsIgnoreCase("Azul") && !color.equalsIgnoreCase("Amarillo") && !color.equalsIgnoreCase("Rosa"));
	}
	
	public void mostrarDatos() {
		System.out.println("ID: " + id);
		System.out.println("Nombre: " + nombre);
		System.out.println("Color: " + color);
		System.out.print("Rol: ");
			if (asesino) {
				System.out.println("Asesino");
			} else {
				System.out.println("Tripulante");
			}
		System.out.print("Estado: ");
			if (esVivo()) {
				System.out.println("Vivo");
			} else {
				System.out.println("Muerto");
			}
	}
}