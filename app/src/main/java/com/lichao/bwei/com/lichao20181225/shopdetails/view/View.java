package com.lichao.bwei.com.lichao20181225.shopdetails.view;

import com.lichao.bwei.com.lichao20181225.basemvp.Viewin;
import com.lichao.bwei.com.lichao20181225.shopdetails.bean.ShopDetailsBean;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */
public interface View {
    void getDateSuccess(ShopDetailsBean shopDetailsBean);
    void getDateFail(String msg);

    void onComplete(Platform platform, int i, HashMap<String, Object> hashMap);
}
