package nandodev.fibonacci.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nandodev.fibonacci.model.Fibonacci;
import nandodev.fibonacci.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/fibonacci")
@Api(value = "API REST Fibonacci")
@CrossOrigin(origins = "*")
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping("")
    @ApiOperation(value = "Retorna a lista de números de fibonacci")
    public List<Fibonacci> index(){
        return fibonacciService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um número de fibonacci dado sua posição")
    public Optional<Fibonacci> indexById(@PathVariable(value = "id") Long id){
        return fibonacciService.findById(id);
    }

    @PostMapping("")
    @ApiOperation(value = "Adiciona um novo número de fibonacci")
    public List<Fibonacci> create(){
        return fibonacciService.create();
    }

    @DeleteMapping("")
    @ApiOperation(value = "Deleta o último número de fibonacci adicionado")
    public List<Fibonacci> delete(){
        return fibonacciService.delete();
    }

}
