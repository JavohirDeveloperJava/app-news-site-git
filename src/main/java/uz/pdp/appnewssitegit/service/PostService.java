package uz.pdp.appnewssitegit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitegit.entity.Post;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.PostDto;
import uz.pdp.appnewssitegit.repository.PostRepository;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;


    public ApiResponse creatPost(PostDto postDto) {
        boolean exists = postRepository.existsByTitle(postDto.getTitle());
        if (exists){
            return new ApiResponse("Bunday maqola bor",false);
        }
        Post post1=new Post();
        post1.setText(postDto.getText());
        post1.setTitle(postDto.getTitle());
        post1.setUrl(postDto.getUrl());
        postRepository.save(post1);
        return new ApiResponse("Maqola qoshildi ",true);
    }

    public ApiResponse editPost(Long id,PostDto postDto) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (!postOptional.isPresent()){
            return new ApiResponse("Bunday maqola mavjut emas",false);
        }
        Post post = postOptional.get();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post ozgardi",true);
    }

    public ApiResponse delet(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()){
            postRepository.deleteById(id);
            return new ApiResponse("Post ochirildi",true);
        }
        return new ApiResponse("Bunday maqola mavjut emas",false);


    }
}
