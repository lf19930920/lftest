package bean;

/**
 * Created by mayn on 2018/7/10.
 * 我的信用卡详情bean
 */

public class CardDetailBean {

    /**
     * code : 1000
     * result : {"bank_name":"中国邮政","real_name":"赵永彬","tail_no":"---- ---- ---- 7862","free_interest_days":51,"bill_date":"02","repay_date":"27","total_amount":"44000.00","bill_time":"07.02","repay_time":"07.27","repay_remaining_days":17,"amount":"1465.61","min_repay_amount":"255.00"}
     * msg : 获取成功
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
         * bank_name : 中国邮政
         * real_name : 赵永彬
         * tail_no :7862
         * free_interest_days : 51
         * bill_date : 02
         * repay_date : 27
         * total_amount : 44000.00
         * bill_time : 07.02
         * repay_time : 07.27
         * repay_remaining_days : 17
         * amount : 1465.61
         * min_repay_amount : 255.00
         * card_no:62305577887862
         * card_no_mask:6203 ···· ···· ···· 7862
         * unrepay_amount:185.23
         * tail_no_mask : ---- ---- ---- 7862
         */

        private String bank_name;
        private String real_name;
        private String tail_no;
        private int free_interest_days;
        private String bill_date;
        private String repay_date;
        private String total_amount;
        private String bill_time;
        private String repay_time;
        private int repay_remaining_days;
        private String amount;
        private String min_repay_amount;
        private String card_no;
        private String card_no_mask;
        private String unrepay_amount;
        private String tail_no_mask;

        public String getTail_no_mask() {
            return tail_no_mask;
        }

        public void setTail_no_mask(String tail_no_mask) {
            this.tail_no_mask = tail_no_mask;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getCard_no_mask() {
            return card_no_mask;
        }

        public void setCard_no_mask(String card_no_mask) {
            this.card_no_mask = card_no_mask;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getTail_no() {
            return tail_no;
        }

        public void setTail_no(String tail_no) {
            this.tail_no = tail_no;
        }

        public int getFree_interest_days() {
            return free_interest_days;
        }

        public void setFree_interest_days(int free_interest_days) {
            this.free_interest_days = free_interest_days;
        }

        public String getBill_date() {
            return bill_date;
        }

        public void setBill_date(String bill_date) {
            this.bill_date = bill_date;
        }

        public String getRepay_date() {
            return repay_date;
        }

        public void setRepay_date(String repay_date) {
            this.repay_date = repay_date;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getBill_time() {
            return bill_time;
        }

        public void setBill_time(String bill_time) {
            this.bill_time = bill_time;
        }

        public String getRepay_time() {
            return repay_time;
        }

        public void setRepay_time(String repay_time) {
            this.repay_time = repay_time;
        }

        public int getRepay_remaining_days() {
            return repay_remaining_days;
        }

        public void setRepay_remaining_days(int repay_remaining_days) {
            this.repay_remaining_days = repay_remaining_days;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMin_repay_amount() {
            return min_repay_amount;
        }

        public void setMin_repay_amount(String min_repay_amount) {
            this.min_repay_amount = min_repay_amount;
        }

        public String getUnrepay_amount() {
            return unrepay_amount;
        }

        public void setUnrepay_amount(String unrepay_amount) {
            this.unrepay_amount = unrepay_amount;
        }
    }
}
