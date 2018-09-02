package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/4.
 */

public class RedPagListBean {

    /**
     * code : 1000
     * result : [{"id":1,"redpkg_name":"新人有礼","amount":"10.00","unit_name":"元","use_condition":"只能还款用","expire_time":"2018-06-09","unit_type":"现金红包"},{"id":3,"redpkg_name":"红色星期五送刷卡金","amount":"50.00","unit_name":"元","use_condition":"只能还款用","expire_time":"2018-06-09","unit_type":"现金红包"}]
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
         * redpkg_name : 新人有礼
         * amount : 10.00
         * unit_name : 元
         * use_condition : 只能还款用
         * expire_time : 2018-06-09
         * unit_type : 现金红包
         * status:0
         * use_remark:只能还款用
         */

        private int id;
        private String redpkg_name;
        private String amount;
        private String unit_name;
        private String use_condition;
        private String expire_time;
        private String unit_type;
        private int status;
        private String use_remark;

        public String getUse_remark() {
            return use_remark;
        }

        public void setUse_remark(String use_remark) {
            this.use_remark = use_remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRedpkg_name() {
            return redpkg_name;
        }

        public void setRedpkg_name(String redpkg_name) {
            this.redpkg_name = redpkg_name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public String getUse_condition() {
            return use_condition;
        }

        public void setUse_condition(String use_condition) {
            this.use_condition = use_condition;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public String getUnit_type() {
            return unit_type;
        }

        public void setUnit_type(String unit_type) {
            this.unit_type = unit_type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
