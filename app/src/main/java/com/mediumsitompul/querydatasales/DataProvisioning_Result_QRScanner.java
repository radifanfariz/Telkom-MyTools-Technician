package com.mediumsitompul.querydatasales;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class DataProvisioning_Result_QRScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){

                    case DialogInterface.BUTTON_POSITIVE:
                        String data = rawResult.getText();
                        Intent ok = new Intent(DataProvisioning_Result_QRScanner.this,DataProvisioning_Result.class);
                        ok.putExtra("barcode_data", data);
                        setResult(RESULT_OK, ok);
                        finish();
                        break;
                        //String barcode_result = rawResult.getText().toString();
                        //Intent ok = new Intent(DataProvisioning_Result_QRScanner.this,DataProvisioning_Result.class);
                        //ok.putExtra(KEY, barcode_result);
                        //setResult(RESULT_OK, ok);

                    case DialogInterface.BUTTON_NEGATIVE:
//                        Intent ulangi = new Intent(DataProvisioning_Result_QRScanner.this,DataProvisioning_Result_QRScanner.class);
//                        startActivity(ulangi);
                        finish();
                        break;
                }
            }
        };
        onPause();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText()).setPositiveButton("SAVE", dialogClickListener).setNegativeButton("EXIT", dialogClickListener).show();
        AlertDialog alert = builder.create();
        alert.show();
        alert.dismiss();
        mScannerView.resumeCameraPreview(this);
    }
}
