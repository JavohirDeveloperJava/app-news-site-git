package uz.pdp.appnewssitegit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewssitegit.entity.User;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.LoginDto;
import uz.pdp.appnewssitegit.payload.RegisterDto;
import uz.pdp.appnewssitegit.security.JwtProvider;
import uz.pdp.appnewssitegit.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> registerUSer(@Valid @RequestBody RegisterDto registerDto){
        ApiResponse apiResponse=authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        User user=(User)authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getLavozim());
        return ResponseEntity.ok(token);
    }

}
