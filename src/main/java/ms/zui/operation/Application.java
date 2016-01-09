package ms.zui.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;


@SpringBootApplication
public class Application {

	public static final String dataPath = "data/";
	
	public Application() {

	}

	@Bean
	HeaderHttpSessionStrategy sessionStrategy() {

		return new HeaderHttpSessionStrategy();
	}
	
    public static void main(String[] args) {

    	SpringApplication.run(Application.class, args);
    }
}
