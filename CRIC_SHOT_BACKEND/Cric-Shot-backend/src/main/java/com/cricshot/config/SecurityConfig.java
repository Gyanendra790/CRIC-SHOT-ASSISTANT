package com.cricshot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cricshot.security.CustomUserDetailService;
import com.cricshot.security.JwtAuthenticationEntryPoint;
import com.cricshot.security.JwtAuthenticationFilter;
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
	    @Autowired
	    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	    @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;

	    @Autowired
	    private CustomUserDetailService customUserDetailService;

	    @Bean
	    public PasswordEncoder passwordEncoder (){
	        return new BCryptPasswordEncoder();
	    }


	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	            http.
	                       csrf(csrf->csrf.disable())
	                       .cors(cors->cors.disable())
	                    .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/**").permitAll()
	                            .requestMatchers(HttpMethod.GET).permitAll()
	                            .anyRequest().authenticated())
	                    .exceptionHandling(ex->ex.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
	                    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
               http.authenticationProvider(dao1AuthenticationProvider());
	        return http.build();
	    }

	    @Bean
	    public DaoAuthenticationProvider dao1AuthenticationProvider(){
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(this.customUserDetailService);
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        return daoAuthenticationProvider;
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)throws Exception
	    {
	    	return configuration.getAuthenticationManager();	    }
	    
	    @Bean
	    public FilterRegistrationBean coresFilter() {
	    	UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
	    	CorsConfiguration corsConfiguration=new CorsConfiguration();
	    	corsConfiguration.setAllowCredentials(true);
	    	corsConfiguration.addAllowedOriginPattern("*");
	    	corsConfiguration.addAllowedHeader("Authorization");
	    	corsConfiguration.addAllowedHeader("Content-Type");
	    	corsConfiguration.addAllowedHeader("Accept");
	    	corsConfiguration.addAllowedMethod("POST");
	    	corsConfiguration.addAllowedMethod("GET");
	    	corsConfiguration.addAllowedMethod("DELETE");
	    	corsConfiguration.addAllowedMethod("PUT");
	    	corsConfiguration.addAllowedMethod("OPTIONS");
	    	corsConfiguration.setMaxAge(3600L);
	    	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	    	
	    	FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(urlBasedCorsConfigurationSource));
	    	return bean;
	    }


	}
