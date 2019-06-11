package com.wyx.mvp.demo.presenter;


import com.wyx.mvp.demo.contract.Contract;
import com.wyx.mvp.demo.model.ModelImpl;

import java.util.List;
import java.util.Map;

/**
 * author   : BananaBoy
 * edition  : 1.0
 * time     : 2019/5/24
 * describe :
 */
public class PresenterImpl implements Contract.Presenter {
    private ModelImpl model;
    private Contract.View view;

    public PresenterImpl(Contract.View view) {
        this.view = view;
        model = new ModelImpl();
    }

    @Override
    public void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.get(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.post(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void put(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.put(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void delete(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass) {
        model.delete(url, headmap, map, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }

    @Override
    public void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class aClass) {
        model.img(url, headmap, map, list, aClass, new Contract.MyCallBack() {
            @Override
            public void success(Object success) {
                view.success(success);
            }

            @Override
            public void error(String error) {
                view.error(error);
            }
        });
    }
}
