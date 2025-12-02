package com.nownow.mybiz.onboarding.proxy.security;




import com.nownow.mybiz.onboarding.proxy.security.filter.ApiKeyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

//import com.gateway.NowNowGateway.security.filter.PayStackWebhookFilter;
;

@Component
//@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final ApiKeyFilter apiKeyFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsSpec -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/proxy/api/v1/token/**","/proxy/api/v1/identity/**","/proxy/api/v1/auth/**","/actuator/**",
                                "/proxy/api/v1/upload/**",
                                "/proxy/swagger-ui/**",
                                "/api-docs/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/**",
                                "/api-docs/**",
                                "/proxy/swagger-ui/index.html").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }




}
