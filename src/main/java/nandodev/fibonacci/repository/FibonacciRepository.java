package nandodev.fibonacci.repository;

import nandodev.fibonacci.model.Fibonacci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FibonacciRepository extends JpaRepository<Fibonacci, Long> {
    Fibonacci findById(long id);
}
