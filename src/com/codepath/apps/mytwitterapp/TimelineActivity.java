package com.codepath.apps.mytwitterapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.fragments.HomeTimelineFragment;
import com.codepath.apps.mytwitterapp.fragments.MentionsFragment;
import com.codepath.apps.mytwitterapp.fragments.TweetsListFragment;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

//public class TimelineActivity extends Activity implements OnScrollListener{
public class TimelineActivity extends FragmentActivity implements TabListener{	
	
	TweetsListFragment fragmentTweets;
	
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	ListView lvTweets;
	private long  id=0;
	private boolean isUpdating = false;
	TweetsAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
		
//				lvTweets = (ListView) findViewById(R.id.lvTweets);
//				adapter = new TweetsAdapter(getBaseContext(), tweets);
//				lvTweets.setAdapter(adapter);
				//loadTimeline();
				//lvTweets.setOnScrollListener(this);	
			
	}
	
	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("Home").setTag("HomeTimelineFragment")
				.setIcon(R.drawable.ic_home).setTabListener(this);
		
		Tab tabMentions = actionBar.newTab().setText("Mentions").setTag("MentionsFragment")
				.setIcon(R.drawable.ic_mentions).setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
		
		
	}
	
	public void onProfileView(MenuItem mi){
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
		
	}


		
	
	
//	private void loadTimeline() {
//        isUpdating = true;
//        MyTwitterApp.getRestClient().getTimeline(id, new JsonHttpResponseHandler() {
//                @Override
//                public void onSuccess(JSONArray jsonTweets) {
//                        Log.d("DEBUG", jsonTweets.toString());
//                        tweets = Tweet.fromJson(jsonTweets);
//                        fragmentTweets.getAdapter().addAll(tweets);
//                        
//                        if(tweets.size()>0) {
//                                Tweet tweet = tweets.get(tweets.size()-1);
//                                id = tweet.getId();
//                        }
//                        isUpdating = false;
//                                
//                }
//        });
//	}
	
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == 100) {
//                if(resultCode==RESULT_OK) {
//                        adapter.clear();
//                        id = 0;
//                        loadTimeline();
//                        Log.d("DEBUG", "onActivityResult-2");
//                }
//        }
//	}


	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	public void composeTweet(MenuItem mi){
		Intent i = new Intent(getApplicationContext(),ComposeTweetActivity.class);
		startActivityForResult(i, 100);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
				
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		if(tab.getTag()=="HomeTimelineFragment"){
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
			
		}else {
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		fts.commit();
				
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				
	}

//	@Override
//	public void onScroll(AbsListView view, int firstVisibleItem,
//		int visibleItemCount, int totalItemCount) {
//		
//		if(isUpdating || totalItemCount == 0)
//			return;
//		if((totalItemCount - firstVisibleItem)<8)
//            loadTimeline();
//	}
//
//	@Override
//	public void onScrollStateChanged(AbsListView view, int scrollState) {
//		
//		
//	}
	
	
	
}
