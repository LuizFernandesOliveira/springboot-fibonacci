package nandodev.fibonacci.repository;

import nandodev.fibonacci.model.Fibonacci;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FibonacciRepository extends CrudRepository<Fibonacci, Long> {

    List<Fibonacci> findAll();

}
