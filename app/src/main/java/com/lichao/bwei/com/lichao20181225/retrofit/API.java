package com.lichao.bwei.com.lichao20181225.retrofit;

import com.lichao.bwei.com.lichao20181225.shopdetails.bean.ShopDetailsBean;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public interface API {
    @GET("searchProducts?keywords=笔记本&page=1")
    Call<ShopShowBean> getShowShop();

    @GET("getProductDetail")
    Call<ShopDetailsBean> getShowDetails(@Query("pid") int pid);
}
