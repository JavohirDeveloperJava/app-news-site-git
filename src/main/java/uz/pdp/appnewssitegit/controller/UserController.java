package uz.pdp.appnewssitegit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.RegisterDto;
import uz.pdp.appnewssitegit.payload.UserDto;
import uz.pdp.appnewssitegit.service.AuthService;
import uz.pdp.appnewssitegit.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping("/register")
    public HttpEntity<?> registerUSer(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse=userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
