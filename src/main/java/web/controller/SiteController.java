package web.controller;

import bean.MeetingRoom;
import bean.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MeetingRoomService;
import service.SiteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIce
 * @date 2018-04-24 13:20
 */
@Controller
@RequestMapping("site")
public class SiteController {
    private SiteService        siteService;
    private MeetingRoomService meetingRoomService;

    @RequestMapping("findAll")
    @ResponseBody
    public List<Site> findAll() {
        return siteService.selectAll();
    }

    @RequestMapping("findAllWithMR")
    @ResponseBody
    public Object findAllWithMR() {
        List<Site> sites = siteService.selectAll();
        List<Map<String, Object>> data = new ArrayList<>(sites.size());
        sites.forEach(site -> {
            List<MeetingRoom> meetingRooms = meetingRoomService.findBySiteId(site.getId());
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("site", site);
            map.put("meetingRooms", meetingRooms);
            data.add(map);
        });
        return data;
    }

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    @Autowired
    public void setMeetingRoomService(MeetingRoomService meetingRoomService) {
        this.meetingRoomService = meetingRoomService;
    }
}
