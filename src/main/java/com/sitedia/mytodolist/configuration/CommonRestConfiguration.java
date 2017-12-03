package com.sitedia.mytodolist.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sitedia.common.rest.annotation.EnableCommonRest;
import com.sitedia.common.rest.configuration.InitializingBean;

@Configuration
@EnableCommonRest
public class CommonRestConfiguration {

    @Value("${server.allowed-paths}")
    private String allowedPaths;

    @Value("${security.auth.server.usersByUsernameQuery}")
    private String usersByUsernameQuery;

    @Value("${security.auth.server.authoritiesByUsernameQuery}")
    private String authoritiesByUsernameQuery;

    @Value("${security.salt}")
    private String salt;
    
    @Bean
    public InitializingBean init() {
        InitializingBean init = new InitializingBean();
        
        init.setBasePackage("com.sitedia");
        init.setAllowedPaths(allowedPaths);
        init.setUsersByUsernameQuery(usersByUsernameQuery);
        init.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);
        init.setSalt(salt);
        
        return init;
    }
    
}
