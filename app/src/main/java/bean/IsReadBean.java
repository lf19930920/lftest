package bean;

import java.io.Serializable;

/**
 * Created by mayn on 2018/7/5.
 */

public class IsReadBean implements Serializable{
    private boolean isRead;
    private int id;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
