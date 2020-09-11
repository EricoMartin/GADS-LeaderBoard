package com.example.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gadsleaderboard.dummy.DummyContent;
import com.google.android.material.tabs.TabLayout;

public class LearnerActivity extends AppCompatActivity  implements ItemFragment.OnListFragmentInteractionListener,  ItemFragment2.OnListFragmentInteractionListener{

    RecyclerView rcv;
    LearnersAdapter learnersAdapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner);

//        actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.show();


        tabLayout = findViewById(R.id.tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText("Learning Leaders"));
        tabLayout.addTab(tabLayout.newTab().setText("Skill IQ Leaders"));
        updatePage();

    }
    public void updatePage(){
        final ViewPager viewPager =
                findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(),
                        tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                           viewPager.setCurrentItem(tab.getPosition());
                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {

                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {

                                                       }

                                                   });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.button1);

//        MenuItemCompat.getActionProvider(item).setVisibilityListener(new View.setVisibilityListener() {
//            @Override
//            public void onClick(View v) {
//                nextActivity(v);
//            }
//        });

        return true;
    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.button1) {
            nextActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nextActivity() {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

}
