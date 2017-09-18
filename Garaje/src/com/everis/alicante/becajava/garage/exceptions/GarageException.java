package com.everis.alicante.becajava.garage.exceptions;

public class GarageException extends Exception {
	
	/**
	 * 
	 */
	private static final String errorMessage="Ha ocurrido un error en el garaje: \n";
	private StringBuilder sb=new StringBuilder();
	private static final long serialVersionUID = 1L;
	
	public GarageException(String message) {
        super(message);
        System.out.println(sb.append(errorMessage).append(message));
    }

}
