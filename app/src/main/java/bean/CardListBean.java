package bean;

import java.util.List;

/**
 * Created by mayn on 2018/6/28.
 */

public class CardListBean {

    /**
     * code : 1000
     * result : [{"id":5,"bank_id":2,"bank_name":"工商银行","tail_no":"0057","real_name":"","repay_date":"28","icon":"22","repay_time":"06.28","repay_remaining_amount":"1865.32","repay_remaining_days":0},{"id":6,"bank_id":3,"bank_name":"建设银行","tail_no":"5410","real_name":"","repay_date":"15","icon":"","repay_time":"06.15","repay_remaining_amount":"1865.32","repay_remaining_days":-13}]
     * msg :
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
         * id : 5
         * bank_id : 2
         * bank_name : 工商银行
         * tail_no : 0057
         * real_name :
         * repay_date : 28
         * icon : 22
         * repay_time : 06.28
         * unrepay_amount : 1865.32
         * repay_remaining_days : 0
         * text_tips : 本期账单
         */

        private int id;
        private int bank_id;
        private String bank_name;
        private String tail_no;
        private String real_name;
        private String repay_date;
        private String icon;
        private String repay_time;
        private String unrepay_amount;
        private int repay_remaining_days;
        private String text_tips;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBank_id() {
            return bank_id;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getTail_no() {
            return tail_no;
        }

        public void setTail_no(String tail_no) {
            this.tail_no = tail_no;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getRepay_date() {
            return repay_date;
        }

        public void setRepay_date(String repay_date) {
            this.repay_date = repay_date;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getRepay_time() {
            return repay_time;
        }

        public void setRepay_time(String repay_time) {
            this.repay_time = repay_time;
        }

        public String getUnrepay_amount() {
            return unrepay_amount;
        }

        public void setUnrepay_amount(String unrepay_amount) {
            this.unrepay_amount = unrepay_amount;
        }

        public int getRepay_remaining_days() {
            return repay_remaining_days;
        }

        public void setRepay_remaining_days(int repay_remaining_days) {
            this.repay_remaining_days = repay_remaining_days;
        }

        public String getText_tips() {
            return text_tips;
        }

        public void setText_tips(String text_tips) {
            this.text_tips = text_tips;
        }
    }
}
