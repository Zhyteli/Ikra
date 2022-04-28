package com.example.ikra_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Text_Content_Activity extends AppCompatActivity {

    private int category = 0;
    private int position = 0;
    private TextView text_content;
    private Typeface face1;
    private ImageView iContent;
    private int [] array_black = {R.string.Sterlad_black_1,R.string.Sevryga_black_2,R.string.Vesnolos_black_3,
            R.string.Kalyga_black_4,R.string.Osetr_black_5,R.string.Bester_black_6};
    private int[] array_red = {R.string.Red_1,R.string.Red_2,R.string.Red_3};
    private int[] array_image = {R.drawable.sterljad,R.drawable.sevrjuga,R.drawable.veslonos,
            R.drawable.kaluga,R.drawable.osetra,R.drawable.bestera};
    private String [] array_title_black = {"Стерлядь", "Севрюга", "Веснолос", "Калуга", "Осетр", "Бестер"};
    private ActionBar actionBar;
    private SharedPreferences def_pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        init();
        recIntent();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void recIntent(){
        Intent i = getIntent();
        if(i != null){
            category = i.getIntExtra("category", 0);
            position = i.getIntExtra("position", 0);
        }
        switch (category){
            case 0:
                iContent.setImageResource(array_image[position]);
                text_content.setText(array_black[position]);
                actionBar.setTitle(array_title_black[position]);
                break;
            case 1:
                text_content.setText(array_red[position]);
                break;
            case 2:

                break;
        }
    }
    private void init() {

        text_content = findViewById(R.id.text_main_content);
        iContent = findViewById(R.id.imageContent);
        face1 = Typeface.createFromAsset(this.getAssets(), "fonts/Mulish-Italic-VariableFont_wght.ttf");
        text_content.setTypeface(face1);
        actionBar = getSupportActionBar();
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        String text_size = def_pref.getString("main_text_size", "Средний");
        if (text_size != null){
            switch (text_size){
                case "Мелкий":
                    text_content.setTextSize(12);
                    break;
                case "Средний":
                    text_content.setTextSize(16);
                    break;
                case "Крупный":
                    text_content.setTextSize(24);
                    break;
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
