package com.v6.yeogaekgi.qna.entity;

import com.v6.yeogaekgi.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@Table(name = "qna")
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="qna_no")
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String images;

    @Column(name="qna_date", nullable=false)
    @CurrentTimestamp
    private Timestamp qnaDate;

    @Column
    private String reply;

    @Column(name="reply_date")
    private Timestamp replyDate;

    @Column(nullable=false)
    private boolean status=false;

    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
}
