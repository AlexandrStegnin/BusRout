
package artofprogramming.ru.busrout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
@DatabaseTable(tableName = "Checkpoints")
public class Checkpoint implements Serializable {

    @DatabaseField
    @SerializedName("code_number")
    @Expose
    private String codeNumber;

    @DatabaseField
    @SerializedName("description")
    @Expose
    private String description;

    @DatabaseField
    @SerializedName("id")
    @Expose
    private Integer id;

    @DatabaseField
    @SerializedName("is_end")
    @Expose
    private Boolean isEnd;

    @DatabaseField
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @DatabaseField
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @DatabaseField
    @SerializedName("name")
    @Expose
    private String name;

    @DatabaseField(id = true)
    @SerializedName("order")
    @Expose
    private Integer order;

    @DatabaseField
    @SerializedName("path")
    @Expose
    private String path;

    @DatabaseField
    @SerializedName("path_distance")
    @Expose
    private Double pathDistance;

    @DatabaseField
    @SerializedName("rsq_id")
    @Expose
    private Integer rsqId;
    private final static long serialVersionUID = -6553691585454680012L;

    public Checkpoint(){

    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getPathDistance() {
        return pathDistance;
    }

    public void setPathDistance(Double pathDistance) {
        this.pathDistance = pathDistance;
    }

    public Integer getRsqId() {
        return rsqId;
    }

    public void setRsqId(Integer rsqId) {
        this.rsqId = rsqId;
    }

}
