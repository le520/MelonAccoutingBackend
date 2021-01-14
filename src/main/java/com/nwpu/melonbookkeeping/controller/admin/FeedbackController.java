package com.nwpu.melonbookkeeping.controller.admin;

import com.nwpu.melonbookkeeping.entity.Feedback;
import com.nwpu.melonbookkeeping.service.FeedbackService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author noorall
 * @date 2021/1/11 5:01 下午
 * @Description: 反馈控制器
 */
@Controller
@RequestMapping("/admin/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    /**
     * 反馈首页
     *
     * @param model
     * @return 反馈页面视图
     */
    @RequestMapping({"/", "index", ""})
    public String index(Model model, HttpSession session) {
        session.setAttribute("unRead", feedbackService.getUNReadCount());
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        return "admin/feedback/index";
    }

    /**
     * 反馈修改
     *
     * @param id                 反馈ID
     * @param redirectAttributes
     * @return 将反馈修改为已读后返回上一页，并设置回执参数
     */
    @RequestMapping("/modify/{id}")
    public String read(@PathVariable("id") int id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (feedbackService.setReadById(id)) {
            redirectAttributes.addFlashAttribute("success", "success");
            session.setAttribute("unRead", feedbackService.getUNReadCount());
        } else {
            redirectAttributes.addFlashAttribute("error", "error");
        }
        return "redirect:/admin/feedback/";
    }

    /**
     * 查看反馈详情
     *
     * @param id    反馈ID
     * @param model
     * @return 详情页面
     */
    @RequestMapping("/details/{id}")
    public String details(@PathVariable("id") int id, Model model, HttpSession session) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        model.addAttribute("feedback", feedback);
        feedbackService.setReadById(id);
        session.setAttribute("unRead", feedbackService.getUNReadCount());
        return "admin/feedback/details";
    }
}
