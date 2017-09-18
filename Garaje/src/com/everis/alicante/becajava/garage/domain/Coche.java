package com.everis.alicante.becajava.garage.domain;

public class Coche extends Vehiculo implements Aparcable{

	public Coche() {
		// TODO Auto-generated constructor stub
	}
	public Coche(String matricula,String modelo, String marca, Cliente cliente) {
		super(matricula,modelo,marca,cliente);
	}
	@Override
	public void aparcar() {
		// TODO Auto-generated method stub
		System.out.println("Sé aparcar");
	}

	@Override
	public void desaparcar() {
		// TODO Auto-generated method stub
		System.out.println("Sé desaparcar");
	}

}
