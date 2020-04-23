package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Random;

/**
* Created by CodeGenerator on 2020/04/20.
*/
@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ApiOperation("缓存用户列表")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        redisUtil.lSet("user",list);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("获取缓存中用户列表")
    @PostMapping("/getUsers")
    public Result getUsers(){
        List<Object> list = redisUtil.lGet("user",0,-1);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("generateCode")
    public Result generateCode(){
        Random random = new Random();
        Integer code = random.nextInt(9999);
        redisUtil.set("code",code,600);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("consumptionCode")
    public Result consumptionCode(@RequestParam @NotNull Integer code){
        Object coded =  redisUtil.get("code");

        if(coded != null){
            if(coded.equals(code)){
                redisUtil.del("code");
                return ResultGenerator.genSuccessResult("验证成功");
            }
            return ResultGenerator.genFailResult("验证码错误");
        }else{
            return ResultGenerator.genFailResult("请先获取验证码");
        }
    }
}
