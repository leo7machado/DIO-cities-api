package com.github.leo7machado.citiesapi.countries.repository;

import com.github.leo7machado.citiesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
