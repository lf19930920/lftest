package bean;

/**
 * Created by mayn on 2018/6/26.
 */

public class LoginBean {

    /**
     * code : 1000
     * result : {"token":"8f4aa949371c5632d7e93ba879a53e99"}
     * msg : 登陆成功
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
         * token : 8f4aa949371c5632d7e93ba879a53e99
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
