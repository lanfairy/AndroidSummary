package com.lanfairy.elly.androidsummary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadUtils {
    private static final String TAG = Downloadimg.class.getSimpleName();
    private OkHttpClient client;

    public DownloadUtils() {
        client = new OkHttpClient();
    }

    public Observable<byte[]> downloadImage(String path) {
        return Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(ObservableEmitter<byte[]> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    Request request = new Request.Builder().url(path).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            emitter.onError(e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data!=null){
                                    emitter.onNext(data);
                                }

                            }
                            emitter.onComplete();
                        }
                    });
                }
            }
        });
    }

    public void downloadImg(String path, Downloadimg setImage){
        downloadImage(path).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<byte[]>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (setImage!=null){
                    setImage.downloadimg(bitmap);
                }
                Log.i(TAG, "onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

   public interface Downloadimg{
        void downloadimg(Bitmap bitmap);
    }

}
