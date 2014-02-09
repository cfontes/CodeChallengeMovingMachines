package story;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.movingmachines.core.GridEngine;
import com.movingmachines.entities.Direction;
import com.movingmachines.entities.Machine;
import com.movingmachines.entities.exceptions.positionNotAvaliableException;

/**
 * 
 * Stories to make sure my machine works
 * 
 * @author Cristiano
 * 
 */
public class MovingMachinesSteps extends MovingMachinesBDDSetup {

	private GridEngine engine;
	private Machine machine;

	@Given("there is a grid of the size $x and $y")
	public void thereIsAGrid(int x, int y){
		this.engine = new GridEngine(x, y);
	}
	
	@Given("the machine is on square $x, $y pointing at $direction")
	public void theMachineIsOn(int x, int y, String direction) throws Exception {
		machine = this.engine.addMachine(x, y, Direction.toDirection(direction));
	}
	
	@When("the machine receives the command $command")
	public void interpretCommand(String command) throws ParseException, positionNotAvaliableException {
		machine.execute(command, engine);
	}

	@Then("it will move to square $x, $y pointing to $direction")
	public void moveTheMachineTo(int x, int y, String direction) throws ParseException {
		assertEquals("X value is not the expected one.", machine.getxPosition(), x);
		assertEquals("Y value is not the expected one.", machine.getyPosition(), y);
		assertEquals("Direction value is not the expected one.", machine.getDirection(),
				Direction.toDirection(direction));
	}
	
}
