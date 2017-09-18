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

import com.everis.alicante.becajava.garage.domain.Cliente;
import com.everis.alicante.becajava.garage.domain.Garaje;
import com.everis.alicante.becajava.garage.domain.Plaza;
import com.everis.alicante.becajava.garage.domain.PlazaCoche;
import com.everis.alicante.becajava.garage.domain.PlazaMoto;
import com.everis.alicante.becajava.garage.domain.Vehiculo;
import com.everis.alicante.becajava.garaje.main.GarajeMain;

public class GarajeController {
	private final static String CABECERATXT="NUMEROPLAZA;PRECIO;TAMAÑO;TIPO;VEHICULO";
	private VehiculoController vehiculoController = new VehiculoController();
	
	public Map<Integer, Plaza> getPlazas() {
		return GarajeMain.garaje.getPlazas();
	}

	public void mostrarCollection(Collection coleccion) {
		for (Object object : coleccion) {
			System.out.println(object.toString());
		}
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

	/**
	 * Inicia plazas
	 * 
	 */
	public void iniciarPlazas() {
		try {
			leerFicheroPlazas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reservarPlaza(int numeroPlaza) {
		Plaza plaza = GarajeMain.garaje.getPlazas().get(numeroPlaza);
		if (plaza != null) {
			if (plaza.getVehiculo() == null) {
				Cliente cliente = new Cliente();
				cliente.inputClienteData();
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.inputVehiculoData();
				reservarPlaza(plaza, cliente, vehiculo);
			} else {
				System.out.println("La plaza ya está ocupada!!!");
			}
		}
	}

	/**
	 * @param plaza
	 * @param cliente
	 * @param vehiculo
	 */
	public void reservarPlaza(Plaza plaza, Cliente cliente, Vehiculo vehiculo) {
		vehiculo.setCliente(cliente);
		plaza.setVehiculo(vehiculo);
	}

	/** Lista las plazas de la clase pasada como Strins
	 * @param tipo
	 * @return //
	 */
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
		if (stringPlaza[4] != "null") {
			vehiculo=new Vehiculo(stringPlaza[4]);
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
		vehiculoController.escribirFicheroVehiculos();
		File fichero = new File("src/main/resources/plazas.txt");
		FileWriter writer = new FileWriter(fichero);
		writer.write(CABECERATXT+"\n");
		writer.close();
		writer = new FileWriter(fichero, true);
		String texto;
		for (Plaza plaza : getPlazas().values()) {
			String tipo = plaza.getClass().toString();
			tipo = plaza.getClass().getSimpleName();
			texto = plaza.getNumeroPlaza() + ";" + plaza.getPrecio() + ";" + plaza.getMetrosCuadrados() + ";" + tipo
					+";"+((plaza.getVehiculo()==null)?"null":plaza.getVehiculo().getMatricula())
					+"\n";
			writer.write(texto);
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
