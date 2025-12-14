package io.lifecore.finance.web.controller;

import io.lifecore.finance.application.dto.CategoryDto;
import io.lifecore.finance.application.service.CategoryQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryQueryService categoryQueryService;

    public CategoryController(CategoryQueryService categoryQueryService) {
        this.categoryQueryService = categoryQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryQueryService.listCategories();
        return ResponseEntity.ok(categories);
    }
}
