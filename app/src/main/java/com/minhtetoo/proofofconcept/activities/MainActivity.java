package com.minhtetoo.proofofconcept.activities;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

import com.minhtetoo.proofofconcept.ProofOfConcept;
import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.adapters.PagerAdapter;
import com.minhtetoo.proofofconcept.dagger.AppComponent;
import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;
import com.minhtetoo.proofofconcept.mvp.presenters.MainPresenter;
import com.minhtetoo.proofofconcept.mvp.views.MainView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView,PopularMovieDelegate {

    android.support.v7.widget.Toolbar toolBar;

    private DrawerLayout mDrawerLayout;

    ViewPager mViewPager;

    @Inject
    public MainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProofOfConcept proofOfConcept = (ProofOfConcept)getApplicationContext();
        AppComponent appComponent = proofOfConcept.getAppComponent();
        appComponent.inject(this);
        mPresenter.onCreate(this);

        toolBar=findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout =  findViewById(R.id.drawer_layout);

        mViewPager =findViewById(R.id.view_pager);

        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        TabLayout mtabLayout = findViewById(R.id.tab_layout);
        mtabLayout.addTab(mtabLayout.newTab().setText("NOW ON CINEMA"));
        mtabLayout.addTab(mtabLayout.newTab().setText("UPCOMING"));
        mtabLayout.addTab(mtabLayout.newTab().setText("MOST POPULAR"));


        NavigationView navigationView =  findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mPresenter.onNavigationItemSelected(menuItem);
                Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onTapMovieItemView() {

    }
}
