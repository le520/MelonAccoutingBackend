package com.nwpu.melonbookkeeping.controller.admin;

import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.service.AdminService;
import com.nwpu.melonbookkeeping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping({"/", "/index", ""})
    public String index(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "admin/user/index";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        return "admin/user/data-table";
    }
}
