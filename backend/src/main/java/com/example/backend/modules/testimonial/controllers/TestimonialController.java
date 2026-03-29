package com.example.backend.modules.testimonial.controllers;

import com.example.backend.modules.testimonial.models.dtos.*;
import com.example.backend.modules.testimonial.models.entities.Testimonial;
import com.example.backend.modules.testimonial.models.entities.TestimonialStatus;
import com.example.backend.modules.testimonial.services.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@RequiredArgsConstructor
@Tag(name = "Testimonial", description = "Gestion de testimonios")


//@SecurityRequirement(name = "bearer-key")





public class TestimonialController {
//todo:
//    GET    /api/testimonials          # listar publicados (con filtros)
//    GET    /api/testimonials/{id}     # detalle
//    POST   /api/testimonials          # crear (EDITOR+)
//    PUT    /api/testimonials/{id}     # editar (EDITOR+)
//    DELETE /api/testimonials/{id}     # eliminar (ADMIN)

    private final TestimonialService testimonialService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener testimonio por id")
    public TestimonialResponse getById(@PathVariable Long id){
        return testimonialService.getById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener testimonios por filtro de busqueda")
    public Page<TestimonialResponse> getByFilters(
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long tagId,
            @RequestParam(required = false) TestimonialStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return testimonialService.findByFilters(rating,categoryId,tagId,status, page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo testimonio")
    public TestimonialResponse create(@RequestBody TestimonialRequest testimonialRequest){
        return testimonialService.create(testimonialRequest);
    }

    @PostMapping("/public/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo testimonio (publico)")
    public TestimonialResponse createPublic(@PathVariable Long categoryId,@RequestBody PublicTestimonialRequest testimonialRequest){
        return testimonialService.createPublic(categoryId,testimonialRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualizar un testimonio")
    public TestimonialResponse update(@PathVariable Long id, @RequestBody TestimonialUpdateRequest testimonialRequest){
        return testimonialService.update(id, testimonialRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Borrar un testimonio por id")
    public void delete(@PathVariable Long id){
        testimonialService.delete(id);
    }

}
