package com.apollo.learnrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends BaseActivity {
    @BindView(R.id.tv_network)
    TextView mTvNetwork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("apollo", "当前线程：" + Thread.currentThread().getName());
                e.onNext(getDataFromNet());
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("apollo", "当前线程：" + Thread.currentThread().getName());
                        mTvNetwork.setText(integer+"");
                    }
                });
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_network;
    }

    private String getDataFromNet() {
        String url = "https://movie.douban.com/top250";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }
}
