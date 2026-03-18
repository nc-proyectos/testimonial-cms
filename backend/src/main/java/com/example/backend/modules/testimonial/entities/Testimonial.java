package com.example.backend.modules.testimonial.entities;

import com.example.backend.modules.category.entities.Category;
import com.example.backend.modules.category.entities.Tag;
import com.example.backend.modules.media.entities.MediaAsset;
import com.example.backend.shared.enums.TestimonialStatus;
import com.example.backend.shared.enums.TestimonialType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "testimonials")
@Builder
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String authorName;

    private String authorRole; // ej: "Alumno - Curso de Java"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TestimonialStatus status = TestimonialStatus.DRAFT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestimonialType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "testimonial_tags",
            joinColumns = @JoinColumn(name = "testimonial_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "testimonial", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<MediaAsset> mediaAssets = new HashSet<>();

    private String rejectionReason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime publishedAt;
}
