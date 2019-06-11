package com.wyx.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wyx.mvp.bean.Data;
import com.wyx.mvp.demo.Contacts;
import com.wyx.mvp.demo.contract.Contract;
import com.wyx.mvp.demo.presenter.PresenterImpl;

public class MainActivity extends AppCompatActivity implements Contract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        PresenterImpl presenter = new PresenterImpl(this);

        presenter.get(Contacts.CESHI, null, null, Data.class);
    }


    @Override
    public void success(Object success) {

    }

    @Override
    public void error(String error) {

    }
}
