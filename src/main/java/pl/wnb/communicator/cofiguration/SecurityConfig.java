package pl.wnb.communicator.cofiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.wnb.communicator.repository.CustomUserDetailsService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler customFailureHandler() {
        return new CustomFailureHandler();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }


    @Configuration
    @Order(1)
    public class AndroidConfigurationAdapter extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/android/**")
                    .csrf().disable().cors().and()
                    .authorizeRequests()
                    .antMatchers("/android/**").authenticated()
                    .and()
                    .formLogin().loginProcessingUrl("/android/login").permitAll().successHandler(customSuccessHandler())
                    .failureHandler(customFailureHandler());
        }
    }


    @Configuration
    @Order(2)
    public class WebConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/web/**").cors().and()
                    .authorizeRequests()
                    .and()
                    .formLogin().loginPage("/web/login").permitAll().failureUrl("/web/login")
                        .defaultSuccessUrl("/")
                    .and()
                    .logout().logoutSuccessUrl("/")
                    .and()
                    .authorizeRequests().antMatchers("/web/**").authenticated().and().logout().logoutSuccessUrl("/");

        }
    }

    @Configuration
    @Order(3)
    public class RegisterConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/register/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and().csrf().disable();

        }
    }

    @Configuration
    @Order(4)
    public class GuestConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/").cors().and()
                    .authorizeRequests().and()
                    .formLogin().loginPage("/web/login").failureUrl("/web/login")
                    .permitAll();

        }
    }
    @Configuration
    @Order(5)
    public class ResourceConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/resources/**").cors().and()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and().csrf().disable();

        }
    }
    @Configuration
    @Order(6)
    public class StaticResourceConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/static/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and().csrf().disable();

        }
    }
    @Configuration
    @Order(7)
    public class JsResourceConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/js/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and().csrf().disable();

        }
    }


}
