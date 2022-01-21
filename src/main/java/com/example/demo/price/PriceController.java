package com.example.demo.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("cryptocurrencies")
public class PriceController {

    private final PriceRepository priceRepository;
    private final PriceService priceService;
    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    private static final Collection<String> ALLOWED_CURRENCIES = Arrays.asList("BTC", "ETH", "XRP");

    public PriceController(PriceRepository priceRepository, PriceService priceService) {
        this.priceRepository = priceRepository;
        this.priceService = priceService;
    }

    @PostConstruct
    public void postConstruct() {
        for (String ccy : ALLOWED_CURRENCIES) {
            Price price = null;
            try {
                 price = priceService.getPrice(ccy + "/USD");
            } catch (IOException e) {
                logger.error("Problem with obtaining information");
            }
            priceRepository.save(price);
        }
    }

    @GetMapping(value = "minprice")
    public ResponseEntity<?> minprice(@RequestParam String name) {
        if (!ALLOWED_CURRENCIES.contains(name)) {
            return ResponseEntity.badRequest().body("Currency not supported");
        }
        return ResponseEntity.ok(priceRepository.findFirstByCurr1OrderByLpriceAsc(name));
    }

    @GetMapping(value = "maxprice")
    public ResponseEntity<?> maxprice(@RequestParam String name) {
        if (!ALLOWED_CURRENCIES.contains(name)) {
            return ResponseEntity.badRequest().body("Currency not supported");
        }
        return ResponseEntity.ok(priceRepository.findFirstByCurr1OrderByLpriceDesc(name));
    }

    @GetMapping
    public ResponseEntity<?> cryptocurrencies(@RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        if (!ALLOWED_CURRENCIES.contains(name)) {
            return ResponseEntity.badRequest().body("Currency not supported");
        }
        return ResponseEntity.ok(priceRepository.findAllByCurr1(name, Pageable.ofSize(size).withPage(page)));
    }
}
