package com.codepath.apps.mytwitterapp.fragments;

import java.util.jar.JarEntry;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.MyTwitterApp;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;

public class MentionsFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load(true);
	}
	
	public void load(boolean reload) {
        // TODO Auto-generated method stub
        MyTwitterApp.getRestClient().getMentions(0, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONArray jsonTweets) {
                        
                        tweets = Tweet.fromJson(jsonTweets);
                        adapter.addAll(tweets);
                        
                }
        });
}

}
