package com.lauzy.freedom.librarys.common;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import com.lauzy.freedom.librarys.R;

import java.io.File;

/**
 * Desc : IntentUtil
 * Author : Lauzy
 * Date : 2017/8/17
 * Blog : http://www.jianshu.com/u/e76853f863a9
 * Email : freedompaladin@gmail.com
 */
public class IntentUtil {

    /**
     * 打开系统设置界面
     *
     * @param context context
     * @return intent
     */
    public static Intent openSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        return intent;
    }

    /**
     * 分享文件
     *
     * @param context  context
     * @param filePath 文件路径
     */
    public static void shareFile(Context context, String filePath) {
        File file = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        Uri uri = Uri.fromFile(file);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        }
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share)));
    }

    /**
     * 打开浏览器
     * @param context context
     * @param url url
     */
    public static void browser(@NonNull Context context, @NonNull String url) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }
}
