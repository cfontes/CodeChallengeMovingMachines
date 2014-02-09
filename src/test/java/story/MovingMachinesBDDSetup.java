package story;

import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public abstract class MovingMachinesBDDSetup extends JUnitStory {

	public MovingMachinesBDDSetup() {
		configuredEmbedder();
	}

	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new MovingMachinesSteps());
	}

}
