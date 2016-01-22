package ms.zui.operation.security;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Inject
	private OpsUserDetailService opsUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();

		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/productions", "/cities", "/users/marketing").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/token", "/productions/**", "/rights/**", "/cities/**", "/roles/**", "/users/**", "/guests/**", "/restaurants/**", "/productions/**").permitAll()
			.anyRequest().authenticated();

		http.csrf().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(opsUserDetailService);
				//.passwordEncoder(new BCryptPasswordEncoder());imp
	}
}
