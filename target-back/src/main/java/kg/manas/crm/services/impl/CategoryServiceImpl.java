package kg.manas.crm.services.impl;

import kg.manas.crm.converters.ServiceCategoryConverter;
import kg.manas.crm.models.ServiceCategoryModel;
import kg.manas.crm.repository.ServiceCategoryRepository;
import kg.manas.crm.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ServiceCategoryConverter categoryConverter;
    private final ServiceCategoryRepository categoryRepository;
    @Override
    public List<ServiceCategoryModel> fetchAllCategories() {
        return categoryRepository.findAll().stream().map(categoryConverter::convetToModel).collect(Collectors.toList());
    }
}
