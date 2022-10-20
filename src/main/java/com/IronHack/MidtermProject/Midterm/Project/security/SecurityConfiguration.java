package com.IronHack.MidtermProject.Midterm.Project.security;

import com.IronHack.MidtermProject.Midterm.Project.services.servicesUser.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/holdersAccessBalanceSavingsAccount/{holderId}").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.GET, "/holdersAccessBalanceCheckingAccount/{holderId}").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.GET, "/holdersAccessBalanceCreditCardAccount/{holderId}").hasAnyRole("USER")
                .mvcMatchers(HttpMethod.PATCH, "/holdersMakeTransfer/").hasAnyRole("USER") //Internal Server Error
                .mvcMatchers(HttpMethod.POST,"/admin-createSavingsAccount/").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/admin-createCheckingAccount/").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/admin-createCreditCardAccount/").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH,"/admin/modifyBalanceAccounts/{accountId}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET,"/adminAccessBalanceAccount/{accountId}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/adminDeleteAccount/{accountId}").hasRole("ADMIN")
                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }
}
