package com.codestates.preproject.article;

import com.codestates.preproject.comment.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-05T17:43:10+0900",
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
        article.email( articlePost.getEmail() );

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
        article.email( articlePatch.getEmail() );

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
        articleResponse.email( article.getEmail() );
        articleResponse.createdAt( article.getCreatedAt() );
        List<Comment> list = article.getComments();
        if ( list != null ) {
            articleResponse.comments( new ArrayList<Comment>( list ) );
        }

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
}
