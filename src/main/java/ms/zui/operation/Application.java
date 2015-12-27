package ms.zui.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

import ms.zui.operation.service.CityService;
import ms.zui.operation.service.GuestService;
import ms.zui.operation.service.RestaurantService;
import ms.zui.operation.service.RightService;
import ms.zui.operation.service.UserService;
import ms.zui.operation.service.RoleService;

@SpringBootApplication
public class Application {

	public static final String dataPath = "data/";

	public static RestaurantService restaurantService = new RestaurantService();
	public static UserService userService = new UserService();
	public static GuestService guestService = new GuestService();
	public static CityService cityService = new CityService();
	public static RightService rightService = new RightService();
	public static RoleService roleService = new RoleService();
	
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
