package com.example.w6d4t.service;

import com.example.w6d4t.exception.NotFoundException;

import com.example.w6d4t.model.Autore;
import com.example.w6d4t.model.AutoreRequest;
import com.example.w6d4t.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;




    public Page<Autore> cercaTuttiGliAutori(Pageable pageable){
        return autoreRepository.findAll(pageable);
    }

    public Autore cercaAutorePerId(int id){
        return autoreRepository.findById(id).
                orElseThrow(()->new NotFoundException("Autore con id="+ id + " non trovato"));
    }

    public Autore salvaAutore(AutoreRequest autoreRequest)throws NotFoundException{
        Autore autore = new Autore(autoreRequest.getNome(), autoreRequest.getCognome(), autoreRequest.getEmail(), autoreRequest.getDataNascita());
        return autoreRepository.save(autore);
    }

    public Autore aggiornaAutore(int id, AutoreRequest autoreRequest) throws NotFoundException{
        Autore a = cercaAutorePerId(id);

        a.setNome(autoreRequest.getNome());
        a.setCognome(autoreRequest.getCognome());
        a.setEmail(autoreRequest.getEmail());
        a.setDataNascita(autoreRequest.getDataNascita());

        return autoreRepository.save(a);
    }

    public void cancellaAutore(int id) throws NotFoundException{
        Autore a = cercaAutorePerId(id);

        autoreRepository.delete(a);
    }
}
