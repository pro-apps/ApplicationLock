package com.speedata.applicationlock.common;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.common.utils.ToolToast;
import com.speedata.applicationlock.main.MainActivity;
import com.speedata.applicationlock.manager.activity.ManagerActivity;
import com.speedata.applicationlock.options.OptionsActivity;

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
 * 创   建:Reginer in  2017/1/4 16:31.
 * 联系方式:QQ:282921012
 * 功能描述:控件操作
 */
public class ViewCommon {

    public static boolean setMenuOptions(MenuItem item, Context context) {
        switch (item.getItemId()) {
            case R.id.action_admin:
                ToolToast.showPwdDialog(context, R.layout.view_input_pwd_dialog_layout,false);
                return true;
            case R.id.action_clear:
                ToolsCommon.clearRecentTask(context);
                return true;
            case R.id.action_about:
                ToolToast.showAboutDialog(context, R.layout.view_about_layout);
                return true;
            case R.id.action_user:
                MainActivity.isAdmin = false;
                return true;
            case R.id.action_management:
                context.startActivity(new Intent(context, ManagerActivity.class));
                //action_management
                return true;
            case R.id.action_import:
                //action_import
                return true;
            case R.id.action_export:
                //action_export
                return true;
            case R.id.action_options:
                //action_options
                context.startActivity(new Intent(context, OptionsActivity.class));
                return true;
            case R.id.action_launch_settings:
                //action_options
                return true;
            case R.id.action_reset_to_default:
                //action_reset_to_default
                return true;
            case R.id.action_exit:
                //action_exit
                return true;
        }
        return false;
    }
}
