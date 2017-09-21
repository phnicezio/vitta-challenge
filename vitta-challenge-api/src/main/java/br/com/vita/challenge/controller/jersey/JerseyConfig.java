package br.com.vita.challenge.controller.jersey;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.com.vita.challenge.controller.SquareController;
import br.com.vita.challenge.controller.TerritoryController;

@Configuration
@ApplicationPath("vitta-challenge")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
	}

	@PostConstruct
	public void setUp() {
		register(TerritoryController.class);
		register(SquareController.class);
		
		 // Register all resources present under the package.
        packages("org.glassfish.jersey.examples.entityfiltering.selectable");

        // Register entity-filtering selectable feature.
        register(SelectableEntityFilteringFeature.class);
        property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "fields");

        // Configure Jackson Json provider
        register(JacksonFeature.class);
        register(ObjectMapperProvider.class);
        register(IncompleteDataException.class);
	}
	
}