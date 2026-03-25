package com.example.backend.modules.testimonial.services;

import com.example.backend.modules.testimonial.models.dtos.PublicTestimonialRequest;
import com.example.backend.modules.testimonial.models.dtos.TestimonialRequest;
import com.example.backend.modules.testimonial.models.dtos.TestimonialResponse;
import com.example.backend.modules.testimonial.models.dtos.TestimonialUpdateRequest;

public interface TestimonialService {
    TestimonialResponse getById(Long id);
    TestimonialResponse create(TestimonialRequest t);
    TestimonialResponse createPublic(Long categoryId, PublicTestimonialRequest t);
    TestimonialResponse update(Long id, TestimonialUpdateRequest t);
    void delete(Long id);
}
