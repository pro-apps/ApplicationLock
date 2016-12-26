package com.speedata.applicationlock.common;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.bean.AppInfo_Table;
import com.speedata.applicationlock.common.db.HideAppDB;

import java.util.List;

import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

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
 * @author Reginer in  2016/12/16 12:10.
 *         Description: 数据库操作
 */
public class DbCommon {


    /**
     * 查询可隐藏app列表
     *
     * @return 可隐藏列表
     */
    public static List<AppInfo> queryAppList(boolean isHide) {

        return select().from(AppInfo.class).where(AppInfo_Table.isHide.is(isHide)).queryList();
    }

    /**
     * 保存应用列表
     *
     * @param mAppList app列表
     */
    public static void saveAppList(List<AppInfo> mAppList) {
        FlowManager.getDatabase(HideAppDB.class)
                .getTransactionManager()
                .getSaveQueue()
                .addAll2(mAppList);
        FlowManager.getDatabase(HideAppDB.class).getTransactionManager().getSaveQueue().purgeQueue();

    }

    /**
     * 升级应用列表
     *
     * @param mAppList app列表
     */
    public static void updateAppList(List<AppInfo> mAppList) {
        FlowManager.getDatabase(HideAppDB.class)
                .getTransactionManager()
                .getSaveQueue()
                .addAll2(mAppList);
        FlowManager.getDatabase(HideAppDB.class).getTransactionManager().getSaveQueue().purgeQueue();
    }


}