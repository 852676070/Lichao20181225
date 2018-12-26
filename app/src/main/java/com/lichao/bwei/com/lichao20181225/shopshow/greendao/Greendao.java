package com.lichao.bwei.com.lichao20181225.shopshow.greendao;

import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
@Entity
public class Greendao {

    @Id(autoincrement = true)
    private Long _id;
    private String image;
    private String name;
    private int price;
    @Generated(hash = 871146437)
    public Greendao(Long _id, String image, String name, int price) {
        this._id = _id;
        this.image = image;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 1534966979)
    public Greendao() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}