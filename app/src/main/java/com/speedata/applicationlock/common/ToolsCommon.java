package com.speedata.applicationlock.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.speedata.applicationlock.R;
import com.speedata.applicationlock.bean.AppsBean;
import com.speedata.applicationlock.bean.HideAppBean;
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
    public static void startApp(List<ResolveInfo> mAppList, int position, Context context) {
        try{
            ResolveInfo info = mAppList.get(position);
            String pkg = info.activityInfo.packageName;
            String cls = info.activityInfo.name;
            ComponentName mComponentName = new ComponentName(pkg, cls);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(mComponentName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent); 
        }catch (Exception e){
            ToolToast.toastShort(context.getString(R.string.app_error));
        }
        
    }

    /**
     * 获取隐藏app后要显示的app
     *
     * @param appsBeanList app列表
     * @return 隐藏app后要显示的app
     */
    public static List<ResolveInfo> getShowAppList(List<AppsBean> appsBeanList) {
        List<ResolveInfo> mAppList = new ArrayList<>();
        for (int i = 0; i < appsBeanList.size(); i++) {
            if (!appsBeanList.get(i).isCheck())
                mAppList.add(appsBeanList.get(i).getResolveInfo());
        }
        return mAppList;
    }

    /**
     * 获取设置显示app后要显示的app
     *
     * @param appsBeanList 可设置显示的app列表
     * @param context      context
     * @return 设置显示app后要显示的app
     */
    public static List<ResolveInfo> getShowAppList(List<AppsBean> appsBeanList, Context context) {
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> mAllAppList = context.getPackageManager().queryIntentActivities(mIntent, 0);
        for (int i = 0; i < appsBeanList.size(); i++) {
            if (!appsBeanList.get(i).isCheck())
                for (int j = 0; j < mAllAppList.size(); j++) {
                    if (appsBeanList.get(i).getResolveInfo().activityInfo.packageName.
                            equals(mAllAppList.get(j).activityInfo.packageName))
                        mAllAppList.remove(j);
                }

        }
        return mAllAppList;
    }

    /**
     * 已安装应用实体类转换
     *
     * @param mAppList 安装应用列表
     * @return 转换实体类
     */
    public static List<AppsBean> getAppsList(List<ResolveInfo> mAppList) {
        List<AppsBean> mAppsList = new ArrayList<>();
        for (int i = 0; i < mAppList.size(); i++) {
            mAppsList.add(new AppsBean(mAppList.get(i), false, false));
        }
        return mAppsList;
    }

    /**
     * 获取选中要隐藏的应用
     *
     * @param mAppsList 全部应用
     * @return 要隐藏的应用
     */

    static List<HideAppBean> getHideList(List<AppsBean> mAppsList) {
        List<HideAppBean> mHideList = new ArrayList<>();
        for (int i = 0; i < mAppsList.size(); i++) {
            if (mAppsList.get(i).isCheck())
                mHideList.add(new HideAppBean(mAppsList.get(i).getResolveInfo().activityInfo.name));
        }
        return mHideList;
    }

    /**
     * 获取已经隐藏的app
     *
     * @param context 上下文
     * @return 隐藏的app
     */
    public static List<AppsBean> getShowList(Context context) {
        List<AppsBean> mAppsList = new ArrayList<>();
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> mAllAppList = context.getPackageManager().queryIntentActivities(mIntent, 0);
        List<HideAppBean> mHideAppBeanList = SQLite.select().
                from(HideAppBean.class).queryList();
        for (int i = 0; i < mAllAppList.size(); i++) {
            for (int j = 0; j < mHideAppBeanList.size(); j++) {
                if (mAllAppList.get(i).activityInfo.name.equals(mHideAppBeanList.get(j).getInfoName())) {
                    mAppsList.add(new AppsBean(mAllAppList.get(i), false, false));
                }

            }
        }
        return mAppsList;
    }

    /**
     * 获取全部可显示的app列表
     *
     * @param mAppList 全部app列表
     * @return 全部显示app列表
     */
    public static List<ResolveInfo> getMainShowList(List<ResolveInfo> mAppList) {
        List<HideAppBean> mHideAppBeanList = SQLite.select().
                from(HideAppBean.class).queryList();
        for (int i = 0; i < mAppList.size(); i++) {
            for (int j = 0; j < mHideAppBeanList.size(); j++) {
                if (mAppList.get(i).activityInfo.name.equals(mHideAppBeanList.get(j).getInfoName()))
                    mAppList.remove(i);
            }
        }
        return mAppList;
    }
}