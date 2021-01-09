package com.nwpu.melonbookkeeping.controller.api;

import com.nwpu.melonbookkeeping.common.ErrorCodeEnum;
import com.nwpu.melonbookkeeping.config.annotation.TokenToUser;
import com.nwpu.melonbookkeeping.controller.api.ov.BookkeepingVO;
import com.nwpu.melonbookkeeping.controller.api.param.BookkeepingAddParam;
import com.nwpu.melonbookkeeping.controller.api.param.BookkeepingDeleteParam;
import com.nwpu.melonbookkeeping.entity.Bookkeeping;
import com.nwpu.melonbookkeeping.entity.User;
import com.nwpu.melonbookkeeping.service.BookkeepingService;
import com.nwpu.melonbookkeeping.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author noorall
 * @date 2021/1/92:51 下午
 * @Description: 记账记录相关API
 */
@RestController
@Api(value = "v1", tags = "2.记账记录相关API")
@RequestMapping("/api/v1")
public class BookkeepingAPI {
    @Autowired
    BookkeepingService bookkeepingService;

    @ApiOperation(value = "获取所有记账记录", notes = "获取用户的所有记账记录，一般在登录时调用")
    @GetMapping("/bookkeeping/getAll")
    public Result<List<BookkeepingVO>> getAll(@TokenToUser User user) {
        Result<List<BookkeepingVO>> result;
        if (user == null) {
            result = new Result<>(ErrorCodeEnum.USER_TOKEN_INVALID.getError());
        } else {
            List<BookkeepingVO> bookkeepingVOList = new ArrayList<>();
            List<Bookkeeping> bookkeepingList = bookkeepingService.getAllBookkeepingByUser(user);
            //坑 不支持直接复制List
            for (Bookkeeping bookkeeping : bookkeepingList) {
                BookkeepingVO bookkeepingVO = new BookkeepingVO();
                BeanUtils.copyProperties(bookkeeping, bookkeepingVO);
                bookkeepingVOList.add(bookkeepingVO);
            }
            result = new Result<>(bookkeepingVOList);
        }
        return result;
    }

    @ApiOperation(value = "新增记账记录", notes = "新增用户的记账记录")
    @PostMapping("/bookkeeping/addOne")
    public Result<String> addOne(@TokenToUser User user, @RequestBody @Valid BookkeepingAddParam bookkeepingAddParam) {
        Result<String> result;
        if (user == null) {
            result = new Result<>(ErrorCodeEnum.USER_TOKEN_INVALID.getError());
        } else {
            Bookkeeping bookkeeping = new Bookkeeping();
            BeanUtils.copyProperties(bookkeepingAddParam, bookkeeping);
            bookkeeping.setUser(user);
            if (bookkeepingService.addOneBookkeeping(bookkeeping)) {
                result = new Result<>();
            } else {
                result = new Result<>(ErrorCodeEnum.BOOKKEEPING_ADD_ERROR.getError());
            }
        }
        return result;
    }

    @ApiOperation(value = "删除记账记录", notes = "删除指定的记账记录")
    @PostMapping("/bookkeeping/deleteOne")
    public Result<String> deleteOne(@TokenToUser User user, @RequestBody @Valid BookkeepingDeleteParam bookkeepingDeleteParam) {
        Result<String> result;
        if (user == null) {
            result = new Result<>(ErrorCodeEnum.USER_TOKEN_INVALID.getError());
        } else {
            if (bookkeepingService.deleteBookkeepingByIdAndUser(bookkeepingDeleteParam.getId(), user)) {
                result = new Result<>();
            } else {
                result = new Result<>(ErrorCodeEnum.BOOKKEEPING_DELETE_ERROR.getError());
            }
        }
        return result;
    }
}
