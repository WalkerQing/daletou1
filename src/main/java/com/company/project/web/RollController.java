package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Roll;
import com.company.project.service.BigLotResultService;
import com.company.project.service.RollService;
import com.company.project.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2020/04/23.
*/
@Api
@CrossOrigin
@RestController
@RequestMapping("/roll")
public class RollController {
    @Resource
    private RollService rollService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private BigLotResultService bigLotResultService;

    @PostMapping("/add")
    public Result add(Roll roll) {
        rollService.save(roll);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        rollService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Roll roll) {
        rollService.update(roll);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Roll roll = rollService.findById(id);
        return ResultGenerator.genSuccessResult(roll);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Roll> list = rollService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/generate")
    public Result generate() {
        List<Integer> redBall = new ArrayList<>(),blueBall = new ArrayList<>()
                ,redBalls = new ArrayList<>(),blueBalls = new ArrayList<>();
        Random random = new Random();

        setTotalKey();

        Integer totalCount = (Integer)redisUtil.get("rollCount");
        if(totalCount == 0){
            return ResultGenerator.genSuccessResult("请明天再来");
        }else if(totalCount > 0){

            for (int index = 1;index < 36; index ++){
                redBall.add(index);
            }
            for (int index = 1;index < 13; index ++){
                blueBall.add(index);
            }

            for (int index = 0;index < 5;index++){
                int i = random.nextInt(redBall.size());
                redBalls.add(redBall.get(i));
                redBall.remove(i);
            }
            for (int index = 0;index < 2;index++){
                int i = random.nextInt(blueBall.size());
                blueBalls.add(blueBall.get(i));
                blueBall.remove(i);
            }
            Collections.sort(redBalls);
            Collections.sort(blueBall);

            Roll roll = new Roll(redBalls.toString(),blueBalls.toString(),new Date());
            rollService.save(roll);
            redisUtil.set("rollCount",0);
            return ResultGenerator.genSuccessResult(roll);
        }
        return ResultGenerator.genSuccessResult();
    }

    public void setTotalKey(){
        if(redisUtil.hasKey("rollCount")){
            return;
        }else{
            Long expiresTime = getSecondsNextEarlyMorning();
            redisUtil.set("rollCount",1,expiresTime.intValue());
        }
    }
    public Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
