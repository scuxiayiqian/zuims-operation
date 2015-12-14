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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableRedisHttpSession
public class Application {

	public static HashMap<String, User> repoUser;

	public static ObjectMapper mapper = null;

	
	public Application() {

		mapper = new ObjectMapper();

		User[] users = null;

		try {
			users = mapper.readValue(new File("user.json"), User[].class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		repoUser = new HashMap<String, User>();

		for (int nIndex = 0; nIndex < users.length; nIndex++) {
			repoUser.put(users[nIndex].getName(), users[nIndex]);
		}
	}

	@Bean
	HeaderHttpSessionStrategy sessionStrategy() {
		System.out.println("HeaderHttpSessionStrategy on");
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
