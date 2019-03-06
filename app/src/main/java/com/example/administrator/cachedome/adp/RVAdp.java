package com.example.administrator.cachedome.adp;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.cachedome.R;

import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class RVAdp extends BaseQuickAdapter<String, BaseViewHolder> {

    public RVAdp(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public RVAdp(int item_recyclerview) {
        super(item_recyclerview);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Log.d("zdl", "=======适配器中的=========" + item);
        helper.setText(R.id.text_view, item);
    }
}
