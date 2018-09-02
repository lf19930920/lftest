package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/9.
 * 我的信用卡详情 -- 还款记录bean
 */

public class RepayHistroyBean {

    /**
     * code : 1000
     * result : [{"date":"6月2018","amount":"10.00","bill_cycle":"05.02-06.27","list":[{"id":4,"amount":"10.00","card_type":"信用卡","create_time":"2018-12-20 12:57:12","status_name":"提交成功"}]},{"date":"5月2018","amount":"75.71","bill_cycle":"04.02-05.27","list":[{"id":3,"amount":"25.59","card_type":"信用卡","create_time":"2018-12-20 12:57:12","status_name":"提交成功"},{"id":2,"amount":"50.12","card_type":"信用卡","create_time":"2018-12-20 12:57:12","status_name":"提交成功"}]},{"date":"1月2018","amount":"381.00","bill_cycle":"12.02-01.27","list":[{"id":1,"amount":"381.00","card_type":"信用卡","create_time":1545281832,"status_name":"银行处理中"}]}]
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
         * date : 6月2018
         * amount : 10.00
         * bill_cycle : 05.02-06.27
         * list : [{"id":4,"amount":"10.00","card_type":"信用卡","create_time":"2018-12-20 12:57:12","status_name":"提交成功"}]
         */

        private String date;
        private String amount;
        private String bill_cycle;
        private List<ListBean> list;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBill_cycle() {
            return bill_cycle;
        }

        public void setBill_cycle(String bill_cycle) {
            this.bill_cycle = bill_cycle;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 4
             * amount : 10.00
             * card_type : 信用卡
             * create_time : 2018-12-20 12:57:12
             * status_name : 提交成功
             */

            private int id;
            private String amount;
            private String card_type;
            private String create_time;
            private String status_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCard_type() {
                return card_type;
            }

            public void setCard_type(String card_type) {
                this.card_type = card_type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }
        }
    }
}
