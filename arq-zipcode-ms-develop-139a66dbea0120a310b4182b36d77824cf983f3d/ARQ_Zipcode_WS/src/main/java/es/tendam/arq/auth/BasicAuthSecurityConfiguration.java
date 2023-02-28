package es.tendam.arq.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("basicAuth")
@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String HEALTH = "/health";

	@Value("${service.auth.basic.username}")
	private String user;
	
	@Value("${service.auth.basic.password}")
	private String password;
	 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser(user).password(passwordEncoder().encode(password)).authorities("NO_ROLE");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers(HEALTH).permitAll()
          .anyRequest().authenticated()
          .and()
          .httpBasic();
    } 
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}