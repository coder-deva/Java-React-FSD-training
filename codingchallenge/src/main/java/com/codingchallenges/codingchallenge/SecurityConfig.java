package com.codingchallenges.codingchallenge;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
            		
            		//User
					.requestMatchers("/api/user/token").authenticated()
					.requestMatchers("/api/user/signup").permitAll()
		
					//doctor
					.requestMatchers("/api/doctor/add").permitAll()
					.requestMatchers("/api/patient/add").permitAll()
					
					.requestMatchers("/api/appointment/add/{patient_id}/{doctor_id}").permitAll()
					
					.requestMatchers("/api/patient//byDoctorId/{doctorId}").hasAuthority("DOCTOR")
					
					
                
//                //Customer
//                .requestMatchers("/api/customer/signup").permitAll()
//                .requestMatchers("/api/customer/product/purchase/*").hasAuthority("CUSTOMER")
//                
//                //Product
//                .requestMatchers("/api/product/add").permitAll()
//                
//                
//                //Review
//                .requestMatchers("/api/review/add/{productId}").hasAuthority("CUSTOMER")
//                .requestMatchers("api/review/getAllReviews").hasAuthority("CUSTOMER")
                
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
            .httpBasic(Customizer.withDefaults()); // <-- Eventually replace with JWT filter
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
}
