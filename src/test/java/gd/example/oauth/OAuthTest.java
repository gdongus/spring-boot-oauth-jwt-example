package gd.example.oauth;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
public class OAuthTest {

	@Value("${local.server.port}")
	int port;

	/* application client */
	private static final String CLIENT_USER = "clientName";
	private static final String CLIENT_SECRET = "clientPassword";

	/* user credentials */
	private static final String OAUTH_TOKEN_USERNAME = "bob";
	private static final String OAUTH_TOKEN_PASSWORD = "test";

	/* API */
	private static String REFRESH_TOKEN_URL;
	private static String OAUTH_TOKEN_URL;
	private static final String ACCESS_TOKEN = "access_token";
	private static final String REFRESH_TOKEN = "refresh_token";

	/* misc */
	private static final String INVALID_INPUT = "INVALID_INPUT";
	private RestTemplate restTemplate = null;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();
		REFRESH_TOKEN_URL = "http://localhost:" + port + "/oauth/token?grant_type=refresh_token&refresh_token=";
		OAUTH_TOKEN_URL = "http://localhost:" + port + "/oauth/token?grant_type=password";
	}

	public HttpHeaders getHttpHeader(final String username, final String password) {
		final String plainCreds = username + ":" + password;

		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);

		final String base64Creds = new String(base64CredsBytes);
		final HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + base64Creds);

		return headers;
	}

	public String getOAuthTokenUrl(String userName, String userPassword) {
		return OAUTH_TOKEN_URL + "&username=" + userName + "&password=" + userPassword;
	}

	public String getOAuthRefreshTokenUrl(String token) {
		return REFRESH_TOKEN_URL + token;
	}

	@Test
	public void generateAccessTokenSuccess() {

		final HttpHeaders headers = getHttpHeader(CLIENT_USER, CLIENT_SECRET);
		final HttpEntity<String> request = new HttpEntity<>(headers);

		final String tokenUrl = getOAuthTokenUrl(OAUTH_TOKEN_USERNAME, OAUTH_TOKEN_PASSWORD);

		final ResponseEntity<Object> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Object.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());

		final Map result = (Map) response.getBody();
		assert (!StringUtils.isEmpty(result.get(ACCESS_TOKEN)));
	}

	@Test
	public void generateAccessTokenFromValidRefreshToken() {

		final HttpHeaders headers = getHttpHeader(CLIENT_USER, CLIENT_SECRET);
		final HttpEntity<String> request = new HttpEntity<>(headers);

		final String tokenUrl = getOAuthTokenUrl(OAUTH_TOKEN_USERNAME, OAUTH_TOKEN_PASSWORD);

		final ResponseEntity<Object> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Object.class);
		final Map result = (Map) response.getBody();
		final String refreshToken = (String) result.get(REFRESH_TOKEN);

		final String refreshTokenUrl = REFRESH_TOKEN_URL + refreshToken;

		final ResponseEntity<Object> refreshResponse = restTemplate.exchange(refreshTokenUrl, HttpMethod.POST, request, Object.class);
		final Map refreshResult = (Map) refreshResponse.getBody();

		assert (!StringUtils.isEmpty(refreshResult.get(ACCESS_TOKEN)));
		assertTrue(refreshResponse.getStatusCode().is2xxSuccessful());
	}
}
