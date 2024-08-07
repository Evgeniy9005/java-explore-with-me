package ru.practicum.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.stats.Stats;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    /*@Mock
    private StatsClient statsClient;*/

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mvc;

    @Test
    void addNewCategory() {
        try (MockedStatic<Stats> theMock = Mockito.mockStatic(Stats.class)) {
          //  theMock.when(Stats::getStatsClient).thenReturn(statsClient);

           /* when(statsClient.put(any())).thenReturn("Ответ");
            mvc.perform(get("/admin/categories")
                            .with(request -> {
                                request.setRemoteAddr("192.168.0.1");
                                return request;
                            })
                            .content(objectMapper.writeValueAsString(new NewCategoryDto("name",1)))
                            .characterEncoding(StandardCharsets.UTF_8)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
            verify(statsClient).get(any(),any());*/
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}