package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/26.
 */

public class HomeBannerBean {

    /**
     * code : 1000
     * result : [{"title":"信用卡管家banner1","url":"","img":"https://52card.oss-cn-hangzhou.aliyuncs.com/banner/banner1.jpg"},{"title":"信用卡管家banner2","url":"","img":"https://52card.oss-cn-hangzhou.aliyuncs.com/banner/banner1.jpg"},{"title":"信用卡管家banner3","url":"","img":"https://52card.oss-cn-hangzhou.aliyuncs.com/banner/banner1.jpg"}]
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
         * title : 信用卡管家banner1
         * url :
         * img : https://52card.oss-cn-hangzhou.aliyuncs.com/banner/banner1.jpg
         */

        private String title;
        private String url;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
