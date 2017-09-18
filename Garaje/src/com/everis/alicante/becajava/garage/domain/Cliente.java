package com.everis.alicante.becajava.garage.domain;

import java.util.Scanner;

public class Cliente implements Comparable<Cliente>{
	protected String nombre, apellidos, dni;
	
	public Cliente() {
		super();
	}
	public Cliente(String nombre, String apellidos, String dni) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
	}

	public void inputClienteData() {
		Scanner in= new Scanner(System.in);
		System.out.print("Introduce nombre: ");
		nombre=in.nextLine();
		System.out.print("Introduce DNI: ");
		dni=in.nextLine();
//		in.close();
	}
	
	public String getNombreCompleto() {
		return nombre+" "+apellidos;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", dni=" + dni + "]";
	}
	@Override
	public int compareTo(Cliente other) {
		// TODO Auto-generated method stub
		if (!this.getNombreCompleto().equalsIgnoreCase(other.getNombreCompleto()))
            return this.getNombreCompleto().compareTo(other.getNombreCompleto());
		return 0;
	}
}
