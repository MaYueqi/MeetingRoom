package bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Time implements Serializable {
    private Integer id;

    @DateTimeFormat(pattern = "HH:mm")
    private Date startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;

    private Integer index;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Time{");
        sb.append("id=").append(id);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}