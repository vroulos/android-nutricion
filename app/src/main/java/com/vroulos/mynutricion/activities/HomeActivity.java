package com.vroulos.mynutricion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vroulos.mynutricion.MessageFragment;
import com.vroulos.mynutricion.R;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        if (getSupportActionBar() != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);


        }






        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch (menuItem.getItemId()){
                            case R.id.messeges:{
                                Intent intent = new Intent(HomeActivity.this, MessageActivity2.class);
                                startActivity(intent);
                            }case R.id.program_nutricion:{
                                // Create new fragment and transaction
                                Fragment newFragment = new MessageFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


                                // Commit the transaction
                                transaction.commit();
                            }
                        }
                        return true;
                    }
                });

        // Create new fragment and transaction
        Fragment newFragment = new MessageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();



        // Commit the transaction
        transaction.commit();





    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
