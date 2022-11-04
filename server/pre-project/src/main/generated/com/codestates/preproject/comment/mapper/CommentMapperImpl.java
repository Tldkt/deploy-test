package com.codestates.preproject.comment.mapper;

import com.codestates.preproject.comment.dto.CommentPatchDto;
import com.codestates.preproject.comment.dto.CommentPostDto;
import com.codestates.preproject.comment.dto.CommentResponseDto;
import com.codestates.preproject.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T14:49:12+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentPostToComment(CommentPostDto commentPostDto) {
        if ( commentPostDto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.email( commentPostDto.getEmail() );
        comment.content( commentPostDto.getContent() );
        comment.articleId( commentPostDto.getArticleId() );

        return comment.build();
    }

    @Override
    public Comment commentPatchToComment(CommentPatchDto commentPatchDto) {
        if ( commentPatchDto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentId( commentPatchDto.getCommentId() );
        comment.email( commentPatchDto.getEmail() );
        comment.content( commentPatchDto.getContent() );

        return comment.build();
    }

    @Override
    public CommentResponseDto commentToCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDto.CommentResponseDtoBuilder commentResponseDto = CommentResponseDto.builder();

        commentResponseDto.commentId( comment.getCommentId() );
        commentResponseDto.content( comment.getContent() );
        commentResponseDto.email( comment.getEmail() );
        commentResponseDto.vote( comment.getVote() );
        commentResponseDto.createdAt( comment.getCreatedAt() );
        commentResponseDto.modifiedAt( comment.getModifiedAt() );
        commentResponseDto.articleId( comment.getArticleId() );

        return commentResponseDto.build();
    }

    @Override
    public List<CommentResponseDto> CommentsToCommentResponseDtos(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentResponse( comment ) );
        }

        return list;
    }
}
