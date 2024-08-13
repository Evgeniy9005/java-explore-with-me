package ru.practicum.category;

import ru.practicum.category.dto.CategoryDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategories(int from, int size, HttpServletRequest request);

    CategoryDto getCategory(Integer catId, HttpServletRequest request);
}
