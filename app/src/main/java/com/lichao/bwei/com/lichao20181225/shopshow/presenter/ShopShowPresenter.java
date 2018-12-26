package com.lichao.bwei.com.lichao20181225.shopshow.presenter;

import com.lichao.bwei.com.lichao20181225.shopshow.view.MainActivity;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;
import com.lichao.bwei.com.lichao20181225.shopshow.model.Model;
import com.lichao.bwei.com.lichao20181225.shopshow.model.ShopShowModel;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public class ShopShowPresenter {
    private MainActivity mMainActivity;
    private ShopShowModel mShopShowModel;

    public ShopShowPresenter(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        mShopShowModel = new ShopShowModel();
    }
    public void getDate(){
        mShopShowModel.getDate(new Model() {
            @Override
            public void getDateSuccess(ShopShowBean shopShowBean) {
                mMainActivity.getDateSuccess(shopShowBean);
            }

            @Override
            public void getDateFail(String msg) {
                mMainActivity.getDateFail(msg);
            }
        });
    }
}
