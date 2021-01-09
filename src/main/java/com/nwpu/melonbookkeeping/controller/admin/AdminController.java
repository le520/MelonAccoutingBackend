package com.nwpu.melonbookkeeping.controller.admin;

import com.nwpu.melonbookkeeping.entity.Admin;
import com.nwpu.melonbookkeeping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * 登录页面
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "/admin/login";
    }

    /**
     * 后台首页
     *
     * @return 后台首页
     */
    @RequestMapping({"/index", "/", ""})
    public String index() {
        return "/admin/index";
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
            return "redirect:/admin/index";
        } else {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误！");
            return "redirect:/admin/login";
        }
    }
}
