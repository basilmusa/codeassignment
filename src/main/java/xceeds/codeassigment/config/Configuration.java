package xceeds.codeassigment.config;

import java.io.IOException;
import java.util.Properties;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;

public class Configuration 
{
	private static final String CONFIG_PROPERTIES_FILE = "/config.properties";

	private static final String DEBUG = "debug";
	private static final String DEBUG_TRUE_VALUE = "true";
	private static final String DEBUG_FALSE_VALUE = "false";
	
	private static final String OAUTH_ACCESSTOKENSECRET = "oauth.accesstokensecret";
	private static final String OAUTH_ACCESSTOKEN = "oauth.accesstoken";
	private static final String OAUTH_CONSUMERSECRET = "oauth.consumersecret";
	private static final String OAUTH_CONSUMERKEY = "oauth.consumerkey";
	private static final String HTTP_PROXY_PORT = "http.proxy.port";
	private static final String HTTP_PROXY_HOST = "http.proxy.host";

	private Properties properties;


	private Configuration() 
	{
		properties = new Properties();
		try 
		{
			properties.load(this.getClass().getResourceAsStream(CONFIG_PROPERTIES_FILE));
			
			// TODO - Validate that all required properties exist, if not
			// then fail fast before any methods get called.
			
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}
	
	// Simple singleton implementation
	private static Configuration INSTANCE = null;
	public static Configuration getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Configuration();
		}
		return INSTANCE;
	}
	
	public String getHttpProxyHost() {
		return Strings.nullToEmpty(properties.getProperty(HTTP_PROXY_HOST));
	}
	
	public Integer getHttpProxyPort() {
		return Integer.valueOf(properties.getProperty(HTTP_PROXY_PORT));
	}
	
	public String getOauthConsumerKey() {
		return properties.getProperty(OAUTH_CONSUMERKEY);
	}

	public String getOauthConsumerSecret() {
		return properties.getProperty(OAUTH_CONSUMERSECRET);
	}
	
	public String getOauthAccessToken() {
		return properties.getProperty(OAUTH_ACCESSTOKEN);
	}

	public String getOauthAccessTokenSecret() {
		return properties.getProperty(OAUTH_ACCESSTOKENSECRET);
	}
	
	public boolean isDebug() {
		String debug = properties.getProperty(DEBUG);
		if (debug.equalsIgnoreCase(DEBUG_TRUE_VALUE)) {
			return true;
		}
		else if (debug.equalsIgnoreCase(DEBUG_FALSE_VALUE)) {
			return false;
		}
		else {
			return false;
		}
	}
}