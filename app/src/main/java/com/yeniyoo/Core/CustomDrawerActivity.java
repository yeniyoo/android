package com.yeniyoo.Core;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yeniyoo.CreateRoundActivity;
import com.yeniyoo.ListActivity;
import com.yeniyoo.ProfileActivity;
import com.yeniyoo.R;
import com.yeniyoo.ShopActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by YJLaptop on 2016-07-16.
 */
public class CustomDrawerActivity extends CustomAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    TextView txtvName;
    private Toolbar mToolbar;


    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    protected boolean isDrawerOpen() {
        if (getDrawerLayout() != null) {
            return (getDrawerLayout().isDrawerOpen(Gravity.LEFT) || getDrawerLayout().isDrawerOpen(Gravity.RIGHT));
        }
        return false;
    }

    protected void closeDrawer() {
        if (getDrawerLayout().isDrawerOpen(Gravity.RIGHT)) {
            getDrawerLayout().closeDrawer(Gravity.RIGHT);
        }
        if (getDrawerLayout().isDrawerOpen(Gravity.LEFT)) {
            getDrawerLayout().closeDrawer(Gravity.LEFT);
        }
    }

    protected void openRight() {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected void onDrawerOpened() {
    }

    @Deprecated
    protected void scrollNavigationToTop() {
        // FIXME : not working
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mNavigationView.scrollTo(0, 0);
                mDrawerLayout.scrollTo(0, 0);
            }
        });
    }

    protected void initializeToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            // Initializing Drawer Layout and ActionBarToggle
            ButterKnife.bind(this);
            View headerView = mNavigationView.inflateHeaderView(R.layout.nav_header_main);
            txtvName = (TextView) headerView.findViewById(R.id.txtvName);
            setDrawerColor(mNavigationView);

            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    CustomDrawerActivity.this.onDrawerOpened();
                    super.onDrawerOpened(drawerView);
                }
            };
            // Setting the actionbarToggle to drawer layout
            mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
            // Initializing NavigationView
            // Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
            mNavigationView.setNavigationItemSelectedListener(this);

            // calling sync state is necessay or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();
        }
    }

    private void setDrawerColor(NavigationView mNavigationView) {
        int[][] state = new int[][]{
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };

        int[] color = new int[]{
                Color.DKGRAY,
                Color.DKGRAY,
                Color.BLUE,
                Color.DKGRAY
        };

        ColorStateList csl = new ColorStateList(state, color);

        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[]{
                Color.GRAY,
                Color.GRAY,
                Color.BLUE,
                Color.DKGRAY
        };

        ColorStateList csl2 = new ColorStateList(states, colors);
        mNavigationView.setItemIconTintList(csl);
        mNavigationView.setItemIconTintList(csl2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //loadProfileImage();
    }

    Intent intent;

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        //Checking if the item is in checked state or not, if not make it in checked state
        if (menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);
        //Closing drawer on item click
        mDrawerLayout.closeDrawers();
        //Check to see which item was being clicked and perform appropriate action

        switch (menuItem.getItemId()) {
            case R.id.profile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                return true;
            case R.id.open:
                intent = new Intent(getApplicationContext(), CreateRoundActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                return true;
            case R.id.list:
                intent = new Intent(getApplicationContext(), ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                return true;
            case R.id.shop:
                intent = new Intent(getApplicationContext(), ShopActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
