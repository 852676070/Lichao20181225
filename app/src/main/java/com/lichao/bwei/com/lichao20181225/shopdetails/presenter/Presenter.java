package com.lichao.bwei.com.lichao20181225.shopdetails.presenter;

import com.lichao.bwei.com.lichao20181225.shopdetails.bean.ShopDetailsBean;
import com.lichao.bwei.com.lichao20181225.shopdetails.model.Model;
import com.lichao.bwei.com.lichao20181225.shopdetails.model.ShopDetailsModel;
import com.lichao.bwei.com.lichao20181225.shopdetails.view.ShopDetailsActivity;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */
public class Presenter {
    private ShopDetailsActivity mShopDetailsActivity;
    private ShopDetailsModel mShopDetailsModel;
    private int Pid;

    public Presenter(int pid,ShopDetailsActivity shopDetailsActivity) {
        Pid = pid;
        mShopDetailsActivity = shopDetailsActivity;
        mShopDetailsModel = new ShopDetailsModel();
    }
    public void getDate(){
        mShopDetailsModel.getDate(Pid, new Model() {
            @Override
            public void getDateSuccess(ShopDetailsBean shopDetailsBean) {
                mShopDetailsActivity.getDateSuccess(shopDetailsBean);
            }

            @Override
            public void getDateFail(String msg) {
                mShopDetailsActivity.getDateFail(msg);
            }
        });
    }
}
