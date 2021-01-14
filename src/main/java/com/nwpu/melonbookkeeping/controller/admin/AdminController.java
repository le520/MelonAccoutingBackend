package com.nwpu.melonbookkeeping.controller.admin;

import com.nwpu.melonbookkeeping.entity.Admin;
import com.nwpu.melonbookkeeping.entity.Feedback;
import com.nwpu.melonbookkeeping.service.AdminService;
import com.nwpu.melonbookkeeping.service.FeedbackService;
import com.nwpu.melonbookkeeping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author noorall
 * @date 2021/1/79:38 下午
 * @Description: 后台用户管理控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    FeedbackService feedbackService;

    /**
     * 登录页面
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 后台首页
     *
     * @return 后台首页
     */
    @RequestMapping({"/index", "/", ""})
    public String index(Model model, HttpSession session) {
        session.setAttribute("unRead", feedbackService.getUNReadCount());
        model.addAttribute("mirrorsData", userService.getUserMirrorsData());
        model.addAttribute("indexData", adminService.getIndexOv());
        return "admin/index";
    }

    /**
     * 登录功能
     *
     * @param admin              提交的参数
     * @param session            session
     * @param redirectAttributes 重定向参数
     * @return 登录结果
     */
    @PostMapping("/login")
    public String adminLogin(@NotEmpty @Valid Admin admin, HttpSession session, RedirectAttributes redirectAttributes) {
        if (adminService.login(admin.getUserName(), admin.getPassword())) {
            session.setAttribute("userName", admin.getUserName());
            session.setAttribute("role", "admin");
            session.setAttribute("unRead", feedbackService.getUNReadCount());
            return "redirect:/admin/index";
        } else {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/admin/login";
        }
    }

    /**
     * 管理员登出
     *
     * @param session session信息
     * @return 重定向至登录页面
     */
    @RequestMapping("/logout")
    public String adminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
