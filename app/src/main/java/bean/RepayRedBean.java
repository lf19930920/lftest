package bean;

import java.util.List;

/**
 * Created by mayn on 2018/8/1.
 */

public class RepayRedBean {

    /**
     * code : 1000
     * result : [{"id":6,"redpkg_name":"省心投>1000元即送","amount":"10.00","unit_name":"元","use_condition":"只能还款用","use_remark":"适用于省心投30天以上项目","expire_time":"2018-06-09","unit_type_name":"现金红包","status":0}]
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
         * id : 6
         * redpkg_name : 省心投>1000元即送
         * amount : 10.00
         * unit_name : 元
         * use_condition : 只能还款用
         * use_remark : 适用于省心投30天以上项目
         * expire_time : 2018-06-09
         * unit_type_name : 现金红包
         * status : 0
         */

        private int id;
        private String redpkg_name;
        private String amount;
        private String unit_name;
        private String use_condition;
        private String use_remark;
        private String expire_time;
        private String unit_type_name;
        private int status;

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

        public String getUse_remark() {
            return use_remark;
        }

        public void setUse_remark(String use_remark) {
            this.use_remark = use_remark;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public String getUnit_type_name() {
            return unit_type_name;
        }

        public void setUnit_type_name(String unit_type_name) {
            this.unit_type_name = unit_type_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
