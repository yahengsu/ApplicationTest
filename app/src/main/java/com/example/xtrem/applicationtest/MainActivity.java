package com.example.xtrem.applicationtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private Button pickupButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets the screen to the main activity xml layout

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        //makes the navigation drawer object and adds items to it

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pickupButton = (Button) findViewById(R.id.PickupButton);
        pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pickup Location Set!", Toast.LENGTH_SHORT).show();
            }
        });
        //when button pressed set location and notify user the pickup location is set


    }

    //adds navigation drawers to the listview by using a string array and arrayadapter
    private void addDrawerItems() {
        String[] navArray = { "User Settings", "Ride History", "Rides Near You", "Leaderboard", "Request A Ride" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navArray);
        mDrawerList.setAdapter(mAdapter);

        //when item is clicked, move to the appropriate activity


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == mAdapter.getPosition("Request A Ride")) {
                    Intent i = new Intent(view.getContext(),RequestRide.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawer(mDrawerList);
                }
                else if (position == mAdapter.getPosition("Leaderboard")){
                    Intent i = new Intent(view.getContext(),LeaderboardActivity.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawer(mDrawerList);
                }
                else if (position == mAdapter.getPosition("Rides Near You")){
                    Intent i = new Intent(view.getContext(),NearbyActivity.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawer(mDrawerList);

                }
                else if (position == mAdapter.getPosition("Ride History")){
                    Intent i = new Intent(view.getContext(),RideHistoryActivity.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawer(mDrawerList);

                }
                else if (position == mAdapter.getPosition("User Settings")){
                    Intent i = new Intent(view.getContext(),UserSettingsActivity.class);
                    startActivity(i);
                    mDrawerLayout.closeDrawer(mDrawerList);

                }
            }
        });
    }

    //sets up the drawer for when it is opened and closed
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("rYYCer");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }


        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }



    //all these methods are just there so the application works, handles the innerwork functionality
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_requestRide) {

        }
        else if (id == R.id.nav_leaderboard){
            return true;
        }
        else if (id == R.id.nav_ridesNearby){
            return true;
        }
        else if (id == R.id.nav_rideHistory){
            return true;
        }
        else if (id == R.id.nav_userSettings){
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
