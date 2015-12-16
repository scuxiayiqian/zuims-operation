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

import java.io.File;
import java.util.HashMap;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableRedisHttpSession
public class Application {

	public static HashMap<String, User> 		repoUser;
	public static HashMap<String, Restaurant> 	repoRestaurant;	
	public static HashMap<String, Guest> 		repoGuest;

	public static ObjectMapper mapper = null;

	public static String dataPath = "data/";
	
	public Application() {

		mapper = new ObjectMapper();

		User[] users = null;
		Restaurant[] restaurants = null;
		Guest[] guests = null;
		
		try {
			users = mapper.readValue(new File(dataPath + "user.json"), User[].class);
			restaurants = mapper.readValue(new File(dataPath + "restaurant.json"), Restaurant[].class);
			guests = mapper.readValue(new File(dataPath + "guest.json"), Guest[].class);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoUser = new HashMap<String, User>();
		repoRestaurant = new HashMap<String, Restaurant>();
		repoGuest = new HashMap<String, Guest>();
		
		for (int nIndex = 0; nIndex < users.length; nIndex++) {
			repoUser.put(users[nIndex].getName(), users[nIndex]);
		}
		
		for (int nIndex = 0; nIndex < restaurants.length; nIndex++) {
			repoRestaurant.put(restaurants[nIndex].getName(), restaurants[nIndex]);
		}
		
		for (int nIndex = 0; nIndex < guests.length; nIndex++) {
			repoGuest.put(guests[nIndex].getName(), guests[nIndex]);
		}

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
