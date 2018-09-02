package bean;

/**
 * Created by mayn on 2018/8/2.
 *  忘记密码bean
 */

public class ForgetPwdBean {

    /**
     * code : 1000
     * result : {"token":"c456ed6e55cd491427515a533a45bbc1"}
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
         * token : c456ed6e55cd491427515a533a45bbc1
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
