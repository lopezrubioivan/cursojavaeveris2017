package com.everis.alicante.becajava.garage.domain;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Vehiculo {
	private String matricula;
	private String modelo, marca;
	private Cliente cliente;
	private static Set<String> marcas = new HashSet<String>();
	
	private void iniMarcas() {
		marcas.add("Ford");
		marcas.add("Seat");
		marcas.add("Fiat");
		marcas.add("Mercedes");
	}
	public Vehiculo() {
		super();
		iniMarcas();
	}
	public Vehiculo(String matricula) {
		super();
		setMatricula(matricula);
		iniMarcas();
	}
	public Vehiculo(String matricula,String modelo, String marca, Cliente cliente) {
		super();
		setMatricula(matricula);
		setMarca(marca);
		setModelo(modelo);
		setCliente(cliente);
		iniMarcas();
	}
	public void inputVehiculoData() {
		Scanner in= new Scanner(System.in);
		System.out.print("Introduce modelo de vehículo: ");
		setModelo(in.nextLine());
		System.out.print("Introduce Matricula: ");
		setMatricula(in.nextLine());
//		in.close();
	}
	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", cliente=" + cliente
				+ "]";
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
}
