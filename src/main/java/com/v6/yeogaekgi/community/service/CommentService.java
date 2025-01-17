package com.v6.yeogaekgi.community.service;


import com.v6.yeogaekgi.community.dto.CommentDTO;
import com.v6.yeogaekgi.community.dto.PostDTO;
import com.v6.yeogaekgi.community.entity.Comment;
import com.v6.yeogaekgi.community.entity.Post;
import com.v6.yeogaekgi.community.repository.CommentRepository;
import com.v6.yeogaekgi.community.repository.PostRepository;
import com.v6.yeogaekgi.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor

public class CommentService {
    private final CommentRepository repository;
    private final PostRepository postRepository;

    public List<CommentDTO> getListOfComment(Long postNo, Member member) {
        Post post = Post.builder().id(postNo).build();
        List<Comment> result = repository.findByPost(post);
        return result.stream().map(Comment -> entityToDto(Comment,member)).collect(Collectors.toList());

    }

    public CommentDTO getComment(Long commentId, Member member) {
        Optional<Comment> optionalComment = repository.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            return entityToDto(comment,member);
        } else return null;

    }
    @Transactional
    public Long register(CommentDTO commentDTO, Member member) {
        Comment comment = dtoToEntity(commentDTO, member);
        // commentCnt + 1
        Optional<Post> result = postRepository.findById(comment.getPost().getId());
        if (result.isPresent()) {
            Post post = result.get();
            post.changeCommentCnt(post.getCommentCnt()+1);
            postRepository.save(post);
        }
        repository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void modify(CommentDTO commentDTO) {
        Optional<Comment> result = repository.findById(commentDTO.getCommentId());
        if (result.isPresent()) {
            Comment comment = result.get();
            comment.changeComment(commentDTO.getContent());
            repository.save(comment);
        }

    }

    @Transactional
    public void remove(Long commentNo, Long postNo) {

        // commentCnt - 1
        Optional<Post> result = postRepository.findById(postNo);
        if (result.isPresent()) {
            Post post = result.get();
            post.changeCommentCnt(post.getCommentCnt()-1);
            postRepository.save(post);
        }
        repository.deleteById(commentNo);

    }


    public Comment dtoToEntity(CommentDTO commentDTO, Member member) {

        Comment comment = Comment.builder()
                .id(commentDTO.getCommentId())
                .content(commentDTO.getContent())
                .post(Post.builder().id(commentDTO.getPostId()).build())
                .member(member)
                .build();

        return comment;
    }

    public CommentDTO entityToDto(Comment comment, Member member) {

        CommentDTO commentDTO = CommentDTO.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .regDate(comment.getRegDate())
                .modDate(comment.getModDate())
                .postId(comment.getPost().getId())
                .memberId(comment.getMember().getId())
                .nickname(comment.getMember().getNickname())
                .code(comment.getMember().getCountry().getCode())
                .currentMemberId(member.getId())
                .currentMemberCode(member.getCountry().getCode())
                .build();

        return commentDTO;
    }


}