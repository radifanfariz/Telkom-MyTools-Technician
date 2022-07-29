package com.mediumsitompul.querydatasales;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.UUID;


public class Update_Image extends AppCompatActivity {


    DataProvisioning_Result dataProvisioningResult = new DataProvisioning_Result();


//    String UPLOAD_URL = "http://192.168.100.78/mytools/_update_image.php";
    String UPLOAD_URL = Maps_Constants.IP+"mytools/android/_update_image.php";
    String UPLOAD_URL2 = Maps_Constants.IP+"mytools/android/_upload_pdf.php";
    //String UPLOAD_URL = "http://36.89.34.66/mytools/_update_image.php";
    //String UPLOAD_URL = "http://192.168.43.85/mytools/android/_update_image.php";



    Context context;
    Activity activity;
    public Uri filePath;
    public Uri filePath2;
    public Uri filePath3;
    public Uri filePath4;
    public Uri filePath5;
    public Uri filePath6;
    public Uri filePathPdf;
    public String imagePath1;
    public String imagePath2;
    public String imagePath3;
    public String imagePath4;
    public String imagePath5;
    public String imagePath6;
    public String pdfPath;
    public  String idx;
    public  String oldPath;
    public  String oldPath2;
    public  String oldPath3;
    public  String oldPath4;
    public  String oldPath5;
    public  String oldPath6;
    public  String oldPathPdf;
    public  Boolean[] updateCondition = {false,false,false,false,false,false};
    public  Boolean updateConditionPdf =false;
//    public Boolean updateCondition = false,updateCondition2 = false,updateCondition3 = false,updateCondition4 = false,updateCondition5 = false, updateCondition6 = false;
//    List<Boolean> callUpload = new ArrayList<>();

    public Update_Image(Context context,Activity activity){
        this.context = context;
        this.activity = activity;
    }
//////////////////////////////////////for file chooser
    public void updateConditionfunc(boolean bool,Uri filePath,String idx,String oldPath){
        updateCondition[0] = bool;
        this.filePath = filePath;
        this.idx = idx;
        this.oldPath = oldPath;

    }
    public void updateConditionfunc2(boolean bool,Uri filePath,String idx,String oldPath){
        updateCondition[1] = bool;
        this.filePath2 = filePath;
        this.idx = idx;
        this.oldPath2 = oldPath;
    }
    public void updateConditionfunc3(boolean bool,Uri filePath,String idx,String oldPath){
        updateCondition[2] = bool;
        this.filePath3 = filePath;
        this.idx = idx;
        this.oldPath3 = oldPath;
    }
    public void updateConditionfunc4(boolean bool,Uri filePath,String idx,String oldPath){
        updateCondition[3] = bool;
        this.filePath4 = filePath;
        this.idx = idx;
        this.oldPath4 = oldPath;
    }
    public void updateConditionfunc5(boolean bool,Uri filePath,String idx,String oldPath){
        updateCondition[4] = bool;
        this.filePath5 = filePath;
        this.idx = idx;
        this.oldPath5 = oldPath;
    }
    public void updateConditionfunc6(boolean bool,Uri filePath,String idx,String oldPath){
        updateCondition[5] = bool;
        this.filePath6 = filePath;
        this.idx = idx;
        this.oldPath6 = oldPath;
    }

    ///////////////////////////////////for camera

    public void updateConditionfunc(boolean bool,String imagePath1,String idx,String oldPath){
        updateCondition[0] = bool;
        this.imagePath1 = imagePath1;
        this.idx = idx;
        this.oldPath = oldPath;

    }
    public void updateConditionfunc2(boolean bool,String imagePath2,String idx,String oldPath){
        updateCondition[1] = bool;
        this.imagePath2 = imagePath2;
        this.idx = idx;
        this.oldPath2 = oldPath;
    }
    public void updateConditionfunc3(boolean bool,String imagePath3,String idx,String oldPath){
        updateCondition[2] = bool;
        this.imagePath3 = imagePath3;
        this.idx = idx;
        this.oldPath3 = oldPath;
    }
    public void updateConditionfunc4(boolean bool,String imagePath4,String idx,String oldPath){
        updateCondition[3] = bool;
        this.imagePath4 = imagePath4;
        this.idx = idx;
        this.oldPath4 = oldPath;
    }
    public void updateConditionfunc5(boolean bool,String imagePath5,String idx,String oldPath){
        updateCondition[4] = bool;
        this.imagePath5 = imagePath5;
        this.idx = idx;
        this.oldPath5 = oldPath;
    }
    public void updateConditionfunc6(boolean bool,String imagePath6,String idx,String oldPath){
        updateCondition[5] = bool;
        this.imagePath6 = imagePath6;
        this.idx = idx;
        this.oldPath6 = oldPath;
    }



    public String getPath(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        //Cursor cursor = getContentResolver().query(uri, null, null);

        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        //WARNING...................................................................................
        cursor = context.getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    public String convertMediaUriToPath(Uri uri) {
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj,  null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }
//////////////////////////////for file chooser
    public void uploadMultipartFileChooser() {
        //Uploading code
        try {
//            String uploadId = UUID.randomUUID().toString();
            if (updateCondition[0] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(Commons.getPath(filePath,context), "image") //Adding file
                        .addParameter("column", "url") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[0] = false;
                Log.d("UpdateCondition :","0");
            }
            if (updateCondition[1] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(Commons.getPath(filePath2,context), "image") //Adding file
                        .addParameter("column", "url2") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath2) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[1] = false;
                Log.d("UpdateCondition :","1");
            }
            if (updateCondition[2] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(Commons.getPath(filePath3,context), "image") //Adding file
                        .addParameter("column", "url3") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath3) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[2] = false;
                Log.d("UpdateCondition :","2");
            }
            if (updateCondition[3] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(Commons.getPath(filePath4,context), "image") //Adding file
                        .addParameter("column", "url4") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath4) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[3] = false;
                Log.d("UpdateCondition :","3");
            }
            if (updateCondition[4] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(Commons.getPath(filePath5,context), "image") //Adding file
                        .addParameter("column", "url5") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath5) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[4] = false;
                Log.d("UpdateCondition :","4");
            }
            if (updateCondition[5] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(Commons.getPath(filePath6,context), "image") //Adding file
                        .addParameter("column", "url6") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath6) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[5] = false;
                Log.d("UpdateCondition :","5");
            }
            if (updateConditionPdf == true){
                uploadMultipartPdf();
                Log.d("Upload PDF :","SUCCESS");
            }
//            Toast.makeText(context,String.valueOf(updateCondition) , Toast.LENGTH_LONG).show();

        } catch (Exception exc) {
            Toast.makeText(context, exc.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Cek Upload Multipart :",exc.toString());
        }
    }
////////////////////////////////for camera
    public void uploadMultipartCamera() {
        //Uploading code
        try {
//            String uploadId = UUID.randomUUID().toString();
            if (updateCondition[0] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(imagePath1, "image") //Adding file
                        .addParameter("column", "url") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[0] = false;
                Log.d("UpdateCondition :","0");
            }
            if (updateCondition[1] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(imagePath2, "image") //Adding file
                        .addParameter("column", "url2") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath2) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[1] = false;
                Log.d("UpdateCondition :","1");
            }
            if (updateCondition[2] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(imagePath3, "image") //Adding file
                        .addParameter("column", "url3") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath3) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[2] = false;
                Log.d("UpdateCondition :","2");
            }
            if (updateCondition[3] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(imagePath4, "image") //Adding file
                        .addParameter("column", "url4") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath4) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[3] = false;
                Log.d("UpdateCondition :","3");
            }
            if (updateCondition[4] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(imagePath5, "image") //Adding file
                        .addParameter("column", "url5") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath5) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[4] = false;
                Log.d("UpdateCondition :","4");
            }
            if (updateCondition[5] == true) {
                //Creating a multi part request
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL)
                        .addFileToUpload(imagePath6, "image") //Adding file
                        .addParameter("column", "url6") //Adding text parameter to the request
                        .addParameter("oldPath", oldPath6) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateCondition[5] = false;
                Log.d("UpdateCondition :","5");
            }
//            Toast.makeText(context,String.valueOf(updateCondition) , Toast.LENGTH_LONG).show();
            Log.d("CekUpload :","SUCCESS");
            if (updateConditionPdf == true){
                uploadMultipartPdf();
                Log.d("Upload PDF :","SUCCESS");
            }

        } catch (Exception exc) {
            Toast.makeText(context, exc.toString(), Toast.LENGTH_LONG).show();
            Log.d("CekUpload MultiPart :",exc.toString());
        }
    }

//    public void callUpload(){
//        Log.d("List :",String.valueOf(callUpload));
//        for (Boolean i : callUpload){
//            uploadMultipart();
//            Log.d("Size :",String.valueOf(callUpload.size()));
//            Log.d("Index :",String.valueOf(callUpload.size()));
//        }
//        callUpload.clear();
//    }

    public void updateConditionFuncPdf(boolean bool,String pdfPath,String idx,String oldPath){
        updateConditionPdf = bool;
//        this.filePathPdf = filePathPdf;
        this.pdfPath = pdfPath;
        this.idx = idx;
        this.oldPathPdf = oldPath;
    }

    public void uploadMultipartPdf(){
        try {
//            String uploadId = UUID.randomUUID().toString();
                //Creating a multi part request
            Log.d("pdf PATH :",pdfPath);
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(context, uploadId, UPLOAD_URL2)
                        .addFileToUpload(pdfPath, "pdf") //Adding file
//                        .addFileToUpload(pdfPath, "pdf") //Adding file
                        .addParameter("column", "pdf_url") //Adding text parameter to the request
                        .addParameter("oldPath", oldPathPdf) //Adding text parameter to the request
                        .addParameter("idx", idx) //Adding text parameter to the request


                        .setMaxRetries(5)
                        .setUsesFixedLengthStreamingMode(false)
                        .startUpload(); //Starting the upload
//            Toast.makeText(context, getPath(filePath), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                updateConditionPdf = false;
                Log.d("UpdateConditionPdf :","Yes");
            } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("UpdateConditionPdf :",e.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("UpdateConditionPdf :",e.toString());
        }
    }

}
