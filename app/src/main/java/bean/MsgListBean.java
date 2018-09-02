package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mayn on 2018/7/4.
 * 消息列表 -- bean
 */

public class MsgListBean {

    /**
     * code : 1000
     * result : [{"id":1,"title":"恭喜您，上周累计获得3成长值","description":"恭喜您，上周累计获得3成长值","img":"1","url":"","create_time":"2018-05-23 11:12:12"}]
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

    public static class ResultBean implements Serializable {
        /**
         * id : 1
         * title : 恭喜您，上周累计获得3成长值
         * description : 恭喜您，上周累计获得3成长值
         * img : 1
         * url :
         * create_time : 2018-05-23 11:12:12
         */

        private int id;
        private String title;
        private String description;
        private String img;
        private String url;
        private String create_time;
        private boolean isRead = false;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public boolean getIsRead() {
            return isRead;
        }

        public void setIsRead(boolean read) {
            isRead = read;
        }
    }
}
