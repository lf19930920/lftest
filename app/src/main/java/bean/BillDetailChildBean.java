package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/9.
 *  账单明细child -- bean
 */

public class BillDetailChildBean {

    /**
     * code : 1000
     * result : [{"id":1,"trade_time":"04.28","tail_no":2767,"trade_info":"还款 支付宝","amount":"+381.00","type_name":"还款"},{"id":2,"trade_time":"04.26","tail_no":2767,"trade_info":"消费 支付宝 - 河南希界维影城有限公司高新分公司","amount":"-49.00","type_name":"购物"},{"id":3,"trade_time":"04.24","tail_no":2767,"trade_info":"消费 支付宝 - 郑州凯森电子科技有限公司","amount":"-25.00","type_name":"网点"},{"id":4,"trade_time":"04.23","tail_no":2767,"trade_info":"代发款项信用卡还款","amount":"+2876.00","type_name":"还款"},{"id":5,"trade_time":"04.21","tail_no":2767,"trade_info":"消费支付宝-滴滴出行科技有限公司","amount":"-27.73","type_name":"网购"},{"id":6,"trade_time":"04.18","tail_no":2767,"trade_info":"消费京东支付","amount":"-67.00","type_name":"网购"},{"id":7,"trade_time":"04.13","tail_no":2767,"trade_info":"消费支付宝-上海浦东发展银行股份有限公司","amount":"-112.00","type_name":"网购"},{"id":8,"trade_time":"04.13","tail_no":2767,"trade_info":"消费支付宝-北京小桔科技有限公司","amount":"-33.88","type_name":"网购"},{"id":9,"trade_time":"04.10","tail_no":2767,"trade_info":"消费支付宝-李晓争","amount":"-12.90","type_name":"网购"},{"id":10,"trade_time":"04.09","tail_no":2767,"trade_info":"阿里云计算有限公司","amount":"-381.00","type_name":"网购"},{"id":11,"trade_time":"04.05","tail_no":2767,"trade_info":"消费支付宝-郑州凯森电子科技有限公司","amount":"-19.00","type_name":"网购"},{"id":12,"trade_time":"04.03","tail_no":2767,"trade_info":"消费支付宝-河南麦金地餐饮管理服务有限公司","amount":"-24.00","type_name":"网购"},{"id":13,"trade_time":"04.02","tail_no":2767,"trade_info":"消费支付宝-杭州里尔北特电子商务有限公司","amount":"-148.00","type_name":"网购"},{"id":14,"trade_time":"04.01","tail_no":2767,"trade_info":"消费微信支付-光大银行","amount":"-26.00","type_name":"其他类型"},{"id":15,"trade_time":"04.01","tail_no":2767,"trade_info":"消费微信支付-上海顶想","amount":"-29.99","type_name":"其他类型"},{"id":16,"trade_time":"04.01","tail_no":2767,"trade_info":"消费支付宝-浙江网商银行股份有限公司","amount":"-190.10","type_name":"其他类型"}]
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
         * trade_time : 04.28
         * tail_no : 2767
         * trade_info : 还款 支付宝
         * amount : +381.00
         * type_name : 还款
         */

        private int id;
        private String trade_time;
        private int tail_no;
        private String trade_info;
        private String amount;
        private String type_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTrade_time() {
            return trade_time;
        }

        public void setTrade_time(String trade_time) {
            this.trade_time = trade_time;
        }

        public int getTail_no() {
            return tail_no;
        }

        public void setTail_no(int tail_no) {
            this.tail_no = tail_no;
        }

        public String getTrade_info() {
            return trade_info;
        }

        public void setTrade_info(String trade_info) {
            this.trade_info = trade_info;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
