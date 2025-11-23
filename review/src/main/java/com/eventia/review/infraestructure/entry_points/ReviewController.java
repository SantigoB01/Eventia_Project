package com.eventia.review.infraestructure.entry_points;

import com.eventia.review.domain.model.Review;
import com.eventia.review.domain.usecase.ReviewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewUseCase useCase;

    @PostMapping
    public Review create(@RequestBody Review review) {
        return useCase.create(review);
    }

    @GetMapping("/artist/{artistId}")
    public List<Review> listByArtist(@PathVariable Long artistId) {
        return useCase.listByArtist(artistId);
    }


}
