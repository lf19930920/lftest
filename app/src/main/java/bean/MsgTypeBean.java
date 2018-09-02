package bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mayn on 2018/6/28.
 */

public class MsgTypeBean {

    /**
     * code : 1000
     * result : [{"id":1,"name":"系统通知","new":{"title":"恭喜您，上周累计获得3成长值","create_time":"05-23"}},{"id":2,"name":"公告","new":{"title":"农行维护中","create_time":"06-07"}},{"id":3,"name":"活动中心","new":{"title":"新手推荐送好礼","create_time":"06-07"}}]
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
         * name : 系统通知
         * new : {"title":"恭喜您，上周累计获得3成长值","create_time":"05-23"}
         */

        private int id;
        private String name;
        @SerializedName("new")
        private NewBean newX;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public NewBean getNewX() {
            return newX;
        }

        public void setNewX(NewBean newX) {
            this.newX = newX;
        }

        public static class NewBean {
            /**
             * title : 恭喜您，上周累计获得3成长值
             * create_time : 05-23
             */

            private String title;
            private String create_time;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
