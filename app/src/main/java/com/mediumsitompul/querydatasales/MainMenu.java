package com.mediumsitompul.querydatasales;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    TextView menu1;
    TextView menu2;
    TextView menu3;
    TextView menu4;
    TextView menu5;
    TextView menu6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);
        menu3 = findViewById(R.id.menu3);
        menu4 = findViewById(R.id.menu4);
        menu5 = findViewById(R.id.menu5);
        menu6 = findViewById(R.id.menu6);
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        menu3.setOnClickListener(this);
        menu4.setOnClickListener(this);
        menu5.setOnClickListener(this);
        menu6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == menu1){
            startActivity(new Intent(getApplicationContext(),DataSales_Search.class));
        }
        if (v == menu2){
            startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
        }
        if (v == menu3){
            startActivity(new Intent(getApplicationContext(),LogProvisioning.class));
        }
        if (v == menu4){
            startActivity(new Intent(getApplicationContext(),LogInfo.class));
        }
        if (v == menu5){
            SharedPreferences preferences = this.getSharedPreferences("MyPref",0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            finish();
            Intent intent = new Intent(MainMenu.this,Login2.class);
            startActivity(intent);
        }
        if (v == menu6){
            startActivity(new Intent(getApplicationContext(),ChangePassword.class));
        }
    }
}
