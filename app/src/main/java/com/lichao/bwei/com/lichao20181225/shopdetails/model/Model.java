package com.lichao.bwei.com.lichao20181225.shopdetails.model;

import com.lichao.bwei.com.lichao20181225.shopdetails.bean.ShopDetailsBean;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */
public interface Model {
    void getDateSuccess(ShopDetailsBean shopDetailsBean);
    void getDateFail(String msg);
}
