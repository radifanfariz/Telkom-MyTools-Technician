package com.mediumsitompul.querydatasales.apihelper;

import com.mediumsitompul.querydatasales.Maps_Constants;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
//    public static final String BASE_URL_API = "http://192.168.43.247/login/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(Maps_Constants.IP+"mytools/android/").create(BaseApiService.class);
    }
}
