package com.example.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.blog.security.JwtAuthenticationEntryPoint;
import com.example.blog.security.JwtAuthenticationFilter;
import com.example.blog.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
	 	private UserService userService;

	    @Autowired
	    private JwtAuthenticationEntryPoint authenticationEntryPoint;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	        auth.authenticationProvider(authenticationProvider());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http
	                .cors()
	                    .and()
	                .csrf()
	                    .disable()
	                .exceptionHandling()
	                    .authenticationEntryPoint(authenticationEntryPoint)
	                    .and()
	                .sessionManagement()
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                    .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST, "/api/post").authenticated()
	                .antMatchers(HttpMethod.POST, "/api/post/**").authenticated()
	                .antMatchers(HttpMethod.PUT, "/api/post").authenticated()
	                .antMatchers(HttpMethod.PUT, "/api/post/**").authenticated()
	                .antMatchers(HttpMethod.DELETE, "/api/post/**").hasRole("ADMIN");

	        // Add our custom JWT security filter
	        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	    }

	    //beans
	    //bcrypt bean definition

	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	    }

	    @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    //authenticationProvider bean definition
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	        auth.setUserDetailsService(userService); //set the custom user details service
	        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
	        return auth;
	    }
}
