package com.dyd.springdata.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Resource
    JdbcTemplate jdbcTemplate;

    //查询所有信息
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql="select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    //添加用户
    @GetMapping("/adduser")
    public String addUser(){
        String sql="insert into mybatis.user(id,name,pwd) values(5,'user5','123')";
        jdbcTemplate.update(sql);
        return "update_success!";
    }

    //添加用户
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql="update mybatis.user set name=?,pwd=? where id="+id;
        Object[] objects=new Object[2];
        objects[0]="user6";
        objects[1]="123456";
        jdbcTemplate.update(sql,objects);
        return "updateUser_success!";
    }

    //添加用户
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql = "delete from mybatis.user where id=?";
        jdbcTemplate.update(sql,id);
        return "deleteUser_success!";
    }
}