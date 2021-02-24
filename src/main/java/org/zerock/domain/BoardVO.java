package org.zerock.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
//@Accessors(chain = true, fluent = true)
@Accessors(chain = true)
public class BoardVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}