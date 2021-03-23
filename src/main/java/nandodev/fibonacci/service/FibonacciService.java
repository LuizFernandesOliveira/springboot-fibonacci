package nandodev.fibonacci.service;

import nandodev.fibonacci.model.Fibonacci;

import java.util.List;
import java.util.Optional;

public interface FibonacciService {

    List<Fibonacci> findAll();

    Optional<Fibonacci> findById(Long id);

    List<Fibonacci> create();

    Fibonacci delete();

}
