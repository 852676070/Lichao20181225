package com.lichao.bwei.com.lichao20181225.shopshow.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lichao.bwei.com.lichao20181225.R;
import com.lichao.bwei.com.lichao20181225.shopshow.bean.ShopShowBean;

import java.util.ArrayList;

/**
 * date:2018/12/25
 * author:李超(li)
 * function:
 */
public class ShopShowAdapter extends XRecyclerView.Adapter<ShopShowAdapter.HolderView> {

    private final Context mContext;
    private final ArrayList<ShopShowBean.DataBean> mDataBeans;

    public ShopShowAdapter(Context context){
        mContext = context;
        mDataBeans = new ArrayList<>();
    }
    public void setDate(ArrayList<ShopShowBean.DataBean> mDataBeans){
        this.mDataBeans.clear();
        this.mDataBeans.addAll(mDataBeans);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ShopShowAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.shopshowitem, null);
        return new HolderView(inflate);
    }
    public static void loadFile(final SimpleDraweeView draweeView, String filePath, final int reqWidth, final int reqHeight) {
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_FILE_SCHEME)
                .path(filePath)
                .build();
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setRotationOptions(RotationOptions.autoRotate())
                .setLocalThumbnailPreviewsEnabled(true)
                .setResizeOptions(new ResizeOptions(reqWidth, reqHeight))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                        if (imageInfo == null) {
                            return;
                        }

                        ViewGroup.LayoutParams vp = draweeView.getLayoutParams();
                        vp.width = reqWidth;
                        vp.height = reqHeight;
                        draweeView.requestLayout();
                    }
                })
                .build();
        draweeView.setController(controller);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopShowAdapter.HolderView holder, final int position) {
        String images = mDataBeans.get(position).getImages();
        if (images.contains("|")){
        String substring = images.substring(5, images.length() - 1);
        images = "http"+substring;
        Log.e("+++",images);}
        holder.mShopshowsim.setImageURI(Uri.parse(images.split("\\|")[0]));
        holder.mShopname.setText(mDataBeans.get(position).getTitle());
        holder.mShopprice.setText("$"+mDataBeans.get(position).getPrice());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mGetPosition.getPid(mDataBeans.get(position).getPid());
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetPosition.get(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }
    public void del(final View view , final int position){

        final float f = view.getX();
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",0,500);
        animator.setDuration(1000);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mDataBeans.remove(position);
                notifyDataSetChanged();
                view.setX(f);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });}
    public class HolderView extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mShopshowsim;
        private final TextView mShopname;
        private final TextView mShopprice;

        public HolderView(View itemView) {
            super(itemView);
            mShopshowsim = itemView.findViewById(R.id.shopshowsim);
            mShopname = itemView.findViewById(R.id.shopname);
            mShopprice = itemView.findViewById(R.id.shopprice);
            loadFile(mShopshowsim, Environment.getExternalStorageDirectory().getPath()+"/123.jpg",120,120);

        }
    }
    public interface getPosition{
        void get(View v,int position);
        void getPid(int position);
    }
    private getPosition mGetPosition;
    public void getDate(getPosition getPosition){
        mGetPosition = getPosition;
    }
}
