package com.wou.commonutils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ListViewInScroll extends ListView {

    public ListViewInScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ListViewInScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewInScroll(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
