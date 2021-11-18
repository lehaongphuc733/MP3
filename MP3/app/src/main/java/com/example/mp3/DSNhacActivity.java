package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

public class DSNhacActivity extends AppCompatActivity {
    ImageView imgSearch;
    ListView ListDSBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsnhac);
        anhxa();
    }
    public void anhxa(){
        imgSearch = findViewById(R.id.imgSearch);
        ListDSBaiHat = findViewById(R.id.ListDSBaiHat);
    }
}