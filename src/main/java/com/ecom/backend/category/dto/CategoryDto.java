package com.ecom.backend.category.dto;

import com.ecom.backend.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  CategoryDto extends AbstractEntity {
    private String category_title;
}
