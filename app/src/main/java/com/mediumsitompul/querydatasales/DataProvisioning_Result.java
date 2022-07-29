package com.mediumsitompul.querydatasales;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;

import androidx.fragment.app.FragmentManager;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mediumsitompul.querydatasales.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataProvisioning_Result extends AppCompatActivity implements View.OnClickListener {


    //    String IP = "http://192.168.100.78/"; //zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
    //String IP = "http://36.89.34.66/"; //zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
    //String IP = "http://192.168.43.85/";
//    String IP = "http://192.168.43.2/";

    //variables api
    String cust_name = "";
    String cust_addr = "";
    String inst_addr = "";


    String stp_name = "";
    //...................................
    String stp_panel = "";
    String stp_port = "";

    String myir_sc = "";
    String internet_number = "";
    String phone_number = "";

    String nosf = "";
    String notech = "";
    String edithp;


    private int param_idx;

    private ListView listView;
    List<DataProvisioning_Product> productList;
    RecyclerView recyclerView;
    //EditText et8;


    EditText etWitel;

    EditText etCustomerName;

    EditText etCustomerAddress;
    EditText etGoogleAddress;
    EditText etInstallationAddress;

    EditText etMyir_sc;
    EditText etInternet_number;
    EditText etPhone_number;


    TextView tvFilename3;

    EditText etStpName;
    TextView tvIdx;
    TextView tvLatitudeOdp;
    TextView tvLongitudeOdp;
    TextView tvLatitudeInstAddr;
    TextView tvLongitudeInstAddr;


    EditText etHp;
    EditText etEmail;
    EditText etPaketIndihome;

    //private String stp_panel;
    //Spinner spinner_05;

    Spinner spinner_05_stppanel;
    Spinner spinner_06_stpport;
    Spinner spinner_07_teknis;
    Spinner spinner_08_nonteknis;
    Spinner spinner_09_statusprov;


    //private String stp_panel;

    ImageView iv;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;


    Button buttonUpload;

    Button scan_barcode;
    EditText barcode_result;

    Button scan_barcode2;
    EditText barcode_result2;

    Button scan_barcode3;
    EditText barcode_result3;

    Button scan_barcode4;
    EditText barcode_result4;

    private int PICK_IMAGE_REQUEST = 1;
    private boolean cekan;
    private boolean cekan2;
    private boolean cekan3;
    private boolean cekan4;
    private boolean cekan5;
    private boolean cekan6;
    public boolean cekan7;
    public Uri filePath;
    public Uri filePath2;
    public Uri filePath3;
    public Uri filePath4;
    public Uri filePath5;
    public Uri filePath6;
    public Uri filePathPdf;
    public Bitmap bitmap;
    private Button buttonChoose;
    private Button buttonChoose2;
    private Button buttonChoose3;
    private Button buttonChoose4;
    private Button buttonChoose5;
    private Button buttonChoose6;
    private Button take_picture;
    private Button buttonChoosePdf;
    private final int requestCode = 1;
    String url, url2, url3, url4, url5, url6, pdf_url;
    Update_Image updateImage;
    String barcodeStr;
    String barcodeStr2;
    String barcodeStr3;
    String barcodeStr4;
    String pseudoIdx;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;
    ArrayAdapter<CharSequence> adapter4;
    ArrayAdapter<CharSequence> adapter5;

    Permission_Access_Second permissionAccessSecond;
//    public boolean updateCondition = false,updateCondition2 = false,updateCondition3 = false,updateCondition4 = false;

    String tag_json_obj = "json_obj_req";

    SharedPreferences pref;
//    SharedPreferences.Editor editor;

    TextView txtnamasf;
    TextView txtnamatech;
    TextView txtnosf;
    TextView txtnotech;
    EditText edit_HP;


    Button tgsfBtn;
    Button tgtechBtn;
    Button wacustBtn;
    Button waaltBtn;

    Button btnEditODP;
    Button btnLatlongODP;
    Button btnLatlongRmh;

    EditText edtJarakRad;
    EditText edtJarakOdo;
    EditText edtCustAddrsALt;
    EditText edtNoSc;

    EditText edit_HP_alt;
    EditText edit_kend_desc;
    EditText edtKet1;
    EditText edtKet2;

    TextView txtPdf;
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private Location location;
//    private LocationCallback locationCallback;
//    private LocationManager locationManager;
//    private LocationRequest locationRequest;
//    private LocationListener locationListener;
    private Double lat, lng;
    private Float accuracy;
    LocationTrack locationTrack;
    static String responseBot;
    public String imageFilePathOri;
    public String imageFilePathCompress;

    public String user_tg_sf;
    public String user_tg_tech;
    public String msgParams;
    RequestOptions options;

    FileExplore fileExplore;
    FragmentManager fm;
    String tag;
    String pdfLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataprovisioning_result); // TEST SIMPLE PARSING....OKE
        options = new RequestOptions().fitCenter().placeholder(R.mipmap.noimage);
        locationTrack = new LocationTrack(DataProvisioning_Result.this);

        fm = getSupportFragmentManager();

        tag = "File Explorer";

        fileExplore = new FileExplore(DataProvisioning_Result.this,DataProvisioning_Result.this,fm,tag);

        try {
            getChatIdTelegram("915211367:AAE2aFwhJbGdPYe1UIEvtwflH-Yns4-3r-8");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//untuk cari lokaso pakai gps
//        checkLocation();
//        //Cara Lain Untuk GetLocation() dengan GPS
////        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
////        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
//        startLocationUpdate();
//            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//Cara Lain Untuk GetLocation() dengan network provider
//            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//            fusedLocationProviderClient.getLastLocation()
//                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if (location != null) {
//                                lat = location.getLatitude();
//                                lng = location.getLongitude();
//                                accuracy = location.getAccuracy();
//                            }
//                        }
//                    });
//            locationCallback = new LocationCallback() {
//                @Override
//                public void onLocationResult(LocationResult locationResult) {
//                    if (locationResult == null){
//                        return;
//                    }
//                    for (Location location : locationResult.getLocations()) {
//                        lat = location.getLatitude();
//                        lng = location.getLongitude();
//                    }
//                }
//            };
        //..........................................................................................



        pref = getApplicationContext().getSharedPreferences("MyPref",0);
//        editor = pref.edit();
//
//        editor.putString("userid",getIntent().getStringExtra("userid"));
//        editor.putString("imei",getIntent().getStringExtra("imei"));
//
//        editor.commit();


        etWitel = findViewById(R.id.editWitel);

        etCustomerName = findViewById(R.id.editCustomerName);

        etCustomerAddress = findViewById(R.id.editCustomerAddress);
        etGoogleAddress = findViewById(R.id.editGoogleAddress);
        etInstallationAddress = findViewById(R.id.editInstallationAddress);

        etMyir_sc = findViewById(R.id.editMyir_sc);
        etInternet_number = findViewById(R.id.editInternet_number);
        etPhone_number = findViewById(R.id.editPhone_number);


        etStpName = findViewById(R.id.editStpName);

        tvLatitudeOdp = findViewById(R.id.textView_lat);
        tvLongitudeOdp = findViewById(R.id.textView_lng);
        tvIdx = findViewById(R.id.textView_Idx);
        tvLatitudeInstAddr = findViewById(R.id.textView_lat2);
        tvLongitudeInstAddr = findViewById(R.id.textView_lng2);

        edit_HP_alt = findViewById(R.id.editHpAlternatif);
        edit_kend_desc = findViewById(R.id.editKendalaDesc);
        edtKet1 = findViewById(R.id.editKeterangan1);
        edtKet2 = findViewById(R.id.editKeterangan2);

        txtPdf = findViewById(R.id.txtPdf);


        etHp = findViewById(R.id.editHp);
        etEmail = findViewById(R.id.editEmail);
        etPaketIndihome = findViewById(R.id.editPacketIndihome);
        iv = (ImageView) findViewById(R.id.imageView);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
        iv5 = (ImageView) findViewById(R.id.imageViewNew);
        iv6 = (ImageView) findViewById(R.id.imageViewNew2);


        buttonUpload = (Button) findViewById(R.id.buttonUpload);

        //SCAN BARCODE
        scan_barcode = (Button) findViewById(R.id.scan_barcode);
        barcode_result = (EditText) findViewById(R.id.barcode_result);
        scan_barcode2 = (Button) findViewById(R.id.scan_barcode2);
        barcode_result2 = (EditText) findViewById(R.id.barcode_result2);
        scan_barcode3 = (Button) findViewById(R.id.scan_barcode3);
        barcode_result3 = (EditText) findViewById(R.id.barcode_result3);
        scan_barcode4 = (Button) findViewById(R.id.scan_barcode4a);
        barcode_result4 = (EditText) findViewById(R.id.barcode_result4a);


        //Permission
//        permissionAccessSecond = new Permission_Access_Second(getApplicationContext(), DataProvisioning_Result.this);
//        permissionAccessSecond.startPemission();




        //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
        //spinner_05 = (Spinner) findViewById(R.id.spinner05);
        //stp_panel = String.valueOf(spinner_05.getSelectedItem());

        spinner_05_stppanel = (Spinner) findViewById(R.id.spinner05);
        spinner_06_stpport = (Spinner) findViewById(R.id.spinner06);
        spinner_07_teknis = (Spinner) findViewById(R.id.spinner07);
        spinner_08_nonteknis = (Spinner) findViewById(R.id.spinner08);
        spinner_09_statusprov = (Spinner) findViewById(R.id.spinner09);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonChoose2 = (Button) findViewById(R.id.buttonChoose2);
        buttonChoose3 = (Button) findViewById(R.id.buttonChoose3);
        buttonChoose4 = (Button) findViewById(R.id.buttonChoose4);
        buttonChoose5 = (Button) findViewById(R.id.buttonChoose5);
        buttonChoose6 = (Button) findViewById(R.id.buttonChoose6);
        take_picture = (Button) findViewById(R.id.take_picture2);
        buttonChoosePdf = (Button) findViewById(R.id.buttonChoosepdf);

        buttonChoose.setOnClickListener(this);
        buttonChoose2.setOnClickListener(this);
        buttonChoose3.setOnClickListener(this);
        buttonChoose4.setOnClickListener(this);
        buttonChoose5.setOnClickListener(this);
        buttonChoose6.setOnClickListener(this);
        take_picture.setOnClickListener(this);
        buttonChoosePdf.setOnClickListener(this);




        adapter = ArrayAdapter.createFromResource(this,R.array.stppanel_arrays,android.R.layout.simple_spinner_item);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.stpport_arrays,android.R.layout.simple_spinner_item);
        adapter3 = ArrayAdapter.createFromResource(this,R.array.kendalateknis_arrays,android.R.layout.simple_spinner_item);
        adapter4 = ArrayAdapter.createFromResource(this,R.array.kendalanonteknis_arrays,android.R.layout.simple_spinner_item);
        adapter5 = ArrayAdapter.createFromResource(this,R.array.statusprov_array,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_05_stppanel.setAdapter(adapter);
        spinner_06_stpport.setAdapter(adapter2);
        spinner_07_teknis.setAdapter(adapter3);
        spinner_08_nonteknis.setAdapter(adapter4);
        spinner_09_statusprov.setAdapter(adapter5);


        txtnamasf = (TextView) findViewById(R.id.txtnamasf);
        txtnamatech = (TextView) findViewById(R.id.txtnamatech);

        txtnosf = (TextView) findViewById(R.id.txtnosf);
        txtnotech = (TextView) findViewById(R.id.txtnotech);
        edit_HP = (EditText) findViewById(R.id.editHp);


        tgsfBtn = (Button) findViewById(R.id.tgBtn);
        tgtechBtn = (Button) findViewById(R.id.tgBtn2);
        wacustBtn = (Button) findViewById(R.id.waBtn);
        waaltBtn = (Button) findViewById(R.id.waBtn2);

        btnEditODP = (Button) findViewById(R.id.btn_edit_odp);
        btnLatlongODP=(Button) findViewById(R.id.btn_latlong_odp);
        btnLatlongRmh = (Button) findViewById(R.id.btn_latlong_rmh);

        edtJarakRad = (EditText) findViewById(R.id.jarak_edt_RAD);
        edtJarakOdo = (EditText) findViewById(R.id.jarak_edt_ODO);
        edtCustAddrsALt = (EditText) findViewById(R.id.editCustomerAddressAlternatif);
        edtNoSc = (EditText) findViewById(R.id.editNomor_SC);


        updateImage = new Update_Image(getApplicationContext(),DataProvisioning_Result.this);


        //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        param_idx = getIntent().getIntExtra("parse_idx", 0);
        final TextView tv = findViewById(R.id.textView_Idx);
        tv.setText(String.valueOf(param_idx));

        System.out.println("Halaman234========================================" + param_idx);
        getData();


        btnEditODP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etStpName.isEnabled()){
                    etStpName.setEnabled(false);
                }else {
                    etStpName.setEnabled(true);
                }
            }
        });

        btnLatlongODP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                locationTrack.getLocation();
                if (locationTrack.getLocation() == null){
                    locationTrack.showSettingsAlert();
                }else {
                    if (locationTrack.canGetLocation()) {


                        double latitude = locationTrack.getLatitude();
                        double longitude = locationTrack.getLongitude();

                        tvLatitudeOdp.setText(Double.toString(latitude));
                        tvLongitudeOdp.setText(Double.toString(longitude));


                        Toast.makeText(getApplicationContext(), "Latitude:" + Double.toString(latitude) + "\nLongitude:" + Double.toString(longitude), Toast.LENGTH_SHORT).show();
                    } else {

                        locationTrack.showSettingsAlert();
                    }
                }
            }
        });
        btnLatlongRmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                locationTrack.getLocation();
                if (locationTrack.getLocation() == null){
                    locationTrack.showSettingsAlert();
                }else {
                    if (locationTrack.canGetLocation()) {


                        double latitude = locationTrack.getLatitude();
                        double longitude = locationTrack.getLongitude();

                        tvLatitudeInstAddr.setText(Double.toString(latitude));
                        tvLongitudeInstAddr.setText(Double.toString(longitude));


                        Toast.makeText(getApplicationContext(), "Latitude:" + Double.toString(latitude) + "\nLongitude:" + Double.toString(longitude), Toast.LENGTH_SHORT).show();
                    } else {

                        locationTrack.showSettingsAlert();
                    }
                }
            }
        });



        tgsfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goToWhatsappsf(txtnosf.getText().toString(),"Saya Ingin Bertanya...");
                goToTelegram(user_tg_sf);
            }
        });



        tgtechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTelegram(user_tg_tech);
            }
        });


        wacustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWhatsapp(etHp.getText().toString(),"Hello");
            }
        });

        waaltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWhatsapp(etHp.getText().toString(),"Hello");
            }
        });



        txtnosf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCall(nosf.toString());
            }
        });


        txtnotech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCall(notech.toString());
            }
        });


        edit_HP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCall(edithp.toString());
            }
        });

        txtPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pdfLink =Maps_Constants.IP+pdf_url;
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                Intent intent = new Intent(Intent. ACTION_VIEW);
                if (pdfLink.contains("http")) {
                    intent.setData(Uri.parse(pdfLink));
                }else {
                    intent.setDataAndType(Uri.fromFile(new File(pdfLink)),"application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                startActivity(intent);
            }
        });



        buttonUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String idx = tvIdx.getText().toString();
                String cust_name = etCustomerName.getText().toString();

                String cust_addr = etCustomerAddress.getText().toString();
                String google_addr = etGoogleAddress.getText().toString();
                String inst_addr = etInstallationAddress.getText().toString();

                String myir_sc = etMyir_sc.getText().toString();
                String internet_number = etInternet_number.getText().toString();
                String phone_number = etPhone_number.getText().toString();


                String stp_name = etStpName.getText().toString(); //batam

                //.............................................................
                String stp_panel = spinner_05_stppanel.getSelectedItem().toString();
                String stp_port = spinner_06_stpport.getSelectedItem().toString();

                String k_teknis = spinner_07_teknis.getSelectedItem().toString();
                String k_nonteknis = spinner_08_nonteknis.getSelectedItem().toString();
                String status_prov = spinner_09_statusprov.getSelectedItem().toString();

                String latODP  = tvLatitudeOdp.getText().toString();
                String lngODP = tvLongitudeOdp.getText().toString();

                String latRmh = tvLatitudeInstAddr.getText().toString();
                String lngRmh = tvLongitudeInstAddr.getText().toString();

                String user_id = pref.getString("userid",null);
                String imei = pref.getString("imei",null);

                String alt_addr = edtCustAddrsALt.getText().toString();
                String no_sc = edtNoSc.getText().toString();
                String rad = edtJarakRad.getText().toString();
                String odo = edtJarakOdo.getText().toString();

                String nohp = edit_HP.getText().toString();
                String alt_nohp = edit_HP_alt.getText().toString();
                String kend_desc = edit_kend_desc.getText().toString();
                String ket1 = edtKet1.getText().toString();
                String ket2 = edtKet2.getText().toString();

//                Toast.makeText(getApplicationContext(), user_id+imei, Toast.LENGTH_LONG).show();

                if (barcodeStr == null){
                    barcodeStr = barcode_result.getText().toString();
                }
                if (barcodeStr2 == null){
                    barcodeStr2 = barcode_result2.getText().toString();
                }
                if (barcodeStr3 == null){
                    barcodeStr3 = barcode_result3.getText().toString();
                }
                if (barcodeStr4 == null){
                    barcodeStr4 = barcode_result4.getText().toString();
                }




//                try {
//                    updateImage.uploadMultipart();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                }


                // this data will send to _update_sf.php
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Looper.prepare();
//                        try {
//                            updateImage.callUpload();
//                        } catch (Exception ignored) {
//                            Log.d("Upload Error:",ignored.toString());
//                        }
//                        Looper.loop();
//                    }
//                }).start();
                updateImage.uploadMultipartCamera();
                submit(idx,
                        cust_name,
                        cust_addr,
                        google_addr,
                        inst_addr,
                        stp_name,
                        stp_panel,
                        stp_port,
                        myir_sc,
                        internet_number,
                        phone_number,
                        k_teknis,
                        k_nonteknis,
                        status_prov,
                        barcodeStr,
                        barcodeStr2,
                        barcodeStr3,
                        barcodeStr4,
                        user_id,
                        imei,
                        latODP,
                        lngODP,
                        latRmh,
                        lngRmh,
                        alt_addr,
                        no_sc,
                        rad,
                        odo,
                        nohp,
                        alt_nohp,
                        kend_desc,
                        ket1,
                        ket2
                );




            }
        });

        //SCAN BARCODE
        scan_barcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DataProvisioning_Result.this,DataProvisioning_Result_QRScanner.class);
                startActivityForResult(i,2);
            }
        });
        //SCAN BARCODE DW
        scan_barcode2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DataProvisioning_Result.this,DataProvisioning_Result_QRScanner.class);
                startActivityForResult(i,3);
            }
        });
        //SCAN BARCODE PORT
        scan_barcode3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DataProvisioning_Result.this,DataProvisioning_Result_QRScanner.class);
                startActivityForResult(i,4);
            }
        });
        scan_barcode4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DataProvisioning_Result.this,DataProvisioning_Result_QRScanner.class);
                startActivityForResult(i,5);
            }
        });
    }

    //SCAN BARCODE
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if(resultCode == RESULT_OK) {
//                String barcodeStr = data.getStringExtra("MyData");
//                barcode_result.setText(barcodeStr);
//            }
//        }
//    }



    private void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

        //..............................................................................................


    void submit(final String paramIdx,
                final String paramName,
                final String paramCust_addr,
                final String paramGoogle_addr,
                final String paramInstallation_addr,
                final String paramStp_name,
                final String paramStp_panel,
                final String paramStp_port,
                final String paramMyir_sc,
                final String paramInternet_number,
                final String paramPhone_number,
                final String paramK_teknis,
                final String paramK_nonteknis,
                final String paramStatus_prov,
                final String barcode,
                final String barcode2,
                final String barcode3,
                final String barcode4,
                final String user_id,
                final String imei,
                final String latODP,
                final String lngODP,
                final String latRmh,
                final String lngRmh,
                final String alt_addr,
                final String no_sc,
                final String rad,
                final String odo,
                final String nohp,
                final String alt_nohp,
                final String kend_desc,
                final String ket_1,
                final String ket_2

    ){

        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println("Submit "+paramName);
        StringRequest postRequest = new StringRequest(Request.Method.POST, Maps_Constants.IP + "mytools/android/_update_sf.php",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response ****", response);
                        String msg = response
                                .replaceAll("<(\"[^\"]*\"|'[^']*'|[^'\">].*)","")
                                .replace(", ","#")
                                .replace("{","###***:#");
                        Log.d(" Msg To Telegram : ",msg);
                        try {
                            sendMessageToTelegram(responseBot,msg,"915211367:AAE2aFwhJbGdPYe1UIEvtwflH-Yns4-3r-8");
                            Log.d(" Response "," "+ "Success");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(DataProvisioning_Result.this,SuccessActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "error");
//                        Toast.makeText(getApplicationContext(), pref.getString("userid",null), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Failed : Internet Bermasalah...!!!", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            //protected Map<String, String> getParams()
            protected Map<String, String> getParams()

            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("idx", paramIdx);
                params.put("cust_name", paramName);
                params.put("cust_addr", paramCust_addr);
                params.put("google_addr", paramGoogle_addr);
                params.put("inst_addr", paramInstallation_addr);
                params.put("stp_name",paramStp_name);
                params.put("stp_panel", paramStp_panel);
                params.put("stp_port", paramStp_port);

                params.put("myir_sc", paramMyir_sc);
                params.put("internet_number", paramInternet_number);
                params.put("phone_number", paramPhone_number);

                params.put("k_teknis", paramK_teknis);
                params.put("k_nonteknis", paramK_nonteknis);
                params.put("status_prov", paramStatus_prov);

                params.put("barcode", barcode);
                params.put("barcode_dw", barcode2);
                params.put("barcode_port", barcode3);
                params.put("barcode_sn", barcode4);
                params.put("user_id", user_id);
                params.put("imei", imei);

                params.put("latodp",latODP);
                params.put("lngodp",lngODP);
                params.put("latrmh",latRmh);
                params.put("lngrmh",lngRmh);

                params.put("alt_addr",alt_addr);
                params.put("no_sc",no_sc);
                params.put("rad",rad);
                params.put("odo",odo);

                params.put("nohp",nohp);
                params.put("alt_nohp",alt_nohp);
                params.put("kend_desc",kend_desc);
                params.put("ket1",ket_1);
                params.put("ket2",ket_2);

//                String msg = params.values().toString()
//                        .replace(", ","#")
//                        .replace("{","###***#");
//                Log.d(" Msg To Telegram : ",msg);
//                try {
//                    sendMessageToTelegram(response,msg,"915211367:AAE2aFwhJbGdPYe1UIEvtwflH-Yns4-3r-8");
//                    Log.d(" Response "," "+ "Success");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                getChatIdTelegram("915211367:AAE2aFwhJbGdPYe1UIEvtwflH-Yns4-3r-8");
//                Log.d(" API Bot Response 2 : "," "+ response);
//                sendMessageToTelegram();

                //params.put("idx", paramIdx);

                return params;
            }
        };
        queue.add(postRequest);


        //Uploading code
//        try {
//            String uploadId = "";
//            new MultipartUploadRequest(this, uploadId, Maps_Constants.UPDATE_URL)
////                            .addFileToUpload(path, "image") //Adding file
//                    .addParameter("cust_name", name)
//                    .addParameter("idx", "1")
////                    .addParameter("cust_addr", cust_addr)
////                    .addParameter("inst_addr", inst_addr)
////                    .addParameter("stp_name", stp_name)
//
//
//                    .setNotificationConfig(new UploadNotificationConfig())
//                    .setMaxRetries(2)
//                    .startUpload(); //Starting the upload
//
//        } catch (Exception exc) {
////                    Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
//            System.out.println("Gagal");
//        }
    }

    void uploadImage(){
        //upload...
        //return path/nama image
        //submit(param1, param1,..., pathImages)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.chat:
                goToWhatsapp();
                return true;
            case R.id.phone:

                goToCall("085276203300");

                //goToCall("085276203300");
                //goToCall("https://chat.whatsapp.com/3rhHd28mHILG4LndLFi6aZ");


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void goToTelegram(){
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        String packageName = "org.telegram.messenger";
//        intent.setPackage(packageName);
//        intent.setType("image/text/plain");
//
//        intent.putExtra(Intent.EXTRA_TEXT,"Hello World");
////        intent = Intent.createChooser(intent,"Share");
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////
//        this.startActivity(Intent.createChooser(intent,"Share"));
////        intent.setData(Uri.parse("http://telegram.me/radifanfariz"));
////        this.startActivity(intent);
//    }
    private void goToTelegram(String username){
        //kode menuju whatsapp/browser
        //String url = "https://api.whatsapp.com/send?phone=628116021971&text=Halooo...Test!!!" ;

        //String url = "https://chat.whatsapp.com/3rhHd28mHILG4LndLFi6aZ";
//        String url = "https://t.me/joinchat/BFk3tQ5neg_0D_STx1lgyA";
//        String url1 = "tg://msg?text=Nice%20Day&to=-306663963";
//        String url = "http://telegram.me/radifanfariz";
        String url = "tg://resolve?domain="+username;


        Intent bukaTG = new Intent(Intent. ACTION_VIEW);
//        bukaTG.setData(Uri. parse(url1));
        bukaTG.setData(Uri. parse(url));
        startActivity(bukaTG);
    }

    private void goToWhatsapp(){
        //kode menuju whatsapp/browser
        System.out.println("Halooo Chaaat");
        //String url = "https://api.whatsapp.com/send?phone=628116021971&text=Halooo...Test!!!" ;

        //String url = "https://chat.whatsapp.com/3rhHd28mHILG4LndLFi6aZ";
          String url = "https://t.me/joinchat/BFk3tQ5neg_0D_STx1lgyA";


        Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
        bukabrowser.setData(Uri. parse(url));
        startActivity(bukabrowser);
    }

        private void goToWhatsapp(String no,String msg){
        //kode menuju whatsapp/browser
        //String noSubSTr = no.substring(1,11);
        String noSubSTr = no.substring(1);

        String url = "https://api.whatsapp.com/send?phone=62"+noSubSTr+"&text="+msg;
        Intent bukawa = new Intent(Intent. ACTION_VIEW);
        bukawa.setData(Uri. parse(url));
        startActivity(bukawa);
    }

//    private void goToWhatsappsf(String no,String msg){
//        //kode menuju whatsapp/browser
//        //String noSubSTr = no.substring(1,11);
//        String noSubSTr = no.substring(1);
//
//        String url = "https://api.whatsapp.com/send?phone=62"+noSubSTr+"&text="+msg;
//        Intent bukawa = new Intent(Intent. ACTION_VIEW);
//        bukawa.setData(Uri. parse(url));
//        startActivity(bukawa);
//    }
//
//    private void goToWhatsapptech(String no,String msg){
//        //kode menuju whatsapp/browser
//        //String noSubSTr = no.substring(1,11);
//        String noSubSTr = no.substring(1);
//
//        String url = "https://api.whatsapp.com/send?phone=62"+noSubSTr+"&text="+msg;
//        Intent bukawa = new Intent(Intent. ACTION_VIEW);
//        bukawa.setData(Uri. parse(url));
//        startActivity(bukawa);
//    }
//
//
//    private void goToWhatsappcust(String no,String msg){
//        //kode menuju whatsapp/browser
//        //String noSubSTr = no.substring(1,11);
//        String noSubSTr = no.substring(1);
//
//        //String url = "https://api.whatsapp.com/send?phone=62"+noSubSTr+"&text="+msg;
//        String url = "https://api.whatsapp.com/send?phone=62"+noSubSTr+"&text="+msg;
//
//        Intent bukawa = new Intent(Intent. ACTION_VIEW);
//        bukawa.setData(Uri. parse(url));
//        startActivity(bukawa);
//    }

    private void sendMessageManuallyToTelegram(String msg){
        String url1 = "tg://msg?text="+msg+"&to=-306663963";

        Intent bukaTG = new Intent(Intent. ACTION_VIEW);
//        bukaTG.setData(Uri. parse(url1));
        bukaTG.setData(Uri. parse(url));
        startActivity(bukaTG);
    }

    private void getChatIdTelegram(String apiToken) throws JSONException {
        String urlBot = "https://api.telegram.org/bot%s/getUpdates";

        urlBot = String.format(urlBot, apiToken);

        new HttpAttemptConnecttion().execute(urlBot);

    };

    private void sendMessageToTelegram(String chatId,String message,String apiToken) throws UnsupportedEncodingException {
        String urlBot = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String msg = URLEncoder.encode(message,"UTF-8");
        urlBot = String.format(urlBot, apiToken, chatId, msg);

        new HttpAttemptConnecttion().execute(urlBot);

    };

    private String setDataBot(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        jsonObject = jsonArray.getJSONObject(jsonArray.length()-1);
        jsonObject = jsonObject.getJSONObject("message");
        jsonObject = jsonObject.getJSONObject("chat");
        String chatId = jsonObject.getString("id");

        return chatId;

    }

//        try {
//            URL url = new URL(urlBot);
//            URLConnection conn = url.openConnection();
//            InputStream is = new BufferedInputStream(conn.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//        try {
//            URL url = new URL(urlBot);
//            URLConnection conn = url.openConnection();
//            InputStream is = new BufferedInputStream(conn.getInputStream());
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String inputLine = "";
//            StringBuilder sb = new StringBuilder();
//            while ((inputLine = br.readLine()) != null) {
//                sb.append(inputLine);
//            }
//            //response of the server
//            String response = sb.toString();
//
//            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//            Log.d("Response :",response);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    private class HttpAttemptConnecttion extends AsyncTask<String,Void,String>{

        private void HttpAttemptConnecttion(){

        }

        @Override
        protected String doInBackground(String... urlParam) {
            try {
                URL url = new URL(urlParam[0]);
                URLConnection conn = url.openConnection();
                InputStream is = new BufferedInputStream(conn.getInputStream());

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                StringBuilder sb = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                //response of the server
                String response = sb.toString();

//                Toast.makeText(DataProvisioning_Result.this, response, Toast.LENGTH_SHORT).show();
                Log.d("Response :",response);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        protected void onPostExecute(String response){
            Log.d(" API Bot Response : ",response);
            try {
                if (DataProvisioning_Result.responseBot == null) {
                    DataProvisioning_Result.responseBot = setDataBot(response);
                }
                Log.d(" API Bot Response 2 : ",DataProvisioning_Result.responseBot);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }






    private void goToCall(String nomor){
        //kode menuju telpon
        System.out.println("Haloooooo Phone");
        Intent panggil = new Intent(Intent. ACTION_DIAL);
        panggil.setData(Uri. fromParts("tel",nomor,null));
        startActivity(panggil);

    }

    private void setImageView(ImageView imageView,String url){
        Glide.with(this)
                .asBitmap()
                .apply(options)
                .load(url)
                .into(imageView);
    }


    private void getData() {
        //String URL_PRODUCTS = IP + "myapi/api_provisioning.php";
        String URL_PRODUCTS = Maps_Constants.IP + "mytools/android/_api_provisioning.php";


        System.out.println(URL_PRODUCTS);
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
//                            System.out.println(array.toString());
                            JSONObject jsonObject= array.getJSONObject(0);

                            msgParams = jsonObject.toString();

                            //////////////////////////////////////////////////
//                            String msg = array.toString().replace(',','#').replace('"',' ').replace("[{","###");
//                            for (int i=0;i<array.length();i++){
//                                Log.d(" Array : ",array.getString(i));
//                            }

//                            Log.d(" Msg To Telegram : ",msg);
                            //////////////////////////////////////////////////

                            String witel = jsonObject.getString("witel");

                            cust_name = jsonObject.getString("cust_name");
                            String cust_addr = jsonObject.getString("cust_addr");

                            String google_addr = jsonObject.getString("google_addr");

                            String inst_addr = jsonObject.getString("inst_addr");

                            String stp_name = jsonObject.getString("stp_name");

                            String latitude_odp = jsonObject.getString("latitude_odp");
                            String longitude_odp = jsonObject.getString("longitude_odp");

                            String latitude_inst_addr = jsonObject.getString("latitude_inst_addr");
                            String longitude_inst_addr = jsonObject.getString("longitude_inst_addr");

                            String stp_panel_data = jsonObject.getString("stp_panel");
                            String stp_port_data = jsonObject.getString("stp_port");

                            String myir_sc = jsonObject.getString("myir_sc");
                            String internet_number = jsonObject.getString("internet_number");
                            String phone_number = jsonObject.getString("phone_number");

                            String kteknis_data = jsonObject.getString("k_teknis");
                            String knonteknis_data = jsonObject.getString("k_nonteknis");
                            String status_prov = jsonObject.getString("status_prov");


                            String barcode_data = jsonObject.getString("barcode_result");
                            String barcode_data2 = jsonObject.getString("barcode_dw");
                            String barcode_data3 = jsonObject.getString("barcode_port");
                            String barcode_data4 = jsonObject.getString("barcode_sn");

                            pseudoIdx = Integer.toString(param_idx);

                            url = jsonObject.getString("url");
                            url2 = jsonObject.getString("url2");
                            url3 = jsonObject.getString("url3");
                            url4 = jsonObject.getString("url4");
                            url5 = jsonObject.getString("url5");
                            url6 = jsonObject.getString("url6");
                            String hp = jsonObject.getString("hp");
                            String email = jsonObject.getString("email");
                            String paket_indihome = jsonObject.getString("packet_indihome");

                            String namasf = jsonObject.getString("namasf");
                            nosf = "0"+jsonObject.getString("nosf");

                            String namatech = jsonObject.getString("namatech");
                            notech = "0"+jsonObject.getString("notech");

                            String alt_addr = jsonObject.getString("alt_addr");
                            String no_sc = jsonObject.getString("no_sc");
                            String rad = jsonObject.getString("rad");
                            String odo = jsonObject.getString("odo");

                            user_tg_sf =  jsonObject.getString("username_tg_sf");
                            user_tg_tech = jsonObject.getString("username_tg_tech");

                            String nohp = jsonObject.getString("nohp");
                            String alt_nohp = jsonObject.getString("alt_nohp");
                            String kend_desc = jsonObject.getString("kend_desc");
                            String ket_1 = jsonObject.getString("ket1");
                            String ket_2 = jsonObject.getString("ket2");

                            pdf_url = jsonObject.getString("pdf_url");

                            File pdfFile = new File(pdf_url);
                            txtPdf.setText(pdfFile.getName());
                            pdfLink =Maps_Constants.IP+pdf_url;







                            //......................................................................
                            etWitel.setText(witel);

                            etCustomerName.setText(cust_name);

                            etCustomerAddress.setText(cust_addr);
                            etGoogleAddress.setText(google_addr);
                            etInstallationAddress.setText(inst_addr);

                            etMyir_sc.setText(myir_sc);
                            etInternet_number.setText(internet_number);
                            etPhone_number.setText(phone_number);

                            etStpName.setText(stp_name);
                            tvLatitudeOdp.setText(latitude_odp);
                            tvLongitudeOdp.setText(longitude_odp);
                            tvLatitudeInstAddr.setText(latitude_inst_addr);
                            tvLongitudeInstAddr.setText(longitude_inst_addr);

                            barcode_result.setText(barcode_data);
                            barcode_result2.setText(barcode_data2);
                            barcode_result3.setText(barcode_data3);
                            barcode_result4.setText(barcode_data4);

                            int spinnerPosition = adapter.getPosition(stp_panel_data);
                            int spinnerPosition2 = adapter2.getPosition(stp_port_data);
                            int spinnerPosition3 = adapter3.getPosition(kteknis_data);
                            int spinnerPosition4 = adapter4.getPosition(knonteknis_data);
                            int spinnerPosition5 = adapter5.getPosition(status_prov);

                            if (stp_panel_data != null) {
                                spinner_05_stppanel.setSelection(spinnerPosition);
                            }
                            if(stp_port_data != null) {
                                spinner_06_stpport.setSelection(spinnerPosition2);
                            }
                            if (kteknis_data != null) {
                                spinner_07_teknis.setSelection(spinnerPosition3);
                            }
                            if(knonteknis_data != null) {
                                spinner_08_nonteknis.setSelection(spinnerPosition4);
                            }
                            if(status_prov != null) {
                                spinner_09_statusprov.setSelection(spinnerPosition5);
                            }


                            etHp.setText(hp);
                            etEmail.setText(email);
                            etPaketIndihome.setText(paket_indihome);

                            txtnamasf.setText(namasf);
                            txtnosf.setText(nosf);
                            txtnamatech.setText(namatech);
                            txtnotech.setText(notech);

                            edtCustAddrsALt.setText(alt_addr);
                            edtNoSc.setText(no_sc);
                            edtJarakRad.setText(rad);
                            edtJarakOdo.setText(odo);

                            edit_HP.setText(nohp);
                            edit_HP_alt.setText(alt_nohp);
                            edit_kend_desc.setText(kend_desc);
                            edtKet1.setText(ket_1);
                            edtKet2.setText(ket_2);





                            //untuk tampilkan foto harus eksekusi method DownloadImageTask, ada dibawah
//                            new DownLoadImageTask(iv).execute(Maps_Constants.IP+url);
//                            new DownLoadImageTask(iv2).execute(Maps_Constants.IP+url2);
//                            new DownLoadImageTask(iv3).execute(Maps_Constants.IP+url3);
//                            new DownLoadImageTask(iv4).execute(Maps_Constants.IP+url4);
//                            new DownLoadImageTask(iv5).execute(Maps_Constants.IP+url5);
//                            new DownLoadImageTask(iv6).execute(Maps_Constants.IP+url6);
                            setImageView(iv,Maps_Constants.IP+url);
                            setImageView(iv2,Maps_Constants.IP+url2);
                            setImageView(iv3,Maps_Constants.IP+url3);
                            setImageView(iv4,Maps_Constants.IP+url4);
                            setImageView(iv5,Maps_Constants.IP+url5);
                            setImageView(iv6,Maps_Constants.IP+url6);




                            System.out.println("=======================URL====================="+Maps_Constants.IP+url);
                            System.out.println("=======================URL2====================="+Maps_Constants.IP+url2);
                            System.out.println("=======================stp_name====================="+stp_name);
                            System.out.println("=======================muir_sc====================="+myir_sc);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idx",Integer.toString(param_idx));
                return params;
            }
        };

        //adding our stringrequest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

//    //Kode untuk konversi url ke bitmap dan tampilkan ke ImageView
//    private class DownLoadImageTask extends AsyncTask<String,Void,byte[]> {
//        ImageView imageView;
//        Context context;
//        //ImageView imageView2;//ok
//
//
//    public DownLoadImageTask(ImageView imageView,Context context){
//            this.imageView = imageView;
//            this.context = context;
//            //this.imageView = imageView2;
//    }
//
//
//
//        /*
//            doInBackground(Params... params)
//                Override this method to perform a computation on a background thread.
//         */
//        protected byte[] doInBackground(String...urls){
//            String urlOfImage = urls[0];
//            Bitmap bitmap = null;
//            try{
//                InputStream is = new URL(urlOfImage).openStream();
////                byte[] bytes = IOUtils.toByteArray(is);
//                /*
//                    decodeStream(InputStream is)
//                        Decode an input stream into a bitmap.
//                 */
//                bitmap = BitmapFactory.decodeStream(is);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
//                byte[] bytes = stream.toByteArray();
//                return bytes;
//            }catch(Exception e){ // Catch the download exception
//                e.printStackTrace();
//                BitmapDrawable bitmapDrawable =(BitmapDrawable) getResources().getDrawable(R.mipmap.noimage);
//                bitmap = bitmapDrawable.getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
//                byte[] bytes = stream.toByteArray();
//                return bytes;
//            }
//        }
//
//        /*
//            onPostExecute(Result result)
//                Runs on the UI thread after doInBackground(Params...).
//         */
//        protected void onPostExecute(byte[] result){
//            Glide.with(context)
//                    .load(result)
//                    .into(imageView);
////            imageView.setImageBitmap(result);
//            //imageView2.setImageBitmap(result);
//        }
//    }
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //Kode untuk konversi url ke bitmap dan tampilkan ke ImageView
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        //ImageView imageView2;//ok


        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
            //this.imageView = imageView2;
        }



        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap image = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();

                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                Bitmap bmp = BitmapFactory.decodeStream(is);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG,80,stream);
                image = BitmapFactory.decodeStream(new ByteArrayInputStream(stream.toByteArray()));
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
                BitmapDrawable bitmapDrawable =(BitmapDrawable) getResources().getDrawable(R.mipmap.noimage);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                image = bitmap;
            }
            return image;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
            //imageView2.setImageBitmap(result);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_MIME_TYPES,"*/*");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST); //Select Picture
    }

//    private void take_picture(){
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        filePath = Uri.fromFile(createImageFile());
//        photoCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePath);
//        if (photoCaptureIntent.resolveActivity(getPackageManager()) != null){
//            startActivityForResult(photoCaptureIntent, requestCode);
//        }
//    };
//    private File createImageFile(){
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"CameraDemo");
//        if (!mediaStorageDir.exists()){
//            if (!mediaStorageDir.mkdir()){
//                return null;
//            }
//        }
//
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        return new File(mediaStorageDir.getPath() + File.separator + "MyTools" + timeStamp + ".jpg");
//    }
    private void take_picture(){
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
        String packageName = getApplicationContext().getPackageName();
        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photoCaptureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(this,getPackageName(),photoFile);
//                photoCaptureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                photoCaptureIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                this.grantUriPermission(packageName,photoUri,Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                photoCaptureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,photoUri);
                Log.d("Filepath :",packageName.toString());
                startActivityForResult(photoCaptureIntent,PICK_IMAGE_REQUEST);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Mytools_"+timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(imageFileName,".jpg",storageDir);

//        imageFile.createNewFile();
//        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
//        byte[] bitmapdata = bos.toByteArray();
//
//        FileOutputStream fos = new FileOutputStream(imageFile);
//        fos.write(bitmapdata);
//        fos.flush();
//        fos.close();

//        File compressedImageFile = new Compressor(this).compressToFile(imageFile);
        imageFilePathOri = imageFile.getAbsolutePath();
//        imageFilePathCompress = compressedImageFile.getAbsolutePath();

        return imageFile;
    };

    private void setPic(ImageView imageView){
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imageFilePathOri, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            cekan = true;
//            showFileChooser();
            take_picture();
//            updateCondition = true;
        }
        if (v == buttonChoose2) {
            cekan2 = true;
//            showFileChooser();
            take_picture();
//            updateCondition2 = true;
        }
        if (v == buttonChoose3) {
            cekan3 = true;
//            showFileChooser();
            take_picture();
//            updateCondition3 = true;
        }
        if (v == buttonChoose4) {
            cekan4 = true;
//            showFileChooser();
            take_picture();
//            updateCondition4 = true;
        }
        if (v == buttonChoose5) {
            cekan5 = true;
//            showFileChooser();
            take_picture();
//            updateCondition4 = true;
        }
        if (v == buttonChoose6) {
            cekan6 = true;
//            showFileChooser();
            take_picture();
//            updateCondition4 = true;
        }
        if (v == take_picture) {
            Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoCaptureIntent, requestCode);
        }
        if (v == buttonChoosePdf) {
            cekan7 = true;
//            showFileChooser();

//            FragmentManager fm = getSupportFragmentManager();
//
//            String tag = "File Explorer";
//
//            fileExplore = new FileExplore(DataProvisioning_Result.this,DataProvisioning_Result.this,fm,tag);

//            fileExplore.loadFileList();
//
//            fileExplore.show(fm,tag);
//
//            Log.d(fileExplore.TAG, fileExplore.path.getAbsolutePath());
//            uploadPDF();
            fileExplore.showDialog();
//            fileExplore.loadFileList();
//
//            fileExplore.show(fm,tag);

        }

    }

    public void compressImage(String imageUrl,int quality){
        File file = new File(imageUrl);
        try{
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality , new FileOutputStream(file));
            imageUrl = file.getAbsolutePath();
            Log.d("Image Compress :","Success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void uploadPDF(){
        if (cekan7){
//            fileExplore.loadFileList();
//
//            fileExplore.show(fm,tag);
            fileExplore.showDialog();

            Log.d("CEK PATH PDF", fileExplore.path.getAbsolutePath());
//                    filePathPdf = data.getData();
            String pdfName = fileExplore.getArguments().getString("file_name");
            String pdfPath = fileExplore.getArguments().getString("file_path");
            Log.d("Cek Data :", pdfPath);
//                    File pdfFile = new File(filePathPdf.getPath());
            txtPdf.setText(pdfName);
            cekan7 = false;
            updateImage.updateConditionFuncPdf(true,pdfPath,pseudoIdx,pdf_url);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
////////////////////'||'-->ubah tanda ini jika error///////////////////////////////////////
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK ) {
            try {
//                pseudoIdx = Integer.toString(param_idx);
                if (cekan){
                    Log.d("Filepath 1 :",imageFilePathOri);
//                    Log.d("Filepath 1 compress :",imageFilePathCompress);
//                    bitmap = BitmapFactory.decodeFile(imageFilePathOri);
                    //////untuk kompress gambar
//                    compressImage(imageFilePathOri,2);
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(iv);
//                    iv.setImageBitmap(bitmap);
//                    setPic(iv);
                    cekan = false;
//                    File f = new File(getApplicationContext().getCacheDir(),imageFilePathOri);
//                    f.createNewFile();
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
//                    byte[] bitmapdata = bos.toByteArray();
//
//                    FileOutputStream fos = new FileOutputStream(f);
//                    fos.write(bitmapdata);
//                    fos.flush();
//                    fos.close();
                    String imageFilePath1 = imageFilePathOri;
                    updateImage.updateConditionfunc(true,imageFilePath1,pseudoIdx,url);
//                    Toast.makeText(getApplicationContext(), updateImage.getPath(filePath), Toast.LENGTH_LONG).show();
                }
                if (cekan2){
                    Log.d("Filepath 2 :",imageFilePathOri);
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(iv2);
                    cekan2 = false;
                    String imageFilePath2 = imageFilePathOri;
                    updateImage.updateConditionfunc2(true,imageFilePath2,pseudoIdx,url2);
                }
                if (cekan3){
                    Log.d("Filepath 3 :",imageFilePathOri);
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(iv3);
                    cekan3 = false;
                    String imageFilePath3 = imageFilePathOri;
                    updateImage.updateConditionfunc3(true,imageFilePath3,pseudoIdx,url3);
                }
                if (cekan4){
                    Log.d("Filepath 4 :",imageFilePathOri);
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(iv4);
                    cekan4 = false;
                    String imageFilePath4 = imageFilePathOri;
                    updateImage.updateConditionfunc4(true,imageFilePath4,pseudoIdx,url4);
                }
                if (cekan5){
                    Log.d("Filepath 5 :",imageFilePathOri);
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(iv5);
                    cekan5 = false;
                    String imageFilePath5 = imageFilePathOri;
                    updateImage.updateConditionfunc5(true,imageFilePath5,pseudoIdx,url5);
                }
                if (cekan6){
                    Log.d("Filepath 6 :",imageFilePathOri);
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(iv6);
                    cekan6 = false;
                    String imageFilePath6 = imageFilePathOri;
                    updateImage.updateConditionfunc6(true,imageFilePath6,pseudoIdx,url6);
                }
//                if (cekan7){
////                    filePathPdf = data.getData();
//                    String pdfName = data.getStringExtra("file_name");
//                    String pdfPath = data.getStringExtra("file_path");
//                    Log.d("Cek Data :", pdfPath);
////                    File pdfFile = new File(filePathPdf.getPath());
//                    txtPdf.setText(pdfName);
//                    cekan7 = false;
//                    updateImage.updateConditionFuncPdf(true,pdfPath,pseudoIdx,pdf_url);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//////////////////////'||'-->ubah tanda ini jika error///////////////////////////////////////
//        if (requestCode == PICK_IMAGE_REQUEST || resultCode == RESULT_OK && data != null && data.getData() != null) {
//            try {
//                pseudoIdx = Integer.toString(param_idx);
//                if (cekan){
//                    filePath = data.getData();
////                    bitmap = (Bitmap) data.getExtras().get("data");
//                    Log.d("Filepath :",imageFilePath);
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
////                    bitmap = BitmapFactory.decodeFile(imageFilePath);
//                    iv.setImageBitmap(bitmap);
////                    iv.setImageURI(filePath);
//                    cekan = false;
////                    updateCondition = true;
//                    updateImage.updateConditionfunc(true,filePath,pseudoIdx,url);
////                    Toast.makeText(getApplicationContext(), updateImage.getPath(filePath), Toast.LENGTH_LONG).show();
//                }
//                if (cekan2){
//                    filePath2 = data.getData();
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath2);
//                    iv2.setImageBitmap(bitmap);
//                    cekan2 = false;
////                    updateCondition2 = true;
//                    updateImage.updateConditionfunc2(true,filePath2,pseudoIdx,url2);
//                }
//                if (cekan3){
//                    filePath3 = data.getData();
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath3);
//                    iv3.setImageBitmap(bitmap);
//                    cekan3 = false;
////                    updateCondition3 = true;
//                    updateImage.updateConditionfunc3(true,filePath3,pseudoIdx,url3);
//                }
//                if (cekan4){
//                    filePath4 = data.getData();
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath4);
//                    iv4.setImageBitmap(bitmap);
//                    cekan4 = false;
////                    updateCondition4 = true;
//                    updateImage.updateConditionfunc4(true,filePath4,pseudoIdx,url4);
//                }
//                if (cekan5){
//                    filePath5 = data.getData();
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath5);
//                    iv5.setImageBitmap(bitmap);
//                    cekan5 = false;
////                    updateCondition4 = true;
//                    updateImage.updateConditionfunc5(true,filePath5,pseudoIdx,url5);
//                }
//                if (cekan6){
//                    filePath6 = data.getData();
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath6);
//                    iv6.setImageBitmap(bitmap);
//                    cekan6 = false;
////                    updateCondition4 = true;
//                    updateImage.updateConditionfunc6(true,filePath6,pseudoIdx,url6);
//                }


        if (resultCode == RESULT_OK) {
            if (requestCode == 2) {
                barcodeStr = data.getStringExtra("barcode_data");
                barcode_result.setText(barcodeStr);
            }
            if (requestCode == 3) {
                barcodeStr2 = data.getStringExtra("barcode_data");
                barcode_result2.setText(barcodeStr2);
            }
            if (requestCode == 4) {
                barcodeStr3 = data.getStringExtra("barcode_data");
                barcode_result3.setText(barcodeStr3);
            }
            if (requestCode == 5) {
                barcodeStr4 = data.getStringExtra("barcode_data");
                barcode_result4.setText(barcodeStr4);
            }
//            if (barcodeStr != null)
//            barcode_result.setText(barcodeStr);
//            if (barcodeStr2 != null)
//                barcode_result2.setText(barcodeStr2);
//            if (barcodeStr3 != null)
//                barcode_result3.setText(barcodeStr3);
        }

    }
//    private boolean isLocationEnabled() {
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    }

//    private boolean checkLocation() {
//        if (!isLocationEnabled())
//            showAlert();
//        return isLocationEnabled();
//    }
//    public void showAlert() {
//        final AlertDialog.Builder dialog = new AlertDialog.Builder(DataProvisioning_Result.this);
//        dialog.setCancelable(false);
//        dialog.setTitle("Harap Aktifkan Lokasi Anda")
//                .setMessage("GPS anda dalam keadaan nonaktif.\nHarap Aktifkan GPS untuk " +
//                        "menggunakan aplikasi ini")
//                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//
//                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                        startActivity(myIntent);
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                        paramDialogInterface.cancel();
//                    }
//                });
//        dialog.create().show();
//    }

//protected void startLocationUpdate(){
////        locationRequest = new LocationRequest();
////        locationRequest.setInterval(1000);
////        locationRequest.setFastestInterval(1000);
////        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,Looper.getMainLooper());
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (requestingLocationUpdate){
//            startLocationUpdate();
//        }
//    }
@Override
protected void onDestroy() {
    super.onDestroy();
    locationTrack.stopListener();
}
}


