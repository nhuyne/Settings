package com.example.btvn_buoi7;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int count;
    Button bt_setting;
    TextView bigTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_setting=findViewById(R.id.bt_setting);
        bigTextView = findViewById(R.id.textView);
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, bt_setting);
                popup.getMenuInflater().inflate(R.menu.menu_settings, popup.getMenu());
                popup.setOnMenuItemClickListener(    new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId() == R.id.st_count)
                        {
                            count++;
                            TextView textView = findViewById(R.id.textView);
                            textView.setText(String.valueOf(count));
                        }
                        else
                        if(menuItem.getItemId() == R.id.cl_black)
                        {
                            bigTextView.setBackgroundColor(getResources().getColor(R.color.black));
                        }
                        else
                        if(menuItem.getItemId() == R.id.cl_blue)
                        {
                            bigTextView.setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                        else
                        if(menuItem.getItemId() == R.id.cl_red)
                        {
                            bigTextView.setBackgroundColor(getResources().getColor(R.color.red));
                        }
                        else
                        if(menuItem.getItemId() == R.id.cl_green)
                        {
                            bigTextView.setBackgroundColor(getResources().getColor(R.color.green));
                        }
                        setValueAndColor();
                        return false;
                    }
                });
                popup.show();
            }
        });

        getValueAndColor();
        getButtons();
    }

    protected void setValueAndColor(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        TextView textView = findViewById(R.id.textView);
        int value = count;
        ColorDrawable bg = (ColorDrawable) textView.getBackground();
        int color = bg.getColor();
        editor.putInt("count", value);
        editor.putInt("color", color);
        editor.apply();
    }

    protected void getValueAndColor(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        int value = sharedPref.getInt("count", 0);
        count = value;
        int color = sharedPref.getInt("color", Color.parseColor("#FF808080"));
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(count));
        textView.setBackgroundColor(color);
    }

    protected void clearValueAndColor(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }

    protected void getButtons(){
        Button buttonBlack = findViewById(R.id.buttonBlack);
        buttonBlack.setTag(0);
        buttonBlack.setOnClickListener(this);

        Button buttonRed = findViewById(R.id.buttonRed);
        buttonRed.setTag(1);
        buttonRed.setOnClickListener(this);

        Button buttonBlue = findViewById(R.id.buttonBlue);
        buttonBlue.setTag(2);
        buttonBlue.setOnClickListener(this);

        Button buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setTag(3);
        buttonGreen.setOnClickListener(this);

        Button buttonCount = findViewById(R.id.buttonCount);
        buttonCount.setTag(4);
        buttonCount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        bigTextView = findViewById(R.id.textView);
        int tag = Integer.parseInt(String.valueOf(view.getTag()));
        switch (tag) {
            case 0:
                bigTextView.setBackgroundColor(getResources().getColor(R.color.black));
                break;
            case 1:
                bigTextView.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case 2:
                bigTextView.setBackgroundColor(getResources().getColor(R.color.blue));
                break;
            case 3:
                bigTextView.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case 4:
                count++;
                break;
            case 5:
                clearValueAndColor();
                getValueAndColor();
                break;
        }
        setValueAndColor();
        getValueAndColor();
    }
}