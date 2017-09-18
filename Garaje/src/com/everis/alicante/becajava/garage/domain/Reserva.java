package com.everis.alicante.becajava.garage.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Reserva {
	private Cliente cliente;
	private Vehiculo vehiculo;
	private Plaza plaza;
	private Calendar fechaReserva;
	private Calendar fechaFinReserva;
	
	public Calendar getFinReservaDias(int dias) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar fechaFinReserva=Calendar.getInstance();
		fechaFinReserva.setTime(fechaReserva.getTime());
		fechaFinReserva.set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR+dias);
		
		System.out.println(sdf.format(fechaFinReserva.getTime()));
		return fechaFinReserva;
	}
	public static void main(String[] args) {
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Plaza getPlaza() {
		return plaza;
	}
	public void setPlaza(Plaza plaza) {
		this.plaza = plaza;
	}
}
