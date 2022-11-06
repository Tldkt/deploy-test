package com.codestates.preproject.article;

import com.codestates.preproject.comment.dto.CommentResponseDto;
import com.codestates.preproject.comment.entity.Comment;
import com.codestates.preproject.reply.entity.Reply;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-07T00:10:41+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (BellSoft)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article articlePostToArticle(ArticlePost articlePost) {
        if ( articlePost == null ) {
            return null;
        }

        Article.ArticleBuilder article = Article.builder();

        article.title( articlePost.getTitle() );
        article.content( articlePost.getContent() );

        return article.build();
    }

    @Override
    public Article articlePatchToArticle(ArticlePatch articlePatch) {
        if ( articlePatch == null ) {
            return null;
        }

        Article.ArticleBuilder article = Article.builder();

        article.articleId( articlePatch.getArticleId() );
        article.title( articlePatch.getTitle() );
        article.content( articlePatch.getContent() );

        return article.build();
    }

    @Override
    public ArticleResponse articleToArticleResponse(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleResponse.ArticleResponseBuilder articleResponse = ArticleResponse.builder();

        if ( article.getArticleId() != null ) {
            articleResponse.articleId( article.getArticleId() );
        }
        articleResponse.title( article.getTitle() );
        articleResponse.content( article.getContent() );
        articleResponse.createdAt( article.getCreatedAt() );
        articleResponse.comments( commentListToCommentResponseDtoList( article.getComments() ) );

        return articleResponse.build();
    }

    @Override
    public List<ArticleResponse> articlesToArticleResponses(List<Article> articles) {
        if ( articles == null ) {
            return null;
        }

        List<ArticleResponse> list = new ArrayList<ArticleResponse>( articles.size() );
        for ( Article article : articles ) {
            list.add( articleToArticleResponse( article ) );
        }

        return list;
    }

    protected CommentResponseDto commentToCommentResponseDto(Comment comment) {
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
        List<Reply> list = comment.getReplies();
        if ( list != null ) {
            commentResponseDto.replies( new ArrayList<Reply>( list ) );
        }

        return commentResponseDto.build();
    }

    protected List<CommentResponseDto> commentListToCommentResponseDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponseDto> list1 = new ArrayList<CommentResponseDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentResponseDto( comment ) );
        }

        return list1;
    }
}
