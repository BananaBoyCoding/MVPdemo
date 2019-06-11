package com.wyx.mvp.demo.contract;

import java.util.List;
import java.util.Map;

/**
 * author   : BananaBoy
 * edition  : 1.0
 * time     : 2019/5/24
 * describe :
 */
public interface Contract {
    interface Model {
        void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass, MyCallBack myCallBack);

        void post(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass, MyCallBack myCallBack);

        void put(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass, MyCallBack myCallBack);

        void delete(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass, MyCallBack myCallBack);

        void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class aClass, MyCallBack myCallBack);
    }

    interface View<T> {
        void success(T success);

        void error(String error);
    }

    interface Presenter {
        void get(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void post(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void put(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void delete(String url, Map<String, Object> headmap, Map<String, Object> map, Class aClass);

        void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, Class aClass);
    }

    interface MyCallBack<T> {
        void success(T success);

        void error(String error);
    }
}
