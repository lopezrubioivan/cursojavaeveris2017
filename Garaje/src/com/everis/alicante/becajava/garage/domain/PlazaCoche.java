package com.everis.alicante.becajava.garage.domain;

public class PlazaCoche extends Plaza{
	/** Default constructor
	 * 
	 */
	public PlazaCoche() {
		
	}
	/**
	 * @param numeroPlaza
	 * @param precio
	 * @param metrosCuadrados
	 * @param vehiculo
	 */
	public PlazaCoche(Integer numeroPlaza, Double precio, Double metrosCuadrados, Vehiculo vehiculo) {
		super(numeroPlaza,precio,metrosCuadrados,vehiculo);
	}
}
