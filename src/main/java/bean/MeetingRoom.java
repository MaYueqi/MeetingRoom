package bean;

import java.io.Serializable;

public class MeetingRoom implements Serializable {
    private Integer id;

    private String name;

    private Integer siteId;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MeetingRoom{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", siteId=").append(siteId);
        sb.append('}');
        return sb.toString();
    }
}