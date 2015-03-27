package com.movingmachines.entities;

import java.text.ParseException;

import com.movingmachines.core.GridEngine;
import com.movingmachines.entities.exceptions.positionNotAvaliableException;

/**
 * 
 * Machine base class
 * 
 * @author Cristiano
 * 
 */
public class Machine implements Runnable{

	//This is ID is only used to output the sysout map with the machine ids on it.
	private static int counter = 1;
	private int id;

	private int xPosition;
	private int yPosition;
	private Direction direction;

	/**
	 * Instantiate the machine
	 * 
	 * @param x
	 *            X position that the machine starts in
	 * @param y
	 *            Y position that the machine starts in
	 * @param direction
	 *            direction that it's pointing at
	 */
	public Machine(int x, int y, Direction direction) {
		this.id = counter++;
		this.xPosition = x;
		this.yPosition = y;
		this.direction = direction;
	}

    /**
     * This machine list of movements
     */
    private char[] commands;

    /**
     * Grid that the machine is on
     */
    private GridEngine grid;

    /**
     * Validates and execute commands sent to the machine
     *
     * @param commandString
     *            A string with commands without spaces
     * @throws ParseException
     *             Problems parsing this commands into valid actions will throw this exception
     * @throws positionNotAvaliableException
     *             Happens when we try to move to a position that is occupied or out of bounds
     * @author Cristiano
     */
    public void recordMovements(String commandString, GridEngine grid) {
        System.out.println("Executing commands " + commandString);
        this.grid = grid;
        this.commands = commandString.toCharArray();
    }

    /**
     * Moves the machine one position in the current direction
     *
     * @author Cristiano
     */
    public synchronized void move() {
//        System.out.println("Current position is now " + this.xPosition + ", " + this.yPosition + " " + this.direction);
        this.setxPosition(this.getxPosition() + this.getDirection().getXCorrection());
		this.setyPosition(this.getyPosition() + this.getDirection().getYCorrection());
//		System.out.println("Current position is now " + this.xPosition + ", " + this.yPosition + " " + this.direction);
	}

	/**
	 * Verify which is the direction left to the current one
	 * 
	 * @return Direction that is left to the actual one
	 * @throws ParseException
	 *             in case of a invalid direction
	 * @author Cristiano
	 */
	public Direction turnLeft() throws ParseException {
		if (this.getDirection() == Direction.N) {
			return Direction.E;
		} else if (this.getDirection() == Direction.E) {
			return Direction.S;
		} else if (this.getDirection() == Direction.S) {
			return Direction.W;
		} else if (this.getDirection() == Direction.W) {
			return Direction.N;
		}
		throw new ParseException("Invalid direction", 0);
	}

	/**
	 * Verify which is the direction right to the current one
	 * 
	 * @return Direction that is right to the actual one
	 * @throws ParseException
	 *             in case of a invalid direction
	 * @author Cristiano
	 */
	public Direction turnRIght() throws ParseException {
		if (this.getDirection() == Direction.N) {
			return Direction.W;
		} else if (this.getDirection() == Direction.W) {
			return Direction.S;
		} else if (this.getDirection() == Direction.S) {
			return Direction.E;
		} else if (this.getDirection() == Direction.E) {
			return Direction.N;
		}
		throw new ParseException("Invalid direction", 0);
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getId() {
		return id;
	}

    @Override
    public void run() {
        try {
            for (char command : commands) {
                switch (command) {
                    case 'L':
                        this.setDirection(this.turnLeft());
//                        System.out.println("Machine "+this.getId()+" Turning Left. Direction now is " + this.direction);
                        break;
                    case 'R':
                        this.setDirection(this.turnRIght());
//                        System.out.println("Machine "+this.getId()+" Turning Right. Direction now is " + this.direction);
                        break;
                    case 'M':
                        synchronized (this) {
                            this.grid.removeMachine(this);
                            //                        System.out.println("Machine "+this.getId()+" Moving");
                            this.move();
                            this.grid.set(this);
                        }
                        break;
                    default:
                        System.out.println("Error reading commands - Error parsing command " + command + " to machine " + this.getId() + " is not a valid command");
                }
            }
        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error reading commands - " + e.getMessage());
        } catch (positionNotAvaliableException e) {
            e.printStackTrace();
        }
    }
}
