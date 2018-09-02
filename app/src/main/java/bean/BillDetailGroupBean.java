package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/10.
 * 账单明细group -- bean
 */

public class BillDetailGroupBean {

    /**
     * code : 1000
     * result : [{"id":1,"year":2018,"month":6,"bill_cycle":"04.03-05.02","amount":"2505.00"},{"id":2,"year":2018,"month":4,"bill_cycle":"04.03-05.02","amount":"254.00"},{"id":3,"year":2018,"month":3,"bill_cycle":"04.03-05.02","amount":"1258.34"},{"id":4,"year":2018,"month":2,"bill_cycle":"02.03-03.02","amount":"2652.78"},{"id":5,"year":2018,"month":1,"bill_cycle":"01.03-02.02","amount":"4987.25"}]
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
         * year : 2018
         * month : 6
         * bill_cycle : 04.03-05.02
         * bill_amount : 2505.00
         */

        private int id;
        private int year;
        private int month;
        private String bill_cycle;
        private String bill_amount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getBill_cycle() {
            return bill_cycle;
        }

        public void setBill_cycle(String bill_cycle) {
            this.bill_cycle = bill_cycle;
        }

        public String getBill_amount() {
            return bill_amount;
        }

        public void setBill_amount(String bill_amount) {
            this.bill_amount = bill_amount;
        }
    }
}
