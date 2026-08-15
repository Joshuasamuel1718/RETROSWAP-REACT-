package com.retroswap.RetroSwap_Backend.Service;

import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Model.ProductSearchRequest;
import com.retroswap.RetroSwap_Backend.Repository.Product_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AiSearchService {

    private final String apiKey;
    private final HttpClient httpClient;

    public AiSearchService(@Value("${groq.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    public List<Product> understandQuery(String query) {

        String prompt = """
                Convert the user's product search into JSON.

                User query:
                %s

                Return ONLY valid JSON with these fields:
                {
                  "keyword": "string or null",
                  "category": number or null,
                  "color": "string or null",
                  "maxPrice": number or null,
                  "minPrice": number or null
                }

                Category IDs:
                1 = Clothing
                2 = Laptops
                3 = Games
                4 = Books
                5 = Watches

                Do not add markdown.
                Do not add explanations.
                If not matches make fields null
                """.formatted(query);

        String jsonBody = """
                {
                  "model": "llama-3.3-70b-versatile",
                  "messages": [
                    {
                      "role": "user",
                      "content": %s
                    }
                  ],
                  "temperature": 0
                }
                """.formatted(toJsonString(prompt));

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://api.groq.com/openai/v1/chat/completions"
                    ))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );
            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response.body());

            String text = root
                    .get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText();



            return result(text);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Autowired
    Product_repo productRepo;
    public List<Product> result(String json)
    {try {
        ObjectMapper mapper = new ObjectMapper();
        ProductSearchRequest filter = mapper.readValue(json, ProductSearchRequest.class);
        Specification<Product> spec = (root, query, cb) -> null;
        boolean noFilters =
                filter.getKeyword() == null &&
                        filter.getCategory() == null &&
                        filter.getColor() == null &&
                        filter.getMinPrice() == null &&
                        filter.getMaxPrice() == null;

        if (noFilters) {
            return List.of();
        }
        if (filter.getKeyword() != null) {
            String keyword = "%" + filter.getKeyword().toLowerCase() + "%";

            spec = spec.and((root, query, cb) ->
                    cb.or(
                            cb.like(cb.lower(root.get("name")), keyword),
                            cb.like(cb.lower(root.get("description")), keyword),
                            cb.like(cb.lower(root.get("details")), keyword)
                    )
            );
        }
        if (filter.getCategory() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category"), filter.getCategory())
            );
        }

        if (filter.getColor() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(
                            cb.lower(root.get("color")),
                            filter.getColor().toLowerCase()
                    )
            );
        }

        if (filter.getMinPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(
                            root.get("price"),
                            filter.getMinPrice()
                    )
            );
        }

        if (filter.getMaxPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(
                            root.get("price"),
                            filter.getMaxPrice()
                    )
            );
        }


        return productRepo.findAll(spec);
    }
    catch (Exception e) {
        e.printStackTrace();
        return List.of();
    }
    }

    private String toJsonString(String text) {
        return "\"" + text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r") + "\"";
    }
}