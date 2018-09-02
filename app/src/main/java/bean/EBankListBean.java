package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/13.
 * 获取银行列表bean
 */

public class EBankListBean {

    /**
     * code : 1000
     * result : [{"id":1,"bank_name":"交通银行","icon":"https://52card.oss-cn-hangzhou.aliyuncs.com/bank/gongshang.png"},{"id":2,"bank_name":"工商银行","icon":"https://52card.oss-cn-hangzhou.aliyuncs.com/bank/gongshang.png"},{"id":3,"bank_name":"建设银行","icon":"https://52card.oss-cn-hangzhou.aliyuncs.com/bank/gongshang.png"},{"id":4,"bank_name":"招商银行","icon":"https://52card.oss-cn-hangzhou.aliyuncs.com/bank/gongshang.png"},{"id":5,"bank_name":"中国邮政","icon":"https://52card.oss-cn-hangzhou.aliyuncs.com/bank/gongshang.png"}]
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
         * bank_name : 交通银行
         * icon : https://52card.oss-cn-hangzhou.aliyuncs.com/bank/gongshang.png
         */

        private int id;
        private String bank_name;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
