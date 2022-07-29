package com.mediumsitompul.querydatasales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

import static java.lang.String.valueOf;


public class DataSales_ProductAdapter extends RecyclerView.Adapter<DataSales_ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<DataSales_Product> productList;
    private Context context;
    private Bundle bundle;
    private String lat;
    private String lng;


    public DataSales_ProductAdapter(Context mCtx, List<DataSales_Product> productList, Bundle bundle) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.bundle = bundle;
    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.datasales_design_view, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final DataSales_Product product = productList.get(position);

        //loading the image

//        holder.textView_cust_name.setText(product.getCust_name());
//        holder.textView_packet_indihome.setText(product.getPacket_indihome());
//        holder.textView_inst_addr.setText(valueOf(product.getInst_addr()));
//        holder.textView_hp.setText(valueOf(product.getHp()));
//        holder.textView_idx.setText(String.valueOf(product.getId()));

        holder.textView_cust_name.setText(product.getCust_name());
        holder.textView_packet_indihome.setText(product.getPacket_indihome());
        holder.textView_inst_addr.setText(valueOf(product.getInst_addr()));
        holder.textView_hp.setText(valueOf(product.getHp()));
        holder.textView_idx.setText(String.valueOf(product.getId()));

        lat = product.getLat();
        lng = product.getLng();


        //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        holder.updatePageBtn.setOnClickListener(new View.OnClickListener(){//


                                                   @Override
                                                   public void onClick(View v) {
                                                       System.out.println(product.getId());
                                                       System.out.println("Halaman1");
                                                       context = v.getContext();
                                                       Intent intent = new Intent(context, DataProvisioning_Result.class);
                                                       intent.putExtra("parse_idx", product.getId());
                                                       intent.putExtra("userid",bundle.getString("parse_userid"));
                                                       intent.putExtra("imei",bundle.getString("parse_imei"));
                                                       context.startActivity(intent);
                                                   }
                                               }
        //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        );

        holder.directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNavigation();
            }
        });



        try {
            Glide.with(mCtx)
                    .load(product.getUrl())
                    .into(holder.imageView0);
        } catch (Exception e) {
            e.printStackTrace();
            BitmapDrawable bitmapDrawable =(BitmapDrawable) mCtx.getResources().getDrawable(R.mipmap.noimage);
            Bitmap bitmap = bitmapDrawable.getBitmap();
            holder.imageView0.setImageBitmap(bitmap);
        }
//        Glide.with(mCtx)
//                .load(product.getUrl())
//                .into(holder.imageView0);

    }

    private void goToNavigation(){
        Uri uri = Uri.parse("google.navigation:q="+lat+","+lng);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        mCtx.startActivity(intent);

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textView_cust_name;
        TextView textView_packet_indihome;
        TextView textView_inst_addr;
        TextView textView_hp;
        TextView textView_idx;
        Button directionBtn;
        Button updatePageBtn;
        ImageView imageView0;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imageView0 = itemView.findViewById(R.id.imageView1);
            textView_cust_name = itemView.findViewById(R.id.textView_cust_name);
            textView_packet_indihome = itemView.findViewById(R.id.textView_packet_indihome);
            textView_inst_addr = itemView.findViewById(R.id.textView_inst_addr);
            textView_hp = itemView.findViewById(R.id.textView_hp);
            textView_idx = itemView.findViewById(R.id.textView_idx);
            directionBtn = itemView.findViewById(R.id.directionBtn);
            updatePageBtn = itemView.findViewById(R.id.updatePageBtn);

        }
    }
}

