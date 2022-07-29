package com.mediumsitompul.querydatasales.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.mediumsitompul.querydatasales.DataProvisioning_Result;
import com.mediumsitompul.querydatasales.R;
import com.mediumsitompul.querydatasales.apihelper.BaseApiService;
import com.mediumsitompul.querydatasales.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogProvisioning_adapter extends RecyclerView.Adapter<LogProvisioning_adapter.LogProvisioningViewHolder> {

    private Context ctx;
    private List<LogProvisioning_getset> logProvisioning_list;
    BaseApiService mApiService;
    ProgressDialog progressDialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String id;
    String userid;
    String imei;
    Intent intent;

    public LogProvisioning_adapter(Context ctx, List<LogProvisioning_getset> logProvisioning_list) {
        this.ctx = ctx;
        this.logProvisioning_list = logProvisioning_list;
        intent = new Intent(ctx, DataProvisioning_Result.class);
    }

    @NonNull
    @Override
    public LogProvisioningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.log_provisioning_design,null);
        return new LogProvisioningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LogProvisioningViewHolder holder, int position) {
        final LogProvisioning_getset logProvisioning_getset = logProvisioning_list.get(position);
        mApiService = UtilsApi.getAPIService();
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Data LogProvisioning");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);

        pref = ctx.getSharedPreferences("MyPref",0);
        userid = pref.getString("userid",null);
        imei = pref.getString("imei",null);

        holder.txtLogProvisioningId.setText(logProvisioning_getset.getId());
        holder.txtLogProvisioningTimestamp.setText(logProvisioning_getset.getTimestamp());
        holder.txtLogProvisioningCustName.setText(logProvisioning_getset.getSf_name());
        holder.txtLogProvisioningCustAddr.setText(logProvisioning_getset.getSf_num());
        holder.txtLogProvisioningStatus.setText(logProvisioning_getset.getStatus());

        holder.idClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = holder.txtLogProvisioningId.getText().toString();

//                maps_dialogInteraction.customDialog();
//                processDB(idx,newAction,column);
//                Toast.makeText(ctx, "BERHASIL DI PROCESS", Toast.LENGTH_SHORT).show();
                showUpdatedeleteDialog();
//                ctx.startActivity(intent);
            }
        });
        holder.timestampClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = holder.txtLogProvisioningId.getText().toString();
                showUpdatedeleteDialog();
            }
        });
        holder.custnameClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = holder.txtLogProvisioningId.getText().toString();
                showUpdatedeleteDialog();
            }
        });
        holder.custaddrClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = holder.txtLogProvisioningId.getText().toString();
//                Toast.makeText(ctx, "BERHASIL DI PROCESS", Toast.LENGTH_SHORT).show();
                showUpdatedeleteDialog();
            }
        });
        holder.statusClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = holder.txtLogProvisioningId.getText().toString();
                showUpdatedeleteDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        return logProvisioning_list.size();
    }

    public class LogProvisioningViewHolder extends RecyclerView.ViewHolder {

        TextView txtLogProvisioningId;
        TextView txtLogProvisioningTimestamp;
        TextView txtLogProvisioningCustName;
        TextView txtLogProvisioningCustAddr;
        TextView txtLogProvisioningStatus;
        CardView idClick;
        CardView timestampClick;
        CardView custnameClick;
        CardView custaddrClick;
        CardView statusClick;

        public LogProvisioningViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtLogProvisioningId = itemView.findViewById(R.id.txtLogProvisioningId);
            this.txtLogProvisioningTimestamp = itemView.findViewById(R.id.txtLogProvisioningTimestamp);
            this.txtLogProvisioningCustName = itemView.findViewById(R.id.txtLogProvisioningCustName);
            this.txtLogProvisioningCustAddr = itemView.findViewById(R.id.txtLogProvisioningCustAddr);
            this.txtLogProvisioningStatus = itemView.findViewById(R.id.txtLogProvisioningStatus);
            this.idClick = itemView.findViewById(R.id.idClick);
            this.timestampClick = itemView.findViewById(R.id.timestampClick);
            this.custnameClick = itemView.findViewById(R.id.custnameClick);
            this.custaddrClick = itemView.findViewById(R.id.custaddrClick);
            this.statusClick = itemView.findViewById(R.id.statusClick);
        }
    }

    public void showUpdatedeleteDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setTitle("Change Data");
        alertDialogBuilder.setMessage("Apakah Anda Yakin Ingin Mengubah(Update) Atau Menghapus(Delete) Data ?");
        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showSureDialog();
            }
        });
        alertDialogBuilder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showSureDialog2();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    public void showSureDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setTitle("Peringatan");
        alertDialogBuilder.setMessage("Apakah anda yakin dengan pilihan anda ?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                processDB(idx,newAction,column);
//                Toast.makeText(ctx, "BERHASIL DI PROCESS", Toast.LENGTH_SHORT).show();
                progressDialog.show();
                deleteDataCollections(id);
//                Toast.makeText(ctx, idx, Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "PROCESS DI BATALKAN", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }
    public void showSureDialog2(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setTitle("Peringatan");
        alertDialogBuilder.setMessage("Apakah anda yakin dengan pilihan anda ?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(ctx, "BERHASIL DI PROCESS", Toast.LENGTH_SHORT).show();
                progressDialog.show();
//                updateDataCollections(id);
                intent.putExtra("parse_idx", Integer.valueOf(id));
                intent.putExtra("userid",userid);
                intent.putExtra("imei",imei);
//                Toast.makeText(ctx, id, Toast.LENGTH_SHORT).show();
                ctx.startActivity(intent);
                progressDialog.dismiss();
//                updateIntent(idx);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "PROCESS DI BATALKAN", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }
    ////////////Intent to Update_SF//////////////////////
//    private void updateIntent(String idx){
//        Intent intent = new Intent(ctx, Maps_SF_Activity.class);
//        intent.putExtra("idx",idx);
//        ctx.startActivity(intent);
//    }

//    public void updateDataCollections(final String id) {
//        mApiService.updateLogProvisioning(id)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()){
//                            progressDialog.dismiss();
//                            try {
//                                JSONArray jsonArray = new JSONArray(response.body().string());
//                                Toast.makeText(ctx, "Berhasil Dimuat", Toast.LENGTH_SHORT).show();
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject jsonRESULTS = jsonArray.getJSONObject(i);
//                                    if (jsonRESULTS.getString("error").equals("false")) {
//                                        String id = jsonRESULTS.getJSONObject("data").getString("id");
//                                        String timestamp = jsonRESULTS.getJSONObject("data").getString("timestamp");
//                                        String sf_name = jsonRESULTS.getJSONObject("data").getString("service_number");
//                                        String sf_num = jsonRESULTS.getJSONObject("data").getString("cust_name");
//                                        String status = jsonRESULTS.getJSONObject("data").getString("status");
//
//                                        intent.putExtra("log_provisioning", (Parcelable) new LogProvisioning_getset(id,timestamp,sf_name
//                                                ,sf_num,status));
//
//                                        ctx.startActivity(intent);
//
//                                    } else {
//                                        String error_message = jsonRESULTS.getString("Data tidak ditemukan");
//                                        Toast.makeText(ctx, error_message, Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Toast.makeText(ctx, e.toString(), Toast.LENGTH_SHORT).show();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                Toast.makeText(ctx, e.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            progressDialog.dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(ctx, t.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


    public void deleteDataCollections(final String id) {
        mApiService.deleteLogProvisioning(id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")) {
                                    Toast.makeText(ctx, "DATA BERHASIL DI HAPUS", Toast.LENGTH_SHORT).show();
//                                    notifyDataSetChanged();

                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(ctx, error_message, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        progressDialog.dismiss();
                    }
                });
    }
}
