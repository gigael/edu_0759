package com.example.secondapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SeeUserActivity extends AppCompatActivity {

    TextView text;
    String selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_user);
        text=findViewById(R.id.text);
        selectedItem=getIntent().getStringExtra("dataUser");
          text.append(selectedItem);
    }
}