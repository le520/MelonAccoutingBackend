package com.nwpu.melonbookkeeping.controller.admin;

import com.google.common.collect.Maps;
import com.nwpu.melonbookkeeping.controller.admin.param.SystemConfigParam;
import com.nwpu.melonbookkeeping.entity.Admin;
import com.nwpu.melonbookkeeping.service.AdminService;
import com.nwpu.melonbookkeeping.service.ConfigService;
import com.nwpu.melonbookkeeping.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author noorall
 * @date 2021/1/98:27 下午
 * @Description: 系统配置控制器
 */
@Controller
@RequestMapping("/admin/config")
public class ConfigController {
    @Autowired
    ConfigService configService;
    @Autowired
    AdminService adminService;
    @Autowired
    FeedbackService feedbackService;

    /**
     * 后台主界面
     *
     * @return 后台界面
     */
    @RequestMapping({"/", "/index", ""})
    public String index(HttpSession session) {
        session.setAttribute("unRead", feedbackService.getUNReadCount());
        return "admin/config/index";
    }

    /**
     * 系统设置修改
     *
     * @return 修改结果
     */
    @PostMapping("/modify/system")
    public String modify(@RequestParam Map<String, String> configs, RedirectAttributes redirectAttributes) {
        if (!configService.saveWebConfig(configs)) {
            redirectAttributes.addFlashAttribute("systemError", "修改失败！");
        } else {
            redirectAttributes.addFlashAttribute("systemSuccess", "修改成功！");
        }
        return "redirect:/admin/config";
    }

    /**
     * 后台修改
     *
     * @return 修改结果
     */
    @PostMapping("/modify/admin")
    public String modifyAdmin(@RequestParam String password, @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        if (!password.isBlank() && password.equals(confirmPassword) && adminService.modifyAdminPassword(password)) {
            redirectAttributes.addFlashAttribute("adminSuccess", "修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("adminError", "修改失败！");
        }
        return "redirect:/admin/config";
    }
}
