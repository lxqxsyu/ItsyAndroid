package com.guohe.ltsyandroid.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guohe.ltsyandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水寒 on 2017/7/21.
 */

public class PhotoExifAdapter extends RecyclerView.Adapter<PhotoExifAdapter.PhotoExifViewHolder>{

    private Context mContext;
    private List<String> mPhotoExifs = new ArrayList<>();

    public PhotoExifAdapter(Context context, List<String> photoExifs){
        mContext = context;
        mPhotoExifs.addAll(photoExifs);
    }

    @Override
    public PhotoExifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoExifViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_photo_exif_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoExifViewHolder holder, int position) {
        String exif = mPhotoExifs.get(position);
        String[] exifKeyValue = exif.split("#");
        holder.mExifKey.setText(String.valueOf(exifKeyValue[0]));
        if(exifKeyValue[1] == null || "null".equals(exifKeyValue[1])){
            holder.mExifValue.setText("未获取");
        }else {
            holder.mExifValue.setText(String.valueOf(exifKeyValue[1]));
        }
    }

    @Override
    public int getItemCount() {
        return mPhotoExifs.size();
    }

    static class PhotoExifViewHolder extends RecyclerView.ViewHolder{
        private TextView mExifKey;
        private TextView mExifValue;
        public PhotoExifViewHolder(View itemView) {
            super(itemView);
            mExifKey = (TextView) itemView.findViewById(R.id.photo_exif_key);
            mExifValue = (TextView) itemView.findViewById(R.id.photo_exif_value);
        }
    }
}
