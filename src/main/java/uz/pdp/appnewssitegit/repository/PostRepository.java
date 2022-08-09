package uz.pdp.appnewssitegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssitegit.entity.Post;
import uz.pdp.appnewssitegit.entity.User;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    boolean existsByTitle(String title);

    Optional<Post> findByTitle(String title);

//    boolean deleteByIdAndCreatedById(Long id, Long createdBy_id);
}
