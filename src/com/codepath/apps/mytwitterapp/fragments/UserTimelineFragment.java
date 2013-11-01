package com.codepath.apps.mytwitterapp.fragments;

import org.json.JSONArray;

import android.os.Bundle;

import com.codepath.apps.mytwitterapp.MyTwitterApp;
import com.codepath.apps.mytwitterapp.ProfileActivity;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	
	 boolean isClear = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load(true);
	}
	public void load(boolean reload) {
		
		isClear = reload;
        long id = ((ProfileActivity) this.getActivity()).getId();

        MyTwitterApp.getRestClient().getUserTimeline(id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                    
                    tweets = Tweet.fromJson(jsonTweets);
                    if(isClear)
                    adapter.clear();
                    adapter.addAll(tweets);
            }
        });

	}
}
