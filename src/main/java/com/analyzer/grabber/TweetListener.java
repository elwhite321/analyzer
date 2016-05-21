package com.analyzer.grabber;

import java.io.*;
import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.json.*;

public class TweetListener implements StatusListener {

	private static BufferedWriter bw;

	public static void main( String[] args ) throws Exception {
	
		try {
			bw = new BufferedWriter(
				new FileWriter( 
				new File("tweets.txt")));
			//bw.write("opened");
		}
		catch (IOException w) {
			System.out.println("couldn't open file");
			return;
		}
		
		/**
		 * authentication now performed with twitter4j.properties
		 *
		 *perform OAuth authorization
		 *Handshaker hand = new Handshaker();
		 * hand.handshake();
		 *
		 **/
		TweetListener listener = new TweetListener();
    		TwitterStream ts = new TwitterStreamFactory().getInstance();
    		ts.addListener(listener);
		
		FilterQuery query = new FilterQuery();
    		String[] handles = {"Verizon", "GovChristie"};

		query.track(handles);
		ts.filter(query);
		
	}
	
	public void onStatus(Status status) {

		System.out.println(status);
		try {
			bw.write(status.getText() + "\n");
			bw.flush();
		}
		catch (IOException e) {
			System.out.println("oops");
		}
	}
	public void onStallWarning(StallWarning warning) {}
	public void onScrubGeo(long id, long statusID) {}
	public void onDeletionNotice(StatusDeletionNotice sdn) {}
	public void onTrackLimitationNotice(int nls) {}
	public void onException(Exception ex) {
    		ex.printStackTrace();
	}
}
