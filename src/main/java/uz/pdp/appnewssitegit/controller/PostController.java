package uz.pdp.appnewssitegit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssitegit.entity.Post;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.PostDto;
import uz.pdp.appnewssitegit.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping("/creat")
    public HttpEntity<?> creatPost(@Valid @RequestBody PostDto postDto){
        System.out.println(postDto);
        ApiResponse apiResponse=postService.creatPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);    }


    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id,@Valid @RequestBody PostDto postDto){
        System.out.println(postDto);
        ApiResponse apiResponse=postService.editPost(id,postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);    }


    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> deletPost(@PathVariable Long id){
        ApiResponse apiResponse=postService.delet(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);    }


}
