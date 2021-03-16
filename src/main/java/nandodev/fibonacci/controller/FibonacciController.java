package nandodev.fibonacci.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nandodev.fibonacci.model.Fibonacci;
import nandodev.fibonacci.repository.FibonacciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/fibonacci")
@Api(value = "API REST Fibonacci")
@CrossOrigin(origins = "*")
public class FibonacciController {

    @Autowired
    FibonacciRepository fibonacciRepository;

    @GetMapping("")
    @ApiOperation(value = "Retorna a lista de números de fibonacci")
    public List<Fibonacci> index(){
        return fibonacciRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um número de fibonacci dado sua posição")
    public Fibonacci indexById(@PathVariable(value = "id") long id){
        return fibonacciRepository.findById(id);
    }

    @PostMapping("")
    @ApiOperation(value = "Adiciona um novo número de fibonacci")
    public Fibonacci create(@RequestBody Fibonacci number){
        return fibonacciRepository.save(number);
    }

    @DeleteMapping("")
    @ApiOperation(value = "Deleta o último número de fibonacci adicionado")
    public void delete(){
        Fibonacci lastFibonacci = fibonacciRepository
                .findAll()
                .stream()
                .max(Comparator.comparing(Fibonacci::getId))
                .get();
        if (lastFibonacci.getId() != null) {
            fibonacciRepository.deleteById(lastFibonacci.getId());
        }
    }

}
