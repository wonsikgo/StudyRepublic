package org.mohajo.studyrepublic.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import lombok.extern.java.Log;


@Log
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetatilsServiceImpl memberservice;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config");
		
		http
		.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	
	      http
	      .authorizeRequests()   
	      .antMatchers("/", "/signup","/StudyPage/**","/index","/member/**").permitAll()
	      .antMatchers("/tutor/signup","/tutor/insert","/pay","/board/**").hasAnyRole("N","A","T","W")
	      .antMatchers("/tutor/**").hasAnyRole("T","A")
	      .antMatchers("/admin/**","/adminPage/**").hasRole("A")
	      ;
		
				
		http
		.formLogin()
		.loginPage("/login")
		.successHandler(new LoginSuccessHandler())
		.defaultSuccessUrl("/index")
		.permitAll();
		
		http
		.exceptionHandling()
		.accessDeniedPage("/accessDenied");
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.addLogoutHandler(new TaskImplementingLogoutHandler())
		.permitAll()
/*		.invalidateHttpSession(true)*/
		.logoutSuccessUrl("/index");
/*		.deleteCookies("JSESSIONID","remember-me");*/
		
		
		http
		.rememberMe()
		.key("member")
		.userDetailsService(memberservice);
		
		http
		.rememberMe()
		.key("member")
		.rememberMeParameter("remember-me")
		.userDetailsService(memberservice)
		.tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(60*60*24);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**");
	}
	
	@Bean		
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	    db.setDataSource(dataSource);
	    return db;
	    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(memberservice);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    

	
	
}

