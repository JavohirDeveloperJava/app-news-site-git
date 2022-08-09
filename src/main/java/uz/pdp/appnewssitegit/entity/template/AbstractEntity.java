package uz.pdp.appnewssitegit.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.appnewssitegit.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data

public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updateAt;

    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;


    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updateBy;
}
