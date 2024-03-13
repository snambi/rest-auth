package org.github.snambi.restauth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping( path = "/getuser")
    @PreAuthorize("hasAuthority('SCOPE_com.example.entities/user.read')")
    public String getUser(){
        return "Access granted: reading user";
    }

    @PostMapping( path = "/updateuser")
    @PreAuthorize("hasAuthority('SCOPE_com.example.entities/user.update')")
    public String writeUser(){
        return "Access granted: updating user";
    }

    @GetMapping( path = "/search")
    public String searchUser(){
        return "Access granted: search";
    }

    @PostMapping(path = "/create")
    public String createUser(){
        return "Access granted: create";
    }
}
