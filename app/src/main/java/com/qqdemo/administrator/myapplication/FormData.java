package com.qqdemo.administrator.myapplication;

import java.util.List;

/**
 * Created by Administrator on 2017/5/1.
 */

public class FormData {


    /**
     * result : true
     * formData : [{"id":0,"fname":"b512eb9e-865f-4fde-8dcc-a8611c86effe_回不去的过去.jpg","furl":"5\\1\\b512eb9e-865f-4fde-8dcc-a8611c86effe_回不去的过去.jpg","fsize":62195,"fdate":"2017-05-01"}]
     */

    private String result;
    /**
     * id : 0
     * fname : b512eb9e-865f-4fde-8dcc-a8611c86effe_回不去的过去.jpg
     * furl : 5\1\b512eb9e-865f-4fde-8dcc-a8611c86effe_回不去的过去.jpg
     * fsize : 62195
     * fdate : 2017-05-01
     */

    private List<FormDataBean> formData;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<FormDataBean> getFormData() {
        return formData;
    }

    public void setFormData(List<FormDataBean> formData) {
        this.formData = formData;
    }

    public static class FormDataBean {
        private int id;
        private String fname;
        private String furl;
        private int fsize;
        private String fdate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getFurl() {
            return furl;
        }

        public void setFurl(String furl) {
            this.furl = furl;
        }

        public int getFsize() {
            return fsize;
        }

        public void setFsize(int fsize) {
            this.fsize = fsize;
        }

        public String getFdate() {
            return fdate;
        }

        public void setFdate(String fdate) {
            this.fdate = fdate;
        }
    }
}
