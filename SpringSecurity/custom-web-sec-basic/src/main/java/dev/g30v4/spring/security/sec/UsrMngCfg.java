package dev.g30v4.spring.security.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UsrMngCfg {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailService = new InMemoryUserDetailsManager();

        var user = User.withUsername("g30v4")
                .password("root123")
                .authorities("read")
                .build();

        userDetailService.createUser(user);
        return userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
