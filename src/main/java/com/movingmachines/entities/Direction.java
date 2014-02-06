package com.movingmachines.entities;

/**
 * Direction that the machine is pointing at
 * 
 * @author Cristiano
 * 
 */
public enum Direction {
	
	N("North"), S("South"), W("West"), E("East");

	String name;

	Direction(String name) {
		this.name = name;
	}
}
