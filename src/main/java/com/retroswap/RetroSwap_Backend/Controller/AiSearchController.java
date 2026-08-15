package com.retroswap.RetroSwap_Backend.Controller;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Model.ProductSearchRequest;
import com.retroswap.RetroSwap_Backend.Service.AiSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
@CrossOrigin()
@RequestMapping("/retroswap")
@RestController
public class AiSearchController {
    @Autowired
    AiSearchService aiSearchService;
    @PostMapping("/ai-test")
    public List<Product>  aiSearch(@RequestBody String query)
    {
        return aiSearchService.understandQuery(query);



    }

}
