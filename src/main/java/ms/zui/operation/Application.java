package ms.zui.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.zui.operation.security.OpsUserDetailService;
import ms.zui.operation.service.GuestService;
import ms.zui.operation.service.RestaurantService;
import ms.zui.operation.service.UserService;

@SpringBootApplication
@EnableRedisHttpSession
public class Application {

	public static final String dataPath = "data/";

	public static UserService userService = new UserService();
	public static GuestService guestService = new GuestService();
	public static RestaurantService restaurantService = new RestaurantService();
	
	
	public Application() {

	}

	@Bean
	HeaderHttpSessionStrategy sessionStrategy() {

		return new HeaderHttpSessionStrategy();
	}
	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Inject
		private OpsUserDetailService opsUserDetailService;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic();
			http
				.authorizeRequests()
					.regexMatchers(HttpMethod.OPTIONS, "/users").permitAll()
					.regexMatchers(HttpMethod.OPTIONS, "/users/logout").permitAll()
					.antMatchers("/token").permitAll()
					.anyRequest().authenticated()
			.and()
				.csrf().disable();
		}
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.userDetailsService(opsUserDetailService);
					//.passwordEncoder(new BCryptPasswordEncoder());
		}

	}
    public static void main(String[] args) {

    	SpringApplication.run(Application.class, args);
    }
}
