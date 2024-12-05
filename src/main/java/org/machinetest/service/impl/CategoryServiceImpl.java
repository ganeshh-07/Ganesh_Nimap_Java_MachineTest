package org.machinetest.service.impl;



import org.machinetest.entity.Category;
import org.machinetest.repository.CategoryRepository;
import org.machinetest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAllCategories(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    public Category getCategoryById(Long id) {
        return (Category)this.categoryRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Category not found with ID: " + id);
        });
    }

    public Category createCategory(Category category) {
        return (Category)this.categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category existingCategory = this.getCategoryById(id);
        existingCategory.setName(category.getName());
        return (Category)this.categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
