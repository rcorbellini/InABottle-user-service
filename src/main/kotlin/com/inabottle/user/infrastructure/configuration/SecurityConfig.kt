package com.inabottle.user.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
class SecurityConfig {


    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {


        return http.csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeExchange()
                .pathMatchers(
                        "/admin/system/state/*",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/public/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/auth/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/user/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/user/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .build()
//.addFilterAt(webFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
//.addFilterAt(new AuthorizationModifierFilter(),SecurityWebFiltersOrder.AUTHENTICATION)


    }


}