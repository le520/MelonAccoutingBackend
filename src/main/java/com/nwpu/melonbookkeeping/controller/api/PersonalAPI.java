package com.nwpu.melonbookkeeping.controller.api;

import com.nwpu.melonbookkeeping.common.ErrorCodeEnum;
import com.nwpu.melonbookkeeping.config.annotation.TokenToUser;
import com.nwpu.melonbookkeeping.controller.api.ov.UserInfoVO;
import com.nwpu.melonbookkeeping.controller.api.param.UserLoginParam;
import com.nwpu.melonbookkeeping.controller.api.param.UserModifyParam;
import com.nwpu.melonbookkeeping.controller.api.param.UserRegisterParam;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.service.UserService;
import com.nwpu.melonbookkeeping.util.Result;
import com.nwpu.melonbookkeeping.util.TokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "v1", tags = "1.用户相关接口")
@RequestMapping("/api/v1")
public class PersonalAPI {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "返回用户信息及accessToken")
    @PostMapping("/user/login")
    public Result<UserInfoVO> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        User user = userService.login(userLoginParam.getUserName(), userLoginParam.getPassword());
        Result<UserInfoVO> result;
        if (user == null) {
            result = new Result<>(ErrorCodeEnum.USER_LOGIN_ERROR_UNAUTHORIZED.getError());
        } else {
            if (user.getStatus() == 0) {
                result = new Result<>(ErrorCodeEnum.USER_LOGIN_ERROR_LOCKED.getError());
            } else {
                UserInfoVO userInfoVO = new UserInfoVO();
                BeanUtils.copyProperties(user, userInfoVO);
                userInfoVO.setAccessToken(TokenProvider.getToken(user));
                result = new Result<>(userInfoVO);
            }
        }
        return result;
    }

    @ApiOperation(value = "获取用户信息", notes = "返回用户信息(不包含Token信息)")
    @GetMapping("/user/info")
    public Result<UserInfoVO> getUserInfo(@TokenToUser User user) {
        Result<UserInfoVO> result;
        if (user == null) {
            result = new Result<>(ErrorCodeEnum.USER_TOKEN_INVALID.getError());
        } else {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user, userInfoVO);
            result = new Result<>(userInfoVO);
        }
        return result;
    }

    @ApiOperation(value = "用户注册", notes = "返回注册结果")
    @PostMapping("/user/register")
    public Result<String> register(@RequestBody @Valid UserRegisterParam userRegisterParam) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterParam,user);
        Result<String> result;
        if(userService.register(user)){
            result=new Result<>();
        }else{
            result=new Result<>(ErrorCodeEnum.USER_REGISTER_ERROR.getError());
        }
        return result;
    }

    @ApiOperation(value="修改信息",notes = "修改用户信息，包含头像")
    @PostMapping("/user/modify")
    public Result<String> modify(@TokenToUser User user, @RequestBody @Valid UserModifyParam userModifyParam){
        Result<String> result;
        if (user == null) {
            result = new Result<>(ErrorCodeEnum.USER_TOKEN_INVALID.getError());
        } else {
            if(userService.modify(user,userModifyParam)){
                result=new Result<>();
            }else{
                result=new Result<>(ErrorCodeEnum.USER_MODIFY_ERROR.getError());
            }
        }
        return result;
    }
}
