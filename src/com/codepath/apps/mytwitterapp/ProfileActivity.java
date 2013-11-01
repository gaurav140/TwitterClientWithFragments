package com.codepath.apps.mytwitterapp;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.mytwitterapp.fragments.UserTimelineFragment;
import com.codepath.apps.mytwitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends FragmentActivity {
	
	TextView tvName;
    TextView tvTagline;
    TextView tvFollowers;
    TextView tvFollowing;
    ImageView ivProfileImage;
    long id=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		tvName = (TextView) findViewById(R.id.tvName);
        tvTagline = (TextView) findViewById(R.id.tvTagline);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        
        
        String name = getIntent().getStringExtra("name");
        String tagline = getIntent().getStringExtra("tagline");
        String followers = getIntent().getStringExtra("followers");
        String following = getIntent().getStringExtra("following");
        String image = getIntent().getStringExtra("image");
        
        id = getIntent().getLongExtra("id", 0);
        
        if(name==null)
        	load();
        else {
                tvName.setText(name);
                tvTagline.setText(tagline);
                tvFollowers.setText(followers + " Followers");
                tvFollowing.setText(following + " Following");
                ImageLoader.getInstance().displayImage(image, ivProfileImage);
                
                UserTimelineFragment userTimelineFragment = (UserTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentUserTimeline);
                userTimelineFragment.load(true);
        }

		
	}
	
	private void load() {
		// TODO Auto-generated method stub
		MyTwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                    
                    User user = User.fromJson(jsonObject);
                    getActionBar().setTitle("@"+user.getsScreenName());
                    populateProfileHeader(user);
            }


            
    });

		
	}

//	private void loadProfileInfo(){
//	MyTwitterApp.getRestClient().getMyInfo(
//			new JsonHttpResponseHandler(){
//				@Override
//				public void onSuccess(JSONObject json) {
//					User u = User.fromJson(json);
//					getActionBar().setTitle("@" + u.getsScreenName());
//					populateProfileHeader(u);
//			
//				}
//				
//			});
//	}
	
	private void populateProfileHeader(User user) {
		tvName.setText(user.getName());
		tvTagline.setText(user.getTagline());
		tvFollowers.setText(user.getFollowersCount()+" Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);

		//Load Profile Image
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	
	public long getId() {
        return id;
	}


}
