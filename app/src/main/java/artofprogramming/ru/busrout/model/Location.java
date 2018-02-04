
package artofprogramming.ru.busrout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Locations")
public class Location implements Serializable {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @DatabaseField
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    private final static long serialVersionUID = -4878974008769354947L;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
