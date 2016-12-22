package com.speedata.applicationlock.hide;

import android.content.Context;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.bean.AppsBean;

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
 * @author Reginer on  2016/12/14 17:29.
 *         Description:设置隐藏应用Adapter
 */
class HideAppAdapter extends CommonRvAdapter<AppsBean> {
    private Context mContext;

    HideAppAdapter(Context context, int layoutResId, List<AppsBean> data) {
        super(context, layoutResId, data);
        mContext = context;
    }

    @Override
    public void convert(BaseAdapterHelper helper, AppsBean item, int position) {
        helper.setImageDrawable(R.id.app_icon, item.getResolveInfo().activityInfo.loadIcon(mContext.getPackageManager()));
        helper.setText(R.id.app_name, item.getResolveInfo().activityInfo.loadLabel(mContext.getPackageManager()).toString());
        helper.setText(R.id.app_size, item.getResolveInfo().activityInfo.packageName);
        helper.setChecked(R.id.hide,item.isCheck());
    }
}