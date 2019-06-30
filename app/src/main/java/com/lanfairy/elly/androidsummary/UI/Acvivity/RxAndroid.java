package com.lanfairy.elly.androidsummary.UI.Acvivity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lanfairy.elly.androidsummary.R;
import com.lanfairy.elly.androidsummary.utils.DownloadUtils;

public class RxAndroid extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);
        img = (ImageView) findViewById(R.id.rx_img);
    }

    public void dowloadImg(View view) {
        String path = "http://pic.58pic.com/58pic/13/82/74/92q58PICeSI_1024.jpg";
        new DownloadUtils().downloadImg(path, new DownloadUtils.Downloadimg() {
            @Override
            public void downloadimg(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }
        });
    }
}
