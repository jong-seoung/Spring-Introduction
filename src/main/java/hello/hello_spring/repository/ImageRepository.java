package hello.hello_spring.repository;

import hello.hello_spring.domain.Image;
import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByPath(String path);
}
