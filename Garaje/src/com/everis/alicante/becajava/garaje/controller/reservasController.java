package com.everis.alicante.becajava.garaje.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.everis.alicante.becajava.garage.domain.Cliente;
import com.everis.alicante.becajava.garage.domain.Plaza;
import com.everis.alicante.becajava.garage.domain.Reserva;
import com.everis.alicante.becajava.garage.domain.Vehiculo;
import com.everis.alicante.becajava.garaje.main.GarajeMain;

public class reservasController {
	/**
	 * Lee un archivo de texto con la información de la Vehiculos
	 * 
	 * @throws IOException
	 */
	private final static String CABECERATXT="IDRESERVA;CLIENTE;VEHICULO;PLAZA;FECHARESERVA;FECHAFINRESERVA";
	private final static String FILERESERVASPATH="src/main/resources/reservas.txt";
	
	
	public Map<String, Reserva> getReservas() {
		return GarajeMain.garaje.getReservas();
	}
	private static Calendar getCalendarByStringDate(String stringDate) {
		Locale currentLocale = new Locale("ES");
		String patternEntrada = "dd/MM/yyyy";
		
		DateFormat format = new SimpleDateFormat(patternEntrada, currentLocale);
		Date date;
		Calendar cal = Calendar.getInstance();
		try {
			date = format.parse(stringDate);
			cal.setTime(date);
		} catch (ParseException e) {
			System.out.println("La fecha "+stringDate+" no tiene el formato correcto");
			return null;
		}
		return cal;
	}
	public Reserva crearReservaByString(String line) {
		String[] stringReserva = line.split(";");
		
		String idReserva = stringReserva[0];
		Cliente cliente = GarajeMain.garaje.getClientes().get(stringReserva[1]);
		Vehiculo vehiculo = GarajeMain.garaje.getVehiculos().get(stringReserva[2]);
		Plaza plaza= GarajeMain.garaje.getPlazas().get(stringReserva[3]);
		Calendar fechaReserva = getCalendarByStringDate(stringReserva[4]);
		Calendar fechaFinReserva = getCalendarByStringDate(stringReserva[5]);
		
		Reserva reserva = new Reserva(idReserva,cliente,vehiculo,plaza,fechaReserva,fechaFinReserva);
		return reserva;
	}
	public void escribirFicheroReserva() throws IOException {
		StringBuilder sb;
		File fichero = new File(FILERESERVASPATH);
		FileWriter writer = new FileWriter(fichero);
		writer.write(CABECERATXT+"\n");
		writer.close();
		writer = new FileWriter(fichero, true);
		String texto;
		for (Vehiculo vehiculo : getReservas().values()) {
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
	
	public void leerFicheroReserva() throws IOException {
		File fichero = new File(FILERESERVASPATH);
		FileReader reader = new FileReader(fichero);
		BufferedReader buffer = new BufferedReader(reader);

		Reserva reserva;
		String line;
		while ((line = buffer.readLine()) != null) {
			if (!line.contains(CABECERATXT)) {
				reserva = crearReservaByString(line);
				getReservas().put(reserva.getIdReserva(), reserva);
			}
		}
		reader.close();
		buffer.close();
	}
}
