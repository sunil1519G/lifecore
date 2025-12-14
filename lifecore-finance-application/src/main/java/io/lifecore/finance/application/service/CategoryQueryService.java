package io.lifecore.finance.application.service;

import io.lifecore.finance.application.dto.CategoryDto;
import io.lifecore.finance.application.mapper.CategoryMapper;
import io.lifecore.finance.application.repository.CategoryRepository;
import io.lifecore.finance.domain.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryQueryService {

    private final CategoryRepository categoryRepository;

    public CategoryQueryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> listCategories() {
        List<Category> categories = categoryRepository.findAllByOrderByNameAsc();
        return categories.stream()
            .map(CategoryMapper::toDto)
            .collect(Collectors.toList());
    }
}
