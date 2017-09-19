package com.everis.alicante.becajava.garage.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.net.ServerSocketFactory;

public class Reserva {
	private String idReserva;
	private Cliente cliente;
	private Vehiculo vehiculo;
	private Plaza plaza;
	private Calendar fechaReserva;
	private Calendar fechaFinReserva;
	
	public Reserva(String idReserva2, Cliente cliente2, Vehiculo vehiculo2, Plaza plaza2, Calendar fechaReserva2,
			Calendar fechaFinReserva2) {
		setIdReserva(idReserva2);
		setCliente(cliente2);
		setVehiculo(vehiculo2);
		setPlaza(plaza2);
		setFechaFinReserva(fechaFinReserva2);
		setFechaReserva(fechaReserva2);
	}
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
	public String getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	public Calendar getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Calendar fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Calendar getFechaFinReserva() {
		return fechaFinReserva;
	}
	public void setFechaFinReserva(Calendar fechaFinReserva) {
		this.fechaFinReserva = fechaFinReserva;
	}
	
}
