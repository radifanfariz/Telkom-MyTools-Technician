package com.mediumsitompul.querydatasales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.String.valueOf;


public class DataProvisioning_ProductAdapter extends RecyclerView.Adapter<DataProvisioning_ProductAdapter.  ProductViewHolder> {
//    String IP = "http://192.168.100.78/";
    //String IP = "http://36.89.34.66/";
//    String IP = "http://192.168.43.2/";
    //String IP = "http://192.168.43.85/";




    private Context mCtx;
    private List<DataProvisioning_Product> productList;
    private Context context;
    Bitmap mIcon11 = null;
    Bitmap mIcon22 = null;




    public DataProvisioning_ProductAdapter(Context mCtx, List<DataProvisioning_Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dataprovisioning_design_view, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final DataProvisioning_Product product = productList.get(position);

        //loading the image

        holder.textView_idx.setText(valueOf(product.getId()));
        holder.editText_cust_name.setText(product.getCust_name());
        holder.editText_cust_addr.setText(product.getCust_addr());
        holder.editText_inst_addr.setText(valueOf(product.getInst_addr()));

//        holder.textView_latitude_odo.setText(valueOf()));
//        holder.textView_longitude_odo.setText(valueOf()));

        holder.editText_packet_indihome.setText(product.getPacket_indihome());
        holder.editText_hp.setText(valueOf(product.getHp()));


        InputStream in = null;
        //InputStream in2 = null;



        try {
            //in = new java.net.URL("http://10.245.26.86/mytools/uploads/20190819_095415a.jpg").openStream();
            //in = new java.net.URL("{0}{1}", IP, product.getUrl()).openStream();
            in = new java.net.URL("{0}{1}", Maps_Constants.IP, product.getUrl()).openStream();


           // System.out.println(MessageFormat.format("{0}{1}", IP, product.getUrl()));



        } catch (IOException e) {
            e.printStackTrace();
        }
        mIcon11 = BitmapFactory.decodeStream(in);
        System.out.println(mIcon11.toString());
        holder.imageView_2.setImageBitmap(mIcon11);
        //holder.imageView_2.setImageURI(Uri.parse("http://10.245.26.86/mytools/uploads/20190819_095415a.jpg"));

        mIcon22 = BitmapFactory.decodeStream(in);
        System.out.println(mIcon22.toString());
        holder.imageView_2.setImageBitmap(mIcon22);
        //holder.imageView_2.setImageURI(Uri.parse("http://10.245.26.86/mytools/uploads/20190819_095415a.jpg"));








        //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        holder.textView_idx.setOnClickListener(new View.OnClickListener(){//


                                                   @Override
                                                   public void onClick(View v) {
                                                       System.out.println(product.getId());
                                                       System.out.println("Halaman1");
                                                       context = v.getContext();
                                                       Intent intent = new Intent(context, DataProvisioning_Result.class);
                                                       intent.putExtra("parse_idx", product.getId());
                                                       context.startActivity(intent);
                                                   }
                                               }
                //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

        );




        Glide.with(mCtx)
                .load(product.getUrl())
                .into(holder.imageView_2);

    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        EditText editText_witel;

        EditText editText_cust_name;
        EditText editText_cust_addr;
        EditText editText_inst_addr;
        EditText editText_stp_name;
        TextView textView_latitude_odp;
        TextView textView_longitude_odp;
        TextView textView_latitude_inst_addr;
        TextView textView_longitude_inst_addr;
        EditText editText_hp;
        EditText editText_email;
        EditText editText_packet_indihome;
        TextView textView_idx;
        ImageView imageView_2;




        public ProductViewHolder(View itemView) {
            super(itemView);
            textView_idx = itemView.findViewById(R.id.textView_idx);

            editText_witel = itemView.findViewById(R.id.editWitel);  //Rumus = ProductViewHolder & File XML

            editText_cust_name = itemView.findViewById(R.id.editCustomerName);  //Rumus = ProductViewHolder & File XML

            //ediText_cust_addr = itemView.findViewById(R.id.editCustomerAddress);
            editText_packet_indihome = itemView.findViewById(R.id.editPacketIndihome);
            editText_inst_addr = itemView.findViewById(R.id.editInstallationAddress);
            editText_stp_name = itemView.findViewById(R.id.editStpName);

            textView_latitude_odp = itemView.findViewById(R.id.textView_lat);
            textView_longitude_odp = itemView.findViewById(R.id.textView_lng);
            textView_latitude_inst_addr = itemView.findViewById(R.id.textView_lat2);
            textView_longitude_inst_addr = itemView.findViewById(R.id.textView_lng2);


            editText_hp = itemView.findViewById(R.id.editHp);
            editText_email = itemView.findViewById(R.id.editEmail);
            imageView_2 = itemView.findViewById(R.id.imageView2);
        }
    }
}


