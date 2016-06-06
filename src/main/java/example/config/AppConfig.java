package example.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Import({ DataConfig.class, SecurityConfig.class })
@PropertySource(value = "conf/app.props", ignoreResourceNotFound = true)
@PropertySource(value = "conf/app-dev.props", ignoreResourceNotFound = true)
@PropertySource(value = "conf/app-test.props", ignoreResourceNotFound = true)
public class AppConfig {
	private static Logger logger = LoggerFactory
			.getLogger(AppConfig.class);

	@Autowired
	private Environment environment;

	@PostConstruct
	public void init() {
		logger.info("Loading {} Spring JavaConfig class. Profiles:\\ {}", 
				AppConfig.class.getName(), environment.getActiveProfiles());
	}
	
	public boolean porfileActive(Profile profile) {
		return Arrays.asList(environment.getActiveProfiles()).contains(profile.toString());
	}
	
	public static enum Profile {
		TEST("test"), DEV("dev");
		
		private String value;
		
		Profile(String val) {
			this.value = val;
		}
		
		@Override
		public String toString() {
			return value;
		}
	}
}
