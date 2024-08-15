package com.v6.yeogaekgi.Member.repository;

import com.v6.yeogaekgi.Member.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> findByCode(String countryCode);
}
