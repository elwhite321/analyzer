package com.analyzer.grabber;

import java.io.*;
import twitter4j.*;
import twitter4j.auth.*;

public class Handshaker {

	public static void main( String[] args ) throws Exception {

		Handshaker tg = new Handshaker();
		tg.handshake();

		return;
	}

	/**
	 * performs OAuth handshake with twitter
	 * @TODO handle persisence of AccessToken
	 **/
	public void handshake() throws Exception {
	
		Twitter tw = TwitterFactory.getSingleton();
		Consumer consumer = new Consumer();

		// set key and secret for OAuth
		tw.setOAuthConsumer(
			//"11",
			consumer.key(),
			consumer.secret()
		);
	
		RequestToken requestToken;
		AccessToken accessToken = null;

		try {
			//grab the access token from twitter
			requestToken = tw.getOAuthRequestToken();
		}
		catch (TwitterException e) {
			System.out.println("Error: invalid Consumer Key");
			return;
		}
		/**
		 * @TODO clean up copy-pasted code from twitter4j.org
		 **/

		while (null == accessToken) {
			
			System.out.print("Open the following URL for PIN"
				+ "\n" + requestToken.getAuthorizationURL()
				+ "\nEnter the [PIN]:");

  			BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));

			String pin = br.readLine();
			try {
				if (pin.length() > 0) 
					accessToken = tw.getOAuthAccessToken(
						requestToken, pin); 
				else
					accessToken= tw.getOAuthAccessToken();
			} 
			catch (TwitterException te) { 
				System.out.print("Can't get access token");
    				//te.printStackTrace();
  			}
		}
	}
}
