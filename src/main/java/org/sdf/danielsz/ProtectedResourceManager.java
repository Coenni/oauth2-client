package org.sdf.danielsz;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TimerTask;

public class ProtectedResourceManager {

	private static String username;
	private static  String password;
	private static  String clientId;
	private static  String clientSecret;
	private static  String site;
    private static String tokenUrl;
    static {
        setProperties();
    }

    public static OAuth2Client client = new OAuth2Client(username, password, clientId, clientSecret, site, tokenUrl);
	private Token token;


	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public ProtectedResourceManager(Token token) {
		this.token = token;
	}


	public static void main(String[] args) throws InterruptedException {

		ProtectedResourceManager manager = new ProtectedResourceManager(client.getAccessToken());
//		MyTimerTask timer = manager.new MyTimerTask();
//		java.util.Timer t = new java.util.Timer();
//        fetch(client, manager);
//		t.schedule(timer, 5000, s1200000);
	}

    private static void setProperties() {
        String filename = "config.properties";
        InputStream input = ProtectedResourceManager.class.getClassLoader().getResourceAsStream(filename);
        if(input==null){
            System.out.println("Sorry, unable to find " + filename);
            return;
        }
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        clientId = prop.getProperty("clientId");
        clientSecret = prop.getProperty("clientSecret");
        site = prop.getProperty("site");
        tokenUrl = prop.getProperty("tokenUrl");
    }

    public static String fetch(OAuth2Client client, ProtectedResourceManager manager, String id) {
		Token token = manager.getToken();
		if (token.isExpired()) manager.setToken(token.refresh(client));		
		String result = manager.getToken().getResource(client, manager.getToken(), "/V1/resource/"+id); //"/api/resource?name=value"
        return result;
    }

	class MyTimerTask extends TimerTask {

		public void run() {
			fetch(client, ProtectedResourceManager.this, null);
		}
	}
}