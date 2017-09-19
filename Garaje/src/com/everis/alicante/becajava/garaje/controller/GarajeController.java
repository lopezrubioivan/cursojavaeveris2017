package com.everis.alicante.becajava.garaje.controller;

import java.io.IOException;
import java.util.Collection;

public class GarajeController {
	private static PlazaController plazaController = new PlazaController();
	private static VehiculoController vehiculoController = new VehiculoController();
	private static ReservaController reservaController = new ReservaController();
	
	/**
	 * Recibe los datos
	 * 
	 */
	public static void getData() {
		try {
			vehiculoController.leerFicheroVehiculos();
			plazaController.leerFicheroPlazas();
			reservaController.leerFicheroReserva();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveData() {
		try {
			getVehiculoController().escribirFicheroVehiculos();
			getReservaController().escribirFicheroReserva();
			getPlazaController().escribirFicheroPlazas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mostrarCollection(Collection coleccion) {
		for (Object object : coleccion) {
			System.out.println(object.toString());
		}
	}
	
	public static PlazaController getPlazaController() {
		return plazaController;
	}
	public static void setPlazaController(PlazaController plazaController) {
		GarajeController.plazaController = plazaController;
	}
	public static VehiculoController getVehiculoController() {
		return vehiculoController;
	}
	public static void setVehiculoController(VehiculoController vehiculoController) {
		GarajeController.vehiculoController = vehiculoController;
	}
	public static ReservaController getReservaController() {
		return reservaController;
	}
	public static void setReservaController(ReservaController reservaController) {
		GarajeController.reservaController = reservaController;
	}

	
}
