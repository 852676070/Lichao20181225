package com.lichao.bwei.com.lichao20181225.shopdetails.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lichao.bwei.com.lichao20181225.R;
import com.lichao.bwei.com.lichao20181225.shopdetails.adapter.ViewpagerAdapter;
import com.lichao.bwei.com.lichao20181225.shopdetails.bean.ShopDetailsBean;
import com.lichao.bwei.com.lichao20181225.shopdetails.model.ShopDetailsModel;
import com.lichao.bwei.com.lichao20181225.shopdetails.presenter.Presenter;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class ShopDetailsActivity extends AppCompatActivity implements com.lichao.bwei.com.lichao20181225.shopdetails.view.View, View.OnClickListener, PlatformActionListener {

    private Presenter mPresenter;
    private SimpleDraweeView userimage;
    private ViewPager detailsimage;
    private TextView detailsname;
    private TextView detailsprice;
    private Button detailsbuy;
    private TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        initView();
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        mPresenter = new Presenter(pid, this);
        mPresenter.getDate();

    }

    @Override
    public void getDateSuccess(ShopDetailsBean shopShowBean) {
        String images = shopShowBean.getData().getImages();
        final String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            String substring = split[i].substring(5, split[i].length());
            split[i] = "http" + substring;
        }
        for (int i = 0; i < split.length; i++) {
            Log.e("===", split[i]);
        }
        num.setText("1/"+split.length);
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(this, split);
        detailsimage.setAdapter(viewpagerAdapter);
        detailsimage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                num.setText((i+1)+"/"+split.length);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        userimage.setImageURI(Uri.parse(shopShowBean.getSeller().getIcon()));
        detailsname.setText(shopShowBean.getData().getTitle());
        detailsprice.setText("$" + shopShowBean.getData().getBargainPrice());
    }

    @Override
    public void getDateFail(String msg) {

    }

    private void initView() {
        userimage = (SimpleDraweeView) findViewById(R.id.userimage);
        detailsimage = (ViewPager) findViewById(R.id.detailsimage);
        detailsname = (TextView) findViewById(R.id.detailsname);
        detailsprice = (TextView) findViewById(R.id.detailsprice);
        detailsbuy = (Button) findViewById(R.id.detailsbuy);
        userimage.setOnClickListener(this);
        detailsbuy.setOnClickListener(this);
        num = (TextView) findViewById(R.id.num);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.userimage:
                //qq第三方
                Platform plat = ShareSDK.getPlatform(QQ.NAME);
                plat.removeAccount(true); //移除授权状态和本地缓存，下次授权会重新授权
                plat.SSOSetting(false); //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
                plat.setPlatformActionListener( this);//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                if (plat.isClientValid()) {
                    //判断是否存在授权凭条的客户端，true是有客户端，false是无
                }
                if (plat.isAuthValid()) {
//判断是否已经存在授权状态，可以根据自己的登录逻辑设置
                    Toast.makeText(this, "已经授权过了", Toast.LENGTH_SHORT).show();
                    return;
                }
//plat.authorize();	//要功能，不要数据
                plat.showUser(null);    //要数据不要功能，主要体现在不会重复出现授权界面
                break;

            case R.id.detailsbuy:
                //qq分享
                // Log.e("yml","fenxiang");
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // title标题，微信、QQ和QQ空间等平台使用
                oks.setTitle(getString(R.string.app_name));
                // titleUrl QQ和QQ空间跳转链接
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("商品");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url在微信、微博，Facebook等平台中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网使用
                oks.setComment("我是测试评论文本");
                // 启动分享GUI
                oks.show(this);
                break;
        }
    }


    @Override
    public void onComplete(Platform platform, int i, final HashMap<String, Object> hashMap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userimage.setImageURI(Uri.parse((String)hashMap.get("figureurl_qq_1")));
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
