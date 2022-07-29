package com.mediumsitompul.querydatasales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputEditText;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MaterialActivity extends AppCompatActivity implements View.OnClickListener {
    ///////userid, internet number dan phone number
    TextInputEditText edtUserId;
    TextInputEditText edtInternetNumber;
    TextInputEditText edtPhoneNumber;

    ////material di odp
    CardView scanQrBtn;
    TextInputEditText edtScanQr;
    Spinner spinJlhSoc;
    Spinner spinJlhSClamp;
    Spinner spinJlhClampRing;
    CardView uploadEviden0Btn;
    ImageView imgEvd0;
    ////tiang antara 1
    Spinner spinJlhSClamp1;
    Spinner spinJlhClampRing1;
    Spinner spinJlhTiang1;
    Spinner spinJlhSplitStopper1;
    CardView uploadEviden1Btn;
    ImageView imgEvd1;
    /////tiang antara 2
    Spinner spinJlhSClamp2;
    Spinner spinJlhClampRing2;
    Spinner spinJlhTiang2;
    Spinner spinJlhSplitStopper2;
    CardView uploadEviden2Btn;
    ImageView imgEvd2;
    /////tiang antara 3
    Spinner spinJlhSClamp3;
    Spinner spinJlhClampRing3;
    Spinner spinJlhTiang3;
    Spinner spinJlhSplitStopper3;
    CardView uploadEviden3Btn;
    ImageView imgEvd3;
    //////pelanggan outdoor
    Spinner spinJlhSClamp4;
    Spinner spinJlhClampHook;
    Spinner spinJlhSplitStopper4;
    CardView uploadEviden4Btn;
    ImageView imgEvd4;
    //////pelanggan indoor
    Spinner spinJlRosetOptic;
    Spinner spinJlhSoc2;
    CardView uploadEviden5Btn;
    ImageView imgEvd5;
    /////submit btn
    CardView saveBtn;

    ArrayAdapter<CharSequence> spinnerAdapter;

    private String imageFilePathOri;
    private List<String> imagePathList;
    private String imagePath;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private String imagePath4;
    private String imagePath5;
    private RequestOptions options;
    boolean cekan,cekan1,cekan2,cekan3,cekan4,cekan5;
    private static final int PICK_IMAGE_REQUEST = 1;

    private String barcodeStr;
    private  String userid;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        pref = getSharedPreferences("MyPref",0);
        userid = pref.getString("userid",null);

        imagePathList = new ArrayList<String>();

        options = new RequestOptions().fitCenter().placeholder(R.mipmap.noimage).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true);

        //////userid, internet number dan phone number
        edtUserId = findViewById(R.id.edtUserId);
        edtUserId.setText(userid);
        edtInternetNumber = findViewById(R.id.edtInternetNumber);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);

        ////material di odp
        scanQrBtn = findViewById(R.id.scanQrBtn);
        edtScanQr = findViewById(R.id.edtScanQr);
        spinJlhSoc = findViewById(R.id.spinJlhSoc);
        spinJlhSClamp = findViewById(R.id.spinJlhClamp);
        spinJlhClampRing = findViewById(R.id.spinJlhClampRing);
        uploadEviden0Btn = findViewById(R.id.uploadEviden0);
        imgEvd0 = findViewById(R.id.imgEvd0);
        /////tiang antara 1
        spinJlhSClamp1 = findViewById(R.id.spinJlhClamp1);
        spinJlhClampRing1 = findViewById(R.id.spinJlhClampRing1);
        spinJlhTiang1 = findViewById(R.id.spinJlhTiang1);
        spinJlhSplitStopper1 = findViewById(R.id.spinJlhSplitStopper1);
        uploadEviden1Btn = findViewById(R.id.uploadEviden1);
        imgEvd1 = findViewById(R.id.imgEvd1);
        /////tiang antara 2
        spinJlhSClamp2 = findViewById(R.id.spinJlhClamp2);
        spinJlhClampRing2 = findViewById(R.id.spinJlhClampRing2);
        spinJlhTiang2 = findViewById(R.id.spinJlhTiang2);
        spinJlhSplitStopper2 = findViewById(R.id.spinJlhSplitStopper2);
        uploadEviden2Btn = findViewById(R.id.uploadEviden2);
        imgEvd2 = findViewById(R.id.imgEvd2);
        /////tiang antara 3
        spinJlhSClamp3 = findViewById(R.id.spinJlhClamp3);
        spinJlhClampRing3 = findViewById(R.id.spinJlhClampRing3);
        spinJlhTiang3 = findViewById(R.id.spinJlhTiang3);
        spinJlhSplitStopper3 = findViewById(R.id.spinJlhSplitStopper3);
        uploadEviden3Btn = findViewById(R.id.uploadEviden3);
        imgEvd3 = findViewById(R.id.imgEvd3);
        //////pelanggan (out door)
        spinJlhClampHook = findViewById(R.id.spinJlhClampHook);
        spinJlhSClamp4 = findViewById(R.id.spinJlhClamp4);
        spinJlhSplitStopper4 = findViewById(R.id.spinJlhSplitStopper4);
        uploadEviden4Btn = findViewById(R.id.uploadEviden4);
        imgEvd4 = findViewById(R.id.imgEvd4);
        /////pelangaan (in door)
        spinJlRosetOptic = findViewById(R.id.spinJlhRosetOptic);
        spinJlhSoc2 = findViewById(R.id.spinJlhSoc2);
        uploadEviden5Btn = findViewById(R.id.uploadEviden5);
        imgEvd5 = findViewById(R.id.imgEvd5);
        //////submit btn
        saveBtn = findViewById(R.id.saveBtn);

        //////spinner adapter
        spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.jumlah,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /////set spinner adapter
        /////material di odp
        spinJlhSoc.setAdapter(spinnerAdapter);
        spinJlhSClamp.setAdapter(spinnerAdapter);
        spinJlhClampRing.setAdapter(spinnerAdapter);
        //////tiang antara 1
        spinJlhSClamp1.setAdapter(spinnerAdapter);
        spinJlhClampRing1.setAdapter(spinnerAdapter);
        spinJlhTiang1.setAdapter(spinnerAdapter);
        spinJlhSplitStopper1.setAdapter(spinnerAdapter);
        //////tiang antara 2
        spinJlhSClamp2.setAdapter(spinnerAdapter);
        spinJlhClampRing2.setAdapter(spinnerAdapter);
        spinJlhTiang2.setAdapter(spinnerAdapter);
        spinJlhSplitStopper2.setAdapter(spinnerAdapter);
        //////tiang antara 3
        spinJlhSClamp3.setAdapter(spinnerAdapter);
        spinJlhClampRing3.setAdapter(spinnerAdapter);
        spinJlhTiang3.setAdapter(spinnerAdapter);
        spinJlhSplitStopper3.setAdapter(spinnerAdapter);
        //////pelanggan (out door)
        spinJlhClampHook.setAdapter(spinnerAdapter);
        spinJlhSClamp4.setAdapter(spinnerAdapter);
        spinJlhSplitStopper4.setAdapter(spinnerAdapter);
        //////pelanggan (in door)
        spinJlRosetOptic.setAdapter(spinnerAdapter);
        spinJlhSoc2.setAdapter(spinnerAdapter);

        /////upload eviden btn
        uploadEviden0Btn.setOnClickListener(this);
        uploadEviden1Btn.setOnClickListener(this);
        uploadEviden2Btn.setOnClickListener(this);
        uploadEviden3Btn.setOnClickListener(this);
        uploadEviden4Btn.setOnClickListener(this);
        uploadEviden5Btn.setOnClickListener(this);
        /////progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Data Collections");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);

        //////scan qr btn
        scanQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaterialActivity.this,DataProvisioning_Result_QRScanner.class);
                startActivityForResult(i,100);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadMultipart() == true) {
                    progressDialog.dismiss();
                    Intent i = new Intent(MaterialActivity.this, SuccessActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private Boolean uploadMultipart(){
        //////userid, internet number dan phone number
        String userid = edtUserId.getText().toString();
        String internetnumber = edtInternetNumber.getText().toString();
        String phonenumber = edtPhoneNumber.getText().toString();
        /////material di odp
        String qrcode = edtScanQr.getText().toString();
        String jlhsoc = spinJlhSoc.getSelectedItem().toString();
        String jlhsclamp = spinJlhSClamp.getSelectedItem().toString();
        String jlhclampring = spinJlhClampRing.getSelectedItem().toString();
        String imgpathevd = imagePath;
        /////tiang antara 1
        String jlhsclamp1 = spinJlhSClamp1.getSelectedItem().toString();
        String jlhclampring1 = spinJlhClampRing1.getSelectedItem().toString();
        String jlhtiang1 = spinJlhTiang1.getSelectedItem().toString();
        String jlhsplitstopper1 = spinJlhSplitStopper1.getSelectedItem().toString();
        String imgpathevd1 = imagePath1;
        /////tiang antara 2
        String jlhsclamp2 = spinJlhSClamp2.getSelectedItem().toString();
        String jlhclampring2 = spinJlhClampRing2.getSelectedItem().toString();
        String jlhtiang2 = spinJlhTiang2.getSelectedItem().toString();
        String jlhsplitstopper2 = spinJlhSplitStopper2.getSelectedItem().toString();
        String imgpathevd2 = imagePath2;
        /////tiang antara 3
        String jlhsclamp3 = spinJlhSClamp3.getSelectedItem().toString();
        String jlhclampring3 = spinJlhClampRing3.getSelectedItem().toString();
        String jlhtiang3 = spinJlhTiang3.getSelectedItem().toString();
        String jlhsplitstopper3 = spinJlhSplitStopper3.getSelectedItem().toString();
        String imgpathevd3 = imagePath3;
        /////pelanggan (out door)
        String jlhsclamphook = spinJlhClampHook.getSelectedItem().toString();
        String jlhsclamp4 = spinJlhSClamp4.getSelectedItem().toString();
        String jlhsplitstopper4 = spinJlhSplitStopper4.getSelectedItem().toString();
        String imgpathevd4 = imagePath4;
        /////pelanggan (in door)
        String jlhrosetoptic = spinJlRosetOptic.getSelectedItem().toString();
        String jlhsoc2 = spinJlhSoc2.getSelectedItem().toString();
        String imgpathevd5 = imagePath5;
        try {
            progressDialog.show();

            String uploadId = UUID.randomUUID().toString();

            new MultipartUploadRequest(this,uploadId,Maps_Constants.UPLOAD_MATERIAL)
                    .addFileToUpload(imgpathevd,"image")
                    .addFileToUpload(imgpathevd1,"image1")
                    .addFileToUpload(imgpathevd2,"image2")
                    .addFileToUpload(imgpathevd3,"image3")
                    .addFileToUpload(imgpathevd4,"image4")
                    .addFileToUpload(imgpathevd5,"image5")
                    //////userid, internet number dan phone number
                    .addParameter("userid",userid)
                    .addParameter("internetnumber",internetnumber)
                    .addParameter("phonenumber",phonenumber)
                    /////material di odp
                    .addParameter("qrcode",qrcode)
                    .addParameter("jlhsoc",jlhsoc)
                    .addParameter("jlhsclamp",jlhsclamp)
                    .addParameter("jlhclampring",jlhclampring)
//                    /////tiang antara 1
                    .addParameter("jlhsclamp1",jlhsclamp1)
                    .addParameter("jlhclampring1",jlhclampring1)
                    .addParameter("jlhtiang1",jlhtiang1)
                    .addParameter("jlhsplitstopper1",jlhsplitstopper1)
//                    /////tiang antara 2
                    .addParameter("jlhsclamp2",jlhsclamp2)
                    .addParameter("jlhclampring2",jlhclampring2)
                    .addParameter("jlhtiang2",jlhtiang2)
                    .addParameter("jlhsplitstopper2",jlhsplitstopper2)
//                    /////tiang antara 3
                    .addParameter("jlhsclamp3",jlhsclamp3)
                    .addParameter("jlhclampring3",jlhclampring3)
                    .addParameter("jlhtiang3",jlhtiang3)
                    .addParameter("jlhsplitstopper3",jlhsplitstopper3)
//                    //////////pelangaan (out door)
                    .addParameter("jlhclamphook",jlhsclamphook)
                    .addParameter("jlhsclamp4",jlhsclamp4)
                    .addParameter("jlhsplitstopper4",jlhsplitstopper4)
//                    ///////pelangaan (in door)
                    .addParameter("jlhrosetoptic",jlhrosetoptic)
                    .addParameter("jlhsoc2",jlhsoc2)

//                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
            Toast.makeText(getApplicationContext(), "Berhasil di Save", Toast.LENGTH_SHORT).show();
            return true;
//            Intent intent = new Intent(MainActivity.this,SuccessActivity.class);
//            startActivity(intent);
//            finish();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == uploadEviden0Btn) {
            cekan = true;
//            showFileChooser();
            take_picture();
//            updateCondition = true;
        }
        if (v == uploadEviden1Btn) {
            cekan1 = true;
//            showFileChooser();
            take_picture();
//            updateCondition2 = true;
        }
        if (v == uploadEviden2Btn) {
            cekan2 = true;
//            showFileChooser();
            take_picture();
//            updateCondition3 = true;
        }
        if (v == uploadEviden3Btn) {
            cekan3 = true;
//            showFileChooser();
            take_picture();
//            updateCondition4 = true;
        }
        if (v == uploadEviden4Btn) {
            cekan4 = true;
//            showFileChooser();
            take_picture();
//            updateCondition4 = true;
        }
        if (v == uploadEviden5Btn) {
            cekan5 = true;
//            showFileChooser();
            take_picture();
//            updateCondition4 = true;
        }
    }

    private void take_picture(){
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
        File imageFile = File.createTempFile(imageFileName,".jpg",storageDir);
//        File compressedImageFile = new Compressor(this).compressToFile(imageFile);
        imageFilePathOri = imageFile.getAbsolutePath();
        imagePathList.add(imageFilePathOri);
//        imageFilePathCompress = compressedImageFile.getAbsolutePath();

        return imageFile;
    };

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            try {
                if (cekan) {
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(imgEvd0);

                    imagePath = imageFilePathOri;
                    cekan = false;
                }
                if (cekan1) {
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(imgEvd1);

                    imagePath1 = imageFilePathOri;
                    cekan1 = false;
                }
                if (cekan2) {
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(imgEvd2);

                    imagePath2 = imageFilePathOri;
                    cekan2 = false;
                }
                if (cekan3) {
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(imgEvd3);

                    imagePath3 = imageFilePathOri;
                    cekan3 = false;
                }
                if (cekan4) {
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(imgEvd4);

                    imagePath4 = imageFilePathOri;
                    cekan4 = false;
                }
                if (cekan5) {
                    Glide.with(this)
                            .asBitmap()
                            .apply(options)
                            .load(imageFilePathOri)
                            .into(imgEvd5);

                    imagePath5 = imageFilePathOri;
                    cekan5 = false;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                barcodeStr = data.getStringExtra("barcode_data");
                edtScanQr.setText(barcodeStr);
            }

        }
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

    private void goToCall(String nomor){
        //kode menuju telpon
        System.out.println("Haloooooo Phone");
        Intent panggil = new Intent(Intent. ACTION_DIAL);
        panggil.setData(Uri. fromParts("tel",nomor,null));
        startActivity(panggil);

    }
}
