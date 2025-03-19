package hello.hello_spring.controller;

import hello.hello_spring.domain.Image;
import hello.hello_spring.service.ImageService;
import jakarta.annotation.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/image")
public class ImageUploadController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/upload/";
    private final ImageService imageService;

    public ImageUploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(UPLOAD_DIR, fileName).toString();

            new File(UPLOAD_DIR).mkdir();
            file.transferTo(new File(filePath));

            Image image = new Image();
            image.update(file.getOriginalFilename(), filePath);
            imageService.createImage(image);

            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("이미지 업로드 실패");
        }
    }
}
