package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.TravelScheduleDTO;
import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {
    final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping("/api/travel/search")
    public List<TravelSchedule> getPostByWord(@RequestParam String word){

        return searchService.getPostBasedOnWord(word)
                .stream().collect(Collectors.toList());
    }
}
