
package ca.ualberta.cs.lonelytwitter;

import java.io.Serializable;
import java.util.Date;


public class Tweet extends AbstractTweet implements Serializable {

	public Tweet(Date tweetDate, String tweetBody) {
		super();
		this.tweetDate = tweetDate;
		this.tweetBody = tweetBody;
	}


	public String getTweetBody() {
		return tweetBody;
	}

	public String toString() {
		return tweetDate +" / "+ tweetBody;
	}

}
