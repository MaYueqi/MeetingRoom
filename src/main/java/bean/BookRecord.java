package bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class BookRecord implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer meetingRoomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer startTimeIndex;

    private Integer endTimeIndex;

    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Integer meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public Integer getStartTimeIndex() {
        return startTimeIndex;
    }

    public void setStartTimeIndex(Integer startTimeIndex) {
        this.startTimeIndex = startTimeIndex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getEndTimeIndex() {
        return endTimeIndex;
    }

    public void setEndTimeIndex(Integer endTimeIndex) {
        this.endTimeIndex = endTimeIndex;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BookRecord{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", meetingRoomId=").append(meetingRoomId);
        sb.append(", startTimeIndex=").append(startTimeIndex);
        sb.append(", date=").append(date);
        sb.append(", endTimeIndex=").append(endTimeIndex);
        sb.append(", isDelete=").append(isDelete);
        sb.append('}');
        return sb.toString();
    }
}