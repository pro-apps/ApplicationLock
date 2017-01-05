package com.speedata.applicationlock.manager.adapter;

import android.content.Context;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.ToolsCommon;

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
 * <p>
 * 创   建:Reginer in  2017/1/4 12:33.
 * 联系方式:QQ:282921012
 * 功能描述:白名单配置Adapter
 */

public class WhiteListAdapter extends CommonRvAdapter<AppInfo> {
    private Context mContext;

    public WhiteListAdapter(Context context, int layoutResId, List<AppInfo> data) {
        super(context, layoutResId, data);
        mContext = context;
    }

    @Override
    public void convert(BaseAdapterHelper helper, AppInfo item, int position) {
        helper.setImageDrawable(R.id.app_icon, ToolsCommon.getAppIcon(mContext, item.getAppPkg(), item.getActName()));
        helper.setText(R.id.app_name, item.getAppLabel());
        helper.setText(R.id.app_size, item.getAppPkg());
        helper.setChecked(R.id.hide, item.isWhiteList());
    }
}
