package com.mediumsitompul.querydatasales;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mediumsitompul.querydatasales.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataSales_Result extends AppCompatActivity {


//String IP = "http://192.168.100.78/";
//String IP = "http://36.89.34.66/";
//String IP = "http://192.168.43.85/";
//String IP = "http://192.168.43.2/";




    public String name1="";
    public String address1="";
    public String nomor_sc1="";
    String tag_json_obj = "json_obj_req";

    private ListView listView;
    List<DataSales_Product>     productList;
    RecyclerView recyclerView;
//    Permission_Access_Second permissionAccessSecond;
    Bundle bbb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datasales_result_recyclerview);

        bbb = getIntent().getExtras();

        name1 = bbb.getString("parse_name");
        address1 = bbb.getString("parse_address");
        nomor_sc1 = bbb.getString("parse_nomorsc");

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        loadProducts();
//        permissionAccessSecond = new Permission_Access_Second(getApplicationContext(), DataSales_Result.this);
//        permissionAccessSecond.startPemission();

        System.out.printf("===================================  %s%n", name1);
        System.out.printf("===================================  %s%n", address1);



    }

    private void loadProducts() {

        //String URL_PRODUCTS = IP + "myapi/api_sales.php?cust_name='" + name1 + "'";
        //String URL_PRODUCTS = IP + "myapi/api_sales.php?inst_addr='" + address1 + "'";
        //String URL_PRODUCTS = IP + "myapi/api_sales.php?cust_name=" + name1;
        //String URL_PRODUCTS = IP + "myapi/api_sales.php?cust_name=" + "'" + name1 + "'"+ "and" + "inst_addr=" + "'"+ address1 + "'" ;
        //String URL_PRODUCTS = IP + "myapi/api_sales.php";
//        String URL_PRODUCTS = IP + "mytools/android/_api_sales.php";







        System.out.println(Maps_Constants.IP + "mytools/android/_api_sales.php");
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Maps_Constants.IP + "mytools/android/_api_sales.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            productList.clear(); //medium

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                //productList.clear();
                                JSONObject product = array.getJSONObject(i);



                                //adding the product to product list, sesuai urutan
                                productList.add(new DataSales_Product(
                                        product.getInt("idx"),
                                        product.getString("cust_name"),
                                        product.getString("packet_indihome"),
                                        product.getString("inst_addr"),
                                        product.getString("google_addr"),
                                        product.getString("hp"),
                                        Maps_Constants.IP + product.getString("url2"),
                                        product.getString("lat"),
                                        product.getString("lng")
                                ));


                                //System.out.println(IP + product.getString("url2"));
                            }

                            //System.out.println("===================================="+productList.toString());


                            //creating adapter object and setting it to recyclerview
                            //ProductAdapter adapter = new ProductAdapter(MainActivity.this, productList);
                            DataSales_ProductAdapter adapter = new DataSales_ProductAdapter(DataSales_Result.this, productList,bbb);
//                            Toast.makeText(DataSales_Result.this, Double.toString(adapter.getItemCount()), Toast.LENGTH_LONG).show();

                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(DataSales_Result.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error: ", error.getMessage());
                        Toast.makeText(DataSales_Result.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",name1);
                params.put("address",address1);
                params.put("nomor_sc",nomor_sc1);
                return params;
            }
        };

        //adding our stringrequest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }


}


