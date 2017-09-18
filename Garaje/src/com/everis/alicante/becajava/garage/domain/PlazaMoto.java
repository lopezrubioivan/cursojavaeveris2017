package com.everis.alicante.becajava.garage.domain;

public class PlazaMoto extends Plaza {
	public PlazaMoto() {

	}

	/**
	 * @param numeroPlaza
	 * @param precio
	 * @param metrosCuadrados
	 * @param vehiculo
	 */
	public PlazaMoto(Integer numeroPlaza, Double precio, Double metrosCuadrados, Vehiculo vehiculo) {
		super(numeroPlaza,precio,metrosCuadrados,vehiculo);
	}
}
