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
    public void create(){
        List<Fibonacci> fibonacciList = fibonacciRepository.findAll();

        Fibonacci fibonacciInitial = new Fibonacci();
        fibonacciInitial.setId(1L);
        fibonacciInitial.setNumber(0L);

        Fibonacci fibonacciFinal = new Fibonacci();
        fibonacciFinal.setId(2L);
        fibonacciFinal.setNumber(1L);

        if(fibonacciList.size() == 0){
            fibonacciRepository.save(fibonacciInitial);
            fibonacciRepository.save(fibonacciFinal);

        }else if(fibonacciList.size() == 1){
            fibonacciRepository.save(fibonacciFinal);
        }else{
            fibonacciInitial.setId(fibonacciList.get(fibonacciList.size() - 2).getId());
            fibonacciInitial.setNumber(fibonacciList.get(fibonacciList.size() - 2).getNumber());
            fibonacciFinal.setId(fibonacciList.get(fibonacciList.size() - 1).getId());
            fibonacciFinal.setNumber(fibonacciList.get(fibonacciList.size() - 1).getNumber());

            Fibonacci fibonacciNew = new Fibonacci();
            fibonacciNew.setId(fibonacciFinal.getId() + 1L);
            fibonacciNew.setNumber(fibonacciInitial.getNumber() + fibonacciFinal.getNumber());
            fibonacciRepository.save(fibonacciNew);
        }
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
