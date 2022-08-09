package uz.pdp.appnewssitegit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssitegit.aop.Chekpermission;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.LavozimDto;
import uz.pdp.appnewssitegit.payload.UserDto;
import uz.pdp.appnewssitegit.service.LavozimService;
import uz.pdp.appnewssitegit.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {
    @Autowired
    LavozimService lavozimService;


    @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")
    @PostMapping
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse apiResponse=lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
    @Chekpermission(huquq = "EDIT_LAVOZIM")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(@PathVariable Long id,@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse apiResponse=lavozimService.editLavozim(id,lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
