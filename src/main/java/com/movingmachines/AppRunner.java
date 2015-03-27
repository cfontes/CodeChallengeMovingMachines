package com.movingmachines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.movingmachines.core.GridEngine;
import com.movingmachines.entities.Direction;
import com.movingmachines.entities.Machine;

/**
 * Main application file
 *
 * @author Cristiano
 */
public class AppRunner {
    /**
     *
     * Start the application and fetch the command file.
     *
     * @param args the input arguments
     * @author Cristiano
     */
	public static void main(String[] args) {
		BufferedReader bufferReader = null;
		GridEngine gridEngine = null;
        List<Machine> machines = new ArrayList<>();

		try {
 
			String line;
 
			bufferReader = new BufferedReader(new FileReader("input.txt"));
			Machine machine = null;

			while ((line = bufferReader.readLine()) != null) {
				String[] commands = line.split("\\s");
				if (gridEngine == null) {
					gridEngine = new GridEngine(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]));
				} else if (commands.length > 1) {
					machine = gridEngine.addMachine(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]),
							Direction.toDirection(commands[2]));
                    machines.add(machine);
				} else if (machine != null) {
                    machine.recordMovements(commands[0], gridEngine);
				}
			}
        } catch (IOException e) {
			System.out.println("Error writing to file - " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error - " + e.getMessage());
		} finally {
			try {
				if (bufferReader != null)
					bufferReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

        List<Thread> threads = new ArrayList<>();
        for (Machine machine : machines) {
                Thread thread = new Thread(machine);
                thread.start();
                threads.add(thread);
        }

        createOutputFile(gridEngine, threads);
    }

    /**
     * Waits for all threads to finish before creating the answer file
     * @param gridEngine grid
     * @param threads list of machine threads
     */
    private static void createOutputFile(GridEngine gridEngine, List<Thread> threads) {
        try {
            for (Thread thread : threads) {
                thread.join();
            }

            if (gridEngine != null) {
                gridEngine.generateOutputFile();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}
