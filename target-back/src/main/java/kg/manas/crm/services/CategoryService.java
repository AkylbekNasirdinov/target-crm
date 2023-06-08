package kg.manas.crm.services;

import kg.manas.crm.models.ServiceCategoryModel;

import java.util.List;

public interface CategoryService {
    List<ServiceCategoryModel> fetchAllCategories();
}
