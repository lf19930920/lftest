package bean;

import java.util.List;

/**
 * Created by mayn on 2018/8/1.
 *  公告bean
 */

public class NoticeBean {
    private String date;
    private List<NoticeChildBean> childList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NoticeChildBean> getChildList() {
        return childList;
    }

    public void setChildList(List<NoticeChildBean> childList) {
        this.childList = childList;
    }
    public static class NoticeChildBean{
        private String create_time;
        private String img;
        private int id;
        private String title;
        private String url;
        private String description;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
