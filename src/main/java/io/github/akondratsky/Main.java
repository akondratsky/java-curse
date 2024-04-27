package io.github.akondratsky;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.akondratsky.repository.CurrencyResourceRepository;

public class Main {
    public static void main(String[] args) {
        CurrencyResourceRepository repo = new CurrencyResourceRepository(new ObjectMapper());
        System.out.println(repo.load(2));
    }
}