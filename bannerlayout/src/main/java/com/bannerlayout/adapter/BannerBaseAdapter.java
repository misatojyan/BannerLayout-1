package com.bannerlayout.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * by y on 2016/10/24.
 */

public abstract class BannerBaseAdapter extends PagerAdapter {

    public int error_image = -1;

    public int place_image = -1;

    public void setImageClickListener(OnBannerImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public void setErrorImage(int error_image) {
        this.error_image = error_image;
    }

    public void setPlaceImage(int place_image) {
        this.place_image = place_image;
    }

    private OnBannerImageClickListener imageClickListener = null;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView img = new ImageView(container.getContext());
        container.addView(getView(img, position));
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageClickListener != null) {
                    imageClickListener.onBannerClick(getPosition(position));
                }
            }
        });
        return img;
    }


    public void imageLoader(Context context, Object url, ImageView imageView) {
        if (place_image == -1 || error_image == -1) {
            throw new NullPointerException("glide placeImage or errorImage is null");
        }
        Glide.with(context).load(url).placeholder(place_image)
                .error(error_image).centerCrop().into(imageView);
    }

    public abstract ImageView getView(ImageView view, int position);

    public abstract int getPosition(int position);

    public interface OnBannerImageClickListener {
        void onBannerClick(int position);
    }
}
