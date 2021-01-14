package com.nwpu.melonbookkeeping.controller.api;

import com.nwpu.melonbookkeeping.common.ErrorCodeEnum;
import com.nwpu.melonbookkeeping.config.annotation.TokenToUser;
import com.nwpu.melonbookkeeping.controller.api.vo.AppInfoVO;
import com.nwpu.melonbookkeeping.controller.api.param.FeedbackParam;
import com.nwpu.melonbookkeeping.entity.App;
import com.nwpu.melonbookkeeping.entity.Feedback;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.service.AppService;
import com.nwpu.melonbookkeeping.service.FeedbackService;
import com.nwpu.melonbookkeeping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author noorall
 * @date 2021/1/11 4:24 下午
 * @Description: 公用API
 */
@RestController
@Api(value = "v1", tags = "3.公用服务接口")
public class PublicAPI {
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    AppService appService;

    @ApiOperation(value = "用户反馈API", notes = "用户反馈API，可以被登录或未登录用户调用")
    @PostMapping("/api/v1/public/feedback")
    public Result<String> feedback(@TokenToUser User user, @RequestBody @Valid FeedbackParam feedbackParam) {
        Result<String> result;
        Feedback feedback = new Feedback();
        if (user != null) {
            feedback.setUserId(user.getId());
        }
        feedback.setContent(feedbackParam.getContent());
        if (feedbackService.addFeedback(feedback)) {
            result = new Result<>();
        } else {
            result = new Result<>(ErrorCodeEnum.FEEDBACK_ERROR.getError());
        }
        return result;
    }

    @ApiOperation(value = "获取最新APP版本API", notes = "获取最新APP版本API，可以被登录或未登录用户调用")
    @GetMapping("/api/v1/public/app")
    public Result<AppInfoVO> getAppInfo() {
        Result<AppInfoVO> result;
        AppInfoVO appInfoVO = new AppInfoVO();
        App app = appService.getNewestApp();
        if (app != null) {
            BeanUtils.copyProperties(app, appInfoVO);
            result = new Result<>(appInfoVO);
        } else {
            result  = new Result<>(ErrorCodeEnum.APP_GET_INFO_ERROR.getError());
        }

        return result;
    }
}
