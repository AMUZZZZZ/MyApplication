package com.qqdemo.administrator.myapplication;

/**
 * Created by Administrator on 2017/4/30.
 */

public class RegisterBean {


    /**
     * id : 0
     * username :
     * password :
     * repassword :
     * email :
     * birthday :
     * msg : {"birthday":"生日不能不能为空","password":"密码不能为空","email":"邮箱不能为空","username":"用户名不能为空"}
     */

    private ErrorBean error;
    /**
     * result : false
     */

    private String result;
    /**
     * error1 : 用户名已存在
     */

    private String error1;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    public static class ErrorBean {
        private int id;
        private String username;
        private String password;
        private String repassword;
        private String email;
        private String birthday;
        /**
         * birthday : 生日不能不能为空
         * password : 密码不能为空
         * email : 邮箱不能为空
         * username : 用户名不能为空
         */

        private MsgBean msg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRepassword() {
            return repassword;
        }

        public void setRepassword(String repassword) {
            this.repassword = repassword;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public MsgBean getMsg() {
            return msg;
        }

        public void setMsg(MsgBean msg) {
            this.msg = msg;
        }

        public static class MsgBean {
            private String birthday;
            private String password;
            private String repassword;
            private String email;
            private String username;

            public String getRepassword() {
                return repassword;
            }

            public void setRepassword(String repassword) {
                this.repassword = repassword;
            }
            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
