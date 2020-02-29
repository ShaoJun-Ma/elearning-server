package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 章节状态实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterStatus {
    private Integer id;
    //是否完成
    private Integer isFinished;
    //用户id
    private Integer uId;
    //章节id
    private Integer ccId;
    //课程详情id
    private Integer cdId;
}
