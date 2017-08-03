package com.xl.undercover.custom.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lijiyuan on 2017/4/10.
 */

public class GridLinearLayoutSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public GridLinearLayoutSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        //由于每行都只有2个，所以第一个都是2的倍数，把左边距设为0
        int position = parent.getChildLayoutPosition(view);
        if(position == 0 || position == 1){
            outRect.top = space;
        }
        if(position % 2 == 0) {
            outRect.left = space / 2;
        }else {
            outRect.right = space / 2;
        }
    }
}
