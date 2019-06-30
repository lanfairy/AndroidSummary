package com.lanfairy.elly.androidsummary.UI.Acvivity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.utils.DownloadUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_rx_android)
public class RxAndroid extends AppCompatActivity {
    private static final String TAG = RxAndroid.class.getSimpleName();

    @ViewInject(R.id.rx_img)
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
    }
//用事件注解  函数需用private 用public 则不起作用
    @Event(value = R.id.btn_download_img)
    private void dowloadImg(View view) {
        String path = "http://pic.58pic.com/58pic/13/82/74/92q58PICeSI_1024.jpg";
        new DownloadUtils().downloadImg(path, new DownloadUtils.Downloadimg() {
            @Override
            public void downloadimg(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }
        });
    }
    @Event(value = {R.id.btn_download_one, R.id.btn_download_two})
    private void xUtils3Event(View view){
        Toast.makeText(this, "点击了 id: "+view.getId(),Toast.LENGTH_LONG).show();
        Log.i(TAG, "点击了 id: "+view.getId());
    }
}
