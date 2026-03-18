package com.example.backend.modules.moderation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testimonials")
public class ModerationControllers {
    //Lo separo de testimonial para que la lógica de negocio
    // de moderación no ensucie el CRUD básico.

//todo:
//    PATCH  /api/testimonials/{id}/approve   # ADMIN/EDITOR
//    PATCH  /api/testimonials/{id}/reject    # ADMIN/EDITOR
//    GET    /api/testimonials/pending        # listar pendientes



}
