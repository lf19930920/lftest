package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/13.
 * 邮箱列表bean
 */

public class MailListBean {

    /**
     * code : 1000
     * result : [{"id":1,"name":"qq邮箱","icon":"","url":"https://mail.qq.com"},{"id":2,"name":"126","icon":"https://mimg.127.net/logo/126logo.gif","url":"https://mail.126.com"},{"id":3,"name":"163","icon":"","url":"https://mail.163.com"},{"id":4,"name":"yeah","icon":"","url":"https://mail.yeah.com"},{"id":5,"name":"189邮箱","icon":"","url":"https://mail.189.cn"},{"id":6,"name":"新浪邮箱","icon":"","url":"https://mail.sina.com.cn"}]
     * msg : 获取成功
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * name : qq邮箱
         * icon :
         * url : https://mail.qq.com
         */

        private int id;
        private String name;
        private String icon;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
