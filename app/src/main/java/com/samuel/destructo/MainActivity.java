package com.samuel.destructo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parse.com analytics
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //When a user is logged in, condition is stored in cache
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser == null){
            navigateToLogin();
        }
        else{
            Log.i(TAG, currentUser.getUsername());
        }

        //Set up action bar
        final ActionBar actionBar = getSupportActionBar();
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //Set up ViewPager with sections adapter
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /*
    The following method is a result of a user action, originally intended to happen after the user
        has taken a picture or video, but in this case it will be after a contact is selected from
        the contact fragment.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //Media stuff
        /*if(resultCode == RESULT_OK){

        }else if(resultCode != RESULT_CANCELED){
            Toast.makeText(this, R.string.general_error, Toast.LENGTH_LONG).show();
        }*/

        Intent recipientsIntent = new Intent(this, RecipientsActivity.class);
        //recipientsIntent.setData(mMediaUri);
        startActivity(recipientsIntent);
    }

    private void navigateToLogin() {
        //Go to log in activity
        Intent intent = new Intent(this, LoginActivity.class);
        //A flag is needed to skip the MainAcitivty from showing up?
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Clear the task so that we cant back arrow into it
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int itemId = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (itemId == R.id.action_logout) {
            //The user has tapped on the log out option
            ParseUser.logOut();
            //Push user back to the login screen
            navigateToLogin();
            return true;
        }
        else if(itemId == R.id.action_edit_friends){
            Intent intent = new Intent(this, EditFriendsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
