package com.github.leo7machado.citiesapi.cities;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cities")
public class CityResource {

    private final CityRepository repository;

    public CityResource(final CityRepository repository) {
        this.repository = repository;
    }


    //  Pageable
    @GetMapping
    public Page<City> cities(final Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/search")
    public ResponseEntity byName(@RequestParam(name = "name") String search) {
        List<City> pesquisa = repository.findAll();
        List<City> lista = pesquisa.stream().filter(city -> city.getName().toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT))).collect(Collectors.toList());

            return ResponseEntity.ok().body(lista);

    }


    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<City> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}