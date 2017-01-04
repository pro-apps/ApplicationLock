package com.speedata.applicationlock.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.speedata.applicationlock.R;
import com.speedata.applicationlock.bean.AppChanged;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.bean.AppInfo_Table;
import com.speedata.applicationlock.common.utils.Logcat;
import com.speedata.applicationlock.common.utils.ToolToast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;
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

    /**
     * 获取app安装卸载之后显示列表
     *
     * @param context context
     * @param isAdd   是否是卸载
     * @param pkgName 包名
     * @return app列表
     */
    public static List<AppInfo> getAppChangedList(Context context, boolean isAdd, String pkgName) {

        List<AppInfo> mAllAppList = new ArrayList<>();
        if (isAdd) {
            Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
            mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> mAppList = context.getPackageManager().queryIntentActivities(mIntent, 0);
            AppInfo mAppInfo = new AppInfo(mAppList.get(mAppList.size() - 1).activityInfo.name,
                    mAppList.get(mAppList.size() - 1).activityInfo.packageName,
                    mAppList.get(mAppList.size() - 1).activityInfo.loadLabel(context.getPackageManager()).toString(), false);
            mAppInfo.save();
        } else {
            SQLite.delete(AppInfo.class).where(AppInfo_Table.appPkg.is(pkgName)).async().execute();
        }
        mAllAppList.addAll(DbCommon.queryAppList(false));
        return mAllAppList;
    }

    /**
     * 获取重新加载的app
     *
     * @param context context
     */
    public static void getCompareDbList(Context context) {
        boolean isRequestReload = false;
        List<AppInfo> mAllAppList = new ArrayList<>();
        mAllAppList.addAll(getAllAppList(context));
        List<String> mAllAppActList = new ArrayList<>();
        for (int i = 0; i < mAllAppList.size(); i++) {
            mAllAppActList.add(mAllAppList.get(i).getActName());
        }
        List<AppInfo> mDbAppList = new ArrayList<>();
        mDbAppList.addAll(SQLite.select().from(AppInfo.class).queryList());
        List<String> mDbActList = new ArrayList<>();
        for (int i = 0; i < mDbAppList.size(); i++) {
            mDbActList.add(mDbAppList.get(i).getActName());
        }

        for (int i = 0; i < mAllAppList.size(); i++) {
            if (!mDbActList.contains(mAllAppActList.get(i))) {
                mAllAppList.get(i).save();
                isRequestReload = true;
            }

        }
        for (int i = 0; i < mDbAppList.size(); i++) {
            if (!mAllAppActList.contains(mDbActList.get(i))) {
                SQLite.delete().from(AppInfo.class).where(AppInfo_Table.actName.is(mDbAppList.get(i).getActName())).async().execute();
                isRequestReload = true;
            }
        }
        if (isRequestReload)
            EventBus.getDefault().post(new AppChanged(true));
    }

    /**
     * 结束最近使用程序
     */
    @SuppressWarnings("deprecation")
    public static void clearRecentTask(Context context) {
        Method mRemoveTask;
        ActivityManager mActivityManager;
        try {
            Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
            mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            mRemoveTask = activityManagerClass.getMethod("removeTask", int.class);
            mRemoveTask.setAccessible(true);
            List<ActivityManager.RecentTaskInfo> mRecentTasks = mActivityManager.getRecentTasks(100, ActivityManager.RECENT_IGNORE_UNAVAILABLE);
            for (int i = 0; i < mRecentTasks.size(); i++) {
                Logcat.d("mRecentTasks.get(i).origActivity is::" +
                        mRecentTasks.get(i).baseIntent.getComponent().getPackageName());
                mRemoveTask.invoke(mActivityManager, mRecentTasks.get(i).persistentId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}