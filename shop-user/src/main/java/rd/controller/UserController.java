package rd.controller;

import com.rd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rd.service.impl.IUserService;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/{uid}")
    public User user(@PathVariable("uid") Integer uid){
        return userService.findByUid(uid);
    }
}
