package com.everis.alicante.becajava.garage.domain;

import java.text.DateFormat;
import java.text.ParseException;
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
	
	public Reserva() {
		super();
	}
	public Reserva(Cliente cliente, Vehiculo vehiculo, Plaza plaza, Calendar fechaReserva, int diasReserva) {
		super();
		setIdReserva(idReserva);
		setCliente(cliente);
		setVehiculo(vehiculo);
		setPlaza(plaza);
		setFechaReserva(fechaReserva);
		setFechaFinReserva(getFinReservaDias(diasReserva));
	}
	public Reserva(Cliente cliente, Vehiculo vehiculo, Plaza plaza, Calendar fechaReserva) {
		super();
		setIdReserva(idReserva);
		setCliente(cliente);
		setVehiculo(vehiculo);
		setPlaza(plaza);
		setFechaReserva(fechaReserva);
		setFechaFinReserva(getFinReservaDias(7));//la reserva dura 7 días por defecto
	}
	public Reserva(Cliente cliente, Vehiculo vehiculo, Plaza plaza, Calendar fechaReserva,
			Calendar fechaFinReserva2) {
		super();
		setIdReserva(idReserva);
		setCliente(cliente);
		setVehiculo(vehiculo);
		setPlaza(plaza);
		setFechaFinReserva(fechaFinReserva2);
		setFechaReserva(fechaReserva);
	}
	public Calendar getFinReservaDias(int dias) {
		Calendar fechaFinReserva=Calendar.getInstance();
		fechaFinReserva.setTime(fechaReserva.getTime());
		fechaFinReserva.add(Calendar.DAY_OF_YEAR, dias);
		return fechaFinReserva;
	}
	public static void main(String[] args) {
	}
	
	private String formatSimpleDate(Calendar calendario) {
		Locale currentLocale = new Locale("ES");
		String patternSalida = "dd/MM/yyyy";
		
		DateFormat formato = new SimpleDateFormat(patternSalida, currentLocale);
		return formato.format(calendario.getTime());
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
	public String getSimpleFechaReserva() {
		return formatSimpleDate(fechaReserva);
	}
	public String getSimpleFechaFinReserva() {
		return formatSimpleDate(fechaFinReserva);
	}
	
}
