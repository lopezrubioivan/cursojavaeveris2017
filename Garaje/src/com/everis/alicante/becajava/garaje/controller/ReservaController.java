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
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.everis.alicante.becajava.garage.domain.Cliente;
import com.everis.alicante.becajava.garage.domain.Garaje;
import com.everis.alicante.becajava.garage.domain.Plaza;
import com.everis.alicante.becajava.garage.domain.Reserva;
import com.everis.alicante.becajava.garage.domain.Vehiculo;
import com.everis.alicante.becajava.garaje.main.GarajeMain;

public class ReservaController {
	/**
	 * Lee un archivo de texto con la información de la Vehiculos
	 * 
	 * @throws IOException
	 */
	private final static String CABECERATXT="CLIENTE;VEHICULO;PLAZA;FECHARESERVA;FECHAFINRESERVA";
	private final static String FILERESERVASPATH="src/main/resources/reservas.txt";
	
	
	public Map<String, Reserva> getReservas() {
		return Garaje.getReservas();
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
		
		Cliente cliente = Garaje.getClientes().get(stringReserva[0]);
		Vehiculo vehiculo = Garaje.getVehiculos().get(stringReserva[1]);
		Plaza plaza= GarajeMain.garaje.getPlazas().get(Integer.parseInt(stringReserva[2]));
		Calendar fechaReserva = getCalendarByStringDate(stringReserva[3]);
		Calendar fechaFinReserva = getCalendarByStringDate(stringReserva[4]);
		
		Reserva reserva = new Reserva(cliente,vehiculo,plaza,fechaReserva,fechaFinReserva);
		return reserva;
	}
	public void escribirFicheroReserva() throws IOException {
		StringBuilder sb;
		File fichero = new File(FILERESERVASPATH);
		FileWriter writer = new FileWriter(fichero);
		writer.write(CABECERATXT+"\n");
		writer.close();
		writer = new FileWriter(fichero, true);
		for (Reserva reserva : getReservas().values()) {
			sb=new StringBuilder();
			sb.append(reserva.getCliente().getDni()).append(";");
			sb.append(reserva.getVehiculo().getMatricula()).append(";");
			sb.append(reserva.getPlaza().getNumeroPlaza()).append(";");
			sb.append(reserva.getSimpleFechaReserva()).append(";");
			sb.append(reserva.getSimpleFechaFinReserva());
			sb.append("\n");
			writer.write(sb.toString());
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
