package message;

import java.io.Serializable;

/**
 * Created by mayn on 2018/7/17.
 *  eventbus发送位置的经纬度信息
 */

public class LocationMessage implements Serializable{
    private double Latitude;
    private double Longitude;

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
