package rc.bootsecurity;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootSecurityApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        // 1) Habilitamos el tráfico SSL
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            
        	// Tipo de seguridad CONFIDENTIAL
        	@Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // 2) Añadimos redireccion de HTTP a HTTPS 
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    /**
     * Necesitamos redirigir de HTTP a HTTPS. 
     * Sin SSL, esta aplicación utiliza el puerto 8082. 
     * Con SSL utilizará el puerto 8443. 
     * Por lo tanto, cualquier solicitud de 8082 debe ser redirigido a HTTPS en 8443.
     **/
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

}
