package uz.pdp.appnewssitegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appnewssitegit.entity.Comment;
import uz.pdp.appnewssitegit.entity.User;

import java.util.Optional;

public interface CommentRepositoriy extends JpaRepository<Comment,Long> {
    Optional<Comment> findByCreatedByAndId(User createdBy, Long id);
    @Query(value = "delete from comment where id=:id and created_by_id=:createdBy_id",nativeQuery = true)
    boolean deleteComment(Long id, Long createdBy_id);




}
