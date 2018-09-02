package bean;

/**
 * Created by mayn on 2018/6/28.
 * 联系我们bean
 */

public class CallUsBean {

    /**
     * code : 1000
     * result : {"icon":"","slogan":"信用卡管家，让有信用的人过的更好","website":"www.9891.com","hotline":"400-001-8888","wechat":"xinyongkaguanjia","qq":"85285254"}
     * msg :
     */

    private int code;
    private ResultBean result;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ResultBean {
        /**
         * icon :
         * slogan : 信用卡管家，让有信用的人过的更好
         * website : www.9891.com
         * hotline : 400-001-8888
         * wechat : xinyongkaguanjia
         * qq : 85285254
         */

        private String icon;
        private String slogan;
        private String website;
        private String hotline;
        private String wechat;
        private String qq;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getHotline() {
            return hotline;
        }

        public void setHotline(String hotline) {
            this.hotline = hotline;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }
    }
}
