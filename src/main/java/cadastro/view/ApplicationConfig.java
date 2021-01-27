package cadastro.view;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {

	
	public ApplicationConfig() {
		
		init();
		
	}
	
	private void init() {
		
		BeanConfig config = new BeanConfig();
		config.setVersion("1.0.0");
		config.setSchemes(new String[] {"http"});
		config.setBasePath("/cadastro/api/v1");
		config.setHost("localhost:8080");
		config.setResourcePackage(ApplicationConfig.class.getPackage().getName());
		config.setScan(Boolean.TRUE);
		config.setTitle("API SWAGGER das operações do CADASTRO-SERVICE");
	}
}
