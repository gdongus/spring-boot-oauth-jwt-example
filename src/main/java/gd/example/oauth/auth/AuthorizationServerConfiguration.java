package gd.example.oauth.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * Configuration for the oauth2 authorization server. The client and server and configured here.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${gd.example.oauth.tokenTimeoutMinutes:5}")
	private int expiration;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		configurer.authenticationManager(authenticationManager);
		configurer.userDetailsService(userDetailsService);
		configurer.accessTokenConverter(accessTokenConverter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("clientName")
			.secret("clientPassword")
			.accessTokenValiditySeconds(expiration * 60)
			.scopes("read", "write")
			.authorizedGrantTypes("password", "refresh_token")
			.resourceIds("resource");
	}

	/**
	 * Use custom jwt token converter to enhance the token with custom fields
	 *
	 * @return CustomTokenConverter
	 */
	@Bean
	public CustomTokenConverter accessTokenConverter() {
		return new CustomTokenConverter();
	}
}
