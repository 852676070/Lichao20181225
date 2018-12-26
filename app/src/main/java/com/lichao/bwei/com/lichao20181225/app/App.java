package com.lichao.bwei.com.lichao20181225.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.mob.MobSDK;
import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public class App extends Application {
    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static App instance;
    @Override
    public void onCreate() {

        super.onCreate();
        JPushInterface.setDebugMode(true);  // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        MobSDK.init(this);
        Fresco.initialize(this, ImagePipelineConfig.newBuilder(App.this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                //设置加载的图片缓存到哪里
                                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory()+"fresco"))
                                .build()
                )
                .build()
        );

        init();
        initDatabass();
    }

    public static App getInstance() {
        return instance;
    }

    private void initDatabass() {
        //这里之后会修改，关于升级数据库
        dbHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = dbHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    private void init() {
        instance = this;
    }
}