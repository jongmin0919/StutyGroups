package org.zerock.project.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
