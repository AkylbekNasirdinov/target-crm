package kg.manas.crm.controller;

import kg.manas.crm.models.ServiceCategoryModel;
import kg.manas.crm.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<ServiceCategoryModel> fetchCategories() {
        return categoryService.fetchAllCategories();
    }
}
