package hello.hello_spring.controller;

import hello.hello_spring.domain.Post;
import hello.hello_spring.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 목록 페이지
    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "posts/post"; // templates/post.html 반환
    }


    // 게시물 생성 폼 페이지
    @GetMapping("/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/post-create"; // templates/posts/post-create.html 반환
    }

    // 게시물 생성
    @PostMapping("/new")
    public String createPost(PostForm form) {
//    public String createPost(Post form) { 안되는 코드
        Post post = new Post();
        post.update(form.getTitle(), form.getContent());

        postService.createPost(post);
        return "redirect:/posts";
    }

    // 게시물 조회
    @GetMapping("/{id}")
    public String showPost(Model model, @PathVariable("id") Long id){
        Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID: " + id));
        model.addAttribute("post", post);
        return "posts/post-detail";
    }

    // 게시물 수정 폼 페이지
    @GetMapping("/edit/{id}")
    public String showEditPostForm(Model model, @PathVariable("id") Long id){
        Post post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID: " + id));
        model.addAttribute("post", post);
        return "posts/post-update";
    }

    // 게시물 수정
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable("id") Long id, PostForm form){
        Post post = new Post();
        post.update(form.getTitle(), form.getContent());

        postService.updatePost(id, post);
        return "redirect:/posts";
    }

    // 게시물 삭제
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
