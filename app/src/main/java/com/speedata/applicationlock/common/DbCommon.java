package com.speedata.applicationlock.common;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.speedata.applicationlock.bean.AppsBean;
import com.speedata.applicationlock.bean.HideAppBean;
import com.speedata.applicationlock.bean.HideAppBean_Table;
import com.speedata.applicationlock.common.db.HideAppDB;

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
 * @author Reginer in  2016/12/16 12:10.
 *         Description: 数据库操作
 */
public class DbCommon {
    /**
     * 修改数据库
     *
     * @param mAppsList 应用列表
     */
    public static void updateDb(List<AppsBean> mAppsList) {
        for (int i = 0; i < mAppsList.size(); i++) {
            if (mAppsList.get(i).isCheck()) {
                SQLite.delete(HideAppBean.class)
                        .where(HideAppBean_Table.infoName.
                                is(mAppsList.get(i).getResolveInfo().activityInfo.name))
                        .async()
                        .execute();
            }

        }
    }

    /**
     * 保存数据库
     *
     * @param mAppsList 隐藏应用列表
     */
    public static void saveDb(List<AppsBean> mAppsList) {
        FlowManager.getDatabase(HideAppDB.class)
                .getTransactionManager()
                .getSaveQueue()
                .addAll2(ToolsCommon.getHideList(mAppsList));
        FlowManager.getDatabase(HideAppDB.class).getTransactionManager().getSaveQueue().purgeQueue();
    }
}