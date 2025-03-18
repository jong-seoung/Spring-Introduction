package hello.hello_spring.service;

import hello.hello_spring.domain.Post;
import hello.hello_spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시물 생성
    public Post createPost(Post post){
        return postRepository.save(post);
    }

    // 게시물 전체 조회
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    // 특정 게시물 조회
    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }

    // 게시물 수정
    public Post updatePost(Long id, Post updatePost){
        return postRepository.findById(id)
                .map(post -> {
                    post.update(updatePost.getTitle(), updatePost.getContent());
                    return postRepository.save(post);
                })
                .orElseThrow(()-> new RuntimeException("Post not found"));
    }

    // 게시물 삭제
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

}
