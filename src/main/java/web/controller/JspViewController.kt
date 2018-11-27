package web.controller

import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author NIce
 * @date 2018-04-25 08:29
 */
//@Controller
//@RequestMapping("jsp")
class JspViewController {
//    @RequestMapping("{jsp}")
    fun toJsp(@PathVariable jsp: String, @RequestParam(required = false) msg: String?, modelMap : ModelMap): String {
        msg?.let {
            modelMap.put("msg", msg)
        }
        return jsp;
    }
}