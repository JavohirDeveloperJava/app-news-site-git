package uz.pdp.appnewssitegit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.CommentDto;
import uz.pdp.appnewssitegit.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/creat")
    public HttpEntity<?> creat(@RequestBody CommentDto commentDto){
        ApiResponse apiResponse=commentService.creat(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> delet(@PathVariable Long id){
        ApiResponse apiResponse=commentService.delet(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }



}
