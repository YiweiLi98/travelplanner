package com.example.trip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.trip.entity.User;
import com.example.trip.mapper.UserMapper;
import com.example.trip.service.UserService;
import com.example.trip.utils.Result;
import com.example.trip.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    // @RequestBody --> 前端传来的得是json格式数据
    public Result login(@RequestBody User user) { // json map to java object

        User currentUser = userMapper.selectByName(user.getUsername());

        log.debug("try find user in our database in backend");
        if(currentUser == null) {
            return Result.error(20001, "no user");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password", currentUser.getPassword());
        User ret = userMapper.selectOne(queryWrapper);

        // Check password
        // TODO: Modify the check condition when the hash is stored into the database
        if(!ret.getPassword().equals(user.getPassword())) {
            return Result.error(20001, "wrong password");
        }
        if (ret == null) {
            return Result.error(20001, "wrong username or password");
        }

        // generate token
        String token = TokenUtils.generateToken(ret);
        ret.setToken(token);

        Result result = Result.ok().data("username",ret.getUsername()).data("avatar",ret.getAvatar()).data("id",ret.getId()).data("token",ret.getToken());
        return result;
    }

    @PostMapping("/logout")  // "token:xxx"
    public Result logout(){
        return Result.ok();
    }


    @GetMapping("/")
    public List<User> index(){
        List<User> all = userMapper.findAll();
        return all;
    }


    //when user are register
    @PostMapping("/register")
    public Result save(@RequestBody User user){
        User res = userMapper.selectByName(user.getUsername());
        if (res != null) {
            return  Result.error(20001, "used username");
        }
        int i = userMapper.insert(user);
        if(i == 1) {
            return Result.ok();
        } else {
            return Result.error(20001,"error insertion");
        }
    }

    @GetMapping("/userByName")
    public User GetByName(String username){
       QueryWrapper<User>queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("username",username);
 
       return  userMapper.selectOne(queryWrapper);
    }

    @PostMapping("/userByID")
    public int UpdateByName(@RequestBody User user){

    int i = userMapper.updateById(user);
    return i;
    }
}
