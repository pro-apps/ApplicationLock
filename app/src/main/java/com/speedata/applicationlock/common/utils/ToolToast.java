package com.speedata.applicationlock.common.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.App;
import com.speedata.applicationlock.hide.HideActivity;
import com.speedata.applicationlock.show.ShowActivity;

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
 * @author Reginer on  2016/9/22 14:36.
 *         Description:吐司封装
 */
public class ToolToast {
    private static Toast mToast;

    /**
     * 弹出较短的Toast消息
     *
     * @param msg 消息内容
     */
    public static void toastShort(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(),
                    msg, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * 弹出一个自定义布局对话框
     *
     * @param context 上下文
     * @param viewId  自定义布局View
     */
    public static Dialog showPwdDialog(final Context context, int viewId, final boolean isShow, final List<ResolveInfo> mAppShowList) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(viewId, null);
        builder.setView(mView);
        final EditText mPwd = (EditText) mView.findViewById(R.id.et_pwd);
        final EditText mPwdNew = (EditText) mView.findViewById(R.id.et_pwd_new);
        final Button mBtnModify = (Button) mView.findViewById(R.id.btn_modify_pwd);
        final Dialog dialog = builder.show();
        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPwdNew.setVisibility(View.VISIBLE);
            }
        });
        mView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPwdNew.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(mPwd.getText()) || TextUtils.isEmpty(mPwdNew.getText())) {
                        ToolToast.toastShort(context.getString(R.string.please_input_pwd));
                        return;
                    }
                    if (mPwd.getText().toString().equals(SPUtils.get(context, "pwd", "1234", "pwd_file"))) {
                        SPUtils.put(context, "pwd", mPwdNew.getText().toString(), "pwd_file");
                        ToolToast.toastShort(context.getString(R.string.modify_success));
                        dialog.dismiss();
                    } else
                        ToolToast.toastShort(context.getString(R.string.pwd_error));
                } else {
                    if (mPwd.getText().toString().equals(SPUtils.get(context, "pwd", "1234", "pwd_file"))) {
                        if (isShow)
                            context.startActivity(new Intent(context, HideActivity.class)
                                    .putParcelableArrayListExtra("appList",
                                            (ArrayList<? extends Parcelable>) mAppShowList));
                        else
                            context.startActivity(new Intent(context, ShowActivity.class));
                        dialog.dismiss();
                    } else {
                        //密码错误
                        ToolToast.toastShort(context.getString(R.string.pwd_error));
                    }

                }
            }
        });
        mView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        return dialog;
    }


}
