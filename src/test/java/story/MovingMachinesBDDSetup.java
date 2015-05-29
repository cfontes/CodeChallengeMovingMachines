package story;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

/**
 * Configuring JBehave to find the stories
 * 
 * @author Cristiano
 * 
 */
@RunWith(JUnitReportingRunner.class)
public abstract class MovingMachinesBDDSetup extends JUnitStory {

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				       .useStoryLoader(new LoadFromClasspath(this.getClass()))
				       .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT))
                       .usePendingStepStrategy(new PassingUponPendingStep());
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
        try {
            return new InstanceStepsFactory(this.configuration(), this.getClass().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
	}

}
