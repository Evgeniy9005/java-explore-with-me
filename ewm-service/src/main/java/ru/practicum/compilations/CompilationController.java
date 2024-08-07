package ru.practicum.compilations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.dto.CompilationDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationController {

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam (required = false) Boolean pinned, //искать только закрепленные/не закрепленные подборки
                                                @RequestParam (defaultValue = "0") int from,
                                                @RequestParam (defaultValue = "10") int size,
                                                HttpServletRequest request
    ) {
        return compilationService.getCompilations(pinned,from,size,request);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilation(@PathVariable @Positive int compId,
                                         HttpServletRequest request) {
        return compilationService.getCompilation(compId,request);
    }
}
