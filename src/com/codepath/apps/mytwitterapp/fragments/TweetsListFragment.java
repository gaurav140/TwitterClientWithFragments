package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.MyTwitterApp;
import com.codepath.apps.mytwitterapp.ProfileActivity;
import com.codepath.apps.mytwitterapp.R;
import com.codepath.apps.mytwitterapp.TweetsAdapter;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

//public class TweetsListFragment extends Fragment implements OnScrollListener{
public class TweetsListFragment extends Fragment implements OnScrollListener{
	
	//private static final int RESULT_OK = 0;
	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	TweetsAdapter adapter;
	ListView lvTweets;
	Tweet tweet;
	long  id=0;
	boolean isUpdating = false;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
		lvTweets.setOnScrollListener(this);
		lvTweets.setOnItemClickListener(new OnItemClickListener() {
			@Override
            public void onItemClick(AdapterView<?> arg0, View parent, int position,
                            long rowId) {
                    Intent i = new Intent(getActivity(), ProfileActivity.class);
                    tweet = tweets.get(position);
                    i.putExtra("name", tweet.getUser().getName());
                    i.putExtra("tagline", tweet.getUser().getTagline());
                    i.putExtra("followers", ""+tweet.getUser().getFollowersCount());
                    i.putExtra("following", ""+tweet.getUser().getFriendsCount());
                    i.putExtra("image", tweet.getUser().getProfileImageUrl());
                    i.putExtra("id", tweet.getUser().getId());
                    startActivity(i);
            }
			
		});
			
	}

	public TweetsAdapter getAdapter() {
		// TODO Auto-generated method stub
		return adapter;
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState){
		return inf.inflate(R.layout.fragments_tweets_list, parent, false);
	}
	

}
