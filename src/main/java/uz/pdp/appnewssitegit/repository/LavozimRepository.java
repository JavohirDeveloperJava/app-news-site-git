package uz.pdp.appnewssitegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssitegit.entity.Lavozim;

import java.util.Optional;

public interface LavozimRepository extends JpaRepository<Lavozim,Long> {
    Optional<Lavozim> findByName(String name);

    boolean existsByName(String name);
}
