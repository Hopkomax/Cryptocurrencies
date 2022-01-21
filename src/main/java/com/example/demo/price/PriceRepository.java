package com.example.demo.price;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findFirstByCurr1OrderByLpriceAsc(String curr1);
    Price findFirstByCurr1OrderByLpriceDesc(String curr1);
    Page<Price> findAllByCurr1(String curr1, Pageable pageable);
}
