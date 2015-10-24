package gd.example.oauth.auth;

import gd.example.oauth.domain.UserCredentials;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Custom JWT token in which additional information can be stored. For example add the user's ID to each token.
 */
public class CustomTokenConverter extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

		info.put("user_id", ((UserCredentials) authentication.getPrincipal()).getUser().getId());

		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);
		return super.enhance(customAccessToken, authentication);
	}
}
