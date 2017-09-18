package com.everis.alicante.becajava.garaje.controller;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
	public Map<Integer, Plaza> listarPlazas() {
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
		for (Plaza plaza : listarPlazas().values()) {
			if (plaza.getVehiculo() == null) {
				plazasLibres.add(plaza);
			}
		}
		return plazasLibres;
	}

	/**
	 * Inicio las plazas con valores para no tener que insertarlas una a una
	 * 
	 */
	public void iniciarPlazas() {
		Plaza plaza;
		try {
			leerFicheroPlazas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (Integer i = 0; i < 10; i++) {
//			if (i < 5) {
//				plaza = new PlazaCoche(i + 1, 75.5d, 2d, null);
//			} else {
//				plaza = new PlazaMoto(i + 1, 75.5d, 2d, null);
//			}
//			GarajeMain.garaje.getPlazas().put(i, plaza);
//		}
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
			}else {
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

	/**
	 * @param tipo
	 * @return //
	 */
	public Map<Integer, Plaza> getPlazasByTipo(String className) {
		Class classType;
		try {
			classType = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		Map<Integer, Plaza> plazas = listarPlazas();
		Map<Integer, Plaza> plazasTipo = new HashMap<Integer, Plaza>();
		for (Plaza plaza : listarPlazas().values()) {
			if (classType.isInstance(plaza)) {
				plazasTipo.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazas;
	}

	public Map<Integer, Plaza> getPlazasCocheString() {
		Map<Integer, Plaza> plazas = listarPlazas();
		Map<Integer, Plaza> plazasCoche = new HashMap<Integer, Plaza>();
		for (Plaza plaza : listarPlazas().values()) {
			if (plaza instanceof PlazaCoche) {
				plazasCoche.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazas;
	}

	public Map<Integer, Plaza> getPlazasMotoString() {
		Map<Integer, Plaza> plazas = listarPlazas();
		Map<Integer, Plaza> plazasMoto = new HashMap<Integer, Plaza>();
		for (Plaza plaza : listarPlazas().values()) {
			if (plaza instanceof PlazaMoto) {
				plazasMoto.put(plaza.getNumeroPlaza(), plaza);
			}
		}
		return plazas;
	}

	public String listarPlazasCocheString() {
		StringBuilder plazasString = new StringBuilder();
		Map<Integer, Plaza> plazas = listarPlazas();
		for (Plaza plaza : plazas.values()) {
			if (plaza instanceof PlazaCoche) {
				plazasString.append(plaza.toString()+"\n");
			}
		}
		return plazasString.toString();
	}

	public String listarPlazasMotoString() {
		StringBuilder plazasString = new StringBuilder();
		Map<Integer, Plaza> plazas = listarPlazas();
		for (Plaza plaza : plazas.values()) {
			if (plaza instanceof PlazaMoto) {
				plazasString.append(plaza.toString()+"\n");
			}
		}
		return plazasString.toString();
	}
	
	public Plaza crearPlazaString(String line) {
		String[] stringPlaza=line.split(";");
		int numPlaza=Integer.parseInt(stringPlaza[0]);
		Double precioPlaza=Double.parseDouble(stringPlaza[1]);
		Double metros2Plaza=Double.parseDouble(stringPlaza[2]);
		Plaza plaza;
		
		switch (stringPlaza[3]) {
		case "com.everis.alicante.becajava.garage.domain.PlazaCoche":
			plaza=new PlazaCoche(numPlaza, precioPlaza,metros2Plaza, null);
			break;
		case "com.everis.alicante.becajava.garage.domain.PlazaMoto":
			plaza=new PlazaMoto(numPlaza, precioPlaza,metros2Plaza, null);
			break;
		default:
			plaza=new Plaza(numPlaza, precioPlaza,metros2Plaza, null);
			break;
		}
		return plaza;
	}
	public void escribirFicheroPlazas() throws IOException {
		File fichero=new File("src/main/resources/plazas.txt");
		FileWriter writer = new FileWriter(fichero);
		writer.write("NUMEROPLAZA;PRECIO;TAMAÑO;TIPO\n");
		writer.close();
		writer = new FileWriter(fichero,true);
		String texto;
		for (Plaza plaza : listarPlazas().values()) {
			String tipo=plaza.getClass().toString();
			texto=plaza.getNumeroPlaza()+";"+plaza.getPrecio()+";"+plaza.getMetrosCuadrados()+";"+tipo+"\n";
			writer.write(texto);
		}
		writer.close();
	}
	public void leerFicheroPlazas() throws IOException {
		File fichero=new File("src/main/resources/plazas.txt");
		FileReader reader=new FileReader(fichero);
		BufferedReader buffer = new BufferedReader(reader);
		
		Plaza plaza;
		
		String line;
		try {
			while ((line=buffer.readLine())!=null) {
				if (!line.contains("NUMEROPLAZA;PRECIO;TAMAÑO;TIPO")) {
					plaza=crearPlazaString(line);
					GarajeMain.garaje.getPlazas().put(plaza.getNumeroPlaza(),crearPlazaString(line));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		reader.close();
	}
}
