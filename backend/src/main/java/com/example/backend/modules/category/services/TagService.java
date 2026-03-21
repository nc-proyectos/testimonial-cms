package com.example.backend.modules.category.services;

import com.example.backend.modules.category.models.dtos.TagRequest;
import com.example.backend.modules.category.models.dtos.TagResponse;
import com.example.backend.modules.category.models.entities.Tag;
import com.example.backend.modules.category.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagResponse>findAll(){
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(this::toResponse)
                .toList();
    }

    public TagResponse findById(Long id){
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no se encontro el id "+id));
        return toResponse(tag);
    }

    public TagResponse create(TagRequest t){
        Tag tag=tagRepository.save(toEntity(t));
        return toResponse(tag);
    }

    public void delete(Long id){
        tagRepository.deleteById(id);
    }

    public TagResponse toResponse(Tag t) {
        return new TagResponse(
                t.getId(),
                t.getName()
        );
    }

    private Tag toEntity(TagRequest request) {
        return Tag.builder()
                .name(request.name())
                .build();
    }

}
