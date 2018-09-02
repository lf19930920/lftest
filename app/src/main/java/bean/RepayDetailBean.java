package bean;

/**
 * Created by mayn on 2018/7/10.
 */

public class RepayDetailBean {

    /**
     * code : 1000
     * result : {"id":1,"card_id":1,"amount":"381.00","redpkg_amount":"20.00","pay_amount":"361.00","pay_info":"农业银行(6775)：381.00","create_time":"2018-12-20 12:57:12","deal_time":"2018-12-20 12:57:12","status":2,"status_name":"银行处理中","card_info":"中国邮政(7862) 赵永彬"}
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
         * id : 1
         * card_id : 1
         * amount : 381.00
         * redpkg_amount : 20.00
         * pay_amount : 361.00
         * pay_info : 农业银行(6775)：381.00
         * create_time : 2018-12-20 12:57:12
         * deal_time : 2018-12-20 12:57:12
         * finish_time : 2018-12-20 12:57:12
         * status : 2
         * status_name : 银行处理中
         * card_info : 中国邮政(7862) 赵永彬
         */

        private int id;
        private int card_id;
        private String amount;
        private String redpkg_amount;
        private String pay_amount;
        private String pay_info;
        private String create_time;
        private String deal_time;
        private String finish_time;
        private int status;
        private String status_name;
        private String card_info;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getRedpkg_amount() {
            return redpkg_amount;
        }

        public void setRedpkg_amount(String redpkg_amount) {
            this.redpkg_amount = redpkg_amount;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getPay_info() {
            return pay_info;
        }

        public void setPay_info(String pay_info) {
            this.pay_info = pay_info;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDeal_time() {
            return deal_time;
        }

        public void setDeal_time(String deal_time) {
            this.deal_time = deal_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getCard_info() {
            return card_info;
        }

        public void setCard_info(String card_info) {
            this.card_info = card_info;
        }
    }
}
