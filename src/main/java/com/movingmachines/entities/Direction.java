package com.movingmachines.entities;

import java.text.ParseException;

/**
 * Direction that the machine is pointing at
 * 
 * @author Cristiano
 * 
 */
public enum Direction {
	
	N(0, 1), W(1, 0), S(0, -1), E(-1, 0);

	private int xCorrection;
	private int yCorrection;

	Direction(int x, int y) {
		this.xCorrection = x;
		this.yCorrection = y;
	}

	public int getXCorrection() {
		return this.xCorrection;
	}

	public int getYCorrection() {
		return this.yCorrection;
	}

	public static Direction toDirection(String dir) throws ParseException {
		switch (dir) {
		case "N":
			return Direction.N;
		case "S":
			return Direction.S;
		case "W":
			return Direction.W;
		case "E":
			return Direction.E;
		default:
			throw new ParseException("Unable to parse Direction", 0);
		}
	}
}
