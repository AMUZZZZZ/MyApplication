package com.qqdemo.administrator.myapplication;

/**
 * Created by Administrator on 2017/4/30.
 */

public  class User {

    /**
     * result : true
     * User : {"id":3,"username":"barry","password":"123","email":"qq@qq.com","birthday":"十二月 13, 1996"}
     */

    private String result;
    /**
     * id : 3
     * username : barry
     * password : 123
     * email : qq@qq.com
     * birthday : 十二月 13, 1996
     */

    private UserBean user;
    /**
     * msg : 用户名或密码不正确
     */

    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class UserBean {
        private int id;
        private String username;
        private String password;
        private String email;
        private String birthday;

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
    }
}
