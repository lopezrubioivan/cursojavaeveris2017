package com.everis.alicante.becajava.garage.domain;

public class Plaza {
	private Integer numeroPlaza;
	private Double precio;
	private Double metrosCuadrados;
	private Vehiculo vehiculo;
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Integer getNumeroPlaza() {
		return numeroPlaza;
	}
	public void setNumeroPlaza(Integer numeroPlaza) {
		this.numeroPlaza = numeroPlaza;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getMetrosCuadrados() {
		return metrosCuadrados;
	}
	public void setMetrosCuadrados(Double metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" [numeroPlaza=" + numeroPlaza + ", precio=" + precio + ", metrosCuadrados=" + metrosCuadrados
				+ ", vehiculo=");
		sb.append((vehiculo==null)?"LIBRE":vehiculo.toString());
		sb.append("]");
		return sb.toString();
	}
	/** Default constructor
	 * 
	 */
	public Plaza() {
		
	}
	/**
	 * @param numeroPlaza
	 * @param precio
	 * @param metrosCuadrados
	 * @param vehiculo
	 */
	public Plaza(Integer numeroPlaza, Double precio, Double metrosCuadrados, Vehiculo vehiculo) {
		super();
		this.numeroPlaza = numeroPlaza;
		this.precio = precio;
		this.metrosCuadrados = metrosCuadrados;
		this.vehiculo = vehiculo;
	}
	

}
