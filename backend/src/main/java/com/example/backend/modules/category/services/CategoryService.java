package com.example.backend.modules.category.services;

import com.example.backend.modules.category.models.dtos.CategoryRequest;
import com.example.backend.modules.category.models.dtos.CategoryResponse;
import com.example.backend.modules.category.models.entities.Category;
import com.example.backend.modules.category.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse>findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::toResponse)
                .toList();
    }

    public CategoryResponse findById(Long id){
        Category category=categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no se encontro id "+id));
        return toResponse(category);
    }

    public CategoryResponse create(CategoryRequest categoryRequest){
        Category category=categoryRepository.save(toEntity(categoryRequest));
        return toResponse(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryResponse toResponse(Category c) {
        return new CategoryResponse(
                c.getId(),
                c.getName(),
                c.getSlug(),
                c.getType().name()
        );
    }
    private Category toEntity(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .slug(request.slug())
                .type(request.type())
                .build();
    }
}
