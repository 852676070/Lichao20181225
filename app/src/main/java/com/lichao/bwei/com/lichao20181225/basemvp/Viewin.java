package com.lichao.bwei.com.lichao20181225.basemvp;

import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */
public interface Viewin<T> {
    void getDateSuccess(T shopShowBean);
    void getDateFail(String msg);
}
