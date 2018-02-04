
package artofprogramming.ru.busrout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SnappedPoints implements Serializable {

    @SerializedName("snappedPoints")
    @Expose
    private List<SnappedPoint> snappedPoints = null;
    @SerializedName("warningMessage")
    @Expose
    private String warningMessage;
    private final static long serialVersionUID = 3026785123030698510L;

    public List<SnappedPoint> getSnappedPoints() {
        return snappedPoints;
    }

    public void setSnappedPoints(List<SnappedPoint> snappedPoints) {
        this.snappedPoints = snappedPoints;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

}
