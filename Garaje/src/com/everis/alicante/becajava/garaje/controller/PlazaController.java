package com.everis.alicante.becajava.garaje.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
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
import com.everis.alicante.becajava.garage.domain.Reserva;
import com.everis.alicante.becajava.garage.domain.Vehiculo;
import com.everis.alicante.becajava.garaje.main.GarajeMain;

public class PlazaController {
	private final static String CABECERATXT = "NUMEROPLAZA;PRECIO;TAMAÑO;TIPO;VEHICULO";
	private static VehiculoController vehiculoController = new VehiculoController();
	private Scanner in = new Scanner(System.in);

	public Map<Integer, Plaza> getPlazas() {
		return GarajeMain.garaje.getPlazas();
	}

	public Map<Integer, Plaza> listarPlazas(Garaje garaje) {
		return garaje.getPlazas();
	}

	public Collection<Plaza> getPlazasLibres() {
		Collection<Plaza> plazasLibres = new HashSet<Plaza>();
		for (Plaza plaza : getPlazas().values()) {
			if (plaza.getVehiculo() == null) {
				plazasLibres.add(plaza);
			}
		}
		return plazasLibres;
	}
	
	public void reservarPlaza() {
		System.out.print("Introduce número de plaza: ");
		int numeroPlaza=in.nextInt();
		in.nextLine();
		if (GarajeMain.garaje.getPlazas().get(numeroPlaza)==null) {
			System.out.println("Esa plaza no existe! (intro para continuar)");
			in.nextLine();
		}else {
			if (GarajeMain.garaje.getPlazas().get(numeroPlaza).getVehiculo()!=null) {
				System.out.println("La plaza está ocupada!");
				return;
			}else {
				reservarPlaza(numeroPlaza);
			}
		}
	}
	public void reservarPlaza(int numeroPlaza) {
		Plaza plaza = GarajeMain.garaje.getPlazas().get(numeroPlaza);
		if (plaza != null) {
			if (plaza.getVehiculo() == null) {
				Cliente cliente = new Cliente();
				cliente.inputClienteData();
				// Hago un nuevo vehículo (si no existe) y lo añado al mapa de vehículos
				Vehiculo vehiculo = vehiculoController.inputVehiculo();
				reservarPlaza(plaza, cliente, vehiculo);
			} else {
				System.out.println("Esa plaza está ocupada! (intro para continuar)");
				in.nextLine();
			}
		}
	}

	/**
	 * @param plaza
	 * @param cliente
	 * @param vehiculo
	 */
	public Reserva reservarPlaza(Plaza plaza, Cliente cliente, Vehiculo vehiculo) {
		vehiculo.setCliente(cliente);
		
		if (plaza.getVehiculo()!=null) {
			System.out.println("La plaza está ocupada!");
			return null;
		}
		
		//Compruebo que la plaza coincida con el tipo de vehículo
		switch (plaza.getClass().getSimpleName()) {
		case "PlazaCoche":
			if (vehiculo instanceof Coche) {
				plaza.setVehiculo(vehiculo);
			}
			break;
		case "PlazaMoto":
			if (vehiculo instanceof Moto) {
				plaza.setVehiculo(vehiculo);
			}
			break;
		default:
			break;
		}
		if (plaza.getVehiculo()==null) {
			System.out.println("La plaza no está disponible para este tipo de vehículo");
			return null;
		}else {
			//añado el vehículo al hashmap
			Garaje.getVehiculos().put(vehiculo.getMatricula(), vehiculo);
		}
		Reserva reserva = new Reserva(cliente, vehiculo, plaza, Calendar.getInstance());//tengo que añadirlo al hashmap!
		Garaje.getReservas().put("0", reserva);
		return reserva;
	}

	/**
	 * Lista las plazas de la clase pasada como Strins
	 * 
	 * @param tipo
	 * @return //
	 */
	public Map<Integer, Plaza> getPlazasByObjectClass(Object objeto) {
		Map<Integer, Plaza> plazas = getPlazas();
		Map<Integer, Plaza> plazasTipo = new HashMap<Integer, Plaza>();
		for (Plaza plaza : plazas.values()) {
			if (plaza.getClass().getSimpleName().equals(objeto.getClass().getSimpleName())) {
				plazasTipo.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazasTipo;
	}
	public Map<Integer, Plaza> getPlazasBySimpleName(String classSimpleName) {
		Map<Integer, Plaza> plazas = getPlazas();
		Map<Integer, Plaza> plazasTipo = new HashMap<Integer, Plaza>();
		for (Plaza plaza : plazas.values()) {
			if (plaza.getClass().getSimpleName().equals(classSimpleName)) {
				plazasTipo.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazasTipo;
	}

	public Map<Integer, Plaza> getPlazasCocheString() {
		Map<Integer, Plaza> plazas = getPlazas();
		Map<Integer, Plaza> plazasCoche = new HashMap<Integer, Plaza>();
		for (Plaza plaza : getPlazas().values()) {
			if (plaza instanceof PlazaCoche) {
				plazasCoche.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazas;
	}

	public Map<Integer, Plaza> getPlazasMotoString() {
		Map<Integer, Plaza> plazas = getPlazas();
		Map<Integer, Plaza> plazasMoto = new HashMap<Integer, Plaza>();
		for (Plaza plaza : getPlazas().values()) {
			if (plaza instanceof PlazaMoto) {
				plazasMoto.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazas;
	}

	public String listarPlazasCocheString() {
		StringBuilder plazasString = new StringBuilder();
		Map<Integer, Plaza> plazas = getPlazas();
		for (Plaza plaza : plazas.values()) {
			if (plaza instanceof PlazaCoche) {
				plazasString.append(plaza.toString() + "\n");
			}
		}
		return plazasString.toString();
	}

	public String listarPlazasMotoString() {
		StringBuilder plazasString = new StringBuilder();
		Map<Integer, Plaza> plazas = getPlazas();
		for (Plaza plaza : plazas.values()) {
			if (plaza instanceof PlazaMoto) {
				plazasString.append(plaza.toString() + "\n");
			}
		}
		return plazasString.toString();
	}

	/**
	 * Genera y devuelve una Plaza pasándole el string leido del archivo de texto
	 * 
	 * @param line
	 * @return
	 */
	public Plaza crearPlazaByString(String line) {
		String[] stringPlaza = line.split(";");
		int numPlaza = Integer.parseInt(stringPlaza[0]);
		Double precioPlaza = Double.parseDouble(stringPlaza[1]);
		Double metros2Plaza = Double.parseDouble(stringPlaza[2]);
		Vehiculo vehiculo = null;
		String matricula = stringPlaza[4];
		if (!matricula.equals("null")) {
			// Si hay matrícula, busco el vehículo con la matricula;
			Map<String, Vehiculo> vehiculosMap = vehiculoController.getVehiculos();
			vehiculo = vehiculosMap.get(matricula);
		}
		Plaza plaza;

		switch (stringPlaza[3]) {
		case "PlazaCoche":
			plaza = new PlazaCoche(numPlaza, precioPlaza, metros2Plaza, vehiculo);
			break;
		case "PlazaMoto":
			plaza = new PlazaMoto(numPlaza, precioPlaza, metros2Plaza, vehiculo);
			break;
		default:
			plaza = new Plaza(numPlaza, precioPlaza, metros2Plaza, vehiculo);
			break;
		}
		return plaza;
	}

	public void escribirFicheroPlazas() throws IOException {
		StringBuilder sb;

		File fichero = new File("src/main/resources/plazas.txt");
		FileWriter writer = new FileWriter(fichero);
		writer.write(CABECERATXT + "\n");
		writer.close();
		writer = new FileWriter(fichero, true);
		for (Plaza plaza : getPlazas().values()) {
			sb = new StringBuilder();
			String tipo = plaza.getClass().getSimpleName();
			sb.append(plaza.getNumeroPlaza()).append(";");
			sb.append(plaza.getPrecio()).append(";");
			sb.append(plaza.getMetrosCuadrados()).append(";");
			sb.append(tipo).append(";");
			sb.append((plaza.getVehiculo() == null) ? "null" : plaza.getVehiculo().getMatricula()).append("\n");
			writer.write(sb.toString());
		}
		writer.close();
	}

	/**
	 * Lee un archivo de texto con la información de la Plazas
	 * 
	 * @throws IOException
	 */
	public void leerFicheroPlazas() throws IOException {
		File fichero = new File("src/main/resources/plazas.txt");
		FileReader reader = new FileReader(fichero);
		BufferedReader buffer = new BufferedReader(reader);

		Plaza plaza;
		String line;
		while ((line = buffer.readLine()) != null) {
			if (!line.contains(CABECERATXT)) {
				plaza = crearPlazaByString(line);
				GarajeMain.garaje.getPlazas().put(plaza.getNumeroPlaza(), plaza);
			}
		}
		reader.close();
		buffer.close();
	}
}
