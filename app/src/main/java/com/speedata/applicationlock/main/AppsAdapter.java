package com.speedata.applicationlock.main;

import android.content.Context;
import android.content.pm.ResolveInfo;

import com.speedata.applicationlock.R;

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
 *
 * @author Reginer on  2016/12/14 14:18.
 *         Description:
 */
class AppsAdapter extends CommonRvAdapter<ResolveInfo> {
    private Context mContext;

    AppsAdapter(Context context, int layoutResId, List<ResolveInfo> data) {
        super(context, layoutResId, data);
        mContext = context;
    }

    @Override
    public void convert(BaseAdapterHelper helper, ResolveInfo item, int position) {
        helper.setText(R.id.tv_name, item.activityInfo.loadLabel(mContext.getPackageManager()).toString());
        helper.setImageDrawable(R.id.iv_icon, item.activityInfo.loadIcon(mContext.getPackageManager()));
    }
}