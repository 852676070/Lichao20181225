package com.lichao.bwei.com.lichao20181225.shopshow.view;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public interface View <ShopShowBean>{
    void getDateSuccess(ShopShowBean shopShowBean);
    void getDateFail(String msg);

}
