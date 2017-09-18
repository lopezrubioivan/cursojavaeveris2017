package com.everis.alicante.becajava.garage.domain;

import java.util.HashMap;
import java.util.Map;

public class Garaje {
	private static Map<Integer,Plaza> plazas = new HashMap<Integer,Plaza>();
	private static Map<String,Cliente> clientes = new HashMap<String,Cliente>();

	public Map<Integer, Plaza> getPlazas() {
		return plazas;
	}

	public void setPlazas(Map<Integer, Plaza> plazas) {
		this.plazas = plazas;
	}
	
	public static Cliente getClienteByField(String fieldName,String fieldValor) {
//		clientes.values().contains("sdg");
		clientes.values();
		for (Cliente cliente : clientes.values()) {
			if ((cliente.nombre)==fieldValor){
				return cliente;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		clientes.put("456456r", new Cliente("Pepe", "López", "456456r"));
		clientes.put("456457r", new Cliente("2", "López", "456456r"));
		clientes.put("456458r", new Cliente("3", "López", "456456r"));
		clientes.put("456459r", new Cliente("4", "López", "456456r"));
		clientes.put("456410r", new Cliente("5", "López", "456456r"));
		clientes.put("456411r", new Cliente("6", "López", "456456r"));
		System.out.println(getClienteByField("nombre","2").toString());
	}

}
