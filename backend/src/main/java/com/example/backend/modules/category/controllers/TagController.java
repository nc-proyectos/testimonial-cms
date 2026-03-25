package com.example.backend.modules.category.controllers;

import com.example.backend.modules.category.models.dtos.TagRequest;
import com.example.backend.modules.category.models.dtos.TagResponse;
import com.example.backend.modules.category.services.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
@Tag(name = "Tags", description = "Gestion de Tags")
public class TagController {
    //todo: falta end point con filtro de busqueda

    private final TagService tagService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener tag por id")
    public TagResponse getById(@PathVariable Long id){
        return tagService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un tag nuevo")
    TagResponse create(@RequestBody TagRequest tagRequest){
        return tagService.create(tagRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Borrar tag por id")
    public void delete(@PathVariable Long id){
        tagService.delete(id);
    }
}
