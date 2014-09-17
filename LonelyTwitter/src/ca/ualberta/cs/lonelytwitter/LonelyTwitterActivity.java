package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;
import ca.ualberta.cs.lonelytwitter.data.DataFileManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {
	
	private IDataManager dataManager;
	
	private EditText bodyText;
	
	private ArrayList<AbstractTweet> tweets;
	
	private ArrayAdapter<AbstractTweet> tweetsViewAdapter;
	
	private ListView oldTweetsList;

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		dataManager = new DataFileManager();
		tweets = dataManager.loadTweets();
		tweetsViewAdapter = new ArrayAdapter<AbstractTweet>(this, R.layout.list_item, tweets);
		oldTweetsList.setAdapter(tweetsViewAdapter);
	}
	
	public void save(View v) {
		
		String text = bodyText.getText().toString();
		
		if (text.contains("*")){
			
			StarredTweet starred = new StarredTweet(new Date(),
						text);
			tweets.add(starred);
		}
		else{
			Tweet tweet = new Tweet(new Date(), text);
			tweets.add(tweet);
		}

		tweetsViewAdapter.notifyDataSetChanged();
		
		bodyText.setText("");
		dataManager.saveTweets(tweets);
	}
	
	public void clear(View v) {
		
		tweets.clear();
		tweetsViewAdapter.notifyDataSetChanged();
		dataManager.saveTweets(tweets);
	}
	

}