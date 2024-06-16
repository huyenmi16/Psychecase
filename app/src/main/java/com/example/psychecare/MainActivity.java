package com.example.psychecare;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import com.example.psychecare.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private FloatingActionButton fab;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        // Khởi tạo FloatingActionButton
        fab = findViewById(R.id.fab);

        // Gán onClickListener cho FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở một hộp thoại hoặc màn hình mới để thêm bài hát mới
                openAddPostDialog();
            }
        });

        navigationView = findViewById(R.id.navigation);



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),5);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.home).setCheckable(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.document).setCheckable(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.notification).setCheckable(true);
                        break;
                    case 3: navigationView.getMenu().findItem(R.id.person).setCheckable(true);
                        break;
                    case 4: navigationView.getMenu().findItem(R.id.search).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.document:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.notification:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.person:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.search:
                        viewPager.setCurrentItem(4);
                        break;

                }



                return true;
            }
        });

    }
    private void openAddPostDialog() {

        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
}