package hello.hello_spring.service;

import hello.hello_spring.domain.Image;
import hello.hello_spring.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image createImage(Image image){
        return imageRepository.save(image);
    }

    public Optional<Image> getImageByPath(String Path){
        return imageRepository.findByPath(Path);
    }
}
