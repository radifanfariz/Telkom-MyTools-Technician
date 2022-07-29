package com.mediumsitompul.querydatasales;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;

public class  DataSales_Search  extends AppCompatActivity implements OnClickListener {
    Button button1;
    EditText namacalang_parameter;
    EditText alamatcalang_parameter;
    EditText nomorsc_parameter;
//    Permission_Access_First permission_access_first;

//    TextView imei_parameter;
//    TextView subscid_parameter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datasales_search);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        namacalang_parameter = (EditText) findViewById(R.id.textNamaCalang);
        alamatcalang_parameter = (EditText) findViewById(R.id.textAlamatCalang);
        nomorsc_parameter = (EditText) findViewById(R.id.textNomor_sc);
//        permission_access_first = new Permission_Access_First(getApplicationContext(),DataSales_Search.this);
//        permission_access_first.checkAppPermission();

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), DataSales_Result.class);
        Bundle b = new Bundle();
        b.putString("parse_name",namacalang_parameter.getText().toString());
        b.putString("parse_address",alamatcalang_parameter.getText().toString());
        b.putString("parse_nomorsc",nomorsc_parameter.getText().toString());
        b.putString("parse_userid",getIntent().getStringExtra("parse_userid"));
        b.putString("parse_imei",getIntent().getStringExtra("parse_imei"));

        //Toast.makeText(Search_DataSales.this, "Result = "+namacalang_parameter.getText().toString(), Toast.LENGTH_LONG).show();  //oke
//        Toast.makeText(getApplicationContext(), getIntent().getStringExtra("parse_imei"), Toast.LENGTH_LONG).show();
        intent.putExtras(b);
        startActivity(intent);
    }
}

