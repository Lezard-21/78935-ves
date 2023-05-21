package mx.uv.practica03;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Configuracion extends WsConfigurerAdapter{

    @Bean
    public XsdSchema materiaSchema(){
        return new SimpleXsdSchema(new ClassPathResource("materia.xsd"));
    }
 


    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> 
    messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);        
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

  

    @Bean(name = "materias")
    public DefaultWsdl11Definition materiasWsdl11Definition(XsdSchema  materiaSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("materiasPort");        
        wsdl.setLocationUri("/ws");   
        wsdl.setTargetNamespace("https://t4is.uv.mx/materia");        
        wsdl.setSchema(materiaSchema);
        return wsdl;
    }
}
