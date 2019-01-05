package gcxy.zeffect.cn.helloandroid.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class MetaUtils {
    public static <T extends Object> T getMetaValue(Context pContext, String key, T defaultValue) {
        if (pContext == null || TextUtils.isEmpty(key)) return defaultValue;
        try {
            ApplicationInfo appInfo = pContext.getPackageManager().getApplicationInfo(pContext.getPackageName(), PackageManager.GET_META_DATA);
            if (defaultValue instanceof String) {
                return (T) appInfo.metaData.getString(key, (String) defaultValue);
            } else if (defaultValue instanceof Integer) {
                return (T) Integer.valueOf(appInfo.metaData.getInt(key, (Integer) defaultValue));
            } else if (defaultValue instanceof Long) {
                return (T) Long.valueOf(appInfo.metaData.getLong(key, (Long) defaultValue));
            }
            return defaultValue;
        } catch (PackageManager.NameNotFoundException e) {
            return defaultValue;
        }
    }
}
