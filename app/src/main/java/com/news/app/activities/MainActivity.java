package com.news.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.news.app.apiServices.ApiHelper;
import com.news.app.apiServices.AppConstant;
import com.news.app.apiServices.NetworkConnection;
import com.news.app.R;
import com.news.app.fragments.HomeFragment;
import com.news.app.model.Weather;
import com.news.app.utils.BaseActivity;
import com.news.app.utils.Prefs;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BaseActivity {

    TextView exampleId;
    NetworkConnection nw;
    List<Weather> weatherList;
    Weather weather;

    private Fragment viewFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TextView nav_region, nav_cimate, nav_temp, nav_date_time, nav_max_temp, nav_min_temp;
    Toolbar toolbar;
    DrawerLayout drawer;
    private int mCurNavigationId;
    private long lastPress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Prefs.getUserId(this).equals("")){
            startActivity(new Intent(this, LoginActivity.class));
            MainActivity.this.finish();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("HOME");
        setSupportActionBar(toolbar);

        mCurNavigationId = R.id.home;


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View hView = navigationView.getHeaderView(0);
        nav_region = hView.findViewById(R.id.region);
        nav_cimate = hView.findViewById(R.id.climate);
        nav_temp = hView.findViewById(R.id.temp);
        nav_date_time = hView.findViewById(R.id.dateTime);
        nav_max_temp = hView.findViewById(R.id.maxTemp);
        nav_min_temp = hView.findViewById(R.id.minTemp);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new HomeFragment()).commit();

        nw = new NetworkConnection(this);

        if (nw.isConnectingToInternet()) {
            new ApiHelper(this, "forecastResponse", AppConstant.forecastApi, forecastResponse).execute();
        } else {
            Toast.makeText(this, getResources().getString(R.string.internet_error), Toast.LENGTH_LONG).show();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager mFragmentManager = getSupportFragmentManager();
        if (id == R.id.homeId) {
            mCurNavigationId = id;
            viewFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerView, viewFragment).commit();
        } else if (id == R.id.editProfile){
            startActivity(new Intent(MainActivity.this, UserDetailsActivity.class));
        } else if (id == R.id.logoutId){
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to Logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Prefs.setUserId(MainActivity.this,"");
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }
        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void closeNavDrawer() {
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    protected boolean isNavDrawerOpen() {
        return drawer != null && drawer.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void setToolBarTitle(String name) {
        toolbar.setTitle(name);
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            if (mCurNavigationId != R.id.home) {
                viewFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, viewFragment).commit();
            }/* else {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastPress > 2000) {
                    showSnackMsg(getString(R.string.confirm_exit));
                    lastPress = currentTime;
                } else {
                    super.onBackPressed();
                }
            }*/
        }
    }


    private ApiHelper.TaskDelegate forecastResponse = new ApiHelper.TaskDelegate() {
        public void onTaskFisnishGettingData(JSONObject result) {
            try {
                if (result != null) {
                    JSONObject jsonObject = result.getJSONObject("location");
                    Log.e("json", jsonObject.toString());
                    String region = jsonObject.getString("name");
                    String localtime = jsonObject.getString("localtime");

                    nav_region.setText(region);
                    nav_date_time.setText(localtime);

                    JSONObject currentObj = result.getJSONObject("current");
                    Log.e("current", currentObj.getString("temp_c"));

                    String temp = currentObj.getString("temp_c");

                    nav_temp.setText(temp);

                    JSONObject climate = currentObj.getJSONObject("condition");
                    Log.e("climate", climate.getString("text"));

                    String climateString = climate.getString("text");
                    nav_cimate.setText(climateString);

                    JSONObject forecast = result.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONObject("day");
                    Log.e("max_temp", forecast.getString("maxtemp_c"));

                    String maxTemp = "Max : " + forecast.getString("maxtemp_c");
                    String minTemp = "Min : " + forecast.getString("mintemp_c");

                    nav_max_temp.setText(maxTemp);
                    nav_min_temp.setText(minTemp);



                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    };
}
