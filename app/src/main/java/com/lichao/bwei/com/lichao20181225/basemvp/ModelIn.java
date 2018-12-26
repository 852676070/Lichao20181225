package com.lichao.bwei.com.lichao20181225.basemvp;

/**
 * date:2018/12/26
 * author:李超(li)
 * function:
 */
public interface ModelIn<T> {
    void getDateSuccess(T shopShowBean);
    void getDateFail(String msg);
}
