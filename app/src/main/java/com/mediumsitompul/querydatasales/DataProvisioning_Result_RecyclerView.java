package com.mediumsitompul.querydatasales;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class DataProvisioning_Result_RecyclerView extends AppCompatActivity {
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work

//    private static final String URL_PRODUCTS = "http://192.168.100.78/mytools/android/_api_provisioning.php";
    //private static final String URL_PRODUCTS = "http://36.89.34.66/mytools/_api_provisioning.php";
    //private static final String URL_PRODUCTS = "http://192.168.43.85/mytools/android/_api_provisioning.php";
    private static final String URL_PRODUCTS = "http://192.168.43.2/mytools/android/_api_provisioning.php";





    List<DataProvisioning_Product> productList;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datasales_result_recyclerview);



        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        List<DataProvisioning_Product> productList;

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();


    }





    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Maps_Constants.IP + "mytools/android/_api_provisioning.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);



                                //adding the product to product list
                                productList.add(new DataProvisioning_Product(
                                        product.getInt("idx"),
                                        product.getString("witel"),
                                        product.getString("cust_name"),
                                        product.getString("cust_addr"),
                                        product.getString("google_addr"),
                                        product.getString("inst_addr"),
                                        product.getString("stp_name"),
                                        product.getString("latitude_odp"),
                                        product.getString("longitude_odp"),
                                        product.getString("latitude_inst_addr"),
                                        product.getString("longitude_inst_addr"),
                                        product.getString("hp"),
                                        product.getString("email"),
                                        product.getString("packet_indihome"),
                                        product.getString("image")
                                ));
                            }


                            //creating adapter object and setting it to recyclerview
                            //ProductAdapter adapter = new ProductAdapter(MainActivity.this, productList);
                            DataProvisioning_ProductAdapter adapter = new DataProvisioning_ProductAdapter(DataProvisioning_Result_RecyclerView.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


}


