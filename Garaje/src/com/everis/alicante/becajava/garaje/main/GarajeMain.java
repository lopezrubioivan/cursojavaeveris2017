package com.everis.alicante.becajava.garaje.main;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.everis.alicante.becajava.garage.domain.Garaje;
import com.everis.alicante.becajava.garage.domain.Plaza;
import com.everis.alicante.becajava.garaje.controller.GarajeController;;

public class GarajeMain {
	public static Garaje garaje;
	public static GarajeController garajeController;
	public static StringBuilder menuInicio;
	public GarajeMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		inicializarComponentes();
		iniciarAplicacion();
	}

	private static void inicializarComponentes() {
		// TODO Auto-generated method stub
		garaje = new Garaje();
		garajeController=new GarajeController();
		garajeController.iniciarPlazas();
		
		menuInicio = new StringBuilder();
		menuInicio.append("¡Bienvenido al garaje!\n");
		menuInicio.append("****************************************\n");
		menuInicio.append("9: Guardar Plazas\n");
		menuInicio.append("0: Listar Plazas\n");
		menuInicio.append("1: Listar Plazas Coche\n");
		menuInicio.append("2: Listar Plazas Moto\n");
		menuInicio.append("3: Reservar Plaza\n");
		menuInicio.append("4: Listar Plazas Libres\n");
		menuInicio.append("5: Listar Clientes\n");
		menuInicio.append("6: Listar Vehículos\n");
//		menuInicio.append("7:\n");
//		menuInicio.append("8:\n");
	}

	public static void iniciarAplicacion() {
		System.out.println(menuInicio.toString());
		
		Scanner in = new Scanner(System.in);
		Integer opcion = in.nextInt();
		System.out.println("Has elegido la opción " + opcion.toString());

		switch (opcion) {
		case 9:
			// logica del caso Guardar Plazas
			try {
				garajeController.escribirFicheroPlazas();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 0:
			// logica del caso 0
			Map<Integer, Plaza> plazas=garajeController.getPlazas();
			for (Plaza plaza : plazas.values()) {
				System.out.println(plaza);
			}
			break;

		case 1:
			// logica del caso 1 listar Plazas de coche
			garajeController.mostrarCollection(garajeController.getPlazasBySimpleName("PlazaCoche").values());
			break;

		case 2:
			// logica del caso 2
			garajeController.mostrarCollection(garajeController.getPlazasBySimpleName("PlazaMoto").values());
			break;
			
		case 3:
			// logica del caso 3
			System.out.print("Introduce número de plaza: ");
			int numeroPlaza=in.nextInt();
			in.nextLine();
			garajeController.reservarPlaza(numeroPlaza);
			
			break;
		case 4:
			// logica del caso 4
			garajeController.mostrarCollection(garajeController.getPlazasLibres());
			
			break;
		default:
			break;
		}
		iniciarAplicacion();
		in.close();
	}
}