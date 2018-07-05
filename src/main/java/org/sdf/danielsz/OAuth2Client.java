package org.sdf.danielsz;

public class OAuth2Client {

	private final String username;
	private final String password;
	private final String clientId;
	private final String clientSecret;
	private final String site;
	private final String tokenUrl;
	
	public OAuth2Client(String username, String password, String clientId, String clientSecret, String site, String tokenUrl) {
		this.username = username;
		this.password = password;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.site = site;
		this.tokenUrl = tokenUrl;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getClientId() {
		return clientId;
	}


	public String getClientSecret() {
		return clientSecret;
	}


	public String getSite() {
		return site;
	}

    public String getTokenUrl() {
        return tokenUrl;
    }

    public Token getAccessToken() {
		OAuth2Config oauthConfig = new OAuth2Config.OAuth2ConfigBuilder(username, password, clientId, clientSecret, site, tokenUrl)
			.grantType("password").build();
		return OAuthUtils.getAccessToken(oauthConfig);
	}
}
