package com.movingmachines.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.movingmachines.entities.Direction;
import com.movingmachines.entities.Machine;
import com.movingmachines.entities.exceptions.positionNotAvaliableException;

/**
 * Handles the core functions of the application like creating the grid and adding machines to it.
 * 
 * @author Cristiano
 * 
 */
public class GridEngine {

	private int rows;
	private int cols;
	private Object[][] grid;
	private List<Machine> machines;

	public GridEngine(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		this.grid = new Object[cols][rows];
		machines = new ArrayList<Machine>();
		System.out.println("Creating Grid with " + cols + " Columns and " + rows + " Rows");
	}

	/**
	 * Adds a machine to the grid in it's initial position
	 * 
	 * @param x
	 *            column to set the machine on
	 * @param y
	 *            row to set machine on
	 * @param direction
	 *            diretion the machine is pointing at
	 * @return a {@link Machine}
	 * @throws Exception
	 * @author Cristiano
	 */
	public Machine addMachine(int x, int y, Direction direction) throws Exception {
		if (x > 0 && x < this.getCols() && y > 0 && y < this.getRows()) {
			Machine machine = new Machine(x, y, direction);
			machines.add(machine);
			this.grid[machine.getxPosition()][machine.getyPosition()] = machine;
			System.out.println("Creating Machine " + machines.size() + " At " + x + " " + y + " Heading "
					+ direction.toString());
			return machine;
		}
		throw new Exception("Cannot create a machine outside of the grid.");
	}
	
	public void removeMachine(Machine machine) {
		this.grid[machine.getxPosition()][machine.getyPosition()] = null;
	}

	/**
	 * Set the position of a machine and validates it.
	 * 
	 * @param machine
	 * @throws positionNotAvaliableException
	 * @author Cristiano
	 */
	public void set(Machine machine) throws positionNotAvaliableException {

		if (machine.getxPosition() < 0 || machine.getxPosition() > this.getCols() || machine.getyPosition() < 0
				|| machine.getyPosition() > this.getRows()) {
			// Moving out of the grid?
			throw new positionNotAvaliableException("It's not possible to move to that position.");
		} else if (this.grid[machine.getxPosition()][machine.getyPosition()] != null) {
			// Moving on an occupied position?
			throw new positionNotAvaliableException("It's not possible to move to that position.");
		}

		this.grid[machine.getxPosition()][machine.getyPosition()] = machine;

	}

	/**
	 * Creates
	 * 
	 * @author Cristiano
	 * @throws IOException
	 */
	public void generateOutputFile() throws IOException {
		System.out.println("Creating Output File");
		File file = new File("output.txt");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (Machine machine : machines) {
			StringBuffer str = new StringBuffer().append(machine.getxPosition()).append(" ")
					.append(machine.getyPosition()).append(" ").append(machine.getDirection()).append("\n");
			bw.write(str.toString());
		}
		bw.close();
		System.out.println("File Created");
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

}
