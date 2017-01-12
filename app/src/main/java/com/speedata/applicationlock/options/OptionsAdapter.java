package com.speedata.applicationlock.options;

import android.content.Context;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.options.source.ItemBean;

import java.util.List;

import xyz.reginer.baseadapter.BaseAdapterHelper;
import xyz.reginer.baseadapter.CommonRvAdapter;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * 创   建:Reginer in  2017/1/12 15:56.
 * 联系方式:QQ:282921012
 * 功能描述:选项配置Adapter
 */
class OptionsAdapter extends CommonRvAdapter<ItemBean> {

    OptionsAdapter(Context context, int layoutResId, List<ItemBean> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void convert(BaseAdapterHelper helper, ItemBean item, int position) {
          helper.setText(R.id.tv_title,item.getTitle());
          helper.setText(R.id.tv_summary,item.getSummary());
    }
}
