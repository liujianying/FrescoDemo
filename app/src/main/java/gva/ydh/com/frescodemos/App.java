package gva.ydh.com.frescodemos;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

/**
 * Created by liujianying on 15/5/16.
 */
public class App extends Application {


    static App weiLeCtx;

    /**
     * @return String 存储路径
     * @category 获取IM文件存放路径
     */
    public static String getFilePath() {
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 有SD卡，且有读写的权限
            String sdcard_path = Environment.getExternalStorageDirectory().getPath();
            path = sdcard_path + "/com/ydh";
        } else {// 无SD卡
            path = weiLeCtx.getCacheDir().getAbsolutePath() + "/com/ydh";
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();// 创建文件
        }
        return path;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        weiLeCtx = (App) this.getApplicationContext();
        File cacheDir = new File(getFilePath());
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
                .setBaseDirectoryName("img000")
                .setBaseDirectoryPath(cacheDir)
                .setMaxCacheSize(1024 * 1024 * 40)
                .build();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this, new OkHttpClient())
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(weiLeCtx, config);
    }


}
