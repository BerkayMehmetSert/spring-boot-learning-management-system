package com.bms.learningmanagementsystem.service;

import com.bms.learningmanagementsystem.core.utilities.generator.NumberGenerator;
import com.bms.learningmanagementsystem.core.utilities.helper.DateHelper;
import com.bms.learningmanagementsystem.core.utilities.rules.BusinessRules;
import com.bms.learningmanagementsystem.core.utilities.rules.ValidationRules;
import com.bms.learningmanagementsystem.dto.CategoryDto;
import com.bms.learningmanagementsystem.dto.converter.CategoryDtoConverter;
import com.bms.learningmanagementsystem.model.Category;
import com.bms.learningmanagementsystem.repositoy.CategoryRepository;
import com.bms.learningmanagementsystem.service.constants.ValidationMessages;
import com.bms.learningmanagementsystem.service.rules.CategoryBusinessRules;
import com.bms.learningmanagementsystem.service.validations.CategoryValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter converter;
    private final CategoryBusinessRules rules;
    private final CategoryValidator validator;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoConverter converter,
                           CategoryBusinessRules rules, CategoryValidator validator) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
        this.rules = rules;
        this.validator = validator;
    }

    public void createCategory(final String description) {
        BusinessRules.run(() -> rules.checkIfCategoryExists(description));

        ValidationRules.run(() -> {
            validator.notEmpty(description, ValidationMessages.CATEGORY_DESCRIPTION_REQUIRED);
            validator.charactersBetween(
                    description,
                    3,
                    50,
                    ValidationMessages.CATEGORY_DESCRIPTION_LENGTH
            );
        });

        var category = new Category(
                NumberGenerator.generateRandomString(10),
                description,
                DateHelper.getCurrentDate(),
                DateHelper.getCurrentDate()
        );

        categoryRepository.save(category);
    }

    public void updateCategory(final String id, final String description) {
        ValidationRules.run(() -> {
            validator.notEmpty(description, ValidationMessages.CATEGORY_DESCRIPTION_REQUIRED);
            validator.charactersBetween(
                    description,
                    3,
                    50,
                    ValidationMessages.CATEGORY_DESCRIPTION_LENGTH
            );
        });

        var category = getCategory(id);

        var updatedCategory = new Category(
                category.getId(),
                category.getCategoryNo(),
                description,
                category.getCreatedDate(),
                DateHelper.getCurrentDate(),
                category.getCourses()
        );

        categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(final String id) {
        categoryRepository.delete(getCategory(id));
    }

    public CategoryDto getCategoryById(final String id) {
        return converter.convert(getCategory(id));
    }

    public CategoryDto getCategoryByCategoryNo(final String categoryNo) {
        var category = BusinessRules.run(() -> rules.checkIfCategoryByCategoryNo(categoryNo));

        return converter.convert(category);
    }

    public List<CategoryDto> getAllCategories() {
        var categories = categoryRepository.findAll();

        BusinessRules.run(() -> rules.checkIfCategoryListIsEmpty(categories));

        return converter.convert(categories);
    }

    protected Category getCategory(final String id) {
        return BusinessRules.run(() -> rules.checkIfCategoryById(id));
    }
}
