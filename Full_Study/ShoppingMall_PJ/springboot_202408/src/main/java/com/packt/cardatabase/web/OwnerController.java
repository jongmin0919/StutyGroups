package com.packt.cardatabase.web;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerController {
    @Autowired
    private OwnerRepository repository;

    @GetMapping(value="/owners")
    public Iterable<Owner> getOwners(){
        return repository.findAll();
    }

    @PostMapping(value="/owner/search")
    public ResponseEntity<?>  searchOwner(@RequestBody HashMap<String, String> searchParams){
        String firstname = searchParams.get("firstname");
        String lastname = searchParams.get("lastname");

        Optional<Owner> result;

        if (firstname != null && lastname != null) {
            // 둘 다 있는 경우 AND 조건으로 검색
            result = repository.findByFirstnameAndLastname(firstname, lastname);
        } else if (firstname != null) {
            // firstname만 있는 경우
            result = repository.findByFirstname(firstname);
        } else if (lastname != null) {
            // lastname만 있는 경우
            result = repository.findByLastname(lastname);
        } else {
            // 조건이 없는 경우, 모든 결과 반환
            return ResponseEntity.ok(repository.findAll());
        }

        // 데이터가 있는 경우
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            // 데이터가 없는 경우 404 반환
            return ResponseEntity.notFound().build();
        }
    }
}
