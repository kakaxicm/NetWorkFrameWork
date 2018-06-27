package com.qicode.kakaxicm.networkframework;

/**
 * Created by chenming on 2018/6/27
 */
public class UserResponse {

    /**
     * code : 1
     * msg :
     * data : {"name":"a","password":"123"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : a
         */

        private String a;

        public String getA() {
            return a;
        }

        public void setA(String name) {
            this.a = name;
        }

    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
