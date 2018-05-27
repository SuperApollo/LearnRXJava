package com.apollo.learnrxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_network)
    Button mBtnNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_network)
    public void onNetwork(Button button){
        Intent intent = new Intent(MainActivity.this, NetworkActivity.class);
        startActivity(intent);
    }


}
