package com.main.MoviesOnDemand.Services;

import com.main.MoviesOnDemand.Dao.ProducerRepository;
import com.main.MoviesOnDemand.Entity.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {
    @Autowired
    private ProducerRepository producerRepository;

    public List<Producer> getAll(){
        return (List<Producer>) producerRepository.findAll();
    }

    public Producer getBookById(int id){
        // return books.stream().filter(book -> book.getId() == id).findFirst().get();
        return producerRepository.findById(id).get();
    }

    public Producer addBook(Producer book){
        // books.add(book);
        return producerRepository.save(book);
    }

    public void deleteBook(int id){
        // return books.removeIf(book -> book.getId() == id);
        producerRepository.deleteById(id);
    }

    public Producer updateBook(Producer book) {
        return producerRepository.save(book);
    }
}
