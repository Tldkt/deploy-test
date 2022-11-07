package com.codestates.preproject.reply.mapper;

import com.codestates.preproject.reply.dto.ReplyPatchDto;
import com.codestates.preproject.reply.dto.ReplyPostDto;
import com.codestates.preproject.reply.dto.ReplyResponseDto;
import com.codestates.preproject.reply.entity.Reply;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T22:39:05+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class ReplyMapperImpl implements ReplyMapper {

    @Override
    public Reply replyPostToReply(ReplyPostDto replyPostDto) {
        if ( replyPostDto == null ) {
            return null;
        }

        Reply.ReplyBuilder reply = Reply.builder();

        reply.email( replyPostDto.getEmail() );
        reply.replyContent( replyPostDto.getReplyContent() );
        reply.commentId( replyPostDto.getCommentId() );

        return reply.build();
    }

    @Override
    public Reply replyPatchToReply(long replyId, ReplyPatchDto replyPatchDto) {
        if ( replyPatchDto == null ) {
            return null;
        }

        Reply.ReplyBuilder reply = Reply.builder();

        if ( replyPatchDto != null ) {
            reply.replyId( replyPatchDto.getReplyId() );
            reply.email( replyPatchDto.getEmail() );
            reply.replyContent( replyPatchDto.getReplyContent() );
        }

        return reply.build();
    }

    @Override
    public ReplyResponseDto replyToResponse(Reply createdReply) {
        if ( createdReply == null ) {
            return null;
        }

        ReplyResponseDto.ReplyResponseDtoBuilder replyResponseDto = ReplyResponseDto.builder();

        replyResponseDto.replyId( createdReply.getReplyId() );
        replyResponseDto.replyContent( createdReply.getReplyContent() );
        replyResponseDto.email( createdReply.getEmail() );
        replyResponseDto.createdAt( createdReply.getCreatedAt() );
        replyResponseDto.modifiedAt( createdReply.getModifiedAt() );
        replyResponseDto.commentId( createdReply.getCommentId() );

        return replyResponseDto.build();
    }

    @Override
    public List<Reply> reliesToResponse(List<Reply> replies) {
        if ( replies == null ) {
            return null;
        }

        List<Reply> list = new ArrayList<Reply>( replies.size() );
        for ( Reply reply : replies ) {
            list.add( reply );
        }

        return list;
    }
}
