package story;

import java.util.Locale;
import java.util.Properties;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public abstract class MovingMachinesBDDSetup extends JUnitStory {

	@Override
	public Configuration configuration() {

		Keywords keywords = new LocalizedKeywords(new Locale("en"));
		Properties viewResources = new Properties();
		viewResources.put("views", "ftl/jbehave-views.ftl");
		StoryReporterBuilder storyReporter = this.createReportBuilder(keywords, viewResources);
		return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(this.getClass()))
				.useStoryReporterBuilder(storyReporter).usePendingStepStrategy(new PassingUponPendingStep());
	}

	private StoryReporterBuilder createReportBuilder(Keywords keywords, Properties viewResources) {

		StoryReporterBuilder storyReporter = new StoryReporterBuilder() {
			@Override
			public StoryReporterBuilder withDefaultFormats() {
				return this.withFormats(org.jbehave.core.reporters.Format.CONSOLE);
			}
		};
		storyReporter.withDefaultFormats().withViewResources(viewResources).withFormats(Format.HTML_TEMPLATE)
				.withKeywords(keywords);

		return storyReporter;
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		try {
			return new InstanceStepsFactory(this.configuration(), this.getClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

}
