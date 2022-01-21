package com.example.demo.price;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

    private final String url = "https://cex.io/api/last_price/";

    private final ObjectMapper objectMapper;

    public PriceService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Price getPrice(String ccyPair) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url + ccyPair, String.class);
        logger.info(result);

        return objectMapper.readValue(result, Price.class);
    }
}
