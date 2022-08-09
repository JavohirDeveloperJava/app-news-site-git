package uz.pdp.appnewssitegit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitegit.entity.Comment;
import uz.pdp.appnewssitegit.entity.Post;
import uz.pdp.appnewssitegit.entity.User;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.CommentDto;
import uz.pdp.appnewssitegit.repository.CommentRepositoriy;
import uz.pdp.appnewssitegit.repository.PostRepository;
import uz.pdp.appnewssitegit.security.JwtFilter;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepositoriy commentRepositoriy;
    @Autowired
    PostRepository postRepository;




    public ApiResponse creat(CommentDto commentDto) {
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (!optionalPost.isPresent()){
            return new ApiResponse("Bunday Post mavjut emas",false);
        }
        Comment comment=new Comment();
        comment.setText(commentDto.getText());
        comment.setPost(optionalPost.get());
        comment.setCreatedBy(User.getUser());
        commentRepositoriy.save(comment);

        return new ApiResponse("Comment saqlandi",true);
    }

    public ApiResponse delet(Long id) {
        System.out.println(User.getUser().getId());
        Optional<Comment> optionalComment = commentRepositoriy.findById(id);
        if (!optionalComment.isPresent()){
            return new ApiResponse("Comment topilmadi",false);
        }
//        Comment comment = optionalComment.get();
//        if (comment.)

        commentRepositoriy.deleteComment(id,User.getUser().getId());
        return new ApiResponse("ochdi",true);
    }
}
