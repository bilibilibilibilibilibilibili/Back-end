package com.example.forumBackEnd.controller.admin;

import com.example.forumBackEnd.pojo.User;
import com.example.forumBackEnd.pojo.response.BasicResponse;
import com.example.forumBackEnd.service.adminService.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="admin/user",produces = "application/json; charset=UTF-8")
public class AccountController {
//    @Autowired
//    private UserService userService;

//    @PostMapping("select-users")
//    public BasicResponse selectUsers(@RequestBody ObjectNode request){
//        int offset = request.get("offset").asInt();
//        List<String> filters = request.get("filters");
//        List<User> userList = userService.selectUsers(offset);
//        if(userList.size() == 0){
//            return BasicResponse.getSuccessResponse("获取失败，结果为空。第"+offset+1+"页。",null);
//        }
//        return BasicResponse.getSuccessResponse("所有用户，第"+offset+1+"页",
//                userList);
//    }
}
