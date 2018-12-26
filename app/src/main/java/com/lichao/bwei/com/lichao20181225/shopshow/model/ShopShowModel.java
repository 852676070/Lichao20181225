package com.lichao.bwei.com.lichao20181225.shopshow.model;

import com.lichao.bwei.com.lichao20181225.retrofit.API;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;
import com.lichao.bwei.com.lichao20181225.retrofit.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public class ShopShowModel {
    public void getDate(final Model model){
        Retrofit build = new Retrofit.Builder().baseUrl(Constant.ShopShow_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = build.create(API.class);
        Call<ShopShowBean> showShop = api.getShowShop();
        showShop.enqueue(new Callback<ShopShowBean>() {
            @Override
            public void onResponse(Call<ShopShowBean> call, Response<ShopShowBean> response) {
                Observable.just(response.body()).subscribe(new Subscriber<ShopShowBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShopShowBean shopShowBean) {
                        model.getDateSuccess(shopShowBean);
                    }
                });
            }

            @Override
            public void onFailure(Call<ShopShowBean> call, Throwable t) {
                model.getDateFail(t.toString());
            }
        });
    }
}
