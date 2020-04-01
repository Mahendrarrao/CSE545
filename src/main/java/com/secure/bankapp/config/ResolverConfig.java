package com.secure.bankapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import com.secure.bankapp.util.PDFBuilder;

@Configuration 
@EnableWebMvc
@ComponentScan(basePackages = "com.secure.bankapp")
public class ResolverConfig extends WebMvcConfigurerAdapter {
	 @Bean  
	    public ResourceBundleViewResolver resourceBundleViewResolver() {  
		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();

		resolver.setBasename("views");
	        return resolver;  
	    }
	 @Override
	 public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp").viewClass(JstlView.class);
		
	}
	 @Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		 registry
         .addResourceHandler("/resources/**")
         .addResourceLocations("/resources/"); 
		}
}
