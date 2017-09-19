package com.everis.alicante.becajava.garaje.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import com.everis.alicante.becajava.garage.domain.Cliente;
import com.everis.alicante.becajava.garage.domain.Coche;
import com.everis.alicante.becajava.garage.domain.Garaje;
import com.everis.alicante.becajava.garage.domain.Moto;
import com.everis.alicante.becajava.garage.domain.Plaza;
import com.everis.alicante.becajava.garage.domain.PlazaCoche;
import com.everis.alicante.becajava.garage.domain.PlazaMoto;
import com.everis.alicante.becajava.garage.domain.Vehiculo;
import com.everis.alicante.becajava.garaje.dao.VehiculoDao;
import com.everis.alicante.becajava.garaje.main.GarajeMain;

public class VehiculoController {
	/**
	 * Lee un archivo de texto con la información de la Vehiculos
	 * 
	 * @throws IOException
	 */
	private final static String CABECERATXT="MATRICULA;MODELO;MARCA;CLASE;CLIENTE";
	private static VehiculoDao dao = new VehiculoDao();
	
	
	public Map<String, Vehiculo> getVehiculos() {
		return GarajeMain.garaje.getVehiculos();
	}
	public Vehiculo crearVehiculoByString(String line) {
		String[] stringVehiculo = line.split(";");
		String matricula = stringVehiculo[0];
		String modelo = stringVehiculo[1];
		String marca = stringVehiculo[2];
		String clase = stringVehiculo[3];
		Cliente cliente = null;
		if (stringVehiculo[4] != "null") {
//			cliente=new Cliente(stringVehiculo[4]);
		}
		Vehiculo vehiculo;

		switch (clase) {
		case "Coche":
			vehiculo = new Coche(matricula, modelo, marca, cliente);
			break;
		case "Moto":
			vehiculo = new Moto(matricula, modelo, marca, cliente);
			break;
		default:
			vehiculo = new Vehiculo(matricula, modelo, marca, cliente);
			break;
		}
		return vehiculo;
	}
	public void escribirFicheroVehiculos() throws IOException {
		StringBuilder sb;
		File fichero = new File("src/main/resources/vehiculos.txt");
		FileWriter writer = new FileWriter(fichero);
		writer.write(CABECERATXT+"\n");
		writer.close();
		writer = new FileWriter(fichero, true);
		String texto;
		for (Vehiculo vehiculo : getVehiculos().values()) {
			sb=new StringBuilder();
			String tipo = vehiculo.getClass().toString();
			tipo = vehiculo.getClass().getSimpleName();
			texto = vehiculo.getMatricula() + ";" + vehiculo.getModelo() + ";" + vehiculo.getMarca()+ ";" + tipo
					+";"+((vehiculo.getCliente()==null)?"null":vehiculo.getCliente().getDni())
					+"\n";
			writer.write(texto);
		}
		writer.close();
	}
	
	public Vehiculo pedirVehiculo() {
		
		return null;
	}
	
	public void leerFicheroVehiculos() throws IOException {
		File fichero = new File("src/main/resources/vehiculos.txt");
		FileReader reader = new FileReader(fichero);
		BufferedReader buffer = new BufferedReader(reader);

		Vehiculo vehiculo;
		String line;
		while ((line = buffer.readLine()) != null) {
			if (!line.contains(CABECERATXT)) {
				vehiculo = crearVehiculoByString(line);
				getVehiculos().put(vehiculo.getMatricula(), vehiculo);
			}
		}
		reader.close();
		buffer.close();
	}
	public Vehiculo inputVehiculo() {
		Scanner in = new Scanner(System.in);
		System.out.print("Introduce Matricula: ");
		String matricula = in.nextLine();

		Vehiculo vehiculo = getVehiculos().get(matricula);

		if (vehiculo == null) {
			vehiculo = new Vehiculo(matricula);
			System.out.print("Introduce modelo de vehículo: ");
			vehiculo.setModelo(in.nextLine());
		}else {
			//el vehiculo ya está en la lista de vehiculos
			System.out.println("Tu coche ya está registrado:");
			System.out.println(vehiculo.toString());
		}
//		Si cierro el scanner Exception in thread "main" java.util.NoSuchElementException
//		in.close();
		return vehiculo;
	}
}
