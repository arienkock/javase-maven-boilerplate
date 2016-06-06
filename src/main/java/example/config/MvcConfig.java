package example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import example.config.AppConfig.Profile;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private AppConfig appConfig;
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		ResourceHandlerRegistration handlerRegistration = registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
				.addResourceHandler("/assets/**")
				.addResourceLocations("/assets");
		if (appConfig.porfileActive(Profile.DEV) 
				|| appConfig.porfileActive(Profile.TEST)) {
			handlerRegistration
					.setCachePeriod(0);
		} else {
			handlerRegistration
					.setCachePeriod(Integer.MAX_VALUE)
					.resourceChain(true);
		}
	}

}
