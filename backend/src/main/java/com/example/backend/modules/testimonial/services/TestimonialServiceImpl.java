package com.example.backend.modules.testimonial.services;

import com.example.backend.modules.category.models.entities.Tag;
import com.example.backend.modules.category.services.CategoryServiceImpl;
import com.example.backend.modules.category.services.TagServiceImpl;
import com.example.backend.modules.testimonial.models.dtos.PublicTestimonialRequest;
import com.example.backend.modules.testimonial.models.dtos.TestimonialRequest;
import com.example.backend.modules.testimonial.models.dtos.TestimonialResponse;
import com.example.backend.modules.testimonial.models.dtos.TestimonialUpdateRequest;
import com.example.backend.modules.testimonial.models.entities.Testimonial;
import com.example.backend.modules.testimonial.repositories.TestimonialRepository;
import com.example.backend.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final CategoryServiceImpl categoryService;
    private final TagServiceImpl tagService;

    @Override
    public TestimonialResponse getById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Testimonial not found"));
        return toResponse(testimonial);
    }

    @Override
    public TestimonialResponse create(TestimonialRequest t) {
        Testimonial testimonial = Testimonial.builder()
                .content(t.content())
                .authorName(t.authorName())
                .authorRole(t.authorRole())
                .rating(t.rating())
                .category(categoryService.applyCategory(t.categoryId()))
                .tags(tagService.applyTags(t.tagIds()))
                .build();
        return toResponse(testimonialRepository.save(testimonial));
    }

    @Override
    public TestimonialResponse createPublic(Long categoryId, PublicTestimonialRequest t) {
        Testimonial testimonial = Testimonial.builder()
                .content(t.content())
                .authorName(t.authorName())
                .authorRole(t.authorRole())
                .rating(t.rating())
                .category(categoryService.applyCategory(categoryId))
                .tags(new HashSet<>())
                .build();
        return toResponse(testimonialRepository.save(testimonial));
    }

    @Override
    public TestimonialResponse update(Long id, TestimonialUpdateRequest t) {
        Testimonial testimonial=testimonialRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Testimonial not found"));

        testimonial.setContent(t.content());
        testimonial.setAuthorName(t.authorName());
        testimonial.setAuthorRole(t.authorRole());
        testimonial.setRating(t.rating());
        testimonial.setCategory(categoryService.applyCategory(t.categoryId()));
        testimonial.setTags(tagService.applyTags(t.tagIds()));

        return toResponse(testimonialRepository.save(testimonial));
    }

    @Override
    public void delete(Long id){
        testimonialRepository.deleteById(id);
    }

    public TestimonialResponse toResponse(Testimonial t) {
        return new TestimonialResponse(
                t.getId(),
                t.getContent(),
                t.getAuthorName(),
                t.getAuthorRole(),
                t.getRating(),
                t.getStatus().name(),
                t.getCategory() != null ? t.getCategory().getName() : null,
                t.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet()),
                t.getMediaAssets().stream()
                        .map(m -> new TestimonialResponse.MediaAssetResponse(
                                m.getId(),
                                m.getUrl(),
                                m.getProvider().name()
                        ))
                        .collect(Collectors.toSet()),
                t.getCreatedAt(),
                t.getPublishedAt()
        );
    }
}
