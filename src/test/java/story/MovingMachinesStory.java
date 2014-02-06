package story;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * 
 * Stories to make sure my machine works
 * 
 * @author Cristiano
 * 
 */
public class MovingMachinesStory extends MovingMachinesBDDSetup {

	@Given("there is a grid of the size $x and $y")
	public void thereIsAGrid(int x, int y){
		
	}
	
	@Given("the machine is on square $x, $y pointing at $direction")
	public void theMachineIsOn(int x, int y, String direction){
		
	}
	
	@When("the machine receives the command $command")
	public void interpretCommand(String command) {

	}

	@Then("it will move to square $x, $y pointing to $direction")
	public void moveTheMachineTo(int x, int y, String direction) {

	}
	
}
