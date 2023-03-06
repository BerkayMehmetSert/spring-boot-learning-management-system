package com.bms.learningmanagementsystem.service.rules;

import com.bms.learningmanagementsystem.core.exceptions.BusinessException;
import com.bms.learningmanagementsystem.core.exceptions.NotFoundException;
import com.bms.learningmanagementsystem.model.Category;
import com.bms.learningmanagementsystem.repositoy.CategoryRepository;
import com.bms.learningmanagementsystem.service.constants.BusinessMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryBusinessRules {
    private final CategoryRepository categoryRepository;

    public CategoryBusinessRules(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void checkIfCategoryExists(String description) {
        var category = categoryRepository.existsByDescriptionIgnoreCase(description);

        if (category) throw new BusinessException(BusinessMessages.CATEGORY_ALREADY_EXISTS);
    }

    public Category checkIfCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.CATEGORY_NOT_FOUND));
    }

    public Category checkIfCategoryByCategoryNo(final String categoryNo) {
        return categoryRepository.findByCategoryNo(categoryNo)
                .orElseThrow(() -> new NotFoundException(BusinessMessages.CATEGORY_NOT_FOUND));
    }

    public void checkIfCategoryListIsEmpty(List<Category> categories) {
        if (categories.isEmpty()) throw new NotFoundException(BusinessMessages.CATEGORY_LIST_IS_EMPTY);
    }
}
