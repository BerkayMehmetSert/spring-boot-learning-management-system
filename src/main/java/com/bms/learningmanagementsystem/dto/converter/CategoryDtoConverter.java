package com.bms.learningmanagementsystem.dto.converter;

import com.bms.learningmanagementsystem.core.dto.DtoConverter;
import com.bms.learningmanagementsystem.dto.CategoryDto;
import com.bms.learningmanagementsystem.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter implements DtoConverter<CategoryDto, Category> {
    private final CategoryCourseDtoConverter courseDtoConverter;

    public CategoryDtoConverter(CategoryCourseDtoConverter courseDtoConverter) {
        this.courseDtoConverter = courseDtoConverter;
    }

    @Override
    public CategoryDto convert(Category from) {
        return new CategoryDto(
                from.getId(),
                from.getCategoryNo(),
                from.getDescription(),
                from.getCreatedDate(),
                from.getCourses() != null ? courseDtoConverter.convert(from.getCourses()) : null
        );
    }
}
