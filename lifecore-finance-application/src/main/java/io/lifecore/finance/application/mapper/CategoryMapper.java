package io.lifecore.finance.application.mapper;

import io.lifecore.finance.application.dto.CategoryDto;
import io.lifecore.finance.domain.entity.Category;

public class CategoryMapper {

    private CategoryMapper() {
        // Utility class
    }

    public static CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDto(
            category.getCode(),
            category.getName()
        );
    }
}
