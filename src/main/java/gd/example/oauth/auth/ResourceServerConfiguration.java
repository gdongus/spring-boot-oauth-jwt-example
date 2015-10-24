package gd.example.oauth.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Configuration for the oauth2 resource server. Defines the application as stateless and configures the accesÏ‚s to
 * resources via http.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/* deactivate sessions */
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.exceptionHandling().and().anonymous().and().authorizeRequests().antMatchers("/api/public/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
			.antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')")
			.antMatchers(HttpMethod.PATCH, "/api/**").access("#oauth2.hasScope('write')")
			.antMatchers(HttpMethod.PUT, "/api/**").access("#oauth2.hasScope('write')")
			.antMatchers(HttpMethod.DELETE, "/api/**").access("#oauth2.hasScope('write')")
				/* allow anonymous page requests */
			.anyRequest().permitAll()

			.and().headers().frameOptions().disable();
	}
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("resource");
	}
}
