package com.everis.alicante.becajava.garaje.main;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.everis.alicante.becajava.garage.domain.Garaje;
import com.everis.alicante.becajava.garage.domain.Plaza;
import com.everis.alicante.becajava.garage.domain.PlazaCoche;
import com.everis.alicante.becajava.garaje.controller.GarajeController;;

public class GarajeMain {
	public static Garaje garaje;
	public static GarajeController garajeController;
	public static StringBuilder menuInicio;
	public static Scanner in = new Scanner(System.in);
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
		GarajeController.getData();
		
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
		Integer opcion = in.nextInt();
		in.nextLine();
		System.out.println("Has elegido la opción " + opcion.toString());

		switch (opcion) {
		case 9:
			// logica del caso Guardar Plazas
			GarajeController.saveData();
			break;
		case 0:
			// logica del caso 0
			Map<Integer, Plaza> plazas=GarajeController.getPlazaController().getPlazas();
			for (Plaza plaza : plazas.values()) {
				System.out.println(plaza);
			}
			break;

		case 1:
			// logica del caso 1 listar Plazas de coche
			GarajeController.mostrarCollection(GarajeController.getPlazaController().getPlazasBySimpleName("PlazaCoche").values());
			break;
		case 2:
			// logica del caso 2 listar Plazas de moto
			GarajeController.mostrarCollection(GarajeController.getPlazaController().getPlazasBySimpleName("PlazaMoto").values());
			break;
		case 3:
			// logica del caso 3
			GarajeController.getPlazaController().reservarPlaza();
			
			break;
		case 4:
			// logica del caso 4
			GarajeController.mostrarCollection(GarajeController.getPlazaController().getPlazasLibres());
			
			break;
		default:
			break;
		}
		iniciarAplicacion();
		in.close();
	}
}