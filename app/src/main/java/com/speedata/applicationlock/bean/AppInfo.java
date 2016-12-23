package com.speedata.applicationlock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.speedata.applicationlock.common.db.HideAppDB;

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
 * @author Reginer on  2016/12/23 15:21.
 *         Description:
 */
@Table(database = HideAppDB.class)
public class AppInfo extends BaseModel implements Parcelable {
    @PrimaryKey(autoincrement = true)
    Long id;
    @Column
    private String actName;
    @Column
    private String appPkg;
    @Column
    private String appLabel;
    @Column
    private boolean isHide;


    public AppInfo(String actName,String appPkg,String appLabel,boolean isHide){
        this.actName = actName;
        this.appPkg = appPkg;
        this.appLabel = appLabel;
        this.isHide = isHide;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getAppPkg() {
        return appPkg;
    }

    public void setAppPkg(String appPkg) {
        this.appPkg = appPkg;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String appIcon) {
        this.actName = appIcon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.actName);
        dest.writeString(this.appPkg);
        dest.writeString(this.appLabel);
        dest.writeByte(this.isHide ? (byte) 1 : (byte) 0);
    }

    public AppInfo() {
    }

    protected AppInfo(Parcel in) {
        this.actName = in.readString();
        this.appPkg = in.readString();
        this.appLabel = in.readString();
        this.isHide = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel source) {
            return new AppInfo(source);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };
}