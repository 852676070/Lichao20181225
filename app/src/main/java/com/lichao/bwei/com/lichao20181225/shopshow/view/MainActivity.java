package com.lichao.bwei.com.lichao20181225.shopshow.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lichao.bwei.com.lichao20181225.R;
import com.lichao.bwei.com.lichao20181225.app.App;
import com.lichao.bwei.com.lichao20181225.map.MapActivity;
import com.lichao.bwei.com.lichao20181225.shopdetails.view.ShopDetailsActivity;
import com.lichao.bwei.com.lichao20181225.shopshow.adapter.ShopShowAdapter;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;
import com.lichao.bwei.com.lichao20181225.shopshow.greendao.Greendao;
import com.lichao.bwei.com.lichao20181225.shopshow.presenter.ShopShowPresenter;
import com.ping.greendao.gen.GreendaoDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, com.lichao.bwei.com.lichao20181225.shopshow.view.View<ShopShowBean> {

    private Button Show_map;
    private ShopShowPresenter mShopShowPresenter;
    private ShopShowAdapter mShopShowAdapter;
    private XRecyclerView shopshow_xrecyclerview;
    private GreendaoDao mGreendaoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mGreendaoDao = App.instance.getSession().getGreendaoDao();
        mShopShowAdapter = new ShopShowAdapter(this);
        mShopShowPresenter = new ShopShowPresenter(this);
        mShopShowPresenter.getDate();
        shopshow_xrecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        shopshow_xrecyclerview.setAdapter(mShopShowAdapter);
        mShopShowAdapter.getDate(new ShopShowAdapter.getPosition() {
            @Override
            public void get(View v, int position) {
                mShopShowAdapter.del(v,position);
            }

            @Override
            public void getPid(int position) {
                Intent intent = new Intent(MainActivity.this, ShopDetailsActivity.class);
                intent.putExtra("pid",position);
                startActivity(intent);
            }
        });
    }



    private void initView() {
        Show_map = (Button) findViewById(R.id.Show_map);

        Show_map.setOnClickListener(this);
        shopshow_xrecyclerview = (XRecyclerView) findViewById(R.id.shopshow_xrecyclerview);
        shopshow_xrecyclerview.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (mGreendaoDao.loadAll()!=null){
            mGreendaoDao.deleteAll();
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Show_map:
                startActivity(new Intent(this, MapActivity.class));
                break;
        }
    }

    @Override
    public void getDateSuccess(ShopShowBean shopShowBean) {
        for (int i = 0; i < shopShowBean.getData().size(); i++) {
            Greendao greendao = new Greendao(null, shopShowBean.getData().get(i).getImages(), shopShowBean.getData().get(i).getTitle(),(int)(shopShowBean.getData().get(i).getPrice()));
            mGreendaoDao.insert(greendao);
        }
        mShopShowAdapter.setDate((ArrayList<ShopShowBean.DataBean>) shopShowBean.getData());
    }

    @Override
    public void getDateFail(String msg) {
        List<ShopShowBean.DataBean> list = new ArrayList<>();
        List<Greendao> greendaos = mGreendaoDao.loadAll();
        if (greendaos.size()!=0){
            for (int i = 0; i < greendaos.size(); i++) {
                list.add(new ShopShowBean.DataBean(greendaos.get(i).getImage(),greendaos.get(i).getPrice(),greendaos.get(i).getName()));
            }
            mShopShowAdapter.setDate((ArrayList<ShopShowBean.DataBean>) list);
        }

    }
}
