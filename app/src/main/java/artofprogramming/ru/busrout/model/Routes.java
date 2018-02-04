
package artofprogramming.ru.busrout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;
@DatabaseTable(tableName = "Routes")
public class Routes implements Serializable {
    @DatabaseField
    @SerializedName("active_from")
    @Expose
    private String activeFrom;

    @DatabaseField
    @SerializedName("active_to")
    @Expose
    private String activeTo;

    @DatabaseField(foreign = true, canBeNull = false, foreignAutoRefresh = true)
    @SerializedName("checkpoints")
    @Expose
    private List<Checkpoint> checkpoints = null;

    @DatabaseField
    @SerializedName("city")
    @Expose
    private String city;

    @DatabaseField
    @SerializedName("city_id")
    @Expose
    private Integer cityId;

    @DatabaseField
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;

    @DatabaseField
    @SerializedName("description")
    @Expose
    private String description;

    @DatabaseField
    @SerializedName("full_name")
    @Expose
    private String fullName;

    @DatabaseField(id = true)
    @SerializedName("id")
    @Expose
    private Integer id;

    @DatabaseField
    @SerializedName("name")
    @Expose
    private String name;

    @DatabaseField
    @SerializedName("number")
    @Expose
    private Integer number;

    @DatabaseField
    @SerializedName("public_name")
    @Expose
    private String publicName;

    @DatabaseField
    @SerializedName("region")
    @Expose
    private Object region;
    private final static long serialVersionUID = 1148441488191780398L;

    public Routes(){

    }

    public String getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(String activeFrom) {
        this.activeFrom = activeFrom;
    }

    public String getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(String activeTo) {
        this.activeTo = activeTo;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

}
