package nandodev.fibonacci.service;

import nandodev.fibonacci.model.Fibonacci;
import nandodev.fibonacci.repository.FibonacciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class FibonacciServiceImpl implements FibonacciService {

    @Autowired
    private FibonacciRepository fibonacciRepository;

    @Override
    public List<Fibonacci> findAll() {
        return fibonacciRepository.findAll();
    }

    @Override
    public Optional<Fibonacci> findById(Long id) {
        return fibonacciRepository.findById(id);
    }

    @Override
    public List<Fibonacci> create(){
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

        return fibonacciRepository.findAll();
    }

    @Override
    public List<Fibonacci> delete(){
        Fibonacci lastFibonacci = fibonacciRepository
                .findAll()
                .stream()
                .max(Comparator.comparing(Fibonacci::getId))
                .get();
        if (lastFibonacci.getId() != null) {
            fibonacciRepository.deleteById(lastFibonacci.getId());
            return fibonacciRepository.findAll();
        }

        return null;
    }
}
