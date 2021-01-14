package com.nwpu.melonbookkeeping.controller.admin;

import com.nwpu.melonbookkeeping.entity.Feedback;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.service.AdminService;
import com.nwpu.melonbookkeeping.service.FeedbackService;
import com.nwpu.melonbookkeeping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author noorall
 * @date 2021/1/96:43 下午
 * @Description: 用户管理相关控制器
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FeedbackService feedbackService;

    /**
     * 用户首页
     *
     * @param model model
     * @return 用户首页
     */
    @RequestMapping({"/", "/index", ""})
    public String index(Model model, HttpSession session) {
        session.setAttribute("unRead", feedbackService.getUNReadCount());
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "admin/user/index";
    }

    /**
     * 用户修改详情
     *
     * @param model model
     * @param id    用户id
     * @return 用户修改页面
     */
    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin/user/index";
        } else {
            model.addAttribute("user", user);
        }
        return "admin/user/modify";
    }

    /**
     * 用户修改
     *
     * @param user               用户信息
     * @param redirectAttributes 重定向参数，反馈修改结果
     * @return 修改结果
     */
    @PostMapping("/modify")
    public String modify(User user, RedirectAttributes redirectAttributes) {
        User realUser = userService.getUserById(user.getId());
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "用户不存在");
            return "redirect:/admin/user/index";
        } else {
            realUser.setStatus(user.getStatus());
            if (!userService.setUserStatus(realUser)) {
                redirectAttributes.addFlashAttribute("error", "数据保存失败");
            } else {
                redirectAttributes.addFlashAttribute("success", "修改成功");
            }
            return "redirect:/admin/user/index";
        }
    }
}
