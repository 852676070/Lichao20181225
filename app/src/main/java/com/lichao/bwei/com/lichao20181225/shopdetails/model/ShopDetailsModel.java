package com.lichao.bwei.com.lichao20181225.shopdetails.model;

import android.util.Log;

import com.lichao.bwei.com.lichao20181225.retrofit.API;
import com.lichao.bwei.com.lichao20181225.retrofit.Constant;
import com.lichao.bwei.com.lichao20181225.shopdetails.bean.ShopDetailsBean;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */
public class ShopDetailsModel {
   public void getDate(int pid, final Model model){
        Retrofit build = new Retrofit.Builder().baseUrl(Constant.ShopShow_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = build.create(API.class);
        Call<ShopDetailsBean> showDetails = api.getShowDetails(pid);
        showDetails.enqueue(new Callback<ShopDetailsBean>() {
            @Override
            public void onResponse(Call<ShopDetailsBean> call, Response<ShopDetailsBean> response) {

                model.getDateSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDetailsBean> call, Throwable t) {
                Log.e("+++", t.toString());
                model.getDateFail(t.toString());
            }
        });
    }
}
