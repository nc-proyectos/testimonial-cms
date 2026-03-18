package com.example.backend.modules.media.entities;

import com.example.backend.modules.testimonial.entities.Testimonial;
import com.example.backend.shared.enums.MediaProvider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media_assets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    private String publicId; // ID en Cloudinary para poder eliminarlo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaProvider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testimonial_id", nullable = false)
    private Testimonial testimonial;
}
