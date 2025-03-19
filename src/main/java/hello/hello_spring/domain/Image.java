package hello.hello_spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="images")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String path;

    public void update(String originFileName, String path){
        this.originFileName = originFileName;
        this.path = path;
    }
}
