package com.codestates.preproject.comment.controller;

import com.codestates.preproject.article.Article;
import com.codestates.preproject.article.ArticleService;
import com.codestates.preproject.comment.dto.CommentDeleteDto;
import com.codestates.preproject.comment.dto.CommentPatchDto;
import com.codestates.preproject.comment.dto.CommentPostDto;
import com.codestates.preproject.comment.dto.CommentVoteDto;
import com.codestates.preproject.comment.entity.Comment;
import com.codestates.preproject.comment.mapper.CommentMapper;
import com.codestates.preproject.comment.service.CommentService;
import com.codestates.preproject.response.SingleResponseDto;
import com.codestates.preproject.security.userDetails.PrincipalDetailsService;
import com.codestates.preproject.user.repository.UserRepository;
import com.codestates.preproject.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    private final UserService userService;
    private final ArticleService articleService;

    private final UserRepository userRepository;


    public CommentController(CommentService commentService, CommentMapper commentMapper, UserService userService, ArticleService articleService, UserRepository userRepository) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.articleService = articleService;
        this.userRepository = userRepository;
    }

    // ?????? ?????? ??????
    @GetMapping("/read/{comment-id}")
    public ResponseEntity getComment(
            @PathVariable("comment-id") @Positive long commentId) {

        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponse(comment)),
                HttpStatus.OK
        );
    }

/*    // ?????? ?????? ??????
    @GetMapping("/all")
    public ResponseEntity getComments() {

        List<Comment> comments = commentService.findComments();

        return new ResponseEntity<>(
                commentMapper.commentsToCommentResponse(comments),
                HttpStatus.OK
        );
    }*/

    // ?????? ??????
    @PostMapping("/{article-id}")
    public ResponseEntity postComment(@Valid @RequestBody CommentPostDto commentPostDto,
                                      @PathVariable("article-id") Long articleId) {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(email);

        commentPostDto.setUserEmail(email);
        commentPostDto.setArticleId(articleId);

        Article article = articleService.findArticle(articleId);

        Comment comment = commentService.createComment(commentMapper.commentPostToComment(commentPostDto), email, articleId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponse(comment)),
                HttpStatus.CREATED);

    }

    // ?????? ??????
    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(
            @PathVariable("comment-id") @Positive long commentId,
            @Valid @RequestBody CommentPatchDto commentPatchDto,
            @AuthenticationPrincipal PrincipalDetailsService.PrincipalDetails principal) {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentPatchDto.setCommentId(commentId);
        commentPatchDto.setUserEmail(email);

        Comment comment = commentService.updateComment(commentMapper.commentPatchToComment(commentId, commentPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponse(comment)),
                HttpStatus.OK);
    }

    // ?????? ??????
    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(
            @PathVariable("comment-id") @Positive long commentId,
            @AuthenticationPrincipal PrincipalDetailsService.PrincipalDetails principal) {

        commentService.deleteComment(commentId);

        return new ResponseEntity<>(new CommentDeleteDto(commentId), HttpStatus.OK);

    }

    @PatchMapping("/vote/{comment-id}")
    public ResponseEntity voteComment(@PathVariable("comment-id") @Positive @NotNull long commentId,
                                      @Valid @RequestBody CommentVoteDto commentVoteDto) {

        Comment votedComment = commentService.voteComment(commentId, commentVoteDto.getVote());

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponse(votedComment)),
                HttpStatus.OK
        );
    }
}
