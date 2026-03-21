package com.example.backend.modules.testimonial.repositories;

import com.example.backend.modules.testimonial.models.entities.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial,Long> {
}
