package com.movingmachines.core;

/**
 * Keeps state of the application.
 * 
 * @author Cristiano
 * 
 */
public class Engine {

	private int rows;
	private int cols;
	private Object[] grid;

	public Engine(int x, int y) {
		this.rows = x;
		this.cols = y;
		this.grid = new Object[x * this.cols + y];
	}

	public void addMachine() {

	}

	public Object at(int x, int y) {
		return this.grid[x * this.cols + y];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

}
