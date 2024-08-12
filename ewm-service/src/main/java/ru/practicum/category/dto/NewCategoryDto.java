package ru.practicum.category.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Data
@RequiredArgsConstructor
public class NewCategoryDto {

    @NotBlank
    @Size(min = 1,max = 50)
    private final String name;

    private final int id;

}
