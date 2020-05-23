package com.spring.bookapi.security;


import com.spring.bookapi.security.jwt.AuthEntryPointJwt;
import com.spring.bookapi.security.jwt.AuthTokenFilter;
import com.spring.bookapi.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.sql.DataSource;
import java.util.Map;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    DataSource dataSource;

    @Bean
    CORSFilter corsFilter() {
        CORSFilter filter = new CORSFilter();
        return filter;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery("SELECT T1.username, T2.role FROM users_roles as T1 INNER JOIN user_roles as T2 ON T1.user_role_id=T2.user_role_id WHERE T1.username=?");
        //.authoritiesByUsernameQuery("select username, role from users_roles where username=?");
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/books/**", "/api/departments/**", "/api/universities/**",
                        "/api/faculties/**", "/api/users/**", "/api/roles/**", "/api/authors/**",
                        "/api/upload/**", "/api/files/**", "/api/category/**", "/api/auth/**",
                        "api/categories/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
 */

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                    .authorizeRequests().antMatchers("/api/books/**", "/api/departments/**",
                    "/api/universities/**", "/api/faculties/**", "/api/users/**", "/api/roles/**", "/api/authors/**",
                    "/api/upload/**", "/api/files/**", "/api/category/**", "/api/auth/**",
                    "api/categories/**").permitAll()
                    .and().logout().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

            http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
            http.exceptionHandling().accessDeniedPage("/403");
        }



        private Map<String, ?> hibernateJpaProperties() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        //http.logout().logoutUrl("/logout");
    }





