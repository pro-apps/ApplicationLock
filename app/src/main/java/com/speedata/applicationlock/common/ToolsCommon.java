package com.speedata.applicationlock.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.utils.ToolToast;

import java.util.ArrayList;
import java.util.List;

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
 * @author Reginer on  2016/12/14 16:45.
 *         Description:方法
 */
public class ToolsCommon {

    /**
     * 启动app
     *
     * @param mAppList 已安装app列表
     * @param position 点击位置
     * @param context  上下文
     */
    public static void startApp(List<AppInfo> mAppList, int position, Context context) {
        try {
            AppInfo info = mAppList.get(position);
            String pkg = info.getAppPkg();
            String cls = info.getActName();
            ComponentName mComponentName = new ComponentName(pkg, cls);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(mComponentName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            ToolToast.toastShort(context.getString(R.string.app_error));
        }

    }

    /**
     * 获取app图标
     *
     * @param context context
     * @param pkgName pkgName
     * @param actName actName
     * @return icon
     */
    public static Drawable getAppIcon(Context context, String pkgName, String actName) {
        ComponentName mComponentName = new ComponentName(pkgName, actName);
        try {
            return context.getPackageManager().getActivityIcon(mComponentName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取app列表
     *
     * @param context 上下文
     * @return 安装app列表
     */
    public static List<AppInfo> getAllAppList(Context context) {
        List<AppInfo> mAllAppList = new ArrayList<>();
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> mAppList = context.getPackageManager().queryIntentActivities(mIntent, 0);
        for (int i = 0; i < mAppList.size(); i++) {
            mAllAppList.add(new AppInfo(mAppList.get(i).activityInfo.name,
                    mAppList.get(i).activityInfo.packageName,
                    mAppList.get(i).activityInfo.loadLabel(context.getPackageManager()).toString(), false));
        }

        return mAllAppList;
    }
}