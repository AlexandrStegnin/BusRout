
package artofprogramming.ru.busrout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
@DatabaseTable(tableName = "Obj")
public class Object implements Serializable
{
    @DatabaseField
    @SerializedName("car_category")
    @Expose
    private String carCategory;

    @DatabaseField
    @SerializedName("car_group")
    @Expose
    private String carGroup;

    @DatabaseField
    @SerializedName("car_id")
    @Expose
    private Integer carId;

    @DatabaseField
    @SerializedName("car_model")
    @Expose
    private String carModel;

    @DatabaseField
    @SerializedName("car_number")
    @Expose
    private String carNumber;

    @DatabaseField
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;

    @DatabaseField(id = true)
    @SerializedName("id")
    @Expose
    private Integer id;

    @DatabaseField
    @SerializedName("organization_name")
    @Expose
    private String organizationName;

    @DatabaseField
    @SerializedName("route_name")
    @Expose
    private String routeName;

    @DatabaseField
    @SerializedName("azimuth")
    @Expose
    private Double azimuth;

    @DatabaseField
    @SerializedName("datetime")
    @Expose
    private String datetime;

    @DatabaseField
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @DatabaseField
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @DatabaseField
    @SerializedName("received_time")
    @Expose
    private String receivedTime;

    @DatabaseField
    @SerializedName("speed")
    @Expose
    private Double speed;
    private final static long serialVersionUID = -8136153145487165636L;

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getCarGroup() {
        return carGroup;
    }

    public void setCarGroup(String carGroup) {
        this.carGroup = carGroup;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}
