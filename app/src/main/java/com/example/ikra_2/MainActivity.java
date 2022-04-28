package com.example.ikra_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ikra_2.Settings.Settings_Activity;
import com.example.ikra_2.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ikra_2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ListView listView1;
    private String[] array;
    private ArrayAdapter<String> adapter;
    private int category_ix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listView1 = findViewById(R.id.ListView);
        array = getResources().getStringArray(R.array.Black_ikra);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , new ArrayList<String>(Arrays.asList(array)));
        listView1.setAdapter(adapter);

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.id_black_ikra, R.id.id_red_ikra, R.id.id_yellow_ikra)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,Text_Content_Activity.class);
                i.putExtra("category", category_ix);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_black_ikra){
            array = getResources().getStringArray(R.array.Black_ikra);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_ix = 0;

        }
        else if (id == R.id.id_red_ikra){
            array = getResources().getStringArray(R.array.Red_ikra);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_ix = 1;
        }
        else if (id == R.id.id_yellow_ikra){
            array = getResources().getStringArray(R.array.Yellow_ikra);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_ix = 2;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent intent = new Intent(MainActivity.this, Settings_Activity.class);
            startActivity(intent);
        }
        return true;
    }
}
