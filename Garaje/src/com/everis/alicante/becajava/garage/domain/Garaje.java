package com.everis.alicante.becajava.garage.domain;

import java.util.HashMap;
import java.util.Map;

public class Garaje {
	private static Map<Integer,Plaza> plazas = new HashMap<Integer,Plaza>();
	private static Map<String,Cliente> clientes = new HashMap<String,Cliente>();
	private static Map<String, Vehiculo> vehiculos = new HashMap<String,Vehiculo>();
	private static Map<String, Reserva> reservas = new HashMap<String,Reserva>();

	public static Map<String, Reserva> getReservas() {
		return reservas;
	}
	public static Map<String, Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(Map<String, Cliente> clientes) {
		Garaje.clientes = clientes;
	}

	public static Map<String, Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public static void setVehiculos(Map<String, Vehiculo> vehiculos) {
		Garaje.vehiculos = vehiculos;
	}

	public Map<Integer, Plaza> getPlazas() {
		return plazas;
	}

	public void setPlazas(Map<Integer, Plaza> plazas) {
		Garaje.plazas = plazas;
	}
	
	public static void main(String[] args) {
	}

}
