package ru.practicum.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import ru.practicum.category.dao.CategoryRepository;
import ru.practicum.data.Controller;
import ru.practicum.stats.Stats;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest extends Controller {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository,categoryMapper);
        initCategory(2);
    }

    @Test
    void getCategories() {
        try (MockedStatic<Stats> theMock = Mockito.mockStatic(Stats.class)) {
            theMock.when(Stats::getStatsClient).thenReturn(statsClient);
            categoryService.getCategories(0,10,request);
            verify(categoryRepository).findAll(any(Pageable.class));
            verify(statsClient).get(any(),any());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void getCategory() {
        try (MockedStatic<Stats> theMock = Mockito.mockStatic(Stats.class)) {
            theMock.when(Stats::getStatsClient).thenReturn(statsClient);
            categoryService.getCategory(1,request);
            verify(categoryRepository).findById(1);
            verify(statsClient).get(any(),any());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}