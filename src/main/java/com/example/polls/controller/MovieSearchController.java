package com.example.polls.controller;

import com.example.polls.model.Answers;
import com.example.polls.model.Movie;
import com.example.polls.service.MovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
public class MovieSearchController {
    @Autowired
    MovieSearchService movieSearchService;

    @PostMapping("/search-movie")
    public ResponseEntity<?> searchMovie(@RequestBody Answers keywords) {
        try {
            System.out.println(keywords);
            Movie result = movieSearchService.advancedSearchByKeywords();
            return ResponseEntity.ok(result);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
