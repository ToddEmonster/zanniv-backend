package fr.todd.zanniv.security;

import fr.todd.zanniv.filter.CustomAuthenticationFilter;
import fr.todd.zanniv.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO : décider des routes publiques
    private static final String[] PUBLIC_MATCHERS = new String[]{
            "/login",
            "/users/signup",
            "/users"
    };

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception  {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception  {
        http
            .cors()
            .and()
                .csrf()
                .disable()
                .httpBasic()
            .and()
            .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll()
            .anyRequest()
                .authenticated()
            .and()
            .formLogin()
            .permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), userMapper));

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
