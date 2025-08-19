package com.example.schema.controller;

import com.example.schema.entity.UserCustom;
import com.example.schema.entity.UserDefault;
import com.example.schema.entity.UserMixed;
import com.example.schema.repository.UserCustomRepo;
import com.example.schema.repository.UserDefaultRepo;
import com.example.schema.repository.UserMixedRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDefaultRepo defaultRepo;
    private final UserCustomRepo customRepo;
    private final UserMixedRepo mixedRepo;

    public UserController(UserDefaultRepo defaultRepo, UserCustomRepo customRepo, UserMixedRepo mixedRepo) {
        this.defaultRepo = defaultRepo;
        this.customRepo = customRepo;
        this.mixedRepo = mixedRepo;
    }

    @PostMapping("/default/{name}")
    public UserDefault addDefault(@PathVariable String name) {
        UserDefault u = new UserDefault();
        u.setName(name);
        return defaultRepo.save(u);
    }

    @PostMapping("/custom/{email}")
    public UserCustom addCustom(@PathVariable String email) {
        UserCustom u = new UserCustom();
        u.setEmail(email);
        return customRepo.save(u);
    }

    @PostMapping("/mixed/{phone}")
    public UserMixed addMixed(@PathVariable String phone) {
        UserMixed u = new UserMixed();
        u.setPhone(phone);
        return mixedRepo.save(u);
    }

    @GetMapping("/all")
    public String getAll() {
        return "Default=" + defaultRepo.count() +
               ", Custom=" + customRepo.count() +
               ", Mixed=" + mixedRepo.count();
    }
}
