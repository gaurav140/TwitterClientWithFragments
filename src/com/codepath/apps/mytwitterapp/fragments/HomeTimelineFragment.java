package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.MyTwitterApp;
import com.codepath.apps.mytwitterapp.R;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.widget.AbsListView;

public class HomeTimelineFragment extends TweetsListFragment {
	
	private boolean isUpdating = false;
    private long  minId=0;
    private List<Tweet> tweets = new ArrayList<Tweet>();

	//TweetsListFragment fragmentTweets;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		load(true);
	}
	
	@Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
            // TODO Auto-generated method stub
            if(isUpdating || totalItemCount == 0)
                    return;
            if((totalItemCount - firstVisibleItem)<8)
                    load(false);
    }
    
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
    }
    
    @Override
    public void onPause() {
            super.onPause();
            
    }
    @Override
    public void onResume() {
            super.onResume();
            
            adapter.clear();
            load(true);
    }
    public void load(boolean reload) {
            if(reload)
                    minId = 0;
            isUpdating = true;
            
            MyTwitterApp.getRestClient().getHomeTimeline(minId, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONArray jsonTweets) {
                            
                            tweets = Tweet.fromJson(jsonTweets);
                            adapter.addAll(tweets);
                            
                            if(tweets.size()>0) {
                                    Tweet tweet = tweets.get(tweets.size()-1);
                                    minId = tweet.getId();
                            }
                            isUpdating = false;
                                    
                    }
            });
    }

		
		
	

}
