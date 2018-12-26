package com.lichao.bwei.com.lichao20181225.shopshow.model;

import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public interface Model {
    void getDateSuccess(ShopShowBean shopShowBean);
    void getDateFail(String msg);
}
