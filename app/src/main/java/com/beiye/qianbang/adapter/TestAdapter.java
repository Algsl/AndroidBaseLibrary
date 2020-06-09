package com.beiye.qianbang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.android.baselibrary.adapter.ViewHolder;
import com.android.baselibrary.adapter.base.RVCommonAdapter;
import com.beiye.qianbang.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: STYL
 * Date: 2020/6/9
 * Time: 16:21
 */
public class TestAdapter extends RVCommonAdapter {


    public TestAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {

    }
}
