package com.project.modac.dto;

import com.project.modac.domain.Category;
import com.project.modac.domain.Gender;
import lombok.Data;

@Data
public class PostSearchCondition {
    private Category category;

    private Gender gender;

    private Integer goe;

    private Integer loe;

}
