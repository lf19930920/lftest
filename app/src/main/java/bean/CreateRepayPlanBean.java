package bean;

/**
 * Created by mayn on 2018/7/31.
 *  创建还款计划bean
 */

public class CreateRepayPlanBean {
    private String times;
    private boolean isCheck;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
