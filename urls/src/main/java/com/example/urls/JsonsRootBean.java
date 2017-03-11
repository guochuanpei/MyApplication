package com.example.urls;

import java.util.List;

/**
 * Created by maodi on 2017/3/10.
 */
public class JsonsRootBean {

    private int retcode;
    private List<Data> data;
    private List<Integer> extend;
    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }
    public int getRetcode() {
        return retcode;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }
    public List< Integer> getExtend() {
        return extend;
    }

    @Override
    public String toString() {
        return "JsonsRootBean{" +
                "retcode=" + retcode +
                ", data=" + data +
                ", extend=" + extend +
                '}';
    }
}